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

    public City(int width, int height){
        this.width = width;
        this.height = height;
        this.roads = new ArrayList<>();

        initializeCity();

    }
    private void initializeCity(){
        roads.add(new Road(new Position(1, 1), new Position(175, 1), '='));

    }

    public int getWidth(){ return width; };

    public int getHeight() { return height; }

    public List<Road> getRoads(){ return roads;}


}
