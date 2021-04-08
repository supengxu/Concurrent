package com.xsp.Lock.ReentrantLock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class WaitNotifyByReentrantLock {

    private static Queue<Integer> queue = new LinkedList<>();
    private static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    public static Condition condition1 = lock.newCondition();
    public static Condition condition2 = lock.newCondition();

    private static void Product() throws InterruptedException {
        lock.lock();
        while (true) {
            queue.add(1);
            Thread.sleep(100);
            if (queue.size() > 10) {
                System.out.println("生产完毕");
                condition.await();
                condition1.await();
                condition2.await();

            } else {
                condition.signal();
            }
        }

    }

    private static void Consumer() throws InterruptedException {
        lock.lock();

        while (true) {
            if (queue.isEmpty()) {
                System.out.println("没货了！");
                condition.await();
            }
            Integer poll = queue.poll();
            if (poll != null) {
                System.out.println("消费一个");
                condition.signal();
            }

        }
    }

    public static void main(String[] args) {


        Thread thread = new Thread(() -> {
            try {
                Product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(30,100,100L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(10));
        threadPoolExecutor.prestartAllCoreThreads();

        threadPoolExecutor.execute(thread);
        new Thread(() -> {
            try {
                Consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
