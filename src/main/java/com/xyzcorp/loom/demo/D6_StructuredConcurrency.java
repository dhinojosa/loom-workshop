package com.xyzcorp.loom.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class D6_StructuredConcurrency {
    public static void task1() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void task2() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
        throws InterruptedException {

        long start = System.currentTimeMillis();
        ThreadFactory tf = Thread.ofVirtual().name("immediate-start").factory();
        try (ExecutorService e = Executors.newThreadExecutor(tf)) {
            e.submit(D6_StructuredConcurrency::task1);
            e.submit(D6_StructuredConcurrency::task2);
        }//join

        long end = System.currentTimeMillis();
        System.out.format("This took %d milliseconds\n", end - start);
    }
}
