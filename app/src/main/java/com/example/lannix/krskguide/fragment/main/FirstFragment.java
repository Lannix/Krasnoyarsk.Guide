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
import com.example.lannix.krskguide.database.article_db.Article;
import com.example.lannix.krskguide.recyclerView.vertical.CardObject;
import com.example.lannix.krskguide.recyclerView.vertical.VertRVAdapter;

import java.util.ArrayList;

import static com.example.lannix.krskguide.activity.main.MainActivity.DB_ARTICLES;


public class FirstFragment extends Fragment {
    
    private MainActivityInterface fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.aboutCityRecyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        VertRVAdapter vertRvAdapter = new VertRVAdapter(getData(), getContext());
        recyclerView.setAdapter(vertRvAdapter);
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

    private ArrayList<CardObject> getData() {
        //Test Data
        ArrayList<CardObject> cardObjects = new ArrayList<>();
        ArrayList<Article> articles = DB_ARTICLES.selectAll();
        for (int i = 0; i < articles.size() & i < 3; i++) {
            cardObjects.add(new CardObject(articles.get(i)));
        }
        return cardObjects;
    }
}
