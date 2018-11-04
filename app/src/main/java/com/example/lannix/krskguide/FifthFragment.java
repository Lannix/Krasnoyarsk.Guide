package com.example.lannix.krskguide;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lannix.krskguide.R;


public class FifthFragment extends Fragment {
    FragmentM fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_fifth, container, false);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentM) {
            fragment = (FragmentM) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


}
