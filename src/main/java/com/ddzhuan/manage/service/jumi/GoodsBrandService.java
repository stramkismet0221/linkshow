package com.ddzhuan.manage.service.jumi;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.jumi.product.GoodsBrand;

import java.util.List;
import java.util.Map;

public interface GoodsBrandService {

    List<GoodsBrand> queryGoodsBrandByName(String name, Pagination pagination);

    Integer addGoodsBrand(GoodsBrand goodsBrand);

    Integer deleteGoodsBrandById(Long id);

    boolean getGoodsBrandByName(String name,Long id);

    BaseResultInfo updateLicenImg(List<Map<String, Object>> fileList);

    BaseResultInfo deleteLiceImg(String url);

    GoodsBrand getGoodsBrandById(Long id);

    Integer editGoodsBrandById(GoodsBrand goodsBrand);

}
