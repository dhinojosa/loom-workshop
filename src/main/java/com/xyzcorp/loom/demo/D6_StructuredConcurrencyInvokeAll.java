package com.xyzcorp.loom.demo;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.concurrent.ExecutionException;

public class D6_StructuredConcurrencyInvokeAll {
    public static Void task1() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Void task2() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
        throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            scope.fork(D6_StructuredConcurrencyInvokeAll::task1);
            scope.fork(D6_StructuredConcurrencyInvokeAll::task2);
            scope.join(); //join
            scope.throwIfFailed();
        }
        long end = System.currentTimeMillis();
        System.out.format("This took %d milliseconds\n", end - start);
    }
}
