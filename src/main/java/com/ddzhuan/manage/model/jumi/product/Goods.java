package com.ddzhuan.manage.model.jumi.product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品基本信息
 *
 * @author likeke
 * @date 2020/01/09
 */
public class Goods implements Serializable {

    private static final long serialVersionUID = 7839109194625659584L;

    /**
     * id
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 条形码
     */
    private String barCode;
    /**
     * 商品自编码
     */
    private String code;

    /**
     * 商品自编码
     */
    private String icons;
    /**
     * 拼音简码
     */
    private String shortCode;
    /**
     * 商品类型id
     */
    private Long goodsTypeId;
    /**
     * 商品类型名称
     */
    private String goodsTypeName;
    /**
     * 商品售价
     */
    private Double price;
    /**
     * 网店价格
     */
    private Double onlinePrice;
    /**
     * 设备价格
     */
    private Double terminalPrice;
    /**
     * 成本价
     */
    private Double costPrice;
    /**
     * 批发价
     */
    private Double tradePrice;
    /**
     * 规格
     */
    private String specs;
    /**
     * 单位id
     */
    private Long unitId;
    /**
     * 单位名称
     */
    private String unitName;
    /**
     * 仓库id
     */
    private Long wareHouseId;
    /**
     * 促销语
     */
    private String promote;
    /**
     * 存储条件
     */
    private String storage;
    /**
     * 有效期（天）
     */
    private Integer validityDay;
    /**
     * 提前几天发预警
     */
    private Integer advanceDay;
    /**
     * 供货商id
     */
    private Long supplierId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 商品分组id
     */
    private Long groupId;
    /**
     * 生产厂家id
     */
    private Long manufacturerId;
    /**
     * 仓库预警开关
     */
    private Integer warnStatus;
    /**
     * 库存最小值
     */
    private Integer minStock;
    /**
     * 库存最大值
     */
    private Integer maxStock;
    /**
     * 销售状态 0：已下架  1：销售中
     */
    private Integer salesStatus;
    /**
     * 展示类型  0：不展示  1：实体  2：电商  1,2 ：电商/实体
     */
    private Integer showType;
    /**
     * 商品类型  1：实物商品  2：虚拟商品  3：卡包商品  4：原材料
     */
    private Integer type;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /** 自定义字段值 */
    private String extendFields;

    private Map<String,Object> extednsFieldsArr;

    /**  库存 */
    private Double stock;

    /**  设备毛利率  */
    private Double terminalGrossRate;

    /** 网店毛利率 */
    private Double onlineGrossRate;

    /**  销量 */
    private Double sales;

    private List<String> checkBoxAns;

    private String priceStr;

    private String onlinePriceStr;

    private String terminalPriceStr;

    private String costPriceStr;

    private String tradePriceStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Long getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Long goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOnlinePrice() {
        return onlinePrice;
    }

    public void setOnlinePrice(Double onlinePrice) {
        this.onlinePrice = onlinePrice;
    }

    public Double getTerminalPrice() {
        return terminalPrice;
    }

    public void setTerminalPrice(Double terminalPrice) {
        this.terminalPrice = terminalPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(Long wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    public String getPromote() {
        return promote;
    }

    public void setPromote(String promote) {
        this.promote = promote;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Integer getValidityDay() {
        return validityDay;
    }

    public void setValidityDay(Integer validityDay) {
        this.validityDay = validityDay;
    }

    public Integer getAdvanceDay() {
        return advanceDay;
    }

    public void setAdvanceDay(Integer advanceDay) {
        this.advanceDay = advanceDay;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Integer getWarnStatus() {
        return warnStatus;
    }

    public void setWarnStatus(Integer warnStatus) {
        this.warnStatus = warnStatus;
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

    public Integer getSalesStatus() {
        return salesStatus;
    }

    public void setSalesStatus(Integer salesStatus) {
        this.salesStatus = salesStatus;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public String getExtendFields() {
        return extendFields;
    }

    public void setExtendFields(String extendFields) {
        this.extendFields = extendFields;
    }

    public Map<String, Object> getExtednsFieldsArr() {
        return extednsFieldsArr;
    }

    public void setExtednsFieldsArr(Map<String, Object> extednsFieldsArr) {
        this.extednsFieldsArr = extednsFieldsArr;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getTerminalGrossRate() {
        return terminalGrossRate;
    }

    public void setTerminalGrossRate(Double terminalGrossRate) {
        this.terminalGrossRate = terminalGrossRate;
    }

    public Double getOnlineGrossRate() {
        return onlineGrossRate;
    }

    public void setOnlineGrossRate(Double onlineGrossRate) {
        this.onlineGrossRate = onlineGrossRate;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public List<String> getCheckBoxAns() {
        return checkBoxAns;
    }

    public void setCheckBoxAns(List<String> checkBoxAns) {
        this.checkBoxAns = checkBoxAns;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public String getOnlinePriceStr() {
        return onlinePriceStr;
    }

    public void setOnlinePriceStr(String onlinePriceStr) {
        this.onlinePriceStr = onlinePriceStr;
    }

    public String getTerminalPriceStr() {
        return terminalPriceStr;
    }

    public void setTerminalPriceStr(String terminalPriceStr) {
        this.terminalPriceStr = terminalPriceStr;
    }

    public String getCostPriceStr() {
        return costPriceStr;
    }

    public void setCostPriceStr(String costPriceStr) {
        this.costPriceStr = costPriceStr;
    }

    public String getTradePriceStr() {
        return tradePriceStr;
    }

    public void setTradePriceStr(String tradePriceStr) {
        this.tradePriceStr = tradePriceStr;
    }
}
