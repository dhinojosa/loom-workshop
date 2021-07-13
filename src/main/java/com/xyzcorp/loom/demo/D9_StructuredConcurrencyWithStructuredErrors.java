package com.xyzcorp.loom.demo;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.*;

public class D9_StructuredConcurrencyWithStructuredErrors {
    /**
     * An unstructured thread might suffer an exception and die alone without
     * anyone noticing; a structured thread’s failure will be observed by its
     * watchful parent, and the failure can then be put in context, for
     * example by stitching the child exception’s stack trace to its parent’s
     * stack trace.
     * <p>
     * But error propagation poses some challenges. Suppose that an exception
     * thrown by a child would automatically propagate to its parent that, as
     * a result, would then cancel (interrupt) all of its other children.
     * This may well be desirable in some situations, but that this should be
     * the default behavior is not so clear. So for the time being, we’re
     * experimenting with more explicit error and result handling.
     *
     * @param args - arguments to the main application
     */
    public static void main(String[] args) throws ExecutionException,
        InterruptedException {
        long start = System.currentTimeMillis();
        ThreadFactory tf =
            Thread.ofVirtual()
                  .name("structured-concurrency-errors")
                  .factory();

        try (ExecutorService e =
                 Executors.newThreadExecutor(tf,
                     Instant.now().plusSeconds(10))) {
            List<Callable<String>> xs = List.of(
                () -> ("a"),
                () -> {
                    throw new IOException("Ooops");
                },
                () -> "b");
            String result = e.invokeAny(xs);
            System.out.println(result);
        }
        long end = System.currentTimeMillis();
        System.out.format("This took %d milliseconds\n", end - start);
    }
}
