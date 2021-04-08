package com.xyzcorp.loom.exercises;

import java.util.concurrent.Callable;

public class E1_OrganizeQuotes {
    public static void main(String[] args) {

        //---Leave these alone
        Callable<String> phrase1 = () -> {Thread.sleep(100);return "struggling to get out ";};
        Callable<String> phrase2 = () -> {Thread.sleep(300);return "but I can usually sedate them ";};
        Callable<String> phrase3 = () -> {Thread.sleep(500);return "-- Bob Thaves";};
        Callable<String> phrase4 = () -> {Thread.sleep(200);return "Inside me there’s a thin person ";};
        Callable<String> phrase5 = () -> {Thread.sleep(100);return "with four or five cupcakes. ";};

        //Use whatever executor service, combination you would like to put these in order.
        //Don't change the timing in the callables, must have a minimum of two executors
    }
}
