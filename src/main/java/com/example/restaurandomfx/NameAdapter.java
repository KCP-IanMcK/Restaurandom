package com.example.restaurandomfx;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class NameAdapter extends TypeAdapter<String> {
    @Override
    public void write(JsonWriter out, String value) throws IOException {

    }

    @Override
    public String read(JsonReader in) throws IOException {
        String information = in.toString();
        return information;
    }
}
