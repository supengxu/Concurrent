package com.xsp.CAS;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicIntegerExample {
    static AtomicInteger a = new AtomicInteger();
    static AtomicStampedReference<Integer> b = new AtomicStampedReference<>(1,2);

    public static void main(String[] args) {
        int i = a.incrementAndGet();
//        b.compareAndSet()
    }
}
