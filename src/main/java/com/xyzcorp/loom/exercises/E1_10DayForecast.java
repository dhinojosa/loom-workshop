package com.xyzcorp.loom.exercises;

import com.xyzcorp.loom.demo.D7_StructuredConcurrencyNested;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class E1_10DayForecast {

    public static String getRandomWeatherString() {
        Random random = new Random();
        List<String> conditions = List.of("Sunny", "Overcast", "Raining", "Snowing", "Hail");
        return conditions.get(random.nextInt(conditions.size()));
    }

    public static void getRandomWeatherByDate(LocalDate localDate){
        ThreadFactory tf = Thread.builder().virtual().factory();
        try (ExecutorService e = Executors.newThreadExecutor(tf)) {
            //iterate over 12AM - 11PM
        }//join
    }
    //Create one Method that will use an execution context to iterate for the next 10 days
    public static void iterateOverNextTenDays() {
        ThreadFactory tf = Thread.builder().virtual().factory();
        try (ExecutorService e = Executors.newThreadExecutor(tf)) {
            //iterate over the next 10 days
        }//join
    }
    public static void main(String[] args) {
       iterateOverNextTenDays();
    }
}
