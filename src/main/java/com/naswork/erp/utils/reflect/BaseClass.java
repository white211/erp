package com.naswork.erp.utils.reflect;

/**
 * @Program: BaseClass
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-08 14:20:52
 **/

public class BaseClass {

    public int baseInt;

    private static void method3(){
        System.out.println("method3");
    }

    private int method4(){
        System.out.println("method4");
        return 0;
    }

    public static int method5(){
        System.out.println("method5");
        return 0;
    }

    void method6(){
        System.out.println("method6");
    }

    public class BaseClassInnerClass{};

    public enum BaseClassMemberEnum{};


}



