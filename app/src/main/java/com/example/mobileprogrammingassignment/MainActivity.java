package com.example.mobileprogrammingassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    Button  butQuiz, butLout, butCer, butRead;

    FloatingActionButton share, butQ, butH;

    GoogleSignInOptions gso;

    GoogleSignInClient gsc;

    TextView name,email;

    ProgressBar ProgressB;

    DatabaseReference progressRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        share = findViewById(R.id.share);
        butQuiz = findViewById(R.id.Question1);
        butLout = findViewById(R.id.logOut);
        butCer = findViewById(R.id.Certificatebtn);
        butQ = findViewById(R.id.btnQuiz);
        butH = findViewById(R.id.home);
        butRead = findViewById(R.id.result);
        ProgressB = findViewById(R.id.progressBar);


        share.setOnClickListener(new View.OnClickListener() {
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

        butQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuizActivity();
            }
        });

        butLout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        butCer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCerActivity();
            }
        });

        butQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuizActivity();
            }
        });

        butH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenHomeActivity();
            }
        });

        butRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResult();
            }
        });

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        progressRef = FirebaseDatabase.getInstance().getReference().child("progress");
        progressRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Integer quizProgress1 = snapshot.child("quiz1").getValue(Integer.class);
                Integer quizProgress2 = snapshot.child("quiz2").getValue(Integer.class);
                Integer quizProgress3 = snapshot.child("quiz3").getValue(Integer.class);
                Integer quizProgress4 = snapshot.child("quiz4").getValue(Integer.class);

                // Calculate the overall progress
                //int overallProgress = (quizProgress + cerProgress + resultProgress + readProgress) / 4;
                int overallProgress = ((quizProgress1 != null ? quizProgress1.intValue() : 0) + (quizProgress2 != null ? quizProgress2.intValue() : 0) + (quizProgress3 != null ? quizProgress3.intValue() : 0) + (quizProgress4 != null ? quizProgress4.intValue() : 0));
                // Update the progress bar's progress value and visibility
                ProgressB.setProgress(overallProgress);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    protected void onPause() {
        super.onPause();
        startService(new Intent(this, Notification.class));
    }

    public void openCerActivity(){
        Intent intent = new Intent(this, Certificate.class);
        startActivity(intent);
    }

    public void openQuizActivity(){

        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }

    public void openLoutActivity(){

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void OpenHomeActivity(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openResult(){

        Intent intent = new Intent(this, Result.class);
        startActivity(intent);
    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }

}