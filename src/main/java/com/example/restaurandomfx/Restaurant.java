package com.example.restaurandomfx;

public class Restaurant {
    public String name;
    public String cuisine;
    public String address;
    public String price;
    public boolean isOpen;
    public String image_url;
    public double distance;
    public String openingHours;

    public Restaurant() {
    }

    public Restaurant(String name, String openingHours, double distance, String image_url, boolean isOpen, String price, String address, String cuisine) {
        this.name = name;
        this.openingHours = openingHours;
        this.distance = distance;
        this.image_url = image_url;
        this.isOpen = isOpen;
        this.price = price;
        this.address = address;
        this.cuisine = cuisine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public String toString() {
        return "Restaurant: " + name +"\nCuisine: " + cuisine +
                "\nAddress: " + address + "\nDistance: " + distance +
                "km\nPrice: " + price +
                "\nIs it open?: " + isOpen + "\nOpening Hours: " + openingHours;
//                "\nImage: " + image +       das bild wird später noch relevant
    }
}

