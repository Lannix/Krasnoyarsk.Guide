package com.example.lannix.krskguide;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lannix.krskguide.recyclerView.CardObject;
import com.example.lannix.krskguide.recyclerView.RVAdapter;

import java.util.ArrayList;


public class FirstFragment extends Fragment {
    FragmentM fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.aboutCityRecyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        RVAdapter rvAdapter = new RVAdapter(getData(), getContext());
        recyclerView.setAdapter(rvAdapter);
        return view;
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

    private ArrayList<CardObject> getData() {
        //Test Data
        ArrayList<CardObject> cardObjects = new ArrayList<>();
        cardObjects.add(new CardObject("Название", "Описание", R.drawable.bobr_log));
        cardObjects.add(new CardObject("Название", "Описание", R.drawable.bobr_log));
        cardObjects.add(new CardObject("Название", "Описание", R.drawable.bobr_log));
        return cardObjects;
    }
}
