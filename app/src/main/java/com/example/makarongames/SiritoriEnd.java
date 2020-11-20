package com.example.makarongames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class SiritoriEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siritori_end);
        TextView resultLabel = findViewById(R.id.resultLabel);

        final int score = getIntent().getIntExtra("SCORE", 0);

        resultLabel.setText(String.valueOf(score));

        Button nextButton = (Button)findViewById(R.id.tweet);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AsyncTask<Void,Void,String> task = new AsyncTask<Void, Void, String>(){
                    @Override
                    protected String doInBackground(Void... params) {
                        final EditText editText = findViewById(R.id.edit_text);
                        String playername = editText.getText().toString();
                        String latestStatus = playername+"さんのクイズスコア："+score+"点";
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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}