package com.example.lannix.krskguide.fragment.map;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lannix.krskguide.activity.map.MainMapInterface;

import com.example.lannix.krskguide.R;
import com.example.lannix.krskguide.activity.description.DescriptionSightActivity;
import com.example.lannix.krskguide.database.sight_db.Sight;

import static android.app.Activity.RESULT_OK;
import static com.example.lannix.krskguide.activity.main.MainActivity.DB_SIGHTS;
import static com.example.lannix.krskguide.activity.map.MainMap.TAG;
import static com.example.lannix.krskguide.fragment.main.ArticlesFragment.DATA_ID_TEG;


public class InfoOfObjectsFragment extends Fragment {
    ImageView imageView;
    Button button;
    TextView textViewName;
    TextView textViewAddress;
    int id;

    private MainMapInterface fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_of_objects, container, false);

        imageView = view.findViewById(R.id.imageViewInfo);
        button = view.findViewById(R.id.buttonInfo);
        textViewAddress = view.findViewById(R.id.sightDescriptionTextView);
        textViewName = view.findViewById(R.id.sightTitleTextView);

        id = Integer.valueOf(getArguments().getString(TAG));
        Sight sight = DB_SIGHTS.select(id);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), sight.getDescription_images().get(0)));
        textViewName.setText(sight.getName());
        textViewAddress.setText(sight.getAddress());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), DescriptionSightActivity.class);
                mIntent.putExtra(DATA_ID_TEG, id);
                startActivityForResult(mIntent, RESULT_OK);
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainMapInterface) {
            fragment = (MainMapInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
}
