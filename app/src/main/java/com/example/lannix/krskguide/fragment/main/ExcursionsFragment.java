package com.example.lannix.krskguide.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lannix.krskguide.R;
import com.example.lannix.krskguide.activity.main.MainActivityInterface;
import com.example.lannix.krskguide.database.excursion_db.Excursion;
import com.example.lannix.krskguide.database.sight_db.Sight;
import com.example.lannix.krskguide.recyclerView.excursions_rv.ExcursionsVerRVAdapter;

import java.util.ArrayList;

import static com.example.lannix.krskguide.activity.main.MainActivity.DB_SIGHTS;


public class ExcursionsFragment extends Fragment {

    private MainActivityInterface fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_excursions, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.excursionsRecyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ExcursionsVerRVAdapter excursionsVerRVAdapter = new ExcursionsVerRVAdapter(getData(), getActivity());
        recyclerView.setAdapter(excursionsVerRVAdapter);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityInterface) {
            fragment = (MainActivityInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private ArrayList<Excursion> getData() {
        ArrayList<Excursion> excursions = new ArrayList<>();

        ArrayList<Integer> sights = new ArrayList<>();
        ArrayList<Sight> db_sights = DB_SIGHTS.selectAll();
        for (int i = 0; i < 3; i++) {
            sights.add(db_sights.get(i).getId());
        }

        Excursion excursion = new Excursion(0, "Обзорная экскурсия", "Эта обзорная экскурсия покажет " +
                "вам самые интересные места в Красноярске, которые туристу стоит посмотреть с самого начала", "", sights);
        excursions.add(excursion);
        return excursions;
    }
}