package com.naswork.erp.utils.framework.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @Program: RegularUtil
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 11:30:52
 **/

public class RegularUtil {

    private static final Pattern IS_POSITIVE_INTEGER = Pattern.compile("^[1-9]\\d*$");
    private static final Pattern IS_DATE = Pattern.compile("((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(10|12|0?[13578])([-\\/\\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(11|0?[469])([-\\/\\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(0?2)([-\\/\\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([3579][26]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$))");
    private static final Pattern IS_EMAIL = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private static final Pattern IS_MOBILE = Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$");
    private static final Pattern IS_IP = Pattern.compile("([1-9]|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])(\\\\.(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3}");

    public RegularUtil() {
    }

    public static boolean isPositiveInteger(String str) {
        return reg(str, IS_POSITIVE_INTEGER);
    }

    public static boolean isDate(String dateStr) {
        return reg(dateStr, IS_DATE);
    }

    public static boolean isEmail(String str) {
        return reg(str, IS_EMAIL);
    }

    public static boolean isMobile(String str) {
        return reg(str, IS_MOBILE);
    }

    public static boolean isIp(String str) {
        return reg(str, IS_IP);
    }

    public static boolean haveDoubleByte(String str) {
        char[] var1 = str.toCharArray();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            char c = var1[var3];
            if (isDoubleByte(c)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isDoubleByte(char c) {
        return c >= 19968 && c <= '龥';
    }

    private static boolean reg(String str, Pattern pattern) {
        return StringUtils.isBlank(str) ? false : pattern.matcher(str.trim()).matches();
    }

}
