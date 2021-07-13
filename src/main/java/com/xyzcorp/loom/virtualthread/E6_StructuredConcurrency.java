package com.xyzcorp.loom.virtualthread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

public class E6_StructuredConcurrency {
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
        ThreadFactory tf = Thread.builder().virtual().factory();
        try (ExecutorService e = Executors.newThreadExecutor(tf)) {
            e.submit(E6_StructuredConcurrency::task1);
            e.submit(E6_StructuredConcurrency::task2);
        }//join
        long end = System.currentTimeMillis();
        System.out.format("This took %d milliseconds\n", end - start);
    }
}
