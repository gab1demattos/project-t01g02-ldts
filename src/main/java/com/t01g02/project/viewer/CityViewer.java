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
import com.t01g02.project.model.Position;
import com.t01g02.project.model.Tile;

import java.io.IOException;

public class CityViewer {
    private final CityModel city;
    private final Screen screen;
    private final BasicTextImage cityImage;
    private final Sprite party;
    private final Sprite house;
    private final Sprite tree;
    private final Sprite lighttree;
    private final Sprite yellowhouse;
    private final Sprite bluehouse;
    private final Sprite pinkhouse;

    //private GameTimer timer;

    public CityViewer(CityModel city, Screen screen) throws IOException {
        this.city = city;
        this.screen = screen;
        this.cityImage = new BasicTextImage(city.getWidth(), city.getHeight());
        this.party = new Sprite(screen, "src/main/resources/extras/party.png");
        this.house = new Sprite(screen, "src/main/resources/extras/house.png");
        this.tree = new Sprite(screen, "src/main/resources/extras/tree.png");
        this.lighttree = new Sprite(screen, "src/main/resources/extras/lighttree.png");
        this.yellowhouse = new Sprite(screen, "src/main/resources/extras/yellowhouse.png");
        this.bluehouse = new Sprite(screen, "src/main/resources/extras/bluehouse.png");
        this.pinkhouse = new Sprite(screen, "src/main/resources/extras/pinkhouse.png");
    }
    public void initializeCityImage() {
        for (int y = 0; y < city.getHeight(); y++) {
            for (int x = 0; x < city.getWidth(); x++) {
                TextCharacter defaultChar = new TextCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#8A9A5B"));
                cityImage.setCharacterAt(new TerminalPosition(x, y), defaultChar);
            }
        }
        for (int y = 0; y < city.getHeight(); y++) {
            for (int x = 0; x < city.getWidth(); x++) {
                Tile tile = city.getTile(x, y);
                if (tile != null) {
                    char displayChar = tile.getType() == Tile.Type.ROAD ? '█' : ' ';
                    TextCharacter textChar = new TextCharacter(displayChar, tile.getColor(), TextColor.Factory.fromString("#636363"));
                    cityImage.setCharacterAt(new TerminalPosition(x, y), textChar);
                }
            }
        }
        for (int y = 0; y < city.getHeight(); y++) {
            for (int x = 0; x < city.getWidth(); x++) {
                Tile tile = city.getTile(x, y);
                if (tile != null && tile.getType() == Tile.Type.PICKUP || tile != null && tile.getType() == Tile.Type.DROPOFF) {
                    char displayChar = '█';
                    TextCharacter textChar = new TextCharacter(displayChar, tile.getColor(), TextColor.Factory.fromString("#636363"));
                    cityImage.setCharacterAt(new TerminalPosition(x, y), textChar);
                }
            }
        }

    }

    public void draw() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows()), ' ');

        graphics.drawImage(new TerminalPosition(0, 0), cityImage);
        party.drawImage(new Position(275, 108));

        house.drawImage(new Position(34, 20));
        house.drawImage(new Position(91, 121));
        house.drawImage(new Position(279, 20));
        house.drawImage(new Position(164, 71));

        tree.drawImage(new Position(307, 20));
        lighttree.drawImage(new Position(310, 27));
        tree.drawImage(new Position(307, 34));

        tree.drawImage(new Position(26, 18));
        lighttree.drawImage(new Position(22, 27));
        tree.drawImage(new Position(24, 38));

        tree.drawImage(new Position(151, 73));
        tree.drawImage(new Position(155, 78));
        lighttree.drawImage(new Position(151, 83));
        tree.drawImage(new Position(155, 87));

        tree.drawImage(new Position(119, 129));
        lighttree.drawImage(new Position(117, 136));

        yellowhouse.drawImage(new Position(95, 71));
        yellowhouse.drawImage(new Position(222, 20));
        yellowhouse.drawImage(new Position(155, 121));

        bluehouse.drawImage(new Position(95, 20));
        bluehouse.drawImage(new Position(188, 121));

        pinkhouse.drawImage(new Position(160, 20));
        pinkhouse.drawImage(new Position(30, 121));
        pinkhouse.drawImage(new Position(222,121));

    }
}


