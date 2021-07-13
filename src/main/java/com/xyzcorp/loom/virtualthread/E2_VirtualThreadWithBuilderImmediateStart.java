package com.xyzcorp.loom.virtualthread;

public class E2_VirtualThreadWithBuilderImmediateStart {
    public static void main(String[] args) {
        Thread thread = Thread
            .builder()
            .virtual()
            .task(() -> System.out.println("Virtual Thread, Lazy"))
            .start();
    }
}
