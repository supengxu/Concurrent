package com.xsp.Lock.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantXexample {
    static ReentrantLock a = new ReentrantLock();

    public static void main(String[] args) {
        a.lock();
    }
}
