package com.example.lannix.krskguide.activity.description;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lannix.krskguide.R;
import com.example.lannix.krskguide.database.sight_db.Sight;

import static com.example.lannix.krskguide.activity.main.MainActivity.DB_SIGHTS;
import static com.example.lannix.krskguide.fragment.main.ArticlesFragment.DATA_ID_TEG;

public class DescriptionSightActivity extends AppCompatActivity {

    private TextView textViewName;
    private ImageView imageView;
    private TextView textViewRar;
    private TextView textViewMinDisc;
    private TextView textViewDesc;
    private Button backToMainActivityButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_sight);
        int id = getIntent().getExtras().getInt(DATA_ID_TEG);

        imageView = findViewById(R.id.imageViewDescription);
        textViewName = findViewById(R.id.textViewDescName);
        textViewRar = findViewById(R.id.textViewDescRating);
        textViewMinDisc = findViewById(R.id.textViewDescLittle);
        textViewDesc = findViewById(R.id.textDescription);
        backToMainActivityButton1 = findViewById(R.id.backToMainActivityButton1);

        Sight sight = DB_SIGHTS.select(id);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), sight.getDescription_images().get(0)));
        textViewName.setText(sight.getName());
        textViewMinDisc.setText(sight.getAddress());
        textViewDesc.setText(sight.getDescription_text());
        //Тут работает на рандоме
        textViewRar.setText(String.valueOf((int) (Math.random() * 50) / 10));

        backToMainActivityButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
