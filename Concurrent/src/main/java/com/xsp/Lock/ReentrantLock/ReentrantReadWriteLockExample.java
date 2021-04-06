package com.xsp.Lock.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {
    static ReentrantReadWriteLock a = new ReentrantReadWriteLock();
    public static void main(String[] args) {
        ReentrantReadWriteLock.WriteLock writeLock = a.writeLock();
        writeLock.lock();
    }
}
