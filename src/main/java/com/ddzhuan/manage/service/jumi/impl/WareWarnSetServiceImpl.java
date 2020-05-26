package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.dao.jumi.GoodsDao;
import com.ddzhuan.manage.dao.jumi.JmGoodsTypeDao;
import com.ddzhuan.manage.dao.jumi.WareWarnSetDao;
import com.ddzhuan.manage.model.jumi.drp.WarnSet;
import com.ddzhuan.manage.model.jumi.drp.WarnSetRel;
import com.ddzhuan.manage.model.jumi.product.Goods;
import com.ddzhuan.manage.model.jumi.product.JmGoodsType;
import com.ddzhuan.manage.service.jumi.WareWarnSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 仓库预警设置
 *
 * @author likeke
 * @date 2020/01/10
 */
@Service
public class WareWarnSetServiceImpl implements WareWarnSetService {

    @Autowired
    private WareWarnSetDao wareWarnSetDao;

    @Autowired
    private JmGoodsTypeDao jmGoodsTypeDao;

    @Autowired
    private GoodsDao goodsDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveWareWarnSet(WarnSet warnSet) {
        if (warnSet.getSelType() == 3) {
            String goodsIds = warnSet.getGoodsIds();
            if (goodsIds.endsWith(",")) {
                goodsIds = goodsIds.substring(0, goodsIds.length()-1);
                warnSet.setGoodsIds(goodsIds);
            }
        }
        if (warnSet.getId() == null) {
            wareWarnSetDao.insertWareWarnSet(warnSet);
            WarnSet set = wareWarnSetDao.queryWarnSetById(null);
            warnSet.setId(set.getId());
        } else {
            wareWarnSetDao.updateWareWarnSet(warnSet);
        }
        if (warnSet.getWarnType() == 1) {
            wareWarnSetDao.deleteWarnSetRelList();
        } else if (warnSet.getWarnType() == 2) {
            String wareParams = warnSet.getWareParams();
            saveWarnSetRel(wareParams, warnSet.getId());
        }
    }

    @Override
    public WarnSet getWareWarnSetById(Long id) {
        WarnSet warnSet = wareWarnSetDao.queryWarnSetById(id);
        if (warnSet == null) {
            return new WarnSet();
        }
        if (!StringUtils.isEmpty(warnSet.getGoodsTypeIds())) {
            fillGoodsTypeInfo(warnSet);
        }
        if (!StringUtils.isEmpty(warnSet.getGoodsIds())) {
            fillGoodsInfo(warnSet);
        }
        if (warnSet.getWarnType() == 2) {
            fillWareHouseSet(warnSet);
        }
        return warnSet;
    }

    @Override
    public List<WarnSetRel> getRelListByWarnSetId(Long warnSetId) {
        if (warnSetId == null) {
            return new ArrayList<>();
        }
        return wareWarnSetDao.queryRelListByWarnSetId(warnSetId);
    }

    private void saveWarnSetRel(String wareParams, Long id) {
        if (StringUtils.isEmpty(wareParams)) {
            return;
        }
        List<WarnSetRel> warnSetRelList = new ArrayList<>();
        WarnSetRel warnSetRel = null;
        String[] wareParamArr = wareParams.split(";");
        for (int i = 0; i < wareParamArr.length; i++) {
            String wareId = wareParamArr[i].split(",")[0].split("-")[0];
            String wareName = wareParamArr[i].split(",")[0].split("-")[1];
            String minStock = wareParamArr[i].split(",")[1].split("-")[0];
            String maxStock = wareParamArr[i].split(",")[1].split("-")[1];
            warnSetRel = new WarnSetRel();
            warnSetRel.setWarnSetId(id);
            warnSetRel.setWareId(Long.parseLong(wareId));
            warnSetRel.setWareName(wareName);
            warnSetRel.setMinStock(Integer.parseInt(minStock));
            warnSetRel.setMaxStock(Integer.parseInt(maxStock));
            warnSetRelList.add(warnSetRel);
        }

        List<WarnSetRel> warnSetRels = wareWarnSetDao.queryWarnSetRelList();
        List<WarnSetRel> hasList = new ArrayList<>();
        List<WarnSetRel> hasNotList = new ArrayList<>();
        if (CollectionUtils.isEmpty(warnSetRels)) {
            hasNotList.addAll(warnSetRelList);
        } else {
            Map<Long, WarnSetRel> warnSetRelMap = new HashMap<>();
            for (int i = 0; i < warnSetRels.size(); i++) {
                warnSetRelMap.put(warnSetRels.get(i).getWareId(), warnSetRels.get(i));
            }
            for (int i = 0; i < warnSetRelList.size(); i++) {
                Long wareId = warnSetRelList.get(i).getWareId();
                if (warnSetRelMap.get(wareId) != null) {
                    WarnSetRel wrel = warnSetRelList.get(i);
                    wrel.setId(warnSetRelMap.get(wareId).getId());
                    hasList.add(wrel);
                } else {
                    hasNotList.add(warnSetRelList.get(i));
                }
            }
        }
        // 批量保存
        if (!CollectionUtils.isEmpty(hasNotList)) {
            wareWarnSetDao.batchInsertRel(hasNotList);
        }
        // 批量修改
        if (!CollectionUtils.isEmpty(hasList)) {
            wareWarnSetDao.batchUpdateRel(hasList);
        }
    }

    /**
     * 填充分类信息
     */
    private void fillGoodsTypeInfo(WarnSet warnSet) {
        String goodsTypeIds = warnSet.getGoodsTypeIds();
        if (StringUtils.isEmpty(goodsTypeIds)) {
            return;
        }
        // goodsTypeIds 是用","分割开来的
        String[] goodsTypeIdArr = goodsTypeIds.split(",");
        List<String> goodsTypeIdList = Arrays.asList(goodsTypeIdArr);
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < goodsTypeIdList.size(); i++) {
            ids.add(Long.parseLong(goodsTypeIdList.get(i)));
        }
        List<JmGoodsType> goodsTypes = jmGoodsTypeDao.queryGoodsTypesByIds(ids);
        warnSet.setJmGoodsTypeList(goodsTypes);
    }

    /**
     * 填充商品信息
     */
    private void fillGoodsInfo(WarnSet warnSet) {
        String goodsIds = warnSet.getGoodsIds();
        String[] goodsIdsArr = goodsIds.split(",");
        List<Long> goodsIdList = new ArrayList<>();
        for (int i = 0; i < goodsIdsArr.length; i++) {
            goodsIdList.add(Long.parseLong(goodsIdsArr[i]));
        }
        List<Goods> goodsList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsIdList)) {
            goodsList = goodsDao.queryGoodsListByIds(goodsIdList);
        }
        warnSet.setGoodsList(goodsList);
    }

    /**
     * 填充分仓信息
     */
    private void fillWareHouseSet(WarnSet warnSet) {
        Long warnSetId = warnSet.getId();
        if (warnSetId == null) {
            return;
        }
        List<WarnSetRel> warnSetRels = wareWarnSetDao.queryRelListByWarnSetId(warnSetId);
        warnSet.setWarnSetRelList(warnSetRels);
    }

}
