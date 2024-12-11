package com.t01g02.project.model;

import com.googlecode.lanterna.TextColor;

import java.util.List;


public class CityModel {
    private final int width;
    private final int height;
    private final Tile[][] map;


    public CityModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Tile[height][width];


    }

    public void initializeRoads() {

        TextColor roadColor = TextColor.Factory.fromString("#222222");

        List<Road> roads = List.of(
                new Road(new Position(1, 15), new Position(1, 249), Tile.Type.ROAD, roadColor),
                new Road(new Position(65, 15), new Position(65, 249), Tile.Type.ROAD, roadColor),
                new Road(new Position(130, 15), new Position(130, 180), Tile.Type.ROAD, roadColor),
                new Road(new Position(195, 15), new Position(195, 120), Tile.Type.ROAD, roadColor),
                new Road(new Position(195, 180), new Position(195, 249), Tile.Type.ROAD, roadColor),
                new Road(new Position(255, 15), new Position(255, 249), Tile.Type.ROAD, roadColor),
                new Road(new Position(313, 15), new Position(313, 249), Tile.Type.ROAD, roadColor),

                new Road(new Position(1, 225), new Position(499, 225), Tile.Type.ROAD, roadColor),
                new Road(new Position(1, 120), new Position(499, 120), Tile.Type.ROAD, roadColor),
                new Road(new Position(65, 170), new Position(255, 170), Tile.Type.ROAD, roadColor),
                new Road(new Position(1, 65), new Position(499, 65), Tile.Type.ROAD, roadColor)
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

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public Tile[][] getMap() {
        return map;
    }


}
