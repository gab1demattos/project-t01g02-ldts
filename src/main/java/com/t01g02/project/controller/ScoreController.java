package com.t01g02.project.controller;

import com.t01g02.project.model.Score;

public class ScoreController extends KittyObserver {
    private final Score score;
    private boolean hasStarBeenPickedUp = false;

    public ScoreController(Score score) {
        this.score = score;
    }

    public void incrementScore(int value) {
        int newScore = calculateScore(value);
        updateScore(newScore);
    }

    private int calculateScore(int value) {
        if (hasStarBeenPickedUp) {
            return score.getScore() + (value * 2);
        } else {
            return score.getScore() + value;
        }
    }

    @Override
    public int getScore() {
        int currentScore = score.getScore();
        if (currentScore < 0) {
            return 0;
        }
        return currentScore;
    }

    private void updateScore(int newScore) {
        score.setScore(newScore);
    }

    @Override
    void friendPickedUp() {
        if (shouldAddBonusForFriendPickup()) {
            updateScore(score.getScore() + 50);
        } else {
            updateScore(score.getScore() + 100);
        }
    }

    @Override
    void friendDroppedOff() {
        if (shouldAddBonusForFriendPickup()) {
            updateScore(score.getScore() + 50);
        } else {
            updateScore(score.getScore() + 100);
        }
    }

    private boolean shouldAddBonusForFriendPickup() {
        return !hasStarBeenPickedUp || score.getScore() == 0;
    }

    @Override
    void pickedStar() {
        int newScore = calculatePickedStarScore();
        updateScore(newScore);
        hasStarBeenPickedUp = true;
    }

    private int calculatePickedStarScore() {
        return score.getScore() * 2;
    }
}
