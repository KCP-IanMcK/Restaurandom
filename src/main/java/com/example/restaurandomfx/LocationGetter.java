package com.example.restaurandomfx;

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

}
