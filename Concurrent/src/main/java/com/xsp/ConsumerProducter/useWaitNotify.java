package com.xsp.ConsumerProducter;

import java.util.LinkedList;
import java.util.Queue;

public class useWaitNotify {


    private static Queue<Integer> queue = new LinkedList<>();
    private static Object lock = new Object();

    private static void Product() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                queue.add(1);
                Thread.sleep(100);
                if (queue.size() > 10) {
                    System.out.println("生产完毕");
                    lock.wait();
                } else {
                    lock.notify();
                }


            }
        }
    }

    private static void Consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (queue.isEmpty()) {
                    System.out.println("没货了！");
                    lock.wait();
                }
                Integer poll = queue.poll();
                if (poll!=null){
                    System.out.println("消费一个");
                    lock.notify();
                }

            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                Consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
