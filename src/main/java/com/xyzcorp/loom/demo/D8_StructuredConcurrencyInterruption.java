package com.xyzcorp.loom.demo;


import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class D8_StructuredConcurrencyInterruption {
    static ThreadFactory tf = Thread
        .ofVirtual()
        .name("structured-concurrency-interruption")
        .factory();


    public static String task1() throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> first = scope.fork(() -> {
                Thread.sleep(12000); //Reset to 12000
                System.out.printf("Performed Subtask 1 in Thread %s\n",
                    Thread.currentThread());
                return "Three";
            });
            Future<String> second = scope.fork(() -> {
                Thread.sleep(7000);
                System.out.printf("Performed Subtask 2 in Thread %s\n",
                    Thread.currentThread());
                return "Two";
            });
            scope.join();
            scope.throwIfFailed();
            return String.format("%s %s", first.resultNow(), second.resultNow());
        }
    }

    public static String task2() throws InterruptedException {
        Thread.sleep(2000);
        System.out.printf("Performed Task 2 in %s\n",
            Thread.currentThread());
        return "One";
    }

    /**
     * An unstructured thread is detached from any context or clear
     * responsibility. Since a structured thread clearly performs some work
     * for its parent, when the parent is cancelled the child should also be
     * cancelled.
     * <p>
     * Thus, if the parent thread is interrupted, we propagate the
     * interruption its child threads. We can also give all tasks a deadline
     * that will interrupt those children that have yet to terminate by the
     * time it expires (as well as the current thread):
     *
     * @param args - arguments to the main application
     */
    public static void main(String[] args) throws ExecutionException,
        InterruptedException {
        long start = System.currentTimeMillis();
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> first =
                scope.fork(D8_StructuredConcurrencyInterruption::task1);
            Future<String> second =
                scope.fork(D8_StructuredConcurrencyInterruption::task2);
            scope.join();
            scope.throwIfFailed();
            System.out.format("%s %s%n", first.resultNow(), second.resultNow());
        }
        long end = System.currentTimeMillis();
        System.out.format("This took %d seconds\n", end - start);
    }
}
