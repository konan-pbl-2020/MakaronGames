package com.example.makarongames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //QuizStart画面へ遷移
        setContentView(R.layout.activity_quiz_start);
        Button nextButton = (Button)findViewById(R.id.GameStart);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QuizStart.this,QuizPlay.class);
                startActivity(intent);
            }
        });

        //QuizHowTo画面へ遷移
        Button nextButton2 = (Button)findViewById(R.id.HowTo);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QuizStart.this,QuizHowTo.class);
                startActivity(intent);
            }
        });

    }
}