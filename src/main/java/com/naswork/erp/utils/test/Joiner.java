package com.naswork.erp.utils.test;

import java.util.StringJoiner;

/**
 * @Program: Joiner
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-08 14:00:15
 **/

public class Joiner {

    public static void main(String[] args) {
        Long aa = System.currentTimeMillis();
        StringJoiner sj  = new StringJoiner("hello");
        sj.add("world");
        Long bb = System.currentTimeMillis();
        System.out.println(bb-aa);

        Long aaa = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer("hello");
        sb.append("world");
        Long bbb = System.currentTimeMillis();
        System.out.println(bbb-aaa);

    }


}
