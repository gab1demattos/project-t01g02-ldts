package com.t01g02.project.viewer;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.Position;
import com.t01g02.project.model.Score;
import com.t01g02.project.model.Tile;

import java.io.IOException;
import java.util.List;

public class CityViewer {
    private final CityModel city;
    private final Screen screen;
    private final BasicTextImage cityImage;
    private final Sprite party, house, tree, lighttree, yellowhouse, bluehouse, pinkhouse; //flowers;
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
        // this.flowers = new Sprite(screen, "src/main/resources/extras/flowers.png");
    }
    public void initializeCityImage() {
        for (int y = 0; y < city.getHeight(); y++) {
            for (int x = 0; x < city.getWidth(); x++) {
                TextCharacter defaultChar = new TextCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#8A9A5B"));
                cityImage.setCharacterAt(new TerminalPosition(x, y), defaultChar);
            }
        }
        // Pink section for timer/score
        /*for (int y = city.getHeight()-15; y < city.getHeight(); y++) {
            for (int x = 0; x < city.getWidth(); x++){
                TextCharacter pinkChar;
                if (y==city.getHeight()-15){
                    pinkChar = new TextCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#B7868D"));
                }else{
                    pinkChar = new TextCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#FFF0F5"));
                }
                cityImage.setCharacterAt(new TerminalPosition(x, y), pinkChar);
            }
        }*/

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

    public void drawingHousesAndTrees(Sprite sprite, List<Position> positions) {
        for (Position position : positions) {
            sprite.drawImage(position);
        }
    }


    public void draw() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows()), ' ');

        graphics.drawImage(new TerminalPosition(0, 0), cityImage);
        party.drawImage(new Position(275, 108));

        drawingHousesAndTrees(house, city.getHousePositions());
        drawingHousesAndTrees(tree, city.getTreePositions());
        drawingHousesAndTrees(lighttree, city.getLighttreePositions());
        drawingHousesAndTrees(yellowhouse, city.getYellowHousePositions());
        drawingHousesAndTrees(bluehouse, city.getBlueHousePositions());
        drawingHousesAndTrees(pinkhouse, city.getPinkHousePositions());
        // drawingHousesAndTrees(flowers, city.getFlowersPosition());

    }
}


