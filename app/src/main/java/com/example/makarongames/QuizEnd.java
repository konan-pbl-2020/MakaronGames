package com.example.makarongames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_end);


        Button nextButton = (Button)findViewById(R.id.OneMoreTime);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QuizEnd.this,QuizStart.class);
                startActivity(intent);
            }
        });


        Button nextButton2 = (Button)findViewById(R.id.GoToTitle);
        nextButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QuizEnd.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}