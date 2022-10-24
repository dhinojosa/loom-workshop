package com.xyzcorp.loom.demo;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class D6_StructuredConcurrencyInvokeAny {
    public static String task1() throws InterruptedException {
        Thread.sleep(3000);
        return "Task1";
    }

    public static String task2() throws InterruptedException {
        Thread.sleep(7000);
        return "Task2";
    }

    public static void main(String[] args)
        throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            //NOTE: There is no need to create Future<?> variables
            scope.fork(D6_StructuredConcurrencyInvokeAny::task1);
            scope.fork(D6_StructuredConcurrencyInvokeAny::task2);
            scope.join(); //join
            System.out.format("""
                Return the first finished thread with the result: %s%n""",
                scope.result());
        }
        long end = System.currentTimeMillis();
        System.out.format("This took %d milliseconds\n", end - start);
    }
}
