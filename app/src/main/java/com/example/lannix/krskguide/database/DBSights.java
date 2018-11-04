package com.example.lannix.krskguide.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lannix.krskguide.R;

import java.util.ArrayList;

import static com.example.lannix.krskguide.database.OpenHelper.COLUMN_ADDRESS;
import static com.example.lannix.krskguide.database.OpenHelper.COLUMN_COORDINATE_LATITUDE;
import static com.example.lannix.krskguide.database.OpenHelper.COLUMN_COORDINATE_LONGITUDE;
import static com.example.lannix.krskguide.database.OpenHelper.COLUMN_DESCRIPTION_IMAGES;
import static com.example.lannix.krskguide.database.OpenHelper.COLUMN_DESCRIPTION_TEXT;
import static com.example.lannix.krskguide.database.OpenHelper.COLUMN_ID;
import static com.example.lannix.krskguide.database.OpenHelper.COLUMN_MAP_IMAGE_ID;
import static com.example.lannix.krskguide.database.OpenHelper.COLUMN_NAME;
import static com.example.lannix.krskguide.database.OpenHelper.TABLE_NAME;

public class DBSights {

    private SQLiteDatabase mDataBase;
    private OpenHelper mOpenHelper;

    public DBSights(Context context) {
        mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();

        //Добавление ненужной информации
        addStuffData();
        testData();
    }

    public long insert(Sight sight) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COORDINATE_LATITUDE, sight.getCoordinates_latitude());
        cv.put(COLUMN_COORDINATE_LONGITUDE, sight.getCoordinates_longitude());
        cv.put(COLUMN_NAME, sight.getName());
        cv.put(COLUMN_MAP_IMAGE_ID, sight.getMap_image_id());
        cv.put(COLUMN_DESCRIPTION_IMAGES, sight.getDescription_images_Json());
        cv.put(COLUMN_DESCRIPTION_TEXT, sight.getDescription_text());
        cv.put(COLUMN_ADDRESS, sight.getAddress());
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Sight sight) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COORDINATE_LATITUDE, sight.getCoordinates_latitude());
        cv.put(COLUMN_COORDINATE_LONGITUDE, sight.getCoordinates_longitude());
        cv.put(COLUMN_NAME, sight.getName());
        cv.put(COLUMN_MAP_IMAGE_ID, sight.getMap_image_id());
        cv.put(COLUMN_DESCRIPTION_IMAGES, sight.getDescription_images_Json());
        cv.put(COLUMN_DESCRIPTION_TEXT, sight.getDescription_text());
        cv.put(COLUMN_ADDRESS, sight.getAddress());
        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[] { String.valueOf(sight.getId())});
    }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(int id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Sight select(long id) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        mCursor.moveToFirst();
        return new Sight(mCursor);
    }

    public ArrayList<Sight> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Sight> arr = new ArrayList<>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                arr.add(new Sight(mCursor));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    public void addStuffData() {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.bobr_log);
        insert(new Sight(0, 55.961248, 92.794484, "Бобровый лог", R.drawable.bobr_log_round, images, "Бобровый лог – одно" +
                " из популярнейших мест отдыха в Красноярске и представляет собой современный всесезонный комплекс, возведенный с учетом всех мировых стандартов и" +
                " требований. Фанпарк располагается в одном из самых красивых мест города - в северо-западной части склона Куйсимских гор, в долине Базаиха. Именно здесь" +
                " находится рекреационная зона, граничащая с заповедником «Столбы».\n" +
                "\n" +
                "Удивительный природный ландшафт, развитая инфраструктура, высококлассный сервис, широкий спектр предлагаемых услуг и удобное месторасположение" +
                " делают Бобровый лог уникальным местом во всем Сибирском регионе. Комплекс работает ежедневно, предоставляя множество возможностей для современного " +
                "отдыха и массовых занятий спортом.", "Сибирская ул., 92, Красноярск, Красноярский край, 660071"));
    }

    public void testData() {
        ArrayList<Sight> sights = selectAll();
        Log.e("data_test", sights.get(0).getName());
    }
}
