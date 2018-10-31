package com.example.lannix.krskguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lannix.krskguide.R;


public class Regin extends Fragment {
    Button RegBnt;
    Button NewRegBut;
    EditText regName;
    EditText regEmal;
    EditText regPass;
    private com.example.lannix.krskguide.Fragment fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_regin, container, false);

        regName=view.findViewById(R.id.name);
        regEmal=view.findViewById(R.id.emailReg);
        regPass=view.findViewById(R.id.passwordReg);
        NewRegBut= view.findViewById(R.id.buttonCreate);
        NewRegBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(chekIt()) {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                //}
            }
        });
        RegBnt=(Button)view.findViewById(R.id.creatBTn);
        RegBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.fragmentReg();
            }
        });
        return view;
    }
    boolean chekIt(){
        return (regPass.length() > 8) && !(regEmal.equals("")) && !(regName.equals(""));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof com.example.lannix.krskguide.Fragment) {
            fragment = (com.example.lannix.krskguide.Fragment) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment1DataListener");
        }

    }

}

