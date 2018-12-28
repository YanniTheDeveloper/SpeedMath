package cn.edu.zafu.ytd.speedmath;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    TextView level;
    TextView score;
    TextView quiz;
    ImageButton yes;
    ImageButton no;
    Game game;
    ProgressBar progressBar;
    int progress;
    int check;
    int i = 0, time, interval;
    private Handler mHandler ;
    CountDownTimer mCountDownTimer;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        check = 0;
        progress = 100;
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



        mCountDownTimer=new CountDownTimer(time,interval) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                if(progress==100 && i!=0 || game.getPlay()==0) return;
                progress = progress-2;
                progressBar.setProgress(progress);
                Log.e("here", "onTick: value: "+progress, null);
            }

            @Override
            public void onFinish() {
                //Do what you want
                if(progress==100 && i!=0) return;
                game.gameOver();
                i=0;
                progressBar.setProgress(100);
                //Intent i = new Intent(MainActivity.this, Home.class);
                //startActivity(i);
            }
        };





        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.checkAnswer(true);
                updateView();
                i=0;
                mCountDownTimer.start();
                }});
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.checkAnswer(false);
                updateView();
                i=0;
                mCountDownTimer.start();
            }});




    }



    private void updateView(){
        time = Math.abs(7 - game.getLevel())*1000;
        interval = time / 50;
        progress = 100;
        quiz.setText(game.getQuiz());
        score.setText(game.getScore()+"");
        level.setText(game.getLevel()+"");
    }
}
