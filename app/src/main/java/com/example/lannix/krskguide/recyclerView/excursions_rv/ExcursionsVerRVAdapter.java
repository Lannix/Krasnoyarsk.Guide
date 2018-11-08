package com.example.lannix.krskguide.recyclerView.excursions_rv;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lannix.krskguide.R;
import com.example.lannix.krskguide.activity.description.DescriptionArticleActivity;
import com.example.lannix.krskguide.database.excursion_db.Excursion;
import com.example.lannix.krskguide.recyclerView.horizontal.HorRVAdapter;
import com.example.lannix.krskguide.recyclerView.horizontal.SimpleCardObject;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.example.lannix.krskguide.activity.main.MainActivity.DB_SIGHTS;
import static com.example.lannix.krskguide.fragment.main.ArticlesFragment.DATA_ID_TEG;

public class ExcursionsVerRVAdapter extends RecyclerView.Adapter<ExcursionsVerRVAdapter.PersonViewHolder> {

    private FragmentActivity fragmentActivity;
    private ArrayList<Excursion> excursions;

    public ExcursionsVerRVAdapter(ArrayList<Excursion> excursions, FragmentActivity fragmentActivity) {
        this.excursions = excursions;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.excursion_card, viewGroup, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExcursionsVerRVAdapter.PersonViewHolder personViewHolder, int i) {
        personViewHolder.getTitleTextView().setText(excursions.get(i).getTitle());
        personViewHolder.getExcursionDescriptionTextView().setText(excursions.get(i).getShortDescription());

        final RecyclerView recyclerView = personViewHolder.getExcursionSightsRecyclerView();
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<SimpleCardObject> cards = new ArrayList<>();
        for (Integer integer : excursions.get(i).getSights()) {
            cards.add(new SimpleCardObject(DB_SIGHTS.select(integer)));
        }

        HorRVAdapter horRVAdapter = new HorRVAdapter(cards, fragmentActivity) {
            @Override
            public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
                super.onBindViewHolder(personViewHolder, i);
                personViewHolder.getCardView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int itemPosition = recyclerView.getChildLayoutPosition(v);
                        ArrayList<SimpleCardObject> cards = getCards();

                        Intent intent = new Intent(fragmentActivity, DescriptionArticleActivity.class);
                        intent.putExtra(DATA_ID_TEG, cards.get(itemPosition).getDataBaseId());
                        fragmentActivity.startActivityForResult(intent, RESULT_OK);
                    }
                });
            }
        };

        personViewHolder.getGoToMapExcursionButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fragmentActivity, "Эта функция пока не готова", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(horRVAdapter);
    }

    @Override
    public int getItemCount() {
        return excursions.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView titleTextView;
        private TextView excursionDescriptionTextView;
        private RecyclerView excursionSightsRecyclerView;
        private Button goToMapExcursionButton;

        PersonViewHolder(final View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.sightsCardView);
            titleTextView = itemView.findViewById(R.id.excursionTitleTextView);
            excursionDescriptionTextView = itemView.findViewById(R.id.excursionDescriptionTextView);
            excursionSightsRecyclerView = itemView.findViewById(R.id.excursionSightsRecyclerView);
            goToMapExcursionButton = itemView.findViewById(R.id.goToMapExcursionButton);
        }

        public CardView getCardView() {
            return cardView;
        }

        public void setCardView(CardView cardView) {
            this.cardView = cardView;
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public void setTitleTextView(TextView titleTextView) {
            this.titleTextView = titleTextView;
        }

        public RecyclerView getExcursionSightsRecyclerView() {
            return excursionSightsRecyclerView;
        }

        public void setExcursionSightsRecyclerView(RecyclerView excursionSightsRecyclerView) {
            this.excursionSightsRecyclerView = excursionSightsRecyclerView;
        }

        public TextView getExcursionDescriptionTextView() {
            return excursionDescriptionTextView;
        }

        public void setExcursionDescriptionTextView(TextView excursionDescriptionTextView) {
            this.excursionDescriptionTextView = excursionDescriptionTextView;
        }

        public Button getGoToMapExcursionButton() {
            return goToMapExcursionButton;
        }

        public void setGoToMapExcursionButton(Button goToMapExcursionButton) {
            this.goToMapExcursionButton = goToMapExcursionButton;
        }
    }

    public ArrayList<Excursion> getExcursions() {
        return excursions;
    }
}
