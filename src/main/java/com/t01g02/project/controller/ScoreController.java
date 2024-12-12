package com.t01g02.project.controller;


import com.t01g02.project.model.Score;

public class ScoreController extends KittyObserver {
    private Score score;

    public ScoreController(Score score) {
        this.score=score;
    }


    @Override
    void pickedUp() {

    }

    @Override
    void droppedOff() {
    }

    @Override
    void pickedStar() {
    }

}
