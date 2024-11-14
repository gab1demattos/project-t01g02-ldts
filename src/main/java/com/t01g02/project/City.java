package com.t01g02.project;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;


public class City {
    private final int width;
    private final int height;
    private final Tile[][] map;


    public City(int width, int height){
        this.width = width;
        this.height = height;
        this.map = new Tile[height][width];

        initializeCity();

    }
    private void initializeCity(){
        TextColor color = TextColor.Factory.fromString("#888888");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[y][x] = new Tile(Tile.Type.EMPTY, TextColor.Factory.fromString("#FFFFFF"));
            }
        }

        Tile.fillLine(map, new Position(2,4), new Position(170, 4 ), Tile.Type.ROAD, color); // outside roads
        Tile.fillLine(map, new Position (2, 4), new Position(2, 50), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position (2, 47), new Position(130, 47), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position (190, 15), new Position(190, 57), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position (130, 54), new Position(190, 54), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position (130, 47), new Position(130, 57), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position (170, 15), new Position(190, 15), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position (170, 4), new Position(170, 15), Tile.Type.ROAD, color);

        Tile.fillLine(map, new Position(2,15), new Position(90, 15 ), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position(62,15), new Position(62, 29 ), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position(40,29), new Position(90, 29 ), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position(90,4), new Position(90, 32), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position(35,15), new Position(35, 50), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position(25,4), new Position(25, 15), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position(90,21), new Position(120, 21), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position(120,21), new Position(120, 47), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position(165,32), new Position(165, 57), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position(120,32), new Position(190, 32), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position(165,45), new Position(190, 45), Tile.Type.ROAD, color);
        Tile.fillLine(map, new Position(140,4), new Position(140, 31), Tile.Type.ROAD, color);




    }

    public Tile getTileAt(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return map[y][x];
        }
        return null;
    }


    public int getWidth(){ return width; };

    public int getHeight() { return height; }



}
