package com.xyzcorp.loom.demo;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class D7_StructuredConcurrencyNested {
    public static void task1() {
        ThreadFactory tf = Thread.builder().virtual().factory();
        try (ExecutorService e = Executors.newThreadExecutor(tf)) {
            Callable<PrintStream> printStreamCallable =
                () -> System.out.printf("Performed Subtask in Thread %s\n",
                    Thread.currentThread());
            e.submit(printStreamCallable);
            e.submit(printStreamCallable);
        }
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
        ThreadFactory tf = Thread.builder().virtual().factory();
        try (ExecutorService e = Executors.newThreadExecutor(tf)) {
            e.submit(D7_StructuredConcurrencyNested::task1);
            e.submit(D7_StructuredConcurrencyNested::task2);
        }//join
        long end = System.currentTimeMillis();
        System.out.format("This took %d seconds\n", end - start);
    }
}
