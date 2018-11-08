package com.example.lannix.krskguide.recyclerView.horizontal;

import com.example.lannix.krskguide.database.article_db.Article;
import com.example.lannix.krskguide.database.sight_db.Sight;

public class SimpleCardObject {

    public static final int ARTICLE = 0;
    public static final int SIGHT = 1;

    private String title;
    private int imageId;
    private int dataBaseId;
    private int typeOfDBId;

    public SimpleCardObject() {
    }

    public SimpleCardObject(String title, int imageId, int dataBaseId, int typeOfDBId) {
        this.title = title;
        this.imageId = imageId;
        this.dataBaseId = dataBaseId;
        this.typeOfDBId = typeOfDBId;
    }

    public SimpleCardObject(Sight sight) {
        this.title = sight.getName();
        this.imageId = sight.getDescription_images().get(0);
        this.dataBaseId = sight.getId();
        this.typeOfDBId = SIGHT;
    }

    public SimpleCardObject(Article article) {
        this.title = article.getTitle();
        this.imageId = article.getImageIds().get(0);
        this.dataBaseId = article.getId();
        this.typeOfDBId = ARTICLE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getDataBaseId() {
        return dataBaseId;
    }

    public void setDataBaseId(int dataBaseId) {
        this.dataBaseId = dataBaseId;
    }

    public int getTypeOfDBId() {
        return typeOfDBId;
    }

    public void setTypeOfDBId(int typeOfDBId) {
        this.typeOfDBId = typeOfDBId;
    }
}
