package com.example.mobileprogrammingassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Quiz extends AppCompatActivity {


    Button bQuiz1, bQuiz2, bQuiz3, bQuiz4;

    FloatingActionButton bMainMenu1, butQ2, butS2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        bQuiz1 = findViewById(R.id.Quiz1);
        bQuiz2 = findViewById(R.id.Quiz2);
        bQuiz3 = findViewById(R.id.Quiz3);
        bQuiz4 = findViewById(R.id.Quiz4);
        bMainMenu1 = findViewById(R.id.home2);
        butQ2 = findViewById(R.id.btnQ2);
        butS2 = findViewById(R.id.share2);





        bMainMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        bQuiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openButton1Quiz();
            }
        });

        bQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openButton2Quiz();
            }
        });
        bQuiz3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openButton3Quiz();
            }
        });

        bQuiz4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openButton4Quiz();
            }
        });

        butQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuiz2();
            }
        });

        butS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Your Body Here";
                String shareSub = "Your Subject Here";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent,"Share Using"));
            }
        });


    }



    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openButton1Quiz(){
        Intent intent = new Intent(this, readingCorner1.class);
        startActivity(intent);
    }

    public void openButton2Quiz(){
        Intent intent = new Intent(this, readingCorner2.class);
        startActivity(intent);
    }

    public void openButton3Quiz(){
        Intent intent = new Intent(this, readingCorner3.class);
        startActivity(intent);
    }

    public void openButton4Quiz(){
        Intent intent = new Intent(this, readingCorner4.class);
        startActivity(intent);
    }

    public void openQuiz2(){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }
}
