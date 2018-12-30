package cn.edu.zafu.ytd.speedmath;

import android.os.CountDownTimer;

public class MyCountDownTimer extends CountDownTimer {
    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {

    }
    public void endTimer(){
        super.cancel();
    }
}
