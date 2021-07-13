package com.xyzcorp.loom.virtualthread;

public class E1_VirtualThread {
    public static void main(String[] args) throws InterruptedException {
        Thread.startVirtualThread(() -> {
            System.out.format("Hello, Loom! in Thread %s",
                Thread.currentThread());
        });
        Thread.sleep(300);
    }
}
