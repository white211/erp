package com.naswork.erp.utils.framework.enums;

import com.naswork.erp.utils.framework.annotation.Desc;

@Desc("系统环境")
public enum EnvType {

    DEV("开发环境"),
    SIT("集成测试环境"),
    UAT("仿真测试环境"),
    PROD("生产环境");


    private String value;

    private EnvType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
