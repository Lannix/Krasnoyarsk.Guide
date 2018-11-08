package com.example.lannix.krskguide.activity.description;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lannix.krskguide.R;
import com.example.lannix.krskguide.database.article_db.Article;
import com.example.lannix.krskguide.database.article_db.DBArticles;

import static com.example.lannix.krskguide.activity.main.MainActivity.DB_ARTICLES;
import static com.example.lannix.krskguide.fragment.main.FirstFragment.DATA_ID_TEG;

public class DescriptionArticleActivity extends AppCompatActivity {

    private ImageView articleMainImageView;
    private TextView titleArticleTextView;
    private TextView descriptionArticleTextView;
    private Button backToMainActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_article);
        Intent intent = getIntent();
        int db_id = intent.getIntExtra(DATA_ID_TEG, R.drawable.bobr_log);
        Article article = DB_ARTICLES.select(db_id);

        articleMainImageView = findViewById(R.id.articleMainImageView);
        articleMainImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), article.getImageIds().get(0)));

        titleArticleTextView = findViewById(R.id.titleArticleTextView);
        titleArticleTextView.setText(article.getTitle());

        descriptionArticleTextView = findViewById(R.id.descriptionArticleTextView);
        descriptionArticleTextView.setText(article.getLongDescription());

        backToMainActivityButton = findViewById(R.id.backToMainActivityButton);
        backToMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
