package com.example.lannix.krskguide.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lannix.krskguide.R;
import com.example.lannix.krskguide.activity.login.LoginActivityInterface;
import com.example.lannix.krskguide.activity.main.MainActivity;


public class LogIn extends Fragment {
    private Button LogBTn;
    private Button buttonLogin;

    private LoginActivityInterface loginActivityInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        LogBTn = (Button) view.findViewById(R.id.LogBTn);
        LogBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivityInterface.fragmentLogin();
            }
        });
        buttonLogin = view.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(chekIt()) {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                //}
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginActivityInterface) {
            loginActivityInterface = (LoginActivityInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment1DataListener");
        }

    }


}
