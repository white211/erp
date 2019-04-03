package com.naswork.erp.utils.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Program: thread3
 * @Description:
 * @Author: White
 * @DateTime: 2019-02-17 17:37:03
 **/

public class thread3 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for(;i<10;i++){
            System.out.println(Thread.currentThread().getName()+""+i);
        }
        return i;
    }

    public static void main(String[] args) {
        thread3 thread3 = new thread3();
        FutureTask<Integer> ft = new FutureTask<>(thread3);
        for(int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);
            if(i==2){
                new Thread(ft,"有返回值的线程").start();
            }
        }
        try{
            System.out.println("子线程的返回值："+ft.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }
}
