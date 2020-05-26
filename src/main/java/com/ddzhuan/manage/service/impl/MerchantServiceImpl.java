package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.dao.MerchantDao;
import com.ddzhuan.manage.model.Merchant;
import com.ddzhuan.manage.service.MerchantService;
import com.ddzhuan.manage.tool.UploadFileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantDao merchantDao;

    @Override
    public List<Merchant> queryMerchantByMerchant(Merchant merchant, Pagination pagination) {
        Integer rowsCount = merchantDao.queryMerchantCountByMerchant(merchant);
        pagination.setTotal(rowsCount);
        List<Merchant> merchants = merchantDao.queryMerchantByMerchant(merchant,pagination.getStart(),pagination.getEnd());
        return merchants;
    }

    @Override
    public boolean updateMerchant(Merchant merchant) {
        return merchantDao.updateMerchant(merchant)==1;
    }



    @Override
    public Merchant getMerchantInfo(Merchant merchant) {
        Merchant merchant1 = merchantDao.getMerchantInfo(merchant);

        return merchant1;
    }

    @Override
    public boolean insertMerchant(Merchant merchant) {
        return merchantDao.insertMerchant(merchant)==1;
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
            String filePath = "linkshow/LICENSEIMG/" +System.currentTimeMillis();
            String[] fileUrl = new String[fileList.size()];
            for(int i = 0; fileList != null && i < fileList.size(); i++){
                InputStream flie = (InputStream)fileList.get(i).get("stream");
                String filename = (String)fileList.get(i).get("filename");
                fileUrl[i] = uploadFileTool.upLoadWithUFilePut(flie, filePath , filename);
            }
            map.put("url",fileUrl[0]);
            result.setExtInfo(map);
            //删除以前的头像
//            if(user.getHeadPicUrl() != null ){
//                uploadFileTool.deleteWithUFileDelete(user.getHeadPicUrl());
//            }
            String url=fileUrl[0];
            HashMap<String,Object> map1 = new HashMap<String,Object>();
            map1.put("url",url);
            result.setExtInfo(map1);
            result.setSuccess(true);
          //  res=userDao.updateHead(uid, url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean MerchantByCompany(String company,Long id) {
        Merchant merchant = merchantDao.getMerchantByCompany(company,id);
        if(merchant != null){
            return true;
        }
        return false;
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
}
