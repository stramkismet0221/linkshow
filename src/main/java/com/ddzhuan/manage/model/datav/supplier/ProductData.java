package com.ddzhuan.manage.model.datav.supplier;

import java.io.Serializable;
import java.util.List;

/**
 * 采购产品数据分析
 *
 * @author likeke
 * @date 2019/09/02
 */
public class ProductData implements Serializable {

    private static final long serialVersionUID = 7241996918937576347L;

    /**
     * 标题
     */
    private String title;
    /**
     * 模块编号
     */
    private String no;
    /**
     * 产品采购排名列表
     */
    private List<ProductInfo> productInfos;

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

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }
}
