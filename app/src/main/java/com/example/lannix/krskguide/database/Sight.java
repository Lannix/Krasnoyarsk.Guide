package com.example.lannix.krskguide.database;

import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static com.example.lannix.krskguide.database.OpenHelper.NUM_COLUMN_ADDRESS;
import static com.example.lannix.krskguide.database.OpenHelper.NUM_COLUMN_COORDINATE_LATITUDE;
import static com.example.lannix.krskguide.database.OpenHelper.NUM_COLUMN_COORDINATE_LONGITUDE;
import static com.example.lannix.krskguide.database.OpenHelper.NUM_COLUMN_DESCRIPTION_IMAGES;
import static com.example.lannix.krskguide.database.OpenHelper.NUM_COLUMN_DESCRIPTION_TEXT;
import static com.example.lannix.krskguide.database.OpenHelper.NUM_COLUMN_ID;
import static com.example.lannix.krskguide.database.OpenHelper.NUM_COLUMN_MAP_IMAGE_ID;
import static com.example.lannix.krskguide.database.OpenHelper.NUM_COLUMN_NAME;

public class Sight {

    private int id;
    private double coordinates_latitude;
    private double coordinates_longitude;
    private String name;
    private int map_image_id;
    private List<Integer> description_images;
    private String description_text;
    private String address;

    public Sight() {
    }

    public Sight(int id, double coordinates_latitude, double coordinates_longitude, String name, int map_image_id, List<Integer> description_images, String description_text, String address) {
        this.id = id;
        this.coordinates_latitude = coordinates_latitude;
        this.coordinates_longitude = coordinates_longitude;
        this.name = name;
        this.map_image_id = map_image_id;
        this.description_images = description_images;
        this.description_text = description_text;
        this.address = address;
    }

    public Sight(int id, double coordinates_latitude, double coordinates_longitude, String name, int map_image_id, String description_images, String description_text, String address) {
        this.id = id;
        this.coordinates_latitude = coordinates_latitude;
        this.coordinates_longitude = coordinates_longitude;
        this.name = name;
        this.map_image_id = map_image_id;
        this.description_text = description_text;
        this.address = address;

        Gson json = new Gson();
        this.description_images = json.fromJson(description_images, new TypeToken<List<Integer>>() {}.getType());
    }

    public Sight(Cursor mCursor) {
        this(mCursor.getInt(NUM_COLUMN_ID), mCursor.getDouble(NUM_COLUMN_COORDINATE_LATITUDE), mCursor.getDouble(NUM_COLUMN_COORDINATE_LONGITUDE),
                mCursor.getString(NUM_COLUMN_NAME), mCursor.getInt(NUM_COLUMN_MAP_IMAGE_ID), mCursor.getString(NUM_COLUMN_DESCRIPTION_IMAGES),
                mCursor.getString(NUM_COLUMN_DESCRIPTION_TEXT), mCursor.getString(NUM_COLUMN_ADDRESS));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCoordinates_latitude() {
        return coordinates_latitude;
    }

    public void setCoordinates_latitude(double coordinates_latitude) {
        this.coordinates_latitude = coordinates_latitude;
    }

    public double getCoordinates_longitude() {
        return coordinates_longitude;
    }

    public void setCoordinates_longitude(double coordinates_longitude) {
        this.coordinates_longitude = coordinates_longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMap_image_id() {
        return map_image_id;
    }

    public void setMap_image_id(int map_image_id) {
        this.map_image_id = map_image_id;
    }

    public List<Integer> getDescription_images() {
        return description_images;
    }

    public String getDescription_images_Json() {
        Gson gson = new Gson();
        return gson.toJson(description_images);
    }

    public void setDescription_images(List<Integer> description_images) {
        this.description_images = description_images;
    }

    public void setDescription_images(String images_json_array) {
        Gson json = new Gson();
        this.description_images = json.fromJson(images_json_array, new TypeToken<List<Integer>>() {
        }.getType());
    }

    public String getDescription_text() {
        return description_text;
    }

    public void setDescription_text(String description_text) {
        this.description_text = description_text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
