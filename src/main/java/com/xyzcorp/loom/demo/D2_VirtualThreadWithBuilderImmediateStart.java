package com.xyzcorp.loom.demo;

public class D2_VirtualThreadWithBuilderImmediateStart {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Virtual Thread, Non-Lazy, Immediate" + System.currentTimeMillis());
            }
        };

        Thread thread = Thread
            .ofVirtual()
            .start(task);

         Thread thread2 = Thread
            .ofVirtual()
            .start(task); //change to unstarted

        Thread.sleep(4000);
        System.out.println("The time is: " + System.currentTimeMillis());
        Thread.sleep(4000);
    }
}
