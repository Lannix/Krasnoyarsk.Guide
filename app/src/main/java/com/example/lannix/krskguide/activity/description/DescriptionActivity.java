package com.example.lannix.krskguide.activity.description;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lannix.krskguide.R;
import com.example.lannix.krskguide.database.sight_db.Sight;

import static com.example.lannix.krskguide.activity.main.MainActivity.DB_SIGHTS;
import static com.example.lannix.krskguide.activity.map.MainMap.TAG;
public class DescriptionActivity extends AppCompatActivity {

    TextView textViewName;
    ImageView imageView;
    TextView textViewRar;
    TextView textViewMinDisc;
    TextView textViewDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        int id = Integer.valueOf(getIntent().getExtras().getString(TAG));

        imageView=findViewById(R.id.imageViewDescription);
        textViewName=findViewById(R.id.textViewDescName);
        textViewRar=findViewById(R.id.textViewDescRating);
        textViewMinDisc=findViewById(R.id.textViewDescLittle);
        textViewDesc=findViewById(R.id.textDescription);

        Sight sight = DB_SIGHTS.select(id);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), sight.getDescription_images().get(0)));
        textViewName.setText(sight.getName());
        //Тут работает на рандоме
        textViewRar.setText(String.valueOf((int)(Math.random()*50)/10));
        textViewMinDisc.setText(sight.getAddress());
        textViewDesc.setText(sight.getDescription_text());

    }
}
