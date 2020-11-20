package com.example.makarongames;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuizPlay extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;
    private Button answerBtn5;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;//問題数

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData1[][] = {
            // {"都道府県名", "正解", "選択肢１", "選択肢２", "選択肢３"}
            {"マカロンの発祥はどこ？             ", "イタリア", "フランス", "アメリカ", "日本", "ベルギー"},
            {"パリ風マカロンの発祥地とされる菓子店の名前は？", "ラデュレ", "ゼルコヴァ", "リデュラ", "ラ・メール", "フィル・デュ・レイヌ・デゥ・ムート"},
            {"マカロンの間のガナッシュ部分の名前は？", "ピエ","ピュレ", "プル", "ピオレ", "ポル"},
            {"マカロンの上下の生地の名前は？　　　　", "コック", "シェフ", "ウエイター", "ホスト", "バリスタ"},
            {"現存しているマカロンの原型を作った菓子店の名前は？", "ダロワイヨ", "デイロレス", "ドダイドス", "ダイオウドウ", "デロリアス"},
    };

    String quizData2[][] = {
            // {"都道府県名", "正解", "選択肢１", "選択肢２", "選択肢３"}
            {"換字式暗号でないのは？", "スキュタレー暗号", "カエサル暗号", "シフト暗号", "シーザー暗号", "ヴィジュネル暗号"},
            {"ここは？", "13号館", "アイコモンズ", "18号館", "1号館", "123号館"},
            {"Aの次は？", "B","C", "D", "E", "F"},
            {"新田先生の名前は？", "直也", "賢哉", "尚哉", "直之", "修大"},
            {"13号館食堂の現在の営業終了時間は？", "13:30", "13:00", "14:30", "15:00", "18:00"},
    };

    String quizData3[][] = {
            // {"都道府県名", "正解", "選択肢１", "選択肢２", "選択肢３"}
            {"リンカーン大統領の大統領になる前の職業は？", "プロレスラー", "プロ野球選手", "タクシー運転手", "漁師", "宇宙飛行士"},
            {"ジンベイザメを英語で言うと？", "ホエールシャーク", "エレファントシャーク", "ダイナソーシャーク", "メガシャーク", "キングシャーク"},
            {"天空の城ラピュタのムスカ大佐の年齢は？", "28歳","35歳", "43歳", "23歳", "58歳"},
            {"ドジョウがするのは？", "おなら", "あくび", "まばたき", "くしゃみ", "ものまね"},
            {"鬼滅の刃炭次郎の名字は？", "竈", "鼈", "龜", "龝", "鼁"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_play);

        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);
        answerBtn5 = findViewById(R.id.answerBtn5);

        //ジャンル番号を取得
        int janruNum = getIntent().getIntExtra("JANRU_NUMBER", 0);

        // クイズデータquizDataからクイズ出題用のquizArrayを作成する
        for (int i = 0; i < quizData1.length; i++) {

            // 新しいArrayListを準備
            ArrayList<String> tmpArray = new ArrayList<>();

            if(janruNum == 1) {
                // クイズデータを追加
                tmpArray.add(quizData1[i][0]);  // 都道府県名
                tmpArray.add(quizData1[i][1]);  // 正解
                tmpArray.add(quizData1[i][2]);  // 選択肢１
                tmpArray.add(quizData1[i][3]);  // 選択肢２
                tmpArray.add(quizData1[i][4]);  // 選択肢３
                tmpArray.add(quizData1[i][5]);  // 選択肢4
            }else if(janruNum == 2){
                // クイズデータを追加
                tmpArray.add(quizData2[i][0]);  // 都道府県名
                tmpArray.add(quizData2[i][1]);  // 正解
                tmpArray.add(quizData2[i][2]);  // 選択肢１
                tmpArray.add(quizData2[i][3]);  // 選択肢２
                tmpArray.add(quizData2[i][4]);  // 選択肢３
                tmpArray.add(quizData2[i][5]);  // 選択肢4
            }else if(janruNum == 3){
                // クイズデータを追加
                tmpArray.add(quizData3[i][0]);  // 都道府県名
                tmpArray.add(quizData3[i][1]);  // 正解
                tmpArray.add(quizData3[i][2]);  // 選択肢１
                tmpArray.add(quizData3[i][3]);  // 選択肢２
                tmpArray.add(quizData3[i][4]);  // 選択肢３
                tmpArray.add(quizData3[i][5]);  // 選択肢4
            }
            // tmpArrayをquizArrayに追加する
            quizArray.add(tmpArray);
        }

        showNextQuiz();

    }
    public void showNextQuiz() {
        // クイズカウントラベルを更新
        countLabel.setText(quizCount + "問目");

        // ランダムな数字を取得
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // randomNumを使って、quizArrayからクイズを一つ取り出す
        ArrayList<String> quiz = quizArray.get(randomNum);

        // 問題文（都道府県名）を表示
        questionLabel.setText(quiz.get(0));

        // 正解をrightAnswerにセット
        rightAnswer = quiz.get(1);

        // クイズ配列から問題文を削除
        quiz.remove(0);

        // 正解と選択肢３つをシャッフル
        Collections.shuffle(quiz);

        // 回答ボタンに正解と選択肢３つを表示
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));
        answerBtn5.setText(quiz.get(4));

        // このクイズをquizArrayから削除
        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view) {

        // どの回答ボタンが押されたか
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;
        if (btnText.equals(rightAnswer)) {
            alertTitle = "正解!";
            rightAnswerCount++;
        } else {
            alertTitle = "不正解...";
        }

        // ダイアログを作成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);

        builder.setMessage("答え : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {
                    // 結果画面へ移動
                    Intent intent = new Intent(getApplicationContext(), QuizEnd.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);
                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

}