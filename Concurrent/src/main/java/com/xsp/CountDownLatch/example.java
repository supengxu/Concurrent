package com.xsp.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class example {

    static CountDownLatch downLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        downLatch.await();
        downLatch.countDown();
    }
}
