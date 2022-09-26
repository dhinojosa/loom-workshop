package com.xyzcorp.loom.demo;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class D7_StructuredConcurrencyNested {
    static ThreadFactory tf = Thread.ofVirtual().name("structured-concurrency-nested").factory();

    public static void task1() {
        try (ExecutorService e = Executors.newThreadPerTaskExecutor(tf)) {
            Callable<PrintStream> printStreamCallable = () -> {
                Thread.sleep(4000);
                return System.out.printf("Performed Subtask in Thread %s\n",
                    Thread.currentThread());
            };

            e.submit(printStreamCallable);
            e.submit(printStreamCallable);
        }//join
    }

    public static void task2() {
        try {
            Thread.sleep(7000);
            System.out.printf("Performed Task 2 in %s\n",
                Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
        throws InterruptedException {

        long start = System.currentTimeMillis();
        try (ExecutorService e = Executors.newThreadPerTaskExecutor(tf)) {
            e.submit(D7_StructuredConcurrencyNested::task1);
            e.submit(D7_StructuredConcurrencyNested::task2);
        }//join
        long end = System.currentTimeMillis();
        System.out.format("This took %d seconds\n", end - start);
    }
}
