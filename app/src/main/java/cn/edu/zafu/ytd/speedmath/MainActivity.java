package cn.edu.zafu.ytd.speedmath;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    TextView level;
    TextView score;
    TextView quiz;
    TextView scoreView;
    TextView hScoreView;
    Button playAgain;
    ImageButton yes;
    ImageButton no;
    Game game;
    ProgressBar progressBar;
    RelativeLayout scorePage;
    ConstraintLayout gamePage;

    int progress;
    int check;
    int hScore = 0, time, interval;
    private Handler mHandler ;
    MyCountDownTimer mCountDownTimer;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        check = 0;
        progress = 100;
        scoreView = findViewById(R.id.scoreView);
        hScoreView = findViewById(R.id.hScoreView);
        playAgain = findViewById(R.id.playAgain);
        scorePage = findViewById(R.id.scorePage);
        gamePage = findViewById(R.id.gamePage);

        level = findViewById(R.id.level);
        score = findViewById(R.id.score);
        quiz = findViewById(R.id.quiz);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        game = new Game();
        if(game!=null)updateView();
        progressBar.setProgress(100);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.startOver();
                updateView();
                gamePage.setVisibility(View.VISIBLE);
                scorePage.setVisibility(View.GONE);

            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesNo(true);
                }});
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesNo(false);
            }});

    }

    private void gameTimer(){
        mCountDownTimer=new MyCountDownTimer(time,interval) {

            @Override
            public void onTick(long millisUntilFinished) {
                if(game.getPlay()==0 )
                    super.cancel();
                progress = progress-1;
                progressBar.setProgress(progress);
                Log.e("here", "onTick: value: "+progress, null);
            }

            @Override
            public void onFinish() {
                //Do what you want
                if(game.getPlay()==0 || check == 1) super.cancel();
                Log.e("Error", "onFinish: "+progress, null);
                endGame();
            }

        };

    }
    private void yesNo(boolean answer){
        mCountDownTimer.endTimer();
        Log.e("yesNo", " score: "+game.getScore(), null);
        if(game.checkAnswer(answer)) {
            updateView();
            mCountDownTimer.start();
        }
        else {
            endGame();
        }

    }
    private void endGame(){
        scorePage.setVisibility(View.VISIBLE);
        gamePage.setVisibility(View.GONE);
        scoreView.setText(""+game.getScore());
        if(game.getScore()>hScore){
            hScore = game.getScore();
            hScoreView.setText(""+hScore);
        }
        game.gameOver();
    }

    private void updateView(){
        time = Math.abs(51 - (4*game.getFast()))*100;
        interval = time/100;
        progress = 100;
        gameTimer();
        quiz.setText(game.getQuiz());
        score.setText(game.getScore()+"");
        level.setText(game.getLevel()+"");
        progressBar.setProgress(progress);
    }
}
