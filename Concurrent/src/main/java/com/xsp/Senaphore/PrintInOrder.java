package com.xsp.Senaphore;

import java.util.concurrent.Semaphore;

public class PrintInOrder {
    static class PrintInOrderAl{
        Semaphore first = new Semaphore(0);
        Semaphore two = new Semaphore(0);

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            first.release(); // 因为设置的0，默认一开始就没有资源，需要先释放
        }

        public void second(Runnable printSecond) throws InterruptedException {
            first.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            two.release();
        }

        public void third(Runnable printThird) throws InterruptedException {

            two.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            two.release();
            first.release();

        }
    }


    public static void main(String[] args) {
        PrintInOrderAl ad = new PrintInOrderAl();

        Thread t1 = new Thread(() -> {
            try {
                ad.first(() -> System.out.println("One"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                ad.second(() -> System.out.println("Two"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                ad.third(() -> System.out.println("Three"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();
        t1.start();
        t3.start();
    }

}
