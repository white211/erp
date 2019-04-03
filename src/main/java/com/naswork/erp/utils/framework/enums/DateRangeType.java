package com.naswork.erp.utils.framework.enums;

import com.naswork.erp.utils.framework.annotation.Desc;

@Desc("时间范围类型")
public enum DateRangeType {
    HOUR("时"),
    YESTERDAY("天"),
    WEEK("周"),
    MONTH("月"),
    QUATER("季"),
    YEAR("年");

    private String value;

    private DateRangeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
