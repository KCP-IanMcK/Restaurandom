package com.example.restaurandomfx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleAPIRequest {

    public static List<JSONObject> restaurantList;

    public static void googleAPIRequest() {
        String apiKey = "AIzaSyBOklWQxqMKLHvS_slwXrMGpC9RPgI01cc";
        String location = "47.3769,8.5417"; //geoLocation einfügen?
        int radius = 200;
        String keyword = "Chinese"; // hier Cuisines einfügen
        try {
            String urlString = String.format(
                    "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s&radius=%d&type=restaurant&keyword=%s&fields=name,formatted_address,types,photos,price_level,opening_hours,geometry&key=%s",
                    location, radius, keyword, apiKey
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

            for (int i = 0; i < results.length(); i++) {
                JSONObject place = results.getJSONObject(i);
                restaurantList.add(place);
                System.out.println("Name: " + place.getString("name"));
                System.out.println("Address: " + place.getString("vicinity"));
                try {
                    System.out.println("Price Level: " + place.getBigInteger("price_level"));
                }catch (Exception e){}
//                System.out.println("Distance: " + place.getDouble("distance"));
                try {
                    System.out.println("Open Now: " + place.getJSONObject("opening_hours").getBoolean("open_now"));
                }catch (Exception e){}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
