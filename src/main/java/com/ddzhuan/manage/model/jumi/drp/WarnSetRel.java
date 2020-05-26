package com.ddzhuan.manage.model.jumi.drp;

import java.io.Serializable;
import java.util.Date;

/**
 * 预警设置分仓设置
 *
 * @author likeke
 * @date 2020/01/10
 */
public class WarnSetRel implements Serializable {

    private static final long serialVersionUID = -2439367119003119853L;

    /**
     * id
     */
    private Long id;
    /**
     * 预警设置id
     */
    private Long warnSetId;
    /**
     * 仓库id
     */
    private Long wareId;
    /**
     * 仓库名称
     */
    private String wareName;
    /**
     * 库存下限预警数
     */
    private Integer minStock;
    /**
     * 库存上限预警数
     */
    private Integer maxStock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWarnSetId() {
        return warnSetId;
    }

    public void setWarnSetId(Long warnSetId) {
        this.warnSetId = warnSetId;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
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

}
