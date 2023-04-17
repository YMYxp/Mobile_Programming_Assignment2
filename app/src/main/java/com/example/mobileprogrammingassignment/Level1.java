package com.example.mobileprogrammingassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Level1 extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button ansBut1, ansBut2, ansBut3;

    private String rightAns;
    private int rightAnsCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 4;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            // {"Question", "Right Answer", "Choice1", Choice2}
            {"___ is a general term of impaired ability to think,remember adn other", "Dementia", "Diabetes", "Autism"},
            {"Dementia affects the ability of___", "Thinking", "Eating", "Sleeping"},
            {"Dementia mostly affects ___", "Old Adult", "Teenagers", "Children"},
            {"At least 65 years of age, estimate ___ million adult with Dementia", "5", "300", "1"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);


        countLabel = (TextView) findViewById(R.id.LV1Page);
        questionLabel = (TextView) findViewById(R.id.LV1Question);
        ansBut1 = (Button) findViewById(R.id.LV1But1);
        ansBut2 = (Button) findViewById(R.id.Lv1But2);
        ansBut3 = (Button) findViewById(R.id.LV1But3);

        //Create Quiz Array from QuizData
        for (int i = 0; i < quizData.length; i++) {
            //Preparing Array
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);  //question
            tmpArray.add(quizData[i][1]);   //RightAns
            tmpArray.add(quizData[i][2]);   //choice1
            tmpArray.add(quizData[i][3]);   //choice2

            //Add tmrArray to quizArray
            quizArray.add(tmpArray);
        }

        showNextQuiz();

    }

    public void showNextQuiz(){
        // update quizCountLabel
        countLabel.setText("Q" + quizCount);

        //Generate random number between 0 and 3 (Quiz Array size minus 1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //Pick 1 quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);

        //Set question and right ans
        //Array format : {"Question", "Right Answer", "Choice1", Choice2}
        questionLabel.setText(quiz.get(0));
        rightAns = quiz.get(1);

        //Remove "Question" from quiz and shuffle choice
        quiz.remove(0);
        Collections.shuffle(quiz);

        //Set Choice
        ansBut1.setText(quiz.get(0));
        ansBut2.setText(quiz.get(1));
        ansBut3.setText(quiz.get(2));

        //Remove this quiz from quizArray
        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view){
        //Get Pushed Button
        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAns)){
            //Correct
            alertTitle = "Correct!!!";
            rightAnsCount++;
            // For example, in Quiz activity
            DatabaseReference progressRef = databaseReference.child("progress");
            progressRef.child("quiz1").setValue(25); // 25% progress


        }else {
            //Wrong
            alertTitle = "Wrong :(";
        }

        //CreateDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer : " + rightAns);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT){
                    //Show the Result
                    Intent intent = new Intent(getApplicationContext(), Result.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnsCount);
                    startActivity(intent);
                }else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();


    }

}