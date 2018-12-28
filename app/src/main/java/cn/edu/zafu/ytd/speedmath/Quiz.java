package cn.edu.zafu.ytd.speedmath;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class Quiz {
    private String question;
    private boolean answer;

    public String getQuestion(int level) {
        generateQuestion(level);
        return question;
    }
    private void generateQuestion(int level){
        level1(level);
       /* switch (level){
            case 1: level1();
                    break;
            case 2: level2();
                break;
            case 3: level3();
                break;
            case 4: level4();
                break;
            default: win();
                break;
        }*/
    }
    private void level1(int level){
        int a = randomNumber(0,100*level);
        int b = randomNumber(0,100*level);
        question = a + " + " + b + " = ";
        if(randomNumber(0,2)==1){
            question += a+b;
            answer = true;
        }else{
            int c = randomNumber(0,11-level);
            if(randomNumber(0,2)==1){
                c*=10;
            }
            if(randomNumber(0,2)==1){
                question += a+b-c;
            }else{
                question += a+b+c;
            }
            answer = false;
        }
        Log.e(TAG, "level1: answer"+getAnswer(), null);

    }
    private void level2(){

    }
    private void level3(){

    }
    private void level4(){

    }
    private void win(){

    }

    private int randomNumber(int min, int max){
        return (int)(Math.random() * max + min);
    }
    public boolean getAnswer(){
        return answer;
    }
}
