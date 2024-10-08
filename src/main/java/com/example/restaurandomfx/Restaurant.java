package com.example.restaurandomfx;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Restaurant {
    public String name;
    public String vicinity;
    public int price_level;
    public JsonObject opening_hours;
    public Boolean open_now;
    public JsonArray photos;
    public String photo_reference;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String name, String vicinity, int price_level, JsonObject opening_hours, JsonArray photos) {
        this.name = name;
        this.vicinity = vicinity;
        this.price_level = price_level;
        this.opening_hours = opening_hours;
        this.photos = photos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public int getPrice_level() {
        return price_level;
    }

    public void setPrice_level(int price_level) {
        this.price_level = price_level;
    }

    public String getPhotoReference(){
        if(photos != null) {
            JsonObject item = photos.get(0).getAsJsonObject();
            this.photo_reference = item.get("photo_reference").getAsString();
        }
        return photo_reference;
    }

    @Override
    public String toString() {
        if(opening_hours != null) {
            this.open_now = opening_hours.get("open_now").getAsBoolean();
        }
        String priceLevelString = "";
        String openNowString;
        if(opening_hours == null) {
            openNowString = "No Information about opening hours";
        } else if(open_now){
            openNowString = "Currently Open";
        } else{
            openNowString = "Currently Closed";
        }
        switch (price_level){
            case 0:
                priceLevelString = "No Information";
                break;
            case 1:
                priceLevelString = "$";
                break;
            case 2:
                priceLevelString = "$$";
                break;
            case 3:
                priceLevelString = "$$$";
                break;
            case 4:
                priceLevelString = "$$$$";
                break;
            default:
                priceLevelString = String.valueOf(price_level);
        }

        return name +
                "\n" + vicinity +
                "\nPrice Level: " + priceLevelString +
//                "\nDistance: " + distance +
                "\n" + openNowString;
    }

    public String toString2() {
        return "No more Restaurants available";
    }
}

