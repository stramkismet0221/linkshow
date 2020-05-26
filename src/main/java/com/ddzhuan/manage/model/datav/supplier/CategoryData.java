package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;
import java.util.List;

/**
 * 采购品类数据分析
 *
 * @author likeke
 * @date 2019/09/02
 */
public class CategoryData implements Serializable {

    private static final long serialVersionUID = -8896307583685630798L;
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
     * 采购增长最快品类
     */
    private String maxGroupCategory;
    /**
     * 采购增长最快值
     */
    private Double maxGroupValue;

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

    public String getMaxGroupCategory() {
        return maxGroupCategory;
    }

    public void setMaxGroupCategory(String maxGroupCategory) {
        this.maxGroupCategory = maxGroupCategory;
    }

    public Double getMaxGroupValue() {
        return maxGroupValue;
    }

    public void setMaxGroupValue(Double maxGroupValue) {
        this.maxGroupValue = maxGroupValue;
    }
}
