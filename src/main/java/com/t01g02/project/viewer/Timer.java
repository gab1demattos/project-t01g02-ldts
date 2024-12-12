package com.t01g02.project.viewer;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

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
}
