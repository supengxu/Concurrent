package com.xsp.Semaphore;

import java.util.concurrent.Semaphore;

public class example {
    static Semaphore available = new Semaphore(10,true);
    public static void main(String[] args) throws InterruptedException {
        available.acquire();
    }
}
