package com.example.restaurandomfx;

import com.google.gson.Gson;
import java.io.*;
import com.google.gson.GsonBuilder;
import java.util.Scanner;

public class Main extends Fx {

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

    public static Restaurant chooseOne(Restaurant[] restaurants) {

        int randomNumber = (int) Math.round(Math.random() * 2);

        return restaurants[randomNumber];
    }


    public static void main(String[] args) {
        launch();
    }
}