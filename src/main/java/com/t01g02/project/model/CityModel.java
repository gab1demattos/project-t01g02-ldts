package com.t01g02.project.model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.viewer.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CityModel {
    private final int width;
    private final int height;
    private final Tile[][] map;
    private final List<Zone> zones;


    public CityModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Tile[height][width];
        this.zones = new ArrayList<>();
    }

    public void initializeRoads() {

        TextColor roadColor = TextColor.Factory.fromString("#222222");

        List<Road> roads = List.of(
                new Road(new Position(1, 1), new Position(1, 179), Tile.Type.ROAD, roadColor),
                new Road(new Position(65, 1), new Position(65, 179), Tile.Type.ROAD, roadColor),
                new Road(new Position(130, 1), new Position(130, 179), Tile.Type.ROAD, roadColor),
                new Road(new Position(195, 1), new Position(195, 120), Tile.Type.ROAD, roadColor),
                new Road(new Position(195, 179), new Position(195, 179), Tile.Type.ROAD, roadColor),
                new Road(new Position(255, 1), new Position(255, 179), Tile.Type.ROAD, roadColor),
                new Road(new Position(320, 1), new Position(320, 179), Tile.Type.ROAD, roadColor),

                new Road(new Position(1, 1), new Position(339, 1), Tile.Type.ROAD, roadColor),
                new Road(new Position(1, 155), new Position(339, 155), Tile.Type.ROAD, roadColor),
                new Road(new Position(65, 105), new Position(255, 105), Tile.Type.ROAD, roadColor),
                new Road(new Position(1, 54), new Position(339, 54), Tile.Type.ROAD, roadColor)
        );

        for (Road segment : roads) {
            Tile.fillLine(map, segment.getStart(), segment.getEnd(), segment.getType(), segment.getColor());
        }

    }

    public void initializeZones(){
        TextColor zonecolor = TextColor.Factory.fromString("#222222");

        zones.add( new Zone(new Position(165, 105), new Position(195, 105), "Kuromi", Tile.Type.PICKUP, zonecolor, CharacterModel.friends.get(0))); // done
        zones.add(new Zone(new Position(35, 54), new Position(65,  54), "Purin", Tile.Type.PICKUP, zonecolor, CharacterModel.friends.get(1)));     // done
        zones.add(new Zone(new Position(90, 155), new Position(120, 155), "MyMelody", Tile.Type.PICKUP, zonecolor, CharacterModel.friends.get(2)));     // done
        zones.add(new Zone(new Position(280, 54), new Position(310, 54), "Cinnamoroll", Tile.Type.PICKUP, zonecolor, CharacterModel.friends.get(3))); // done

        zones.add(new Zone(new Position(285, 155), new Position(315, 155), "Party", Tile.Type.DROPOFF, zonecolor, null));

        for (Zone zone : zones) {
            Tile.fillLine(map, zone.getStartposition(), zone.getEndposition(), zone.getType(), zone.getColor());
        }

    }

    private final List<Position> housePositions = List.of(
            new Position(34, 20),
            new Position(91, 121),
            new Position(279, 20),
            new Position(164, 71)
    );

    private final List<Position> treePositions = List.of(
            new Position(307, 20),
            new Position(307, 34),
            new Position(26, 18),
            new Position(24, 38),
            new Position(151, 73),
            new Position(119, 129),
            new Position(155, 78),
            new Position(155, 87)
    );

    private final List<Position> yellowHousePositions = List.of(
            new Position(95, 71),
            new Position(222, 20),
            new Position(155, 121)
    );

    private final List<Position> blueHousePositions = List.of(
            new Position(95, 20),
            new Position(188, 121),
            new Position(222, 71)
    );

    private final List<Position> pinkHousePositions = List.of(
            new Position(160, 20),
            new Position(30, 121),
            new Position(222,121)
    );

    private final List<Position> lighttreePositions = List.of(
            new Position(310, 27),
            new Position(22, 27),
            new Position(151, 83),
            new Position(117, 136)
    );

    /* private final List<Position> flowersPosition = List.of(
            new Position(30, 100),
            new Position(40, 110)
    ); */

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
    public List<Zone> getZones(){
        return zones;
    }

    public List<Position> getHousePositions() {
        return housePositions;
    }

    public List<Position> getBlueHousePositions() {
        return blueHousePositions;
    }

    public List<Position> getLighttreePositions() {
        return lighttreePositions;
    }

    public List<Position> getPinkHousePositions() {
        return pinkHousePositions;
    }

    public List<Position> getTreePositions() {
        return treePositions;
    }

    public List<Position> getYellowHousePositions() {
        return yellowHousePositions;
    }

    /* public List<Position> getFlowersPosition() {
        return flowersPosition;
    } */

}
