package com.xsp.Semaphore;

import java.util.concurrent.Semaphore;

public class example {
    static Semaphore available = new Semaphore(0);
    public static void main(String[] args) throws InterruptedException {
        available.release();
        available.acquire();
    }
}
