package com.xyzcorp.loom.demo;

public class D2_VirtualThreadWithBuilderImmediateStart {
    public static void main(String[] args) {
        Thread thread = Thread
            .builder()
            .virtual()
            .task(() -> System.out.println("Virtual Thread, Lazy"))
            .start();
    }
}
