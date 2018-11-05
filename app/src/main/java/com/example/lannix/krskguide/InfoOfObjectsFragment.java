package com.example.lannix.krskguide;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lannix.krskguide.database.Sight;

import static com.example.lannix.krskguide.MainActivity.DB_SIGHTS;
import static com.example.lannix.krskguide.MainMap.TAG;


public class InfoOfObjectsFragment extends Fragment {

    private com.example.lannix.krskguide.FragmentMgMap fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_info_of_objects, container, false);

        //int id = savedInstanceState.getInt(TAG);
        //Sight sight = DB_SIGHTS.select(id);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof com.example.lannix.krskguide.FragmentMgMap) {
            fragment = (com.example.lannix.krskguide.FragmentMgMap) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


}
