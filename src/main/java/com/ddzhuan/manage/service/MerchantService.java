package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.Merchant;

import java.util.List;
import java.util.Map;

public interface MerchantService {

    List<Merchant> queryMerchantByMerchant(Merchant merchant, Pagination pagination);

    boolean updateMerchant(Merchant merchant);

    Merchant getMerchantInfo(Merchant merchant);

    boolean insertMerchant(Merchant merchant);

    BaseResultInfo updateLicenImg(List<Map<String, Object>> fileList);

    boolean MerchantByCompany(String company,Long id);

    BaseResultInfo deleteLiceImg(String url);

}
