package com.example.lannix.krskguide.recyclerView.horizontal;

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

import com.example.lannix.krskguide.R;

import java.util.ArrayList;

public class HorRVAdapter extends RecyclerView.Adapter<HorRVAdapter.PersonViewHolder> {

    private Context context;
    private ArrayList<SimpleCardObject> cards;

    public HorRVAdapter(ArrayList<SimpleCardObject> cards, Context context) {
        this.cards = cards;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_content_card, viewGroup, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorRVAdapter.PersonViewHolder personViewHolder, int i) {
        personViewHolder.getTitleTextView().setText(cards.get(i).getTitle());
        personViewHolder.getCardImageView().setImageBitmap(BitmapFactory.decodeResource(context.getResources(), cards.get(i).getImageId()));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView titleTextView;
        private ImageView cardImageView;

        PersonViewHolder(final View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.sightsCardView);
            titleTextView = itemView.findViewById(R.id.sightsTitleTextView);
            cardImageView = itemView.findViewById(R.id.sightCardImageView);
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

        public ImageView getCardImageView() {
            return cardImageView;
        }

        public void setCardImageView(ImageView cardImageView) {
            this.cardImageView = cardImageView;
        }
    }

    public ArrayList<SimpleCardObject> getCards() {
        return cards;
    }
}
