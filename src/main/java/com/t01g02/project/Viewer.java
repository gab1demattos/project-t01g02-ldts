package com.t01g02.project;

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

        screen.refresh();

    }
}


