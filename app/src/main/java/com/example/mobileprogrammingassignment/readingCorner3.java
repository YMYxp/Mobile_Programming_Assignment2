package com.example.mobileprogrammingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class readingCorner3 extends AppCompatActivity {

    Button btnBack, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_corner3);

        btnBack = findViewById(R.id.btnBack3);
        btnNext = findViewById(R.id.btnNext3);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBack();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNextQ3();
            }
        });
    }

    public void openBack(){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }

    public void openNextQ3(){
        Intent intent = new Intent(this, Level3.class);
        startActivity(intent);
    }

}