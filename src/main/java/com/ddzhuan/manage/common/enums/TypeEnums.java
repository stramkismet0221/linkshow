package com.ddzhuan.manage.common.enums;

/**
 * @author jiang yong tao
 * @date 2019/8/15 10:16
 */
public class TypeEnums {



    public enum MachineType{

        ALL("1","全部设备"),
        PART("2","部分设备"),
        ;
        public String code;

        private String desc;

        MachineType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

}
