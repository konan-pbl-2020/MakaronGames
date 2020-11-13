package com.example.makarongames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class QuizEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_end);

        TextView resultLabel = findViewById(R.id.resultLabel);
        //TextView totalScoreLabel = findViewById(R.id.totalScoreLabel);

        // 正解数を取得
        final int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        // トータルスコアの読み出し
        SharedPreferences prefs = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = prefs.getInt("totalScore", 0);

        // トータルスコアに今回のスコアを加算
        //totalScore += score;

        // TextViewに表示する
        resultLabel.setText(score + " / 5");
        //totalScoreLabel.setText("トータルスコア : " + totalScore);

        // トータルスコアを保存
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt("totalScore", totalScore);
//        editor.apply();

        //  結果のツイート
        Button nextButton = (Button)findViewById(R.id.tweet);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AsyncTask<Void,Void,String> task = new AsyncTask<Void, Void, String>(){
                    @Override
                    protected String doInBackground(Void... params) {
                        final EditText editText = findViewById(R.id.edit_text);
                        String playername = editText.getText().toString();
                        String latestStatus = playername+"さんのしりとりスコア："+score+"点";
                        Twitter twitter = TwitterFactory.getSingleton();
                        try {
                            twitter4j.Status status = twitter.updateStatus(latestStatus);
                        } catch (TwitterException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                task.execute();
            }
        });
    }

    public void returnTop(View view) {
        Intent intent = new Intent(getApplicationContext(), QuizStart.class);
        startActivity(intent);
    }

}