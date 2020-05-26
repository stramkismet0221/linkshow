package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.dao.jumi.GoodsBrandDao;
import com.ddzhuan.manage.model.jumi.product.GoodsBrand;
import com.ddzhuan.manage.service.jumi.GoodsBrandService;
import com.ddzhuan.manage.tool.UploadFileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsBrandServiceImpl implements GoodsBrandService {

    @Autowired
    private GoodsBrandDao goodsBrandDao;
    @Override
    public List<GoodsBrand> queryGoodsBrandByName(String name, Pagination pagination) {
        Integer count = goodsBrandDao.countBrandListByName(name);
        pagination.setTotal(count);
        List<GoodsBrand> goodsBrands = goodsBrandDao.queryBrandListByName(name,pagination.getStart(),pagination.getEnd());
        return goodsBrands;
    }

    @Override
    @Transactional
    public Integer addGoodsBrand(GoodsBrand goodsBrand) {
        Integer res=0;
        Integer maxsno = goodsBrandDao.maxSno();
        if(goodsBrand.getSno()<=maxsno){
            goodsBrandDao.snoAddOne(goodsBrand.getSno().intValue());
        }else{
            goodsBrand.setSno(Long.valueOf(maxsno+1));
        }
        res = goodsBrandDao.insertGoodsBrand(goodsBrand);
        return res;
    }

    @Override
    @Transactional
    public Integer deleteGoodsBrandById(Long id) {
        Integer res = 0;
        GoodsBrand goodsBrand = goodsBrandDao.getGoodsBrandById(id);
        res = goodsBrandDao.deleteGoodsBrandById(id);
        goodsBrandDao.reduceOne(goodsBrand.getSno().intValue());
        return res;
    }

    @Override
    public boolean getGoodsBrandByName(String name, Long id) {
        List<GoodsBrand> goodsBrands = new ArrayList<GoodsBrand>();
        goodsBrands = goodsBrandDao.getGoodsBrandByName(name,id);
        if(goodsBrands.size()>0){
            return false;
        }
        return true;
    }


    @Override
    public BaseResultInfo updateLicenImg(List<Map<String, Object>> fileList) {
        BaseResultInfo result = new BaseResultInfo();
        result.setMsg("");
        result.setSuccess(false);
        HashMap<String,Object> map = new HashMap<String, Object>();
        result.setSuccess(false);
        try {
            UploadFileTool uploadFileTool=UploadFileTool.getInstance();
            String filePath = "linkshow/GOODSBRAND/" +System.currentTimeMillis();
            String[] fileUrl = new String[fileList.size()];
            for(int i = 0; fileList != null && i < fileList.size(); i++){
                InputStream flie = (InputStream)fileList.get(i).get("stream");
                String filename = (String)fileList.get(i).get("filename");
                fileUrl[i] = uploadFileTool.upLoadWithUFilePut(flie, filePath , filename);
            }
            map.put("url",fileUrl[0]);
            result.setExtInfo(map);
            String url=fileUrl[0];
            HashMap<String,Object> map1 = new HashMap<String,Object>();
            map1.put("url",url);
            result.setExtInfo(map1);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public BaseResultInfo deleteLiceImg(String url) {
        BaseResultInfo result = new BaseResultInfo();
        result.setMsg("");
        result.setSuccess(false);
        try {
            UploadFileTool uploadFileTool=UploadFileTool.getInstance();
            if(url != null ){
                boolean state=  uploadFileTool.deleteWithUFileDelete(url);
                result.setSuccess(state);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public GoodsBrand getGoodsBrandById(Long id) {
        return goodsBrandDao.getGoodsBrandById(id);
    }

    @Override
    @Transactional
    public Integer editGoodsBrandById(GoodsBrand goodsBrand) {
        Integer res=0;
        Integer maxsno = goodsBrandDao.maxSno();
        GoodsBrand goodsBrand1=goodsBrandDao.getGoodsBrandById(goodsBrand.getId());
        if(goodsBrand.getSno().intValue() != goodsBrand1.getSno().intValue()){
            goodsBrandDao.editSno(goodsBrand1.getSno().intValue(),goodsBrand.getSno().intValue());
        }
        if(goodsBrand.getSno()<=maxsno){
            goodsBrand.setSno(goodsBrand.getSno());
        }else{
            goodsBrand.setSno(Long.valueOf(maxsno));
        }
        res = goodsBrandDao.updateGoodsBrand(goodsBrand);
        return res;
    }
}
