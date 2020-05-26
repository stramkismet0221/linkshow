package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.Merchant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantDao {


   List<Merchant> queryMerchantByMerchant(@Param("merchant") Merchant merchant, @Param("start") Integer start, @Param("size") Integer size);

   Integer queryMerchantCountByMerchant(@Param("merchant") Merchant merchant);

   //修改商户
   int updateMerchant(Merchant merchant);

   Merchant getMerchantInfo(Merchant merchant);

   int insertMerchant(Merchant merchant);

   //校验
   Merchant getMerchantByCompany(@Param("company") String company,@Param("id") Long id);



}
