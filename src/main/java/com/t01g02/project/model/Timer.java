package com.t01g02.project.model;

public class Timer {
    private int countdownDuration;
    private int totalElapsedTime;
    private boolean isRunning;

    public Timer(int minutes, int seconds) {
        this.countdownDuration = (minutes * 60 + seconds) * 1000;
        this.totalElapsedTime = 0;
        this.isRunning = true;
    }

    public void update(int frameTime) {
        if (isRunning) {
            totalElapsedTime += frameTime;


            if (totalElapsedTime >= countdownDuration) {
                totalElapsedTime = countdownDuration;
                isRunning = false;
            }
        }
    }

    public String getFormattedTime() {
        int remainingTime = countdownDuration - totalElapsedTime;

        if (remainingTime < 0) remainingTime = 0;

        int minutes = (remainingTime / 1000) / 60;
        int seconds = (remainingTime / 1000) % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public boolean isTimeUp() {
        return totalElapsedTime >= countdownDuration;
    }

    public void resetTimer(int minutes, int seconds) {
        this.countdownDuration = (minutes * 60 + seconds) * 1000;
        this.totalElapsedTime = 0;
        this.isRunning = true;
    }
}
