package com.xyzcorp.loom.demo;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class D9_StructuredConcurrencyWithStructuredErrors {
    /**
     * An unstructured thread might suffer an exception and die alone without
     * anyone noticing; a structured thread’s failure will be observed by its
     * watchful parent, and the failure can then be put in context, for
     * example by stitching the child exception’s stack trace to its parent’s
     * stack trace.
     * But error propagation poses some challenges. Suppose that an exception
     * thrown by a child would automatically propagate to its parent that, as
     * a result, would then cancel (interrupt) all of its other children.
     * This may well be desirable in some situations, but that this should be
     * the default behavior is not so clear. So for the time being, we’re
     * experimenting with more explicit error and result handling.
     *
     * @param args - arguments to the main application
     */
    public static void main(String[] args) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println(processCallables());
        long end = System.currentTimeMillis();
        System.out.format("This took %d milliseconds\n", end - start);
    }

    private static List<String> processCallables() throws Throwable {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            List<Callable<String>> xs = List.of(
                () -> ("a"),
//                () -> {
//                    throw new IOException("Ooops");
//                },
                () -> "b");
            List<Future<String>> futures =
                xs.stream().map(scope::fork).toList();
            scope.joinUntil(Instant.now().plusSeconds(2));
            scope.throwIfFailed(e -> e);
            return futures.stream().map(Future::resultNow).toList();
        }
    }
}
