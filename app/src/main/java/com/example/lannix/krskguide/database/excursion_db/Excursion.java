package com.example.lannix.krskguide.database.excursion_db;

import java.util.ArrayList;

public class Excursion {

    private int id;
    private String title;
    private String shortDescription;
    private String longDescription;
    private ArrayList<Integer> sights;

    public Excursion() {
    }

    public Excursion(int id, String title, String shortDescription, String longDescription, ArrayList<Integer> sights) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.sights = sights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
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

    public ArrayList<Integer> getSights() {
        return sights;
    }

    public void setSights(ArrayList<Integer> sights) {
        this.sights = sights;
    }
}
