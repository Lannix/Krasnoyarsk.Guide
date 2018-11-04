package com.example.lannix.krskguide;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import com.example.lannix.krskguide.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements Fragment  {
    Bundle bundle = new Bundle();
    private LogIn loginFragment=new LogIn();
    private Regin reginFragment=new Regin();

    int a =0;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginFragment.setArguments(bundle);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, loginFragment);
        // .addToBackStack();
        fragmentTransaction.commit();



    }


    @Override
    public void fragmentLogin() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

        android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.container);


        //bundle.putString("KEY",s);
        reginFragment.setArguments(bundle);


        fm.beginTransaction()
                .replace(R.id.container, reginFragment)
                .commit();

    }

    @Override
    public void fragmentReg() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();


        android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.container);



        loginFragment.setArguments(bundle);


        fm.beginTransaction()
                .replace(R.id.container, loginFragment)
                .commit();


    }
}


