package com.example.restaurandomfx;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LocationGetter {

    public static String getLocation(String locationString) {
        double xCoordinate = 0;
        double yCoordinate = 0;
        switch (locationString) {
            case "Oerlikon":
                xCoordinate = 47.407482;
                yCoordinate = 8.543809;
                break;
            case "Zürich Zentrum":
                xCoordinate = 47.375718;
                yCoordinate = 8.529358;
                break;
            case "Bülach":
                xCoordinate = 47.516260;
                yCoordinate = 8.541718;
                break;
            case "Embrach":
                xCoordinate = 47.509631;
                yCoordinate = 8.593855;
                break;
            case "Winterthur":
                xCoordinate = 47.499854;
                yCoordinate = 8.725709;
                break;
            case "Oberglatt":
                xCoordinate = 47.476569;
                yCoordinate = 8.519202;
                break;
            case "Eglisau":
                xCoordinate = 47.575485;
                yCoordinate = 8.521802;
                break;
            case "Rümlang":
                xCoordinate = 47.450577;
                yCoordinate = 8.529695;
                break;
        }


        double[] location = {xCoordinate, yCoordinate};
        String finalLocation = location[0] + "," + location[1];
        System.out.println("Got location: " + finalLocation);
        return finalLocation;
    }

    public static String getGeoLocation() {
        String response = "";
        try {
            // Example API URL
            String apiUrl = "https://api.ipgeolocation.io/ipgeo?apiKey=a249de2a6af040799641c387ee9feb75";   //"http://ip-api.com/json/";
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
        double lat = jsonObject.getDouble("latitude"); //lat
        double lon = jsonObject.getDouble("longitude"); //lon

        return lat + "," + lon;
    }

}
