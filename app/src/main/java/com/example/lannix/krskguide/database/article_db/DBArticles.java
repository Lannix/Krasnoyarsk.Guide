package com.example.lannix.krskguide.database.article_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lannix.krskguide.R;

import java.util.ArrayList;

import static com.example.lannix.krskguide.database.article_db.DBArticlesHelper.COLUMN_LONG_DESCRIPTION;
import static com.example.lannix.krskguide.database.article_db.DBArticlesHelper.COLUMN_IMAGE_ID;
import static com.example.lannix.krskguide.database.article_db.DBArticlesHelper.COLUMN_SHORT_DESCRIPTION;
import static com.example.lannix.krskguide.database.article_db.DBArticlesHelper.COLUMN_TITLE;
import static com.example.lannix.krskguide.database.article_db.DBArticlesHelper.COLUMN_ID;
import static com.example.lannix.krskguide.database.article_db.DBArticlesHelper.TABLE_NAME;

public class DBArticles {

    private SQLiteDatabase mDataBase;
    private DBArticlesHelper mDBArticlesHelper;

    public DBArticles(Context context) {
        mDBArticlesHelper = new DBArticlesHelper(context);
        mDataBase = mDBArticlesHelper.getWritableDatabase();

        //Удаление бд
        mDBArticlesHelper.onUpgrade(mDataBase, 1, 1);

        //Добавление ненужной информации
        addStuffData();
    }

    public long insert(Article article) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, article.getTitle());
        cv.put(COLUMN_SHORT_DESCRIPTION, article.getShortDescription());
        cv.put(COLUMN_LONG_DESCRIPTION, article.getLongDescription());
        cv.put(COLUMN_IMAGE_ID, article.getImageIdsJson());
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Article article) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, article.getTitle());
        cv.put(COLUMN_SHORT_DESCRIPTION, article.getShortDescription());
        cv.put(COLUMN_LONG_DESCRIPTION, article.getLongDescription());
        cv.put(COLUMN_IMAGE_ID, article.getImageIdsJson());
        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(article.getId())});
    }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(int id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Article select(long id) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        mCursor.moveToFirst();
        return new Article(mCursor);
    }

    public ArrayList<Article> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Article> arr = new ArrayList<>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                arr.add(new Article(mCursor));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    public void addStuffData() {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.flag);
        insert(new Article(0, "Главное", "Красноя́рск — один из крупнейших городов России," +
                " центр Сибири.",
                "Красноя́рск — один из крупнейших городов России, культурный, " +
                "экономический, промышленный и образовательный центр Центральной и Восточной Сибири." +
                " Административный центр Красноярского края (второго по площади субъекта Российской Федерации)" +
                " и городского округа город Красноярск. Центр Восточно-Сибирского экономического района. Основанный " +
                "в 1628 году, является крупнейшим из старинных городов Сибири. Во времена «золотой лихорадки» " +
                "долгое время был крупным процветающим купеческим центром Сибири. Самый восточный город-миллионер" +
                " в России. Город расположен в центре России, на обоих берегах Енисея на стыке Западносибирской" +
                " равнины, Среднесибирского плоскогорья и Саянских гор в ущелье, образованном самыми северными" +
                " отрогами Восточного Саяна. Население города — 1 090 811 (2018). В Красноярской агломерации" +
                " проживает более полутора миллионов жителей.", images));
        images.clear();
        images.add(R.drawable.history);
        insert(new Article(0, "История", "В 1619 г. на берегах Енисея казаками " +
                "был основан Енисейск. Енисейские казаки продолжили экспансию России на восток и юг.",
                "В 1619 г. на берегах Енисея казаками " +
                "был основан Енисейск. Енисейские казаки продолжили экспансию России на восток и юг. Ими были основаны" +
                " остроги, а позднее города, Иркутск, Братск на Ангаре, Якутск на Лене, Верхнеудинск в Забайкалье. Если" +
                " на востоке экспансия шла относительно мирно и спокойно, то на юге жили довольно многочисленные и " +
                "воинствующие киргизы тюрского происхождения, татары, джунгары, которые сопротивлялись пришлым.\n" +
                "\n" +
                "Для защиты от их нападений был необходим острог южнее и выше по течению Енисея. Потому в 1624 г. " +
                "из Енисейска был отправлен Андрей Дубенский с казаками для выбора места будущего острога. А. Дубенский выбрал таковое в четырех днях конного пути от Енисейска на стрелке (высоком плоском месте) между устьем реки Изыр-су (Качи) и Енисеем на его левом берегу. Был составлен чертеж будущего острога и отправлен в г. Тобольск. Затем А. Дубенский был отправлен в Москву, - \"для защиты проекта\" острога. Царь Михаил Романов с думскими боярами назначил А. Дубенского \"городчиком\", т.е. строителем и главой будущего острога.\n" +
                "\n" +
                "В 1627 г. из Тобольска на 16 дощаниках, 5 лодках и 1 струге под началом воеводы А. Дубенского " +
                "вышла экспедиция числом 303 служивых людей (3 атамана, 5 пятидесятников, 24 десятника, 270 рядовых казаков). После долгого и трудного похода в средине июля 1628 г. они добрались до места и сразу же приступили к возведению временного защитного сооружения - дощатого городка из дощаников, на которых и приплыли казаки.\n" +
                "\n" +
                "Этот городок был укреплен надолбами - врытыми в землю столбами, соединенных свеху и снизу " +
                "толстыми жердями. Надолбы шли от места постройки острога (по горе 100 сажен - 213 м) до пристани." +
                " Городок сразу же был востребован, ибо уже 26 июля казаки отбивались от качинских татар. Дощатый " +
                "городок из-за своей ненадежности был лишь временным защитным сооружением. Потому позднее был" +
                " заготовлен сосновый лес в 2 днях пути вверх по реке, сплавлен вниз по течению и 6 августа " +
                "было начато сооружение стен, башен, амбаров и других служебных зданий острога. Строительство" +
                " острога было закончено быстро.\n" +
                "\n" +
                "Поскольку у киргизов не было артиллерии, то вопреки традиции был построен более легкий " +
                "вариант острога - \"строг стоячий\" с облегченными стенами из вертикально расположенных " +
                "стволов деревьев, а не рубленный с горизонтальным расположением бревен в 2 ряда. Острожные" +
                " стены имели высоту 3,4 м с 5 башнями высотой 18-20 м и шириной 8,5 х 8,5 м. Три угловые" +
                " башни для удобства обстрела нападающих выступали на 2-3 метра вперед. Сразу же вокруг " +
                "острога был вырыт ров глубиной 3 м и шириной 10 м с частоколом из острых кольев на дне.\n" +
                "\n" +
                "Из-за красоты места и красной почвы на высоком левом берегу (яру) Енисея А. Дубенский" +
                " назвал острог Красным вопреки обычной традиции давать название, используя топонимику," +
                " согласно которой острог должен был называться Нижнекачинским или просто Качинским. " +
                "Так, 6 августа (по старому стилю) 1628 года возник город на берегу Енисея у Красного" +
                " Яра - Красноярск. Вольно или невольно, но день начала сооружения острога оказался " +
                "очень знаменательным - в этот день в православной традиции отмечается праздник " +
                "Преображения Господа Иисуса Христа на горе Фаворской.", images));
        images.clear();
        images.add(R.drawable.ges);
        insert(new Article(0, "Климат", "Красноярск расположился в умеренном климатическом поясе," +
                " в самом сердце Евразийского континента.",
                "Красноярск расположился в умеренном климатическом поясе," +
                " в самом сердце Евразийского континента, потому климат в городе сухой и резко-континентальный " +
                "со значительными изменениями температуры дня и ночи, зимы и лета. До строительства красноярской" +
                " ГЭС, средняя температура зимы в городе была -35 градусов, сейчас близость Красноярского " +
                "водохранилища смягчила климат, сегодня средняя температура января -20 градусов по Цельсию." +
                " Июль в городе – достаточно жаркая пора.\n" +
                "\n" +
                "Красноярцы знают о том, что живут в достаточно грязном, с экологической точки зрения, " +
                "городе. Богатая лесами и реками Сибирь, сегодня превратилась в зону добычи черных металлов," +
                " захоронения ядерных отходов и добычи электричества. Климат изменен деятельностью человека.\n" +
                "\n" +
                "Климатические условия в регионе до 60-х годов 20 века были очень суровыми, зимой стояли " +
                "морозы до -50 градусов, а все лето царила знойная жара. Красноярская ГЭС изменила суровый " +
                "климат. Комсомольскую стройку века начали в 1956 году. Тогда послевоенная Россия, активно" +
                " развивалась, и электроэнергии, получаемой от маленьких электростанций, не хватало. Комсомольские" +
                " дружины прорубали леса, строили дорогу. Горная трасса, простирающаяся от Красноярска до города " +
                "Дивногорск, расположенного около ГЭС – работа комсомольцев. В 1972 году ГЭС была сдана в " +
                "эксплуатацию, она стала второй по мощности в России после Саяно-Шушенской, и седьмой по мощности " +
                "– в мире. Плотина полностью перегородила реку Енисей. Её высота – 124 метра, а ширина 1,65 м." +
                " Соответственно, в верховьях река образовала огромное водохранилище.", images));
        images.clear();
    }

    public void testData() {
        ArrayList<Article> articles = selectAll();
        Log.e("data_test", articles.get(0).getTitle());
    }
}
