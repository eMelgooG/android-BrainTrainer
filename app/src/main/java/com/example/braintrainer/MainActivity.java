package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

GridLayout answers;
TextView couuntdown, scoreboard, ecuation, verifier;
CountDownTimer timer;
    Button playAgain;

int correctAnswers = 0;
int answer;
int rAns;
int questions = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answers = findViewById(R.id.answers);
        couuntdown = findViewById(R.id.countDownTV);
        scoreboard = findViewById(R.id.scoreboardTV);
        ecuation = findViewById(R.id.ecuationTV);
        verifier = findViewById(R.id.verifierTV);
        playAgain = findViewById(R.id.playAgain);


    }

    public void generateEcuation() {
        Random r = new Random();
        int randomInt1 = r.nextInt(50)+ 1;
        int randomInt2 = r.nextInt(50) + 1;
        answer = randomInt1+randomInt2;
        ecuation.setText("" + randomInt1 + " + " + randomInt2);

        rAns = r.nextInt(4) + 1;

        for (int i = 0; i < answers.getChildCount();i++) {
            TextView child = (TextView) answers.getChildAt(i);

            if(child.getTag().toString().equals(Integer.toString(rAns))){
                child.setText(""+ answer);
            } else {
                int r3 = r.nextInt(100) + 1;
                child.setText("" + r3);
            }
        }
    }
private void updateScoreBoard() {
    questions++;
    scoreboard.setText("" + correctAnswers + "/" + questions);
}

    public void chooseAnswer(View view) {
        if (view.getTag().equals(Integer.toString(rAns))) {
            correctAnswers++;
            verifier.setText("Correct!");
        } else {
            verifier.setText("Wrong! :(");
        }
  updateScoreBoard();
    generateEcuation();
    }
    public void startGame(View view) {
        view.setVisibility(View.GONE);
        answers.setVisibility(View.VISIBLE);
        couuntdown.setVisibility(View.VISIBLE);
        scoreboard.setVisibility(View.VISIBLE);
        ecuation.setVisibility(View.VISIBLE);

        startTimer();
        scoreboard.setText("" + correctAnswers + "/" + questions);
        generateEcuation();


    }

    public void startTimer() {
        timer = new CountDownTimer(30000+1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            couuntdown.setText(millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {
                verifier.setText("Game finished");
                verifier.setText("\nScore: " + correctAnswers + "/ " + questions);
                for (int i = 0; i < answers.getChildCount();i++) {
                    TextView child = (TextView) answers.getChildAt(i);
                child.setClickable(false);
                }

                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void playAgain(View view) {
        correctAnswers = 0;
        questions = 0;
        for (int i = 0; i < answers.getChildCount();i++) {
            TextView child = (TextView) answers.getChildAt(i);
            child.setClickable(true);
        }
        verifier.setText("");
        view.setVisibility(View.INVISIBLE);

        startTimer();
        scoreboard.setText("" + correctAnswers + "/" + questions);
        generateEcuation();
    }
}
