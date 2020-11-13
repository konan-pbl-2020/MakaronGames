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
        setContentView(R.layout.activity_quiz_start);

        //ジャンル番号を取得
        final int janruNum = getIntent().getIntExtra("JANRU_NUMBER", 0);

        //QuizPlay画面へ遷移
        setContentView(R.layout.activity_quiz_start);
        Button nextButton = (Button)findViewById(R.id.GameStart);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QuizStart.this,QuizPlay.class);
                intent.putExtra("JANRU_NUMBER", janruNum);
                startActivity(intent);
            }
        });

    }
}