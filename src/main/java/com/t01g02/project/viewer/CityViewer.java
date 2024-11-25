package com.t01g02.project.viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.Tile;

import java.io.IOException;

public class CityViewer {
    private final CityModel city;
    private final Screen screen;
    private final BasicTextImage cityImage;
    //private GameTimer timer;

    public CityViewer(CityModel city, Screen screen) {
        this.city = city;
        this.screen = screen;
        this.cityImage = new BasicTextImage(500, 250);

    }
    public void initializeCityImage() {
        for (int y = 0; y < city.getHeight(); y++) {
            for (int x = 0; x < city.getWidth(); x++) {
                // Set a basic tile background color to 'empty' for now
                TextCharacter defaultChar = new TextCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#FFE1EA"));
                cityImage.setCharacterAt(new TerminalPosition(x, y), defaultChar);
            }
        }
        for (int y = 0; y < city.getHeight(); y++) {
            for (int x = 0; x < city.getWidth(); x++) {
                Tile tile = city.getTile(x, y);
                if (tile != null) {
                    char displayChar = tile.getType() == Tile.Type.ROAD ? '█' : ' ';
                    TextCharacter textChar = new TextCharacter(displayChar, tile.getColor(), TextColor.Factory.fromString("#FFE1EA"));
                    cityImage.setCharacterAt(new TerminalPosition(x, y), textChar);
                }
            }
        }
    }

    public void draw() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        //graphics.setBackgroundColor(TextColor.Factory.fromString("#FFE1EA"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows()), ' ');

        graphics.drawImage(new TerminalPosition(0, 0), cityImage);

    }
}


