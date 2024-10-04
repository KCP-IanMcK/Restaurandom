package com.example.restaurandomfx;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleAPIRequest {


    public static void googleAPIRequest(List<String> desiredCuisines, String locationString, String[] allCuisines, String priceLevel) {
        String apiKey = "AIzaSyBOklWQxqMKLHvS_slwXrMGpC9RPgI01cc";
        String location = locationString;
        String maxprice = "";
        int radius = 20000;
        String finalDesiredCuisines = "";
        if(desiredCuisines.toString().equals("[Select all]")) {
            desiredCuisines.addAll(List.of(allCuisines));
            desiredCuisines.remove("Select all");
        }
        for (String desiredCuisine : desiredCuisines) {
            finalDesiredCuisines += desiredCuisine;
        }
        String keyword = finalDesiredCuisines;
        System.out.println("Desired cuisines: " + desiredCuisines);

        if (priceLevel.equals("2") || priceLevel.equals("3")) {
            maxprice = priceLevel;
        } else if (priceLevel.equals("4 (expensive)")) {
            maxprice = "4";
        } else {
            maxprice = "1";
        }

        System.out.println(maxprice);
        try {
            String urlString = String.format(
                    "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s&radius=%d&type=restaurant&keyword=%s&maxprice=%s&fields=name,formatted_address,types,photos,price_level,opening_hours,geometry&key=%s",
                    location, radius, keyword, maxprice, apiKey
            );

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONArray results = jsonResponse.getJSONArray("results");

            try(FileWriter file = new FileWriter("output.json")){
                file.write(results.toString(4));
                file.flush();
            } catch (IOException e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
