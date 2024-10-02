package com.example.restaurandomfx;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LocationGetter {

    public static String getGeoLocation() {
        String response = "";
        try {
            String apiUrl = "https://api.ipgeolocation.io/ipgeo?apiKey=a249de2a6af040799641c387ee9feb75";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                response = content.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(response);
        double lat = jsonObject.getDouble("latitude");
        double lon = jsonObject.getDouble("longitude");

        return lat + "," + lon;
    }

}
