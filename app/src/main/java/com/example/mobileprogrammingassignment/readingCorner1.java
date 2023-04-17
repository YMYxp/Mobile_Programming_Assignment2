package com.example.mobileprogrammingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class readingCorner1 extends AppCompatActivity {

    Button btnBack, btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_corner1);

        btnBack = findViewById(R.id.btnBack1);
        btnNext = findViewById(R.id.btnNext1);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBack();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNextQ1();
            }
        });

    }

    public void openBack(){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }

    public void openNextQ1(){
        Intent intent = new Intent(this, Level1.class);
        startActivity(intent);
    }


}