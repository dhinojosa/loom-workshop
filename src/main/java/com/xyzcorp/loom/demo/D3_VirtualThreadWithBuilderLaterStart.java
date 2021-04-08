package com.xyzcorp.loom.demo;

public class D3_VirtualThreadWithBuilderLaterStart {
    public static void main(String[] args) {
        Thread thread = Thread.builder()
                              .virtual()
                              .task(() -> System.out.println("Virtual Thread, Lazy"))
                              .build();

        System.out.println("You should see this message first");

        thread.start();
    }
}
