package com.naswork.erp.utils.framework.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: IntegerUtil
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 11:30:20
 **/

public class IntegerUtil {
    public IntegerUtil() {
    }

    public static List<Integer> str2IntegerList(String str) {
        String[] split = str.split(",");
        List<Integer> intArr = new ArrayList();
        String[] var3 = split;
        int var4 = split.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String id = var3[var5];
            if (RegularUtil.isPositiveInteger(id)) {
                intArr.add(Integer.parseInt(id));
            }
        }

        return intArr;
    }

    public static List<Long> str2LongList(String str) {
        String[] split = str.split(",");
        List<Long> intArr = new ArrayList();
        String[] var3 = split;
        int var4 = split.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String id = var3[var5];
            if (RegularUtil.isPositiveInteger(id)) {
                intArr.add(Long.parseLong(id));
            }
        }

        return intArr;
    }

}
