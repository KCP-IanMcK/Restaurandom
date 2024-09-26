package com.example.restaurandomfx;

import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PhotoRequest {
    public static void requestPhoto(String photoReference) {
        String apiKey = "AIzaSyBOklWQxqMKLHvS_slwXrMGpC9RPgI01cc";
        int maxWidth = 400;

        try {
            String urlString = String.format(
                    "https://maps.googleapis.com/maps/api/place/photo?maxwidth=%d&photoreference=%s&key=%s",
                    maxWidth, photoReference, apiKey
            );
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = conn.getInputStream();
            FileOutputStream out = new FileOutputStream("photo.jpg");

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            in.close();
            out.close();
            conn.disconnect();

            System.out.println("Photo saved as photo.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}