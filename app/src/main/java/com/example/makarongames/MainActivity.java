package com.example.makarongames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //しりとり画面へ遷移
        Button nextButton = (Button)findViewById(R.id.b_siritori_start);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SiritoriStart.class);
                startActivity(intent);
            }
        });

        //クイズ画面へ遷移
        Button nextButton2 = (Button)findViewById(R.id.b_quiz_start);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuizStart.class);
                startActivity(intent);
            }
        });
    }
}