package com.xsp.Lock.ReentrantLock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantXexample {
    static ReentrantLock a = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        a.lock();
        Semaphore aa = new Semaphore(0);
        aa.acquire();
        aa.release();
        a.lockInterruptibly();
        a.unlock();

    }
}
