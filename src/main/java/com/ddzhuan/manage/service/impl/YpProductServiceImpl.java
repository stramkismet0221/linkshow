package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.YpProductDao;
import com.ddzhuan.manage.model.YpProductCategory;
import com.ddzhuan.manage.model.YpProduct;
import com.ddzhuan.manage.service.YpProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 友朋商品接口实现类
 * @author likeke
 * @date 2019/07/15
 */
@Service
public class YpProductServiceImpl implements YpProductService {

    @Autowired
    private YpProductDao ypProductDao;

    @Override
    public List<YpProductCategory> getYpCategoryList(List<String> ids) {
        return ypProductDao.queryYpCategoryList(ids);
    }

    @Override
    public List<YpProduct> getYpProductListByConditions(YpProduct product, String keyword, Pagination pagination) {
        pagination.setTotal(ypProductDao.queryYpProductCount(product, keyword));
        List<YpProduct> products = ypProductDao.queryYpProductListByConditions(product, keyword, pagination.getPage(), pagination.getRows());
        fillCategory(products);
        return products;
    }

    @Override
    public void saveYpProduct(YpProduct product) {
        ypProductDao.insertYpProduct(product);
    }

    @Override
    public void batchSaveYpCategory(List<YpProductCategory> categories) {
        ypProductDao.batchInsertYpCategory(categories);
    }

    @Override
    public YpProduct getYpProductByBarCode(String barCode) {
        return ypProductDao.queryYpProductByBarCode(barCode);
    }

    @Override
    public void removeProductByBarCode(String barCode) {
        ypProductDao.deleteProductByBarCode(barCode);
    }

    /**
     * 填充商品分类名称
     * @param products 商品列表
     */
    private void fillCategory(List<YpProduct> products) {
        if (CollectionUtils.isEmpty(products)) {
            return;
        }
        // 取商品分类id后去重
        List<String> categoryIds = products.stream().map(YpProduct::getCategoryId).collect(Collectors.toList());
        categoryIds.stream().distinct().collect(Collectors.toList());
        // 根据id获取商品分类列表
        if (CollectionUtils.isEmpty(categoryIds)) {
            return;
        }
        List<YpProductCategory> categoryList = ypProductDao.queryYpCategoryList(categoryIds);
        Map<String, String> categoryMap = new HashMap<>();
        for (YpProductCategory category : categoryList) {
            categoryMap.put(category.getId(), category.getName());
        }
        if (CollectionUtils.isEmpty(categoryMap)) {
            return;
        }
        for (YpProduct product : products) {
            if (!StringUtils.isEmpty(product.getCategoryId())) {
                product.setCategoryName(categoryMap.get(product.getCategoryId()));
            }
        }
    }
}
