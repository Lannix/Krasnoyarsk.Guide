package com.lannix.krskguide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

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


