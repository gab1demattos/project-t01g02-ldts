package com.t01g02.project.viewer;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.*;

import java.io.IOException;
import java.util.List;

public class CityViewer {
    private final CityModel city;
    private final Screen screen;
    private final BasicTextImage cityImage;
    private final Sprite party;
    private final DecorViewer decor;

    public CityViewer(CityModel city, Screen screen) throws IOException {
        this.city = city;
        this.screen = screen;
        this.decor = new DecorViewer(screen);
        this.cityImage = new BasicTextImage(city.getWidth(), city.getHeight());
        this.party = new Sprite(screen, "src/main/resources/extras/party.png");
    }

    public void initializeCityImage() {
        for (int y = 0; y < city.getHeight(); y++) {
            for (int x = 0; x < city.getWidth(); x++) {
                Tile tile = city.getTile(x, y);
                TextCharacter defaultChar = new TextCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#8A9A5B"));
                cityImage.setCharacterAt(new TerminalPosition(x, y), defaultChar);
                if (tile != null) {
                    char displayChar = tile.getType() == Tile.Type.ROAD ? '█' : ' ';
                    TextCharacter textChar = new TextCharacter(displayChar, tile.getColor(), TextColor.Factory.fromString("#636363"));
                    cityImage.setCharacterAt(new TerminalPosition(x, y), textChar);
                }
                if (tile != null && (tile.getType() == Tile.Type.PICKUP || tile.getType() == Tile.Type.DROPOFF)) {
                    char displayChar = '█';
                    TextCharacter textChar = new TextCharacter(displayChar, tile.getColor(), TextColor.Factory.fromString("#636363"));
                    cityImage.setCharacterAt(new TerminalPosition(x, y), textChar);
                }
            }
        }
    }

    public void drawingHousesAndTrees(Sprite sprite, List<Position> positions) {
        for (Position position : positions) {
            if (position.getX() >= 0 && position.getY() >= 0) {
                sprite.drawImage(position);
            }
        }
    }


    public void draw() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows()), ' ');

        graphics.drawImage(new TerminalPosition(0, 0), cityImage);
        party.drawImage(new Position(275, 108));

        decor.drawDecorations();


    }

    public BasicTextImage getCityImage() {
        return cityImage;
    }

    public DecorViewer getDecor() {
        return decor;
    }

    public Sprite getParty() {
        return party;
    }
}


