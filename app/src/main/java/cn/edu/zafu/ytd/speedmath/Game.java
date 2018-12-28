package cn.edu.zafu.ytd.speedmath;

public class Game {
    int level;
    int score;
    int multiplier;
    int totalQA;
    int play;
    Quiz currentQuiz;

    public Game(){
        resetValues();
        currentQuiz = new Quiz();
    }
    public String getQuiz(){
        return currentQuiz.getQuestion(level);
    }
    public boolean checkAnswer(boolean answer){
        if(answer == currentQuiz.getAnswer()) {
            updateValue();
            return true;
        }
        resetValues();
        return false;
    }
    private void updateValue(){
        play = 1;
        score += multiplier;
        totalQA ++;
        if(totalQA == 5) {
            totalQA = 0;
            level++;
            multiplier *= 2;
        }

    }
    private void resetValues() {
        level = 1;
        score = 0;
        totalQA = 0;
        multiplier = 5;
        play = 0;
    }
    public void gameOver(){
       resetValues();
    }
    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public int getPlay() {
        return play;
    }
}
