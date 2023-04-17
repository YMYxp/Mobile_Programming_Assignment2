package com.example.mobileprogrammingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class readingCorner2 extends AppCompatActivity {

    Button btnBack, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_corner2);

        btnBack = findViewById(R.id.btnBack2);
        btnNext = findViewById(R.id.btnNext2);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBack();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNextQ2();
            }
        });

    }

    public void openBack(){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }

    public void openNextQ2(){
        Intent intent = new Intent(this, Level2.class);
        startActivity(intent);
    }

}