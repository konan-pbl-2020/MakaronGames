package com.example.makarongames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //最初の言葉をランダムで出す
            int count = 0;
            int a = new Random().nextInt(15);
            String firstWord = new String();

            InputStream is =null;
            BufferedReader br = null;
            String text ="";

            try {
                is=this.getAssets().open("Makaron_Siritori.txt");
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
            TextView textView1 =
                    (TextView) findViewById(R.id.text2);
            textView1.setText(firstWord);




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
}