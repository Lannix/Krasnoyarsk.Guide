package com.example.lannix.krskguide.recyclerView.vertical;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lannix.krskguide.R;

import java.util.ArrayList;

public class VertRVAdapter extends RecyclerView.Adapter<VertRVAdapter.PersonViewHolder> {

    private Context context;
    private ArrayList<CardObject> cards;

    public VertRVAdapter(ArrayList<CardObject> cards, Context context) {
        this.cards = cards;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_card, viewGroup, false);
        PersonViewHolder personViewHolder = new PersonViewHolder(view);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
        personViewHolder.getTitleTextView().setText(cards.get(i).getTitle());
        personViewHolder.getMainTextView().setText(cards.get(i).getDescription());
        personViewHolder.getCardImageView().setImageBitmap(BitmapFactory.decodeResource(context.getResources(), cards.get(i).getImageId()));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView titleTextView;
        private TextView mainTextView;
        private ImageView cardImageView;

        PersonViewHolder(final View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.aboutCityCardView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            mainTextView = itemView.findViewById(R.id.mainTextView);
            cardImageView = itemView.findViewById(R.id.cardImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), titleTextView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
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

        public TextView getMainTextView() {
            return mainTextView;
        }

        public void setMainTextView(TextView mainTextView) {
            this.mainTextView = mainTextView;
        }

        public ImageView getCardImageView() {
            return cardImageView;
        }

        public void setCardImageView(ImageView cardImageView) {
            this.cardImageView = cardImageView;
        }
    }
}