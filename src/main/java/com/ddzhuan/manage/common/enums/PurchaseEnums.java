package com.ddzhuan.manage.common.enums;

/**
 * @author jiang yong tao
 * @date 2020/2/5 12:18
 */
public enum PurchaseEnums {
    GH(1,"购货订单"),
    GHDD(2,"购货入库"),
    GHT(3,"退货订单"),

    DEAFSTATUS(0,"待审核"),
    CHECK(1,"审核"),
    UNCHECK(2,"反审核"),

    ;
    public Integer code;

    public String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    PurchaseEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
