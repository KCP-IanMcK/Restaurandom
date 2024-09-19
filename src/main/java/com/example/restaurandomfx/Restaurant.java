package com.example.restaurandomfx;

public class Restaurant {
    public String name;
    public String vicinity;
    public int price_level;
    //public String image_url; //photos -> photo_reference
//    public double distance;
    public boolean open_now; //opening_hours

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String name, String vicinity, int price_level, boolean open_now) {
        this.name = name;
        this.vicinity = vicinity;
        this.price_level = price_level;
        this.open_now = open_now;
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

    public boolean isOpen_now() {
        return open_now;
    }

    public void setOpen_now(boolean open_now) {
        this.open_now = open_now;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nAdress: " + vicinity +
                "\nPrice Level: " + price_level +
//                "\nDistance: " + distance +
                "\nOpen Now: " + open_now;
    }

    public String toString2() {
        return "No more Restaurants available";
    }
}

