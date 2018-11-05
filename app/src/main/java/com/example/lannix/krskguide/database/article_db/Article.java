package com.example.lannix.krskguide.database.article_db;

import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.lannix.krskguide.database.article_db.DBArticlesHelper.NUM_COLUMN_LONG_DESCRIPTION;
import static com.example.lannix.krskguide.database.article_db.DBArticlesHelper.NUM_COLUMN_ID;
import static com.example.lannix.krskguide.database.article_db.DBArticlesHelper.NUM_COLUMN_IMAGE_ID;
import static com.example.lannix.krskguide.database.article_db.DBArticlesHelper.NUM_COLUMN_SHORT_DESCRIPTION;
import static com.example.lannix.krskguide.database.article_db.DBArticlesHelper.NUM_COLUMN_TITLE;

public class Article {

    private int id;
    private String title;
    private String shortDescription;
    private String longDescription;
    private ArrayList<Integer> imageIds;

    public Article() {
    }

    public Article(int id, String title, String shortDescription, String longDescription, ArrayList<Integer> imageId) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageIds = imageId;
    }

    public Article(int id, String title, String shortDescription, String longDescription, String imageId_json) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;

        Gson json = new Gson();
        this.imageIds = json.fromJson(imageId_json, new TypeToken<List<Integer>>() {
        }.getType());
    }

    public Article(Cursor mCursor) {
        this(mCursor.getInt(NUM_COLUMN_ID), mCursor.getString(NUM_COLUMN_TITLE), mCursor.getString(NUM_COLUMN_SHORT_DESCRIPTION), mCursor.getString(NUM_COLUMN_LONG_DESCRIPTION), mCursor.getString(NUM_COLUMN_IMAGE_ID));
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public ArrayList<Integer> getImageIds() {
        return imageIds;
    }

    public String getImageIdsJson() {
        Gson gson = new Gson();
        return gson.toJson(imageIds);
    }

    public void setImageIds(ArrayList<Integer> imageIds) {
        this.imageIds = imageIds;
    }

    public void setImageIds(String imageIds_json) {
        Gson json = new Gson();
        this.imageIds = json.fromJson(imageIds_json, new TypeToken<List<Integer>>() {
        }.getType());
    }
}
