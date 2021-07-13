package com.xyzcorp.loom.virtualthread;

//Note this is lower level, you will not need to do this
public class E0_Continuations {

    public static void work(ContinuationScope scope) {
        System.out.println("Begin");
        Continuation.yield(scope);

        System.out.println("Level 1");
        Continuation.yield(scope);

        System.out.println("Level 2");
        Continuation.yield(scope);

        System.out.println("Level 3");
        Continuation.yield(scope);

        System.out.println("Level 4");
        Continuation.yield(scope);

        System.out.println("End");
    }
    public static void main(String[] args) {
        ContinuationScope myScope = new ContinuationScope("myScope");

        Continuation continuation = new Continuation(myScope, new Runnable() {
            @Override
            public void run() {
                work(myScope);
            }
        });

        while(!continuation.isDone()) {
            System.out.println("Running");
            continuation.run();
        }
    }
}
