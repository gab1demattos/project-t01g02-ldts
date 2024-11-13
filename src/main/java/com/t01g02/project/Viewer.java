package com.t01g02.project;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Viewer {
    private final City city;
    private final Screen screen;

    public Viewer(City city, Screen screen){
        this.city = city;
        this.screen = screen;

    }
    public void draw() throws IOException{
        screen.clear();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFE1EA"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows()), ' ');

        for (Road road : city.getRoads()){
            for (TerminalPosition pos : road.getPositions()) {
                graphics.putString(pos, String.valueOf(road.getSymbol()));
            }
        }
        screen.refresh();
    }
    //aqqui ter metodos de draw game, draw city, draw road
}
