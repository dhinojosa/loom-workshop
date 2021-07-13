package com.xyzcorp.loom.virtualthread;

import java.io.PrintStream;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class E8_StructuredConcurrencyInterruption {

    public static void task1() {
        ThreadFactory tf = Thread.builder().virtual().factory();
        try (ExecutorService e = Executors.newThreadExecutor(tf)) {
            e.submit(() -> {
                try {
                    Thread.sleep(12000); //Reset to 12000
                    System.out.printf("Performed Subtask 1 in Thread %s\n",
                        Thread.currentThread());
                } catch (InterruptedException interruptedException) {
                    System.out.println("Caught Interruption in subtask 1 in " +
                        "task 1");
                    interruptedException.printStackTrace();
                }
            });
            e.submit(() -> {
                try {
                    Thread.sleep(7000);
                    System.out.printf("Performed Subtask 2 in Thread %s\n"
                        , Thread.currentThread());
                } catch (InterruptedException interruptedException) {
                    System.out.println("Caught Interruption in subtask 2 " +
                        "in task 1");
                    interruptedException.printStackTrace();
                }
            });
        }
    }

    public static void task2() {
        try {
            Thread.sleep(2000);
            System.out.printf("Performed Task 2 in %s\n",
                Thread.currentThread());
        } catch (InterruptedException interruptedException) {
            System.out.println("Caught Interruption in task 2");
            interruptedException.printStackTrace();
        }
    }

    /**
     * An unstructured thread is detached from any context or clear
     * responsibility. Since a structured thread clearly performs some work
     * for its parent, when the parent is cancelled the child should also be
     * cancelled.
     * <p>
     * Thus, if the parent thread is interrupted, we propagate the
     * interruption its child threads. We can also give all tasks a deadline
     * that will interrupt those children that have yet to terminate by the
     * time it expires (as well as the current thread):
     *
     * @param args - arguments to the main application
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ThreadFactory tf = Thread.builder().virtual().factory();
        try (ExecutorService e =
                 Executors.newThreadExecutor(tf).withDeadline(Instant.now().plusSeconds(10))) {
            e.submit(E8_StructuredConcurrencyInterruption::task1);
            e.submit(E8_StructuredConcurrencyInterruption::task2);
        }
        long end = System.currentTimeMillis();
        System.out.format("This took %d seconds\n", end - start);
    }
}
