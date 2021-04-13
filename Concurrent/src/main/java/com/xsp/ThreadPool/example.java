package com.xsp.ThreadPool;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class example {
    public static void main(String[] args) {


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(30,100,100L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(10));
        threadPoolExecutor.prestartAllCoreThreads();
        threadPoolExecutor.execute(new Thread(()-> System.out.println("我来也")));
    }
}
