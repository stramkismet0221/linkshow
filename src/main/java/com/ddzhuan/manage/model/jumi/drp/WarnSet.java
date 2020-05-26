package com.ddzhuan.manage.model.jumi.drp;

import com.ddzhuan.manage.model.jumi.product.Goods;
import com.ddzhuan.manage.model.jumi.product.JmGoodsType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 预警设置
 *
 * @author likeke
 * @date 2020/01/10
 */
public class WarnSet implements Serializable {

    private static final long serialVersionUID = -1091613741068410203L;

    /**
     * id
     */
    private Long id;
    /**
     * 选择商品方式
     */
    private Integer selType;
    /**
     * 商品类型ids，多个用","分割
     */
    private String goodsTypeIds;
    /**
     * 商品ids，多个用","分割
     */
    private String goodsIds;
    /**
     * 临期预警天数
     */
    private Integer beforeWarnDay;
    /**
     * 预警类型 1：统一预警 2：分仓预警
     */
    private Integer warnType;
    /**
     * 库存下限预警数
     */
    private Integer minStock;
    /**
     * 库存上限预警数
     */
    private Integer maxStock;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 分仓设置相关参数
     */
    private String wareParams;
    /**
     * 分仓设置信息列表
     */
    private List<WarnSetRel> warnSetRelList;
    /**
     * 商品分类信息
     */
    private List<JmGoodsType> jmGoodsTypeList;
    /**
     * 商品信息列表
     */
    private List<Goods> goodsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSelType() {
        return selType;
    }

    public void setSelType(Integer selType) {
        this.selType = selType;
    }

    public String getGoodsTypeIds() {
        return goodsTypeIds;
    }

    public void setGoodsTypeIds(String goodsTypeIds) {
        this.goodsTypeIds = goodsTypeIds;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public Integer getBeforeWarnDay() {
        return beforeWarnDay;
    }

    public void setBeforeWarnDay(Integer beforeWarnDay) {
        this.beforeWarnDay = beforeWarnDay;
    }

    public Integer getWarnType() {
        return warnType;
    }

    public void setWarnType(Integer warnType) {
        this.warnType = warnType;
    }

    public Integer getMinStock() {
        return minStock;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    public Integer getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getWareParams() {
        return wareParams;
    }

    public void setWareParams(String wareParams) {
        this.wareParams = wareParams;
    }

    public List<WarnSetRel> getWarnSetRelList() {
        return warnSetRelList;
    }

    public void setWarnSetRelList(List<WarnSetRel> warnSetRelList) {
        this.warnSetRelList = warnSetRelList;
    }

    public List<JmGoodsType> getJmGoodsTypeList() {
        return jmGoodsTypeList;
    }

    public void setJmGoodsTypeList(List<JmGoodsType> jmGoodsTypeList) {
        this.jmGoodsTypeList = jmGoodsTypeList;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
