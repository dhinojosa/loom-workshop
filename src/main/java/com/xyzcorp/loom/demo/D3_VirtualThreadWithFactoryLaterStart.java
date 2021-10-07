package com.xyzcorp.loom.demo;

import java.util.concurrent.ThreadFactory;

public class D3_VirtualThreadWithFactoryLaterStart {
    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory =
            Thread.ofVirtual()
                  .name("D3_Factory")
                  .factory();

        Thread thread =
            threadFactory
                .newThread(() -> {
                    System.out.println(Thread.currentThread());
                    System.out.println("Virtual Thread, Lazy");
                });


        System.out.println("You should see this message first");

        thread.start();

        Thread.sleep(4000);
    }
}
