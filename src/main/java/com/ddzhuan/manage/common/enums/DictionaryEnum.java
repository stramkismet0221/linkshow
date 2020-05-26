package com.ddzhuan.manage.common.enums;

public enum DictionaryEnum {

    ICON_DICTIONARY_TYPE(1,"icon","icon"),//图标类型
    ELECTRONIC_CIGARETTE_DICTIONARY_TYPE(2,"electronic_cigaretie","electronic_cigaretie"), //电子烟售货机
    NUMDECIMALS_DICTIONARY_TYPE(3,"numdecimals","numdecimals"), //商品数量小数位
    PRICEDECIMALS_DICTIONARY_TYPE(4,"pricedecimals","pricedecimals"),//商品价格小数位
    BROADCAST_DICTIONARY_TYPE(5,"broadcast","broadcast"),//广告播控设置
    N_YunLogin1_DICTIONARY_TYPE(6,"云后台","N_YunLogin1"),//云后台
    N_XcxLogin1_DICTIONARY_TYPE(6,"小程序端","N_XcxLogin1"),//小程序端
    N_DevLogin1_DICTIONARY_TYPE(6,"设备终端","N_DevLogin1"),//设备终端
    ;


    public Integer type;
    public String name;
    public String code;

    DictionaryEnum(Integer type,String name,String code){
        this.type=type;
        this.name=name;
        this.code=code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
