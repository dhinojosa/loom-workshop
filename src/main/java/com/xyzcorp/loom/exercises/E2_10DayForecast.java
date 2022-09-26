package com.xyzcorp.loom.exercises;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class E2_10DayForecast {

    public static String getRandomWeatherString() {
        Random random = new Random();
        List<String> conditions =
            List.of("Sunny", "Overcast", "Raining", "Snowing", "Hail");
        return conditions.get(random.nextInt(conditions.size()));
    }

    @SuppressWarnings("unused")
    public static void getRandomWeatherByDate(LocalDate localDate) {
        //Create a boundary
        //Iterate over 12AM-11PM
        //join
    }

    @SuppressWarnings("unused")
    private static PrintStream printWeatherFor(LocalDate localDate,
                                               java.time.LocalDateTime localDateTime) {
        return System.out.printf("%s %s %s\n", localDate, localDateTime,
            getRandomWeatherString());
    }

    @SuppressWarnings("unused")
    private static boolean isSameDay(LocalDate localDate,
                                     java.time.LocalDateTime localDateTime) {
        return localDate.getDayOfMonth() == localDateTime.getDayOfMonth() &&
            localDate.getMonth() == localDateTime.getMonth();
    }

    //Create one Method that will use an execution context to iterate for the
    // next 10 days
    public static void iterateOverNextTenDays() {
        // Create a boundary
        // Iterate over the next ten days
        //join
    }

    public static void main(String[] args) {
        iterateOverNextTenDays();
    }
}
