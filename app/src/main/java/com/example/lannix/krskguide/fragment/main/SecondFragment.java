package com.example.lannix.krskguide.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lannix.krskguide.R;
import com.example.lannix.krskguide.activity.description.DescriptionArticleActivity;
import com.example.lannix.krskguide.activity.description.DescriptionSightActivity;
import com.example.lannix.krskguide.activity.main.MainActivityInterface;
import com.example.lannix.krskguide.database.article_db.Article;
import com.example.lannix.krskguide.database.sight_db.Sight;
import com.example.lannix.krskguide.recyclerView.horizontal.HorRVAdapter;
import com.example.lannix.krskguide.recyclerView.horizontal.SimpleCardObject;
import com.example.lannix.krskguide.recyclerView.vertical.CardObject;
import com.example.lannix.krskguide.recyclerView.vertical.VertRVAdapter;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.example.lannix.krskguide.activity.main.MainActivity.DB_ARTICLES;
import static com.example.lannix.krskguide.activity.main.MainActivity.DB_SIGHTS;
import static com.example.lannix.krskguide.fragment.main.FirstFragment.DATA_ID_TEG;


public class SecondFragment extends Fragment {

    private MainActivityInterface fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.sightsRecyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        HorRVAdapter horRVAdapter = new HorRVAdapter(getData(), getContext()){
            @Override
            public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
                super.onBindViewHolder(personViewHolder, i);
                personViewHolder.getCardView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int itemPosition = recyclerView.getChildLayoutPosition(v);
                        ArrayList<SimpleCardObject> cards = getCards();

                        Intent intent = new Intent(getContext(), DescriptionSightActivity.class);
                        intent.putExtra(DATA_ID_TEG, cards.get(itemPosition).getDataBaseId());
                        startActivityForResult(intent, RESULT_OK);
                    }
                });
            }
        };
        recyclerView.setAdapter(horRVAdapter);
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

    private ArrayList<SimpleCardObject> getData() {
        //Test Data
        ArrayList<SimpleCardObject> simpleCardObjects = new ArrayList<>();
        ArrayList<Sight> sights = DB_SIGHTS.selectAll();
        for (int i = 0; i < sights.size() & i < 5; i++) {
            simpleCardObjects.add(new SimpleCardObject(sights.get(i)));
        }
        return simpleCardObjects;
    }
}