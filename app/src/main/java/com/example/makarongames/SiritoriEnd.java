package com.example.makarongames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class SiritoriEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siritori_end);

        Button nextButton = (Button)findViewById(R.id.tweet);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AsyncTask<Void,Void,String> task = new AsyncTask<Void, Void, String>(){
                    @Override
                    protected String doInBackground(Void... params) {
                        String latestStatus = "○○さんのしりとりスコア：〇点";
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

}