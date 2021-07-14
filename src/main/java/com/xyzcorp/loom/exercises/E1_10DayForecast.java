package com.xyzcorp.loom.exercises;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

public class E1_10DayForecast {

    private static final ThreadFactory threadFactory =
        Thread.ofVirtual().name("10-day-forecast").factory();

    public static String getRandomWeatherString() {
        Random random = new Random();
        List<String> conditions =
            List.of("Sunny", "Overcast", "Raining", "Snowing", "Hail");
        return conditions.get(random.nextInt(conditions.size()));
    }

    public static void getRandomWeatherByDate(LocalDate localDate) {
        try (ExecutorService e = Executors.newThreadExecutor(threadFactory)) {
            //Iterate over 12AM-11PM
        }//join
    }

    private static PrintStream printWeatherFor(LocalDate localDate,
                                               java.time.LocalDateTime localDateTime) {
        return System.out.printf("%s %s %s\n", localDate, localDateTime,
            getRandomWeatherString());
    }

    private static boolean isSameDay(LocalDate localDate,
                                     java.time.LocalDateTime localDateTime) {
        return localDate.getDayOfMonth() == localDateTime.getDayOfMonth() &&
            localDate.getMonth() == localDateTime.getMonth();
    }

    //Create one Method that will use an execution context to iterate for the
    // next 10 days
    public static void iterateOverNextTenDays() {
        try (ExecutorService e = Executors.newThreadExecutor(threadFactory)) {
            //Iterate over the next ten days
        }//join
    }

    public static void main(String[] args) {
        iterateOverNextTenDays();
    }
}
