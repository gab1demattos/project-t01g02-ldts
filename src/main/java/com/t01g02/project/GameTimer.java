package com.t01g02.project;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private int remainingSeconds;
    private final Timer timer;

    public GameTimer(int minutes) {
        this.remainingSeconds = 300;  // Set countdown in seconds (5 minutes = 300 seconds)
        this.timer = new Timer();
    }

    public void start() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (remainingSeconds <= 0) {
                    System.out.println("Time's up!");
                    timer.cancel(); // Stop the timer when it reaches 0
                } else {
                    System.out.print("\rTime Remaining: " + formatTime(remainingSeconds));
                    remainingSeconds--;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);  // Schedule the task to run every second
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static void main(String[] args) {
        GameTimer timer = new GameTimer(5);  // Initialize a 5-minute timer
        timer.start();
    }


}