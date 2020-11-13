package com.example.makarongames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizJanruSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_janru_select);

        //QuizHowTo画面へ遷移
        Button nextButton1 = (Button)findViewById(R.id.HowTo);
        nextButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QuizJanruSelect.this,QuizHowTo.class);
                startActivity(intent);
            }
        });

        //1つ目のジャンルのスタート画面へ遷移
        Button nextButton2 = (Button)findViewById(R.id.janruSelectBtn1);
        nextButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QuizJanruSelect.this,QuizStart.class);
                startActivity(intent);
            }
        });

        //2つ目のジャンルのスタート画面へ遷移
        Button nextButton3 = (Button)findViewById(R.id.janruSelectBtn2);
        nextButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QuizJanruSelect.this,QuizStart.class);
                startActivity(intent);
            }
        });

        //2つ目のジャンルのスタート画面へ遷移
        Button nextButton4 = (Button)findViewById(R.id.janruSelectBtn3);
        nextButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QuizJanruSelect.this,QuizStart.class);
                startActivity(intent);
            }
        });



    }
}