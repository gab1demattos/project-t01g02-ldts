package com.t01g02.project;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Timer;

public class Viewer {
    private final City city;
    private final Screen screen;

    public Viewer(City city, Screen screen) {
        this.city = city;
        this.screen = screen;
        //this.timerBox = new TimerBox(screen);

    }
    /*public void drawTimer(TextGraphics graphics, int remainingSeconds) {
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(0, 0, "Time Remaining: " + formatTime(remainingSeconds));  // Draw timer at top row
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }*/


    public void draw() throws IOException {
        screen.clear();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFE1EA"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows()), ' ');

        for (int y = 0; y < city.getHeight(); y++) {
            for (int x = 0; x < city.getWidth(); x++) {
                Tile tile = city.getTileAt(x, y);
                if (tile != null) {
                    graphics.setForegroundColor(tile.getColor());
                    char displayChar = tile.getType() == Tile.Type.ROAD ? '█' : ' ';
                    graphics.putString(new TerminalPosition(x, y), String.valueOf(displayChar));
                }
            }
        }
        graphics.setForegroundColor(TextColor.Factory.fromString("#5386E4"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(12, 9), "1");
        graphics.putString(new TerminalPosition(100, 19), "2");
        graphics.putString(new TerminalPosition(45, 45), "3");
        graphics.putString(new TerminalPosition(127, 52), "4");
        graphics.putString(new TerminalPosition(12, 45), "H");
        graphics.putString(new TerminalPosition(180, 13), "X");






        //drawTimer(graphics, remainingSeconds);  // Draw the timer
        /*Sprite sprite = new Sprite(screen, "hellokitty.png");
        sprite.scaleImage(150, 50);
        sprite.drawImage(1, 1);*/

        screen.refresh();

    }
}


