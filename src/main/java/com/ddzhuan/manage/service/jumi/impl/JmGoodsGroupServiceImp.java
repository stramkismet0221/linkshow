package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.dao.jumi.JmGoodsGroupDao;
import com.ddzhuan.manage.model.jumi.product.GoodsBrand;
import com.ddzhuan.manage.model.jumi.product.GoodsGroup;
import com.ddzhuan.manage.service.jumi.JmGoodsGroupService;
import com.ddzhuan.manage.tool.UploadFileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JmGoodsGroupServiceImp implements JmGoodsGroupService {

    @Autowired
    private JmGoodsGroupDao jmGoodsGroupDao;

    @Override
    public List<GoodsGroup> queryGoodsGroupBy(String name, Pagination pagination) {
        Integer count = jmGoodsGroupDao.countGroupListByName(name);
        pagination.setTotal(count);
        List<GoodsGroup> list = jmGoodsGroupDao.queryGroupListBy(name, pagination.getStart(), pagination.getEnd());
        return list;
    }

    @Override
    public BaseResultInfo updateIconImg(List<Map<String, Object>> fileList) {
        BaseResultInfo res = new BaseResultInfo();
        res.setMsg("");
        res.setSuccess(false);
        HashMap<String,Object> hm = new HashMap<>();
        res.setSuccess(false);

        try {
            UploadFileTool fileTool = UploadFileTool.getInstance();
            String path = "linkshow/GOODSBRAND/" + System.currentTimeMillis();
            String[] url = new String[fileList.size()];
            for (int i = 0; fileList != null && i < fileList.size(); i++) {
                InputStream is = (InputStream) fileList.get(i).get("stream");
                String fileName = (String) fileList.get(i).get("filename");
                url[i] = fileTool.upLoadWithUFilePut(is, path,fileName);
            }
            hm.put("url", url[0]);
            res.setExtInfo(hm);
            String fileurl = url[0];

            HashMap<String,Object> map = new HashMap<>();
            map.put("url", fileurl);
            res.setExtInfo(map);
            res.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public Boolean getGoodsGroupByName(String name, Long id) {
        List<GoodsGroup> list = jmGoodsGroupDao.queryGroupByName(name, id);
        if (list.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Integer addGoodsGroup(GoodsGroup goodsGroup) {
        Integer res = 0;
        Integer maxno = jmGoodsGroupDao.maxSno();
        if (goodsGroup.getSno() <= maxno) {
            jmGoodsGroupDao.snoAddOne(goodsGroup.getSno().intValue());
        } else {
            goodsGroup.setSno(Long.valueOf(maxno + 1));
        }
        res = jmGoodsGroupDao.insertGoodsGroup(goodsGroup);
        return res;
    }

    @Override
    public Integer deleGroupsById(Long id) {
        Integer res = 0;
        GoodsGroup goodsGroup = jmGoodsGroupDao.queryGroupById(id);
        res = jmGoodsGroupDao.deleGroupsById(id);
        jmGoodsGroupDao.reduceOne(goodsGroup.getSno().intValue());
        return res;
    }

    @Override
    public GoodsGroup getGroupsById(Long id) {
        return jmGoodsGroupDao.queryGroupById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer editGoodsBrand(GoodsGroup goodsGroup) {
        Integer res=0;
        Integer maxsno = jmGoodsGroupDao.maxSno();
        GoodsGroup goodsGroup1=jmGoodsGroupDao.queryGroupById(goodsGroup.getId());
        if(goodsGroup.getSno().intValue() != goodsGroup1.getSno().intValue()){
            jmGoodsGroupDao.editSno(goodsGroup1.getSno().intValue(),goodsGroup.getSno().intValue());
        }
        if(goodsGroup.getSno()<=maxsno){
            goodsGroup.setSno(goodsGroup.getSno());
        }else{
            goodsGroup.setSno(Long.valueOf(maxsno));
        }
        res = jmGoodsGroupDao.updateGoodsBrand(goodsGroup);
        return res;
    }

}
