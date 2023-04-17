package com.example.mobileprogrammingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class readingCorner4 extends AppCompatActivity {

    Button btnBack, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_corner4);

        btnBack = findViewById(R.id.btnBack4);
        btnNext = findViewById(R.id.btnNext4);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBack();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNextQ4();
            }
        });
    }

    public void openBack(){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }

    public void openNextQ4(){
        Intent intent = new Intent(this, Level4.class);
        startActivity(intent);
    }
}