package com.example.bpbhatta.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button btn = (Button) findViewById(R.id.btn_Again);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextView scoreTxtView = (TextView) findViewById(R.id.score);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
        ImageView img = (ImageView) findViewById(R.id.img1);
        TextView message = (TextView) findViewById(R.id.tv_Message);


        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        ratingBar.setRating(score);
        scoreTxtView.setText(String.valueOf(score));

        if (score == 0) {
            img.setImageResource(R.drawable.score_0);
        } else if (score == 1) {
            img.setImageResource(R.drawable.score_1);
        } else if (score == 2) {
            img.setImageResource(R.drawable.score_2);
        } else if (score == 3) {
            img.setImageResource(R.drawable.score_3);
        } else if (score == 4) {
            img.setImageResource(R.drawable.score_4);
        } else if (score == 5) {
            img.setImageResource(R.drawable.score_5);
        }
    }
}
