package com.example.lannix.krskguide.database.sight_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lannix.krskguide.R;

import java.util.ArrayList;

import static com.example.lannix.krskguide.database.sight_db.DBSightsOpenHelper.COLUMN_ADDRESS;
import static com.example.lannix.krskguide.database.sight_db.DBSightsOpenHelper.COLUMN_COORDINATE_LATITUDE;
import static com.example.lannix.krskguide.database.sight_db.DBSightsOpenHelper.COLUMN_COORDINATE_LONGITUDE;
import static com.example.lannix.krskguide.database.sight_db.DBSightsOpenHelper.COLUMN_DESCRIPTION_IMAGES;
import static com.example.lannix.krskguide.database.sight_db.DBSightsOpenHelper.COLUMN_DESCRIPTION_TEXT;
import static com.example.lannix.krskguide.database.sight_db.DBSightsOpenHelper.COLUMN_ID;
import static com.example.lannix.krskguide.database.sight_db.DBSightsOpenHelper.COLUMN_MAP_IMAGE_ID;
import static com.example.lannix.krskguide.database.sight_db.DBSightsOpenHelper.COLUMN_NAME;
import static com.example.lannix.krskguide.database.sight_db.DBSightsOpenHelper.TABLE_NAME;

public class DBSights {

    private SQLiteDatabase mDataBase;
    private DBSightsOpenHelper mDBSightsOpenHelper;

    public DBSights(Context context) {
        mDBSightsOpenHelper = new DBSightsOpenHelper(context);
        mDataBase = mDBSightsOpenHelper.getWritableDatabase();

        //Удаление бд
         mDBSightsOpenHelper.onUpgrade(mDataBase, 1, 1);

        //Добавление ненужной информации
        addStuffData();
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
        images.clear();
        images.add(R.drawable.siberian_rivers);
        insert(new Sight(0, 56.008035, 92.869761, "Реки Сибири", R.drawable.siberian_rivers_circle, images, "Фонтан «Реки Сибири» в " +
                "Красноярске находится на центральной лестнице, ведущей от Театра оперы и балета к набережной Енисея,  на улице Бограда.  Его открытие состоялось в 2005 году, до 2008 года " +
                "композиция дополнялась статуями. Он стал первым каскадным фонтаном в Сибирском федеральном округе.\n" +
                " \n" +
                "Это потрясающий результат совместной творческой работы группы талантливых людей: заслуженный архитектор России А. С. Демирханов и скульптор Константин Зинич – главные авторы " +
                "проекта. Константин Зинич является создателем могучей скульптуры батюшки Енисея и прекрасной Ангары. Изящные девушки – Тунгуска (олицетворение Подкаменной Тунгуски и Нижней Тунгуски)" +
                " и Хатанга – творение известного скульптора Андрея Кияницына, Базаиха и Кача – работы Алексея Ничепорчука, автор Бирюсы и Маны - Виктор Мосиелев.\n" +
                "Интересно, что при «рождении» прекрасных статуй скульпторам позировали красноярские девушки. Авторы создавали бронзовые фигуры именно с той, в ком видели образ определенной реки, её " +
                "характер.", "ул. Бограда, 21, Красноярск, Красноярский край, 660049"));
        images.clear();
        images.add(R.drawable.krasnoyarsk_riverbank);
        insert(new Sight(0, 56.012963, 92.898777, "Белая лошадь", R.drawable.krasnoyarsk_riverbank_cerkl, images, "Скульптурная композиция «Лошадь белая» была открыта в июне 2006 года в Красноярске в рамках празднования Дня города. \n" +
                "Установлена на берегу Енисея на «Стрелке», в месте считающимся историческим центром Красноярска. Это - памятник в честь русских первопроходцев в Сибири.\n" +
                "Согласно одной из легенд, именно здесь в 17-м веке впервые высадились казаки во главе с Андреем Дубенским.(можно увидеть закладной камень с крестом позади лошади). Воевода выбрался на берег Енисея на лошади, бросил на камень шашку, нагайку и папаху, сказал, что в этом месте построят город, а затем отправился осматривать " +
                "местность. Животное осталось отдыхать и поджидать своего хозяина. " +
                "Этот момент истории и увековечен в скульптуре.", "\n" +
                "\n" +
                "Красноярск, Красноярский край, 660049"));
    }

    public void testData() {
        ArrayList<Sight> sights = selectAll();
        Log.e("data_test", sights.get(0).getName());
    }
}
