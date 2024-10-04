package com.example.restaurandomfx;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.GsonBuilder;

public class Main extends Fx {

    static boolean firstTime = true;
    private static final List<Restaurant> availableOptions = new ArrayList<>();

    public static Restaurant[] readRestaurants() {
        Gson gson = new GsonBuilder()
                .create();

        Restaurant[] restaurants = new Restaurant[50]; // Grösse des Arrays anpassen

        try (Reader reader = new FileReader("output.json")) {

            restaurants = gson.fromJson(reader, Restaurant[].class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurants;
    }

    public static Restaurant chooseOne(Restaurant[] restaurants) {
        if (firstTime) {
            availableOptions.addAll(Arrays.asList(restaurants));
            firstTime = false;
        }

        int randomNumber = (int) Math.round(Math.random() * (availableOptions.size() - 1));

        try {
            Restaurant choice = availableOptions.get(randomNumber);
            availableOptions.remove(randomNumber);
            return choice;
        } catch (IndexOutOfBoundsException e) {
            return new Restaurant("noRestaurant"); //IMG: Sad Emoji
        }
    }


    public static void main(String[] args) {
        launch();
    }
}