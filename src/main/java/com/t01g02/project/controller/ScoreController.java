package com.t01g02.project.controller;


import com.t01g02.project.model.Score;

public class ScoreController extends KittyObserver {
    private Score score;


    public ScoreController(Score score) {
        this.score= score;
    }

    public void incrementScore(int value){
        score.setScore(score.getScore()+value);
    }

    public int getScore(){
        return score.getScore();
    }

    @Override
    void friendPickedUp() {
        score.setScore(score.getScore()+50);
        System.out.println(score.getScore());
    }

    @Override
    void friendDroppedOff() {
        score.setScore(score.getScore()+50);
        System.out.println(score.getScore());
    }

    @Override
    void pickedStar() {
        score.setScore(score.getScore()*2);
    }

}
