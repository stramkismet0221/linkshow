package com.ddzhuan.manage.common.enums;

/**
 * 通用状态枚举
 *
 * @author likeke
 * @date 2019/06/25
 */
public enum CommonStatusEnum {

    /**
     * 删除
     */
    DELETED(0, "删除"),
    /**
     * 正常
     */
    NORMAL(1, "正常");

    private int code;

    private String name;

    CommonStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 根据code获取状态。
     *
     * @param code 状态code.
     * @return CommonStatusEnum.
     */
    public static CommonStatusEnum parse(int code) {
        CommonStatusEnum[] statuses = CommonStatusEnum.values();
        for (CommonStatusEnum status : statuses) {
            if (status.code != code) {
                continue;
            }
            return status;
        }
        return null;
    }

}
