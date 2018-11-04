package com.example.lannix.krskguide.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    // Данные базы данных и таблиц
    protected static final String DATABASE_NAME = "Krasnoyarsk.db";
    protected static final int DATABASE_VERSION = 1;
    protected static final String TABLE_NAME = "Krasnoyarsk";

    // Название столбцов
    protected static final String COLUMN_ID = "id";
    protected static final String COLUMN_COORDINATE_LATITUDE = "coordinates_latitude";
    protected static final String COLUMN_COORDINATE_LONGITUDE = "coordinates_longitude";
    protected static final String COLUMN_NAME = "name";
    protected static final String COLUMN_MAP_IMAGE_ID = "map_image_id";
    protected static final String COLUMN_DESCRIPTION_IMAGES = "description_images";
    protected static final String COLUMN_DESCRIPTION_TEXT = "description_text";
    protected static final String COLUMN_ADDRESS = "address";

    // Номера столбцов
    protected static final int NUM_COLUMN_ID = 0;
    protected static final int NUM_COLUMN_COORDINATE_LATITUDE = 1;
    protected static final int NUM_COLUMN_COORDINATE_LONGITUDE = 2;
    protected static final int NUM_COLUMN_NAME = 3;
    protected static final int NUM_COLUMN_MAP_IMAGE_ID = 4;
    protected static final int NUM_COLUMN_DESCRIPTION_IMAGES = 5;
    protected static final int NUM_COLUMN_DESCRIPTION_TEXT = 6;
    protected static final int NUM_COLUMN_ADDRESS = 7;

    OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COORDINATE_LATITUDE + " REAL, " +
                COLUMN_COORDINATE_LONGITUDE + " REAL, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_MAP_IMAGE_ID + " INTEGER, " +
                COLUMN_DESCRIPTION_IMAGES + " TEXT, " +
                COLUMN_DESCRIPTION_TEXT + " TEXT, " +
                COLUMN_ADDRESS + " TEXT); ";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}