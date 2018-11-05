package com.example.lannix.krskguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lannix.krskguide.database.DBSights;

public class MainActivity extends AppCompatActivity implements FragmentM {

    Button button;
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    FourthFragment fourthFragment = new FourthFragment();
    FifthFragment fifthFragment = new FifthFragment();
    Bundle bundleM1 = new Bundle();
    Bundle bundleM2 = new Bundle();
    Bundle bundleM3 = new Bundle();
    Bundle bundleM4 = new Bundle();
    Bundle bundleM5 = new Bundle();

    public static DBSights DB_SIGHTS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstFragment.setArguments(bundleM1);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.firstChare, firstFragment);
        // .addToBackStack();
        fragmentTransaction.commit();
        secondFragment.setArguments(bundleM2);
        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.secondChare, secondFragment);
        // .addToBackStack();
        fragmentTransaction2.commit();

        thirdFragment.setArguments(bundleM3);
        android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.thirdChare, thirdFragment);
        // .addToBackStack();
        fragmentTransaction3.commit();


        fourthFragment.setArguments(bundleM4);
        android.support.v4.app.FragmentTransaction fragmentTransaction4 = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fourthChare, fourthFragment);
        // .addToBackStack();
        fragmentTransaction4.commit();

        fifthFragment.setArguments(bundleM5);
        android.support.v4.app.FragmentTransaction fragmentTransaction5 = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fifthChare, fifthFragment);
        // .addToBackStack();
        fragmentTransaction5.commit();


        button = (Button) findViewById(R.id.GoToMap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainMap.class));
            }
        });


        DB_SIGHTS = new DBSights(this);
    }


}
