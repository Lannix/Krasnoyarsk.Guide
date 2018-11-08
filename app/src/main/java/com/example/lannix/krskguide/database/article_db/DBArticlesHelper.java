package com.example.lannix.krskguide.database.article_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBArticlesHelper extends SQLiteOpenHelper {

    // Данные базы данных и таблиц
    protected static final String DATABASE_NAME = "krasnoyarsk_articles.db";
    protected static final int DATABASE_VERSION = 1;
    protected static final String TABLE_NAME = "Articles";

    // Название столбцов
    protected static final String COLUMN_ID = "id";
    protected static final String COLUMN_TITLE = "title";
    protected static final String COLUMN_SHORT_DESCRIPTION = "short_description";
    protected static final String COLUMN_LONG_DESCRIPTION = "long_description";
    protected static final String COLUMN_IMAGE_ID = "image_id";

    // Номера столбцов
    protected static final int NUM_COLUMN_ID = 0;
    protected static final int NUM_COLUMN_TITLE = 1;
    protected static final int NUM_COLUMN_SHORT_DESCRIPTION = 2;
    protected static final int NUM_COLUMN_LONG_DESCRIPTION = 3;
    protected static final int NUM_COLUMN_IMAGE_ID = 4;

    DBArticlesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_SHORT_DESCRIPTION + " TEXT, " +
                COLUMN_LONG_DESCRIPTION + " TEXT, " +
                COLUMN_IMAGE_ID + " INTEGER); ";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
