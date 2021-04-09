package com.xyzcorp.loom.demo;

public class D2_VirtualThreadWithBuilderImmediateStart {
    public static void main(String[] args) throws InterruptedException {
//        Thread thread = Thread
//            .ofVirtual()
//            .start(() -> System.out.println("Virtual Thread, Lazy"));
//

        Thread thread = Thread
            .ofVirtual()
            .unstarted(() -> System.out.println("Virtual Thread, Lazy"));

        thread.start();
        Thread.sleep(4000);
    }
}
