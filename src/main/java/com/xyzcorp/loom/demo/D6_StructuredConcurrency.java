package com.xyzcorp.loom.demo;

import jdk.incubator.concurrent.StructuredTaskScope;

public class D6_StructuredConcurrency {
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
        throws InterruptedException {

        long start = System.currentTimeMillis();
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            scope.fork(D6_StructuredConcurrency::task1);
            scope.fork(D6_StructuredConcurrency::task2);
            scope.join(); //join
        }

        long end = System.currentTimeMillis();
        System.out.format("This took %d milliseconds\n", end - start);
    }
}
