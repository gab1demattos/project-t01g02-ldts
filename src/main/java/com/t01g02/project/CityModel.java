package com.t01g02.project;

import com.googlecode.lanterna.TextColor;

import java.util.List;


public class CityModel {
    private final int width;
    private final int height;
    private final Tile[][] map;


    public CityModel(int width, int height){
        this.width = width;
        this.height = height;
        this.map = new Tile[height][width];


    }
    public void initializeRoads(){

        TextColor roadColor = TextColor.Factory.fromString("#888888");

        List<Road> roads = List.of(
                new Road(new Position(2, 15), new Position(400, 15), Tile.Type.ROAD, roadColor), // outside roads
                new Road(new Position(2, 15), new Position(2, 200), Tile.Type.ROAD, roadColor),
                new Road(new Position(2, 200), new Position(300, 200), Tile.Type.ROAD, roadColor),
                new Road(new Position(435, 70), new Position(435, 245), Tile.Type.ROAD, roadColor),
                new Road(new Position(380, 15), new Position(380, 70), Tile.Type.ROAD, roadColor),
                new Road(new Position(380, 70), new Position(440, 70), Tile.Type.ROAD, roadColor),
                new Road(new Position(300, 200), new Position(300, 245), Tile.Type.ROAD, roadColor),
                new Road(new Position(300, 221), new Position(440, 221), Tile.Type.ROAD, roadColor),
                new Road(new Position(55, 15), new Position(55, 60), Tile.Type.ROAD, roadColor),
                new Road(new Position(2, 60), new Position(210, 60), Tile.Type.ROAD, roadColor),
                new Road(new Position(70, 60), new Position(70, 200), Tile.Type.ROAD, roadColor),
                new Road(new Position(134, 60), new Position(134, 120), Tile.Type.ROAD, roadColor),
                new Road(new Position(70, 120), new Position(210, 120), Tile.Type.ROAD, roadColor),
                new Road(new Position(196, 15), new Position(196, 144), Tile.Type.ROAD, roadColor),
                new Road(new Position(197, 90), new Position(250, 90), Tile.Type.ROAD, roadColor),
                new Road(new Position(250, 90), new Position(250, 200), Tile.Type.ROAD, roadColor),
                new Road(new Position(250, 125), new Position(440, 125), Tile.Type.ROAD, roadColor),
                new Road(new Position(325, 15), new Position(325, 125), Tile.Type.ROAD, roadColor),
                new Road(new Position(375, 125), new Position(375, 245), Tile.Type.ROAD, roadColor),
                new Road(new Position(375, 180), new Position(440, 180), Tile.Type.ROAD, roadColor)
        );

        for (Road segment : roads) {
            Tile.fillLine(map, segment.getStart(), segment.getEnd(), segment.getType(), segment.getColor());
        }

        }


    public Tile getTile(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return map[y][x];
        }
        return null;
    }


    public int getWidth(){ return width; };

    public int getHeight() { return height; }

    public Tile[][] getMap() {
        return map;
    }



}
