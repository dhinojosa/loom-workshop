package com.xyzcorp.loom.demo;

public class D1_VirtualThread {
    public static void main(String[] args) throws InterruptedException {
        Thread.startVirtualThread(() -> {
            System.out.format("Hello, Loom! in Thread %s",
                Thread.currentThread());
        });
        Thread.sleep(300);
    }
}
