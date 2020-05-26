package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.dao.DictionaryDao;
import com.ddzhuan.manage.dao.jumi.GoodsDao;
import com.ddzhuan.manage.dao.jumi.JmGoodsTypeDao;
import com.ddzhuan.manage.model.Dictionary;
import com.ddzhuan.manage.model.jumi.product.Goods;
import com.ddzhuan.manage.model.jumi.product.GoodsSelectFields;
import com.ddzhuan.manage.service.jumi.GoodsService;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ddzhuan.manage.common.enums.DictionaryEnum.PRICEDECIMALS_DICTIONARY_TYPE;
import static java.util.stream.Collectors.toList;

/**
 * 商品信息实现类
 *
 * @author likeke
 * @date 2020/01/09
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private JmGoodsTypeDao jmGoodsTypeDao;

    @Autowired
    private DictionaryDao dictionaryDao;

    @Override
    public List<Goods> getGoodsInfoList(GoodsSelectFields fields, Integer type, Pagination pagination) {

        //查询商品单价小数位位数
        Dictionary dictionary = new Dictionary();
        dictionary.setType(PRICEDECIMALS_DICTIONARY_TYPE.getType());
        dictionary.setCode("pricedecimals");
        String value = dictionaryDao.queryDictionaryValueByCodeAndType(dictionary);
        List<Goods> goods = goodsDao.queryGoodsInfoList(fields,type,pagination.getStart(),pagination.getEnd()).stream().peek(v->{
            v.setGoodsTypeName(jmGoodsTypeDao.queryJmGoodsTypeById(v.getGoodsTypeId()).getName());
            if (!StringUtils.isEmpty(v.getIcons()) && v.getIcons().contains(",")) {
                String[] split = v.getIcons().split(",");
                if (split.length > 1) {
                    v.setIcons(split[0]);
                }
            }
            //价格转字符
            priceToString(v,value);
        }).collect(toList());
        int count = goodsDao.countGoodsInfoList(fields,type);
        pagination.setTotal(count);
        return goods;
    }

    private String getDoubleValue(Double price, int parseInt) {
        DecimalFormat df = new DecimalFormat("#0.0000");
        String str = df.format(price);
        //小数位
        String ss1 = str.substring(str.indexOf(".")+1);
        //整数位
        String ss2 = str.substring(0,str.indexOf("."));
        StringBuilder no = new StringBuilder();
        if (ss1.length() > parseInt) {
            ss1 = ss1.substring(0,parseInt);
            if (!StringUtils.isEmpty(ss1)) {
                str = ss2 + "." + ss1;
            }else {
                str = ss2;
            }
        }
        if (ss1.length() <= parseInt) {
            for (int i = 0; i < parseInt-ss1.length(); i++) {
                no.append("0");
            }
            str += no.toString();
        }
        return str;
    }

    private void priceToString(Goods goods, String value){
        if (null != goods.getPrice()) {
            goods.setPriceStr(getDoubleValue(goods.getPrice(),Integer.parseInt(value)));
        }
        if (null != goods.getTerminalPrice()) {
            goods.setTerminalPriceStr(getDoubleValue(goods.getTerminalPrice(),Integer.parseInt(value)));
        }
        if (null != goods.getTradePrice()) {
            goods.setTradePriceStr(getDoubleValue(goods.getTradePrice(),Integer.parseInt(value)));
        }
        if (null != goods.getCostPrice()) {
            goods.setCostPriceStr(getDoubleValue(goods.getCostPrice(),Integer.parseInt(value)));
        }
        if (null != goods.getOnlinePrice()) {
            goods.setOnlinePriceStr(getDoubleValue(goods.getOnlinePrice(),Integer.parseInt(value)));
        }
    }

    @Override
    public Goods getGoodsInfoById(Long id) {
        if (null == id) {
            return null;
        }
        Goods goods = goodsDao.queryGoodsInfoById(id);
        if (!StringUtils.isEmpty(goods.getExtendFields())) {
            String[] arr = goods.getExtendFields().split(",");
            Map<String,Object> kvmap = new HashMap<>(16);
            List<String> checkAns = Lists.newArrayList();
            if (arr.length > 0) {
                for (int i=0;i<arr.length;i++){
                    String v = arr[i];
                    if (v.contains("┋")) {
                        String[] kv = v.split("┋");
                        checkAns.add(kv[1]);
                        continue;
                    }
                    String[] kv = v.split(":");
                    kvmap.put(kv[0],kv[1]);
                }
            }
            goods.setCheckBoxAns(checkAns);
            goods.setExtednsFieldsArr(kvmap);
        }
        //查询商品单价小数位位数
        Dictionary dictionary = new Dictionary();
        dictionary.setType(PRICEDECIMALS_DICTIONARY_TYPE.getType());
        dictionary.setCode("pricedecimals");
        String value = dictionaryDao.queryDictionaryValueByCodeAndType(dictionary);
        //价格转字符
        priceToString(goods,value);
        return goods;
    }

    @Override
    public BaseResultInfo saveGoodsInfo(Goods goods) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            if (null != goods.getMaxStock() || null != goods.getMinStock()) {
                goods.setWarnStatus(1);
            }else {
                goods.setWarnStatus(2);
            }
            goodsDao.insertGoodsInfo(goods);
            HashMap<String, Object> extInfo = new HashMap<>();
            extInfo.put("type", goods.getType());
            result.setSuccess(true);
            result.setMsg("保存商品成功");
            result.setExtInfo(extInfo);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("保存商品异常");
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    @Override
    public BaseResultInfo modifyGoodsInfo(Goods goods) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            if (null == goods) {
                return result;
            }
            if (null != goods.getMaxStock() || null != goods.getMinStock()) {
                goods.setWarnStatus(1);
            }else {
                goods.setWarnStatus(2);
            }
            goodsDao.updateGoodsInfo(goods);
            HashMap<String, Object> extInfo = new HashMap<>();
            extInfo.put("type", goods.getType());
            result.setSuccess(true);
            result.setMsg("保存商品成功");
            result.setExtInfo(extInfo);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("保存商品异常");
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    @Override
    public void removeGoodsInfoById(Long id) {
        if (null == id) {
            return;
        }
        goodsDao.deleteGoodsInfoById(id);
    }
}
