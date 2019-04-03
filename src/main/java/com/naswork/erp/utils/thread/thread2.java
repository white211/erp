package com.naswork.erp.utils.thread;

/**
 * @Program: thread2
 * @Description:
 * @Author: White
 * @DateTime: 2019-02-17 17:28:25
 **/

public class thread2 implements Runnable {

    public String ThreadName;
    public thread2(String threadName){
        ThreadName = threadName;
    }
    @Override
    public void run() {
        for(int i = 0;i<10;i++){
            System.out.println(ThreadName);
        }
    }

    public static void main(String[] args) {
            thread2 thread1 = new thread2("线程A:");
            thread2 thread2 = new thread2("线程B:");

            Thread th1 = new Thread(thread1);
            Thread th2 = new Thread(thread2);
            th1.start();
            th2.start();
    }
}
