package com.xyzcorp.loom.demo;

public class D2_VirtualThreadWithImmediateStart {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () ->
            System.out.format("Virtual Thread, Non-Lazy, Immediate: %s%n", System.currentTimeMillis());

        Thread
            .ofVirtual()
            .start(task);

        Thread
            .ofVirtual()
            .start(task);

        Thread.sleep(4000);
        System.out.println("The time is: " + System.currentTimeMillis());
        Thread.sleep(4000);
    }
}
