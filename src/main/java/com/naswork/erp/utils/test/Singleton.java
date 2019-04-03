package com.naswork.erp.utils.test;

/**
 * @Program: Singleton
 * @Description:
 * @Author: White
 * @DateTime: 2019-02-12 20:31:22
 **/

public class Singleton {

    private Singleton(){}
    private static Singleton singleton = null;

    public static Singleton getInstance(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }


}
