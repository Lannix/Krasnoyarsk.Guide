package com.example.lannix.krskguide.recyclerView;

import com.example.lannix.krskguide.database.Article;
import com.example.lannix.krskguide.database.Sight;

public class CardObject {

    private String title;
    private String description;
    private int imageId;

    public CardObject(String title) {
        this.title = title;
    }

    public CardObject(String title, String description, int imageId) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
    }

    public CardObject(Sight sight) {
        this.title = sight.getName();
        this.description = sight.getAddress();
        this.imageId = sight.getDescription_images().get(0);
    }

    public CardObject(Article article) {
        this.title = article.getTitle();
        this.description = article.getDescription();
        this.imageId = article.getImageId();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
