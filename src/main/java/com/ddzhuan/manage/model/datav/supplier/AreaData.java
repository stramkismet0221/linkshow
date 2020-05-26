package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;
import java.util.List;

/**
 * 采购城市数据分析
 *
 * @author likeke
 * @date 2019/09/02
 */
public class AreaData implements Serializable {

    private static final long serialVersionUID = 3153079875697078315L;
    /**
     * 标题
     */
    private String title;
    /**
     * 图形模块编号
     */
    private String no;
    /**
     * 省市采购数据
     */
    private List<AreaInfo> areaInfos;
    /**
     * 采购增长最快城市名称
     */
    private String maxGrowthArea;
    /**
     * 采购增长最快值
     */
    private Double maxGrowthValue;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public List<AreaInfo> getAreaInfos() {
        return areaInfos;
    }

    public void setAreaInfos(List<AreaInfo> areaInfos) {
        this.areaInfos = areaInfos;
    }

    public String getMaxGrowthArea() {
        return maxGrowthArea;
    }

    public void setMaxGrowthArea(String maxGrowthArea) {
        this.maxGrowthArea = maxGrowthArea;
    }

    public Double getMaxGrowthValue() {
        return maxGrowthValue;
    }

    public void setMaxGrowthValue(Double maxGrowthValue) {
        this.maxGrowthValue = maxGrowthValue;
    }
}
