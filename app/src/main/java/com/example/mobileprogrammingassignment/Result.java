package com.example.mobileprogrammingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultLabel = (TextView) findViewById(R.id.resultLabel);
        TextView totalScoreLabel = (TextView) findViewById(R.id.totalScore);
        btnReturn = findViewById(R.id.resultReturn);


        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        SharedPreferences settings = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore", 0);
        totalScore += score;

        resultLabel.setText(score + " / 4");
        totalScoreLabel.setText("Total Score : " + totalScore);

        //Update total score
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("totalScore", totalScore);
        editor.commit();


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBtnReturn();
            }
        });

    }

    public void openBtnReturn(){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }
}