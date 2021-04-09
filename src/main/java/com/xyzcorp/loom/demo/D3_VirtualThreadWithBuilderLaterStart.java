package com.xyzcorp.loom.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class D3_VirtualThreadWithBuilderLaterStart {
    public static void main(String[] args) {
        ThreadFactory threadFactory = Thread.ofVirtual()
                              .name("D3_Factory")
                              .factory();
        Thread thread = Thread
            .ofVirtual()
            .unstarted(() -> System.out.println("Virtual Thread, Lazy"));


        System.out.println("You should see this message first");

        thread.start();
    }
}
