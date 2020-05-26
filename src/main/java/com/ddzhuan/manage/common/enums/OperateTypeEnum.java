package com.ddzhuan.manage.common.enums;

/**
 * @author jiang yong tao
 * @date 2019/7/3 9:11
 */
public enum OperateTypeEnum {

    DETAIL(0,"详情"),
    EDIT(1,"编辑"),
    DELETE(2,"删除"),
    LOCK(3,"锁定"),

    HISTORY(4,"历史记录"),

    ;

    public Integer code;

    public String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    OperateTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
