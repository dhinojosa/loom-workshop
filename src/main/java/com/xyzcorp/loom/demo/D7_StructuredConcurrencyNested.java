package com.xyzcorp.loom.demo;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.io.PrintStream;
import java.util.concurrent.*;

public class D7_StructuredConcurrencyNested {
    static ThreadFactory tf = Thread
        .ofVirtual()
        .name("structured-concurrency-nested")
        .factory();

    public static String task1() throws InterruptedException,
        ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            Callable<String> printStreamCallable = () -> {
                Thread.sleep(4000);
                return String.format("Performed Subtask in Thread %s\n",
                    Thread.currentThread());
            };
            scope.fork(printStreamCallable);
            scope.fork(printStreamCallable);
            scope.join();
            String result = scope.result();
            System.out.println(result);
            return result;
        }//join
    }

    public static String task2() throws InterruptedException {
        Thread.sleep(7000);
        return String.format("Performed Task 2 in %s\n", Thread.currentThread());
    }

    public static void main(String[] args)
        throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            scope.fork(D7_StructuredConcurrencyNested::task1);
            scope.fork(D7_StructuredConcurrencyNested::task2);
            scope.join();
            String result = scope.result();
            System.out.printf("The result is %s%n", result);
        }//join

        long end = System.currentTimeMillis();
        System.out.format("This took %d seconds\n", end - start);
    }
}
