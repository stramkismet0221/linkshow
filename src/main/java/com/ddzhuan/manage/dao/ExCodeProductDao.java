package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.ExCodeProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/8/5 14:48
 */
public interface ExCodeProductDao {

    int batchInsertExCodeProduct(@Param("exCodeProducts") List<ExCodeProduct> exCodeProducts);

    int insertExCodeProduct(ExCodeProduct exCodeProduct);

    List<ExCodeProduct> queryProductAmountByExCode(Long exCodeId);

    int modifyExCodeProduct(@Param("amount") Integer amount,
                            @Param("exCodeId") Long exCodeId,
                            @Param("barCode") String barCode);


    int batchUpdateExCodeProduct(List<ExCodeProduct> exCodeProducts);


    int countExCodeProductByExCodeIdAndBarcode(@Param("exCodeId")Long exCodeId, @Param("barCode")String barCode);

    int modifyExCodeProduct(ExCodeProduct exCodeProduct);



}
