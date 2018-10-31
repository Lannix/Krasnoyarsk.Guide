package com.example.lannix.krskguide;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lannix.krskguide.R;


class Card {
    private String name;
    private String text;

    public Card(String name, String text){
        this.name = name;
        this.text = text;
    }

    public String getName() {
            return name;
        }
    public void setName(String name) {
            this.name = name;
        }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
