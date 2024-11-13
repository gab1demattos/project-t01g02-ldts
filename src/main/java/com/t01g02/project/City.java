package com.t01g02.project;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public class City {
    private final int width;
    private final int height;
    private final List<Road> roads;
    private final Tile[][] map;


    public City(int width, int height){
        this.width = width;
        this.height = height;
        this.roads = new ArrayList<>();
        this.map = new Tile[height][width];

        initializeCity();

    }
    private void initializeCity(){
        // i need to find a way to make the blocks bigger block (1*2) -> (3*6) maybe

        for (int i = 0; i < width; i++) {
            for (int j = 5; j < height; j++) {
                if (i == 0 || i == 1 || i == width - 1 || i == width -2 || j == 5 || j == height - 1) {
                    map[j][i] = Tile.ROAD;  // Define the outer boundary as a road
                } else {
                    map[j][i] = Tile.EMPTY; // Default is empty space
                }
            }
        }



    }

    public Tile getTileAt(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return map[y][x];
        }
        return null;
    }

    /*public void render(TextGraphics graphics) {
        // Loop through the grid and draw roads or buildings
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = map[i][j];
                if (tile == Tile.ROAD) {
                    graphics.putString(j, i, "█");  // Display road symbol
                } else if (tile == Tile.BUILDING) {
                    graphics.putString(j, i, "#");  // Display building symbol
                }
                // Add other tile types here
            }
        }
    }*/


    public int getWidth(){ return width; };

    public int getHeight() { return height; }

    public List<Road> getRoads(){ return roads;}

    public enum Tile {
        ROAD, BUILDING, EMPTY
    }

}
