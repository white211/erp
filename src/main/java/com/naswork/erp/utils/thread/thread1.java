package com.naswork.erp.utils.thread;

/**
 * @Program: thread1
 * @Description:
 * @Author: White
 * @DateTime: 2019-02-17 17:25:01
 **/

public class thread1 extends Thread{

    @Override
    public void run() {
        for(int i = 0;i<10;i++){
            System.out.println("this is thread   " + this.getId());
        }
    }

    public static void main(String[] args) {
        thread1 th1 = new thread1();
        thread1 th2 = new thread1();
        th1.start();
        th2.start();
    }
}
