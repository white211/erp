package com.naswork.erp.utils.thread;

/**
 * @Program: VolatileTest
 * @Description:
 * @Author: White
 * @DateTime: 2019-02-17 18:38:12
 **/

public class VolatileTest {

    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    for(int j=0;j<10;j++) {
                        test.increase();
                    }
                };
            }.start();
        }
        while(Thread.activeCount()>1) {  //保证前面的线程都执行完
            Thread.yield();
        }
        System.out.println(test.inc);
    }

}
