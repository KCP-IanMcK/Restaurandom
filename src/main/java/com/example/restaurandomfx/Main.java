package com.example.restaurandomfx;

import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.GsonBuilder;

public class Main extends Fx {

    private static boolean firstTime = true;
    private static final List<Restaurant> availableOptions = new ArrayList<>();

    public static List<String> availableCuisines(Restaurant[] restaurants){
        List<String> cuisines = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if(!cuisines.contains(restaurant.getCuisine())) {
                cuisines.add(restaurant.getCuisine());
            }
    }
        return cuisines;}

    public static Restaurant[] readRestaurants() {
        Gson gson = new GsonBuilder()
                .create();

        Restaurant[] restaurants = new Restaurant[50]; // Grösse des Arrays anpassen

        try(Reader reader = new FileReader("src/main/java/com/example/restaurandomfx/restaurant1.json")){

            restaurants = gson.fromJson(reader, Restaurant[].class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurants;
    }

    public static Restaurant chooseOne(Restaurant[] restaurants, List<String> desiredCuisines) {
        if(firstTime) {
            if(desiredCuisines.getFirst().equals("Select all")) {
                availableOptions.addAll(Arrays.asList(restaurants));
                firstTime = false;
            } else {
                for (Restaurant restaurant : restaurants) {
                    if (desiredCuisines.contains(restaurant.getCuisine())) {
                        availableOptions.add(restaurant);
                    }
                }
                firstTime = false;
            }
        }

        int randomNumber = (int) Math.round(Math.random() * (availableOptions.size() -1));

        try {
            Restaurant choice = availableOptions.get(randomNumber);
            availableOptions.remove(randomNumber);
            return choice;
        } catch (IndexOutOfBoundsException e){
            return new Restaurant("noRestaurant", "https://i.kym-cdn.com/entries/icons/original/000/043/047/sad_emoji_meme.jpg");
        }


    }




    public static void main(String[] args) {
        launch();
    }
}