package com.example.lannix.krskguide.activity.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lannix.krskguide.fragment.main.FifthFragment;
import com.example.lannix.krskguide.fragment.main.ArticlesFragment;
import com.example.lannix.krskguide.fragment.main.FourthFragment;
import com.example.lannix.krskguide.activity.map.MainMap;
import com.example.lannix.krskguide.R;
import com.example.lannix.krskguide.fragment.main.SightsFragment;
import com.example.lannix.krskguide.fragment.main.ExcursionsFragment;
import com.example.lannix.krskguide.database.article_db.DBArticles;
import com.example.lannix.krskguide.database.sight_db.DBSights;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    ImageView mapImageView;
    ArticlesFragment articlesFragment = new ArticlesFragment();
    SightsFragment sightsFragment = new SightsFragment();
    ExcursionsFragment excursionsFragment = new ExcursionsFragment();
    FourthFragment fourthFragment = new FourthFragment();
    FifthFragment fifthFragment = new FifthFragment();
    Bundle bundleM1 = new Bundle();
    Bundle bundleM2 = new Bundle();
    Bundle bundleM3 = new Bundle();
    Bundle bundleM4 = new Bundle();
    Bundle bundleM5 = new Bundle();

    public static DBSights DB_SIGHTS;
    public static DBArticles DB_ARTICLES;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        articlesFragment.setArguments(bundleM1);
        sightsFragment.setArguments(bundleM2);
        excursionsFragment.setArguments(bundleM3);
        fourthFragment.setArguments(bundleM4);
        fifthFragment.setArguments(bundleM5);

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.firstPart, articlesFragment)
                .replace(R.id.secondPart, sightsFragment)
                .replace(R.id.thirdPart, excursionsFragment);
                //.replace(R.id.fourthPart, fourthFragment)
                //.replace(R.id.fifthPart, fifthFragment);
        fragmentTransaction.commit();


        mapImageView = findViewById(R.id.mapImageView);
        mapImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainMap.class));
            }
        });


        DB_SIGHTS = new DBSights(this);
        DB_ARTICLES = new DBArticles(this);
    }
}
