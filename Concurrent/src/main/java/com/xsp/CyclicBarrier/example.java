package com.xsp.CyclicBarrier;

import java.util.concurrent.*;

public class example {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(10,()->{
            System.out.println("爷被唤醒了");
        });

        Thread thread = new Thread(() -> {
            try {
                System.out.println("我已经达到公司：名称=="+Thread.currentThread().getName());

                cb.await();
                System.out.println("开始笔试"+Thread.currentThread().getName());
                cb.await();
                System.out.println("开始面试"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,100,100L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(10));
        threadPoolExecutor.prestartAllCoreThreads();
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.execute(thread);

    }
}
