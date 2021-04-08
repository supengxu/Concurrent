package com.xsp.Condition;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交叉打印
 */
public class CrossPrint {
    ReentrantLock lock = new ReentrantLock();
    Condition firstCondition = lock.newCondition();
    Condition secondCondition = lock.newCondition();
    Condition thirdCondition = lock.newCondition();
    AtomicInteger status = new AtomicInteger(1);

    public CrossPrint() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        if (status.get() != 1) {
            firstCondition.await();
        }
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        status.incrementAndGet();
        secondCondition.signal();
        lock.unlock();

    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        if (status.get() != 2) {
            secondCondition.await();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        status.incrementAndGet();
        thirdCondition.signal();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        if (status.get() != 3) {
            thirdCondition.await();
        }

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        Math.min(1,2);
        status.incrementAndGet();
        lock.unlock();

    }
}
