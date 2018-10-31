package com.example.lannix.krskguide;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lannix.krskguide.R;


public class LogIn extends Fragment {
    Button LogBTn;

    private com.example.lannix.krskguide.Fragment fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_log_in, container, false);
        LogBTn=(Button) view.findViewById(R.id.LogBTn);
        LogBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.fragmentLogin();
            }
        });
        return view;
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
