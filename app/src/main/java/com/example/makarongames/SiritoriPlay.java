package com.example.makarongames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.res.AssetManager;
import android.icu.text.CaseMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class SiritoriPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siritori_play);

        final boolean[] flag = {true};
        final int[] check = {0};
        int count=0;
        final int[] game_win = {0};
        final AssetManager as =getResources().getAssets();
        InputStream is=null;
        final int[] score={0};
        final boolean[] fond = {false};

        //最初の言葉をランダムで出す
        final int a = new Random().nextInt(15);
        String firstWord = new String();

        //InputStream is = null;
        BufferedReader br = null;
        String text = "";

        try {
            //is = this.getAssets().open("Makaron_Siritori.txt");
            //br = new BufferedReader(new InputStreamReader(is));
            is=as.open("Makaron_Siritori.txt");
            br=new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                firstWord = br.readLine();
                firstWord = toUpper(firstWord);
                firstWord = remove(firstWord);
                if (count == a) {
                    break;
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //最初の文字出力、ここまで


        //CPU側しりとり
        final TextView textView1 =
                (TextView) findViewById(R.id.siritoritext2);
        textView1.setText(firstWord);

        final TextView textView2 =
                (TextView) findViewById(R.id.siritoritext1);

        String data = new String();
        data = firstWord;
        String inputstr = new String();

        //ユーザ入力
        final EditText player = (EditText) findViewById(R.id.PlayerReturn);
        //ユーザ入力反映
        final TextView player_text = (TextView) findViewById(R.id.Playertext);
        //入力決定ボタン
        final Button player_ok = (Button) findViewById(R.id.Ok);


        //ボタンが押された際、前の言葉に続くかどうかを判定する
        final String[] finalData = {data};

        player_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (check[0] == 0) {
                    String inputstr = player.getText().toString();
                    toUpper(inputstr);
                    remove(inputstr);
                    //ンが語尾につくか
                    if(checkStr(inputstr)){
                        flag[0]=false;
                    }
                    if (!(finalData[0].charAt(finalData[0].length() - 1) == inputstr.charAt(0))) {
                        inputstr = "続く言葉を入力してください";
                        player_text.setText(inputstr);
                        check[0] = 1;
                    }
                    player_text.setText(inputstr);
                    textView2.setText("次の言葉は…");
                    InputStream is2 = null;
                    BufferedReader br2 = null;
                    try {
                        is2 = as.open("Makaron_Siritori.txt");
                        br2 = new BufferedReader(new InputStreamReader(is2));
                        String text2 = "";
                        while (br2.ready()) {
                            text2 = br2.readLine();
                            text2 = toUpper(text2);
                            text2 = remove(text2);
                            if (inputstr.charAt(inputstr.length() - 1) == text2.charAt(0)) {
                                textView1.setText(text2);
                                finalData[0]=text2;
                                fond[0]=true;
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    score[0]++;
                    if(!fond[0]){
                        textView2.setText("NO WORD...");
                        textView1.setText("+10Point!");
                        score[0]+=10;
                        game_win[0]=4;
                    }

                    check[0] = 1;
                }

                if(!flag[0]){
                    textView1.setText("あなたの負け！");
                    textView2.setText(" ");
                    game_win[0]=5;
                }

                if(game_win[0]==5) {
                    player_text.setText("ゲームオーバー\nOKを押してください");
                    player_ok.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Intent intent = new Intent(SiritoriPlay.this,SiritoriEnd.class);
                            startActivity(intent);
                        }
                        });
                }
                else if(game_win[0]==4){
                    player_text.setText("あなたの勝ち！\nOKを押してください");
                    player_ok.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Intent intent = new Intent(SiritoriPlay.this,SiritoriEnd.class);
                            startActivity(intent);
                        }
                    });
                }
                else{
                    check[0]=0;
                    fond[0]=false;
                }
            }
        });
    }

    //子文字を大文字に変換する
    private static String toUpper(String str) {
        str = str.replace("ャ","ヤ");
        str = str.replace("ュ","ユ");
        str = str.replace("ョ","ヨ");
        str = str.replace("ァ","ア");
        str = str.replace("ィ","イ");
        str = str.replace("ゥ","ウ");
        str = str.replace("ェ","エ");
        str = str.replace("ォ","オ");
        return str;
    }
    //"ー"を消去する
    private static String remove(String str2){
        if(str2.substring(str2.length()-1).equals("ー")){
            str2 = str2.substring(0,(str2.length()-1));
        }
        return str2;
    }
    //「ン」を判別する
    private static boolean checkStr(String check){
        boolean result=false;
        if(check.substring(check.length()-1).equals("ン")){
            return true;
        }
        return result;
    }
}