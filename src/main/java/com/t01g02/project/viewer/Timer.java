package com.t01g02.project.viewer;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Timer {
    private Screen screen;

    public void drawTimer(Screen screen) throws IOException  {

        TextColor timerBackgroundColor = TextColor.ANSI.CYAN;  // Timer background
        TextColor timerTextColor = TextColor.ANSI.WHITE;      // Timer text color
        int timerWidth = 15;  // Width of the timer box
        int timerHeight = 3;  // Height of the timer box
        int timerX = 2;       // X-coordinate of the timer
        int timerY = 1;       // Y-coordinate of the timer

        // Create a TextGraphics object to draw the timer
        TextGraphics textGraphics = screen.newTextGraphics();

        // Timer state
        long startTime = System.currentTimeMillis();

        // Game loop
        try {
            while (true) {
                // Calculate elapsed time
                long elapsedTime = System.currentTimeMillis() - startTime;
                int minutes = (int) (elapsedTime / 60000);
                int seconds = (int) (elapsedTime / 1000) % 60;
                // Format the timer

                String timerText = String.format("⏱️ %02d:%02d", minutes, seconds);

                // Draw the timer background
                textGraphics.setBackgroundColor(timerBackgroundColor);
                textGraphics.setForegroundColor(timerTextColor);
                for (int x = 0; x < timerWidth; x++) {
                    for (int y = 0; y < timerHeight; y++) {
                        textGraphics.setCharacter(timerX + x, timerY + y, ' ');
                    }
                }

                // Draw the timer text
                textGraphics.putString(timerX + 2, timerY + 1, timerText);

                // Render the rest of your game here (example: draw player '@')
                screen.setCharacter(20, 10, new TextCharacter('@', TextColor.ANSI.YELLOW, TextColor.ANSI.BLACK));

                // Refresh the screen to apply changes
                screen.refresh();

                // Simulate game update delay (adjust frame rate)
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Cleanup and close the screen
            screen.stopScreen();
        }
    }
}

