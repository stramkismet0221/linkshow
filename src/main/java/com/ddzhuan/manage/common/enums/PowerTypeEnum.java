package com.ddzhuan.manage.common.enums;

/**
 * 权限类型枚举
 *
 * @author likeke
 * @date 2019/07/06
 */
public enum PowerTypeEnum {

    /**
     * 系统登录权限
     */
    SYSTEMLOGIN(1, "系统登录权限"),
    /**
     * 菜单权限
     */
    MENU(2, "菜单权限"),
    /**
     * 操作权限
     */
    OPERATE(3, "操作权限"),
    /**
     * 系统管理员权限
     */
    SYSTEMMANAGE(4, "系统管理员权限");

    public int code;

    public String name;

    PowerTypeEnum(int code, String name) {
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
     * @return PowerTypeEnum.
     */
    public static PowerTypeEnum parse(int code) {
        PowerTypeEnum[] statuses = PowerTypeEnum.values();
        for (PowerTypeEnum status : statuses) {
            if (status.code != code) {
                continue;
            }
            return status;
        }
        return null;
    }

}
