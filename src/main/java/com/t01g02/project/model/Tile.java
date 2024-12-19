package com.t01g02.project.model;

import com.googlecode.lanterna.TextColor;

public class Tile {
    private final Type type;
    private final TextColor color;
    private final String identifier;

    public enum Type {
        ROAD, TREE, PICKUP, DROPOFF, EMPTY
    }

    public Tile(Type type, TextColor color) {
        this(type, color, null);
    }

    public Tile(Type type, TextColor color, String identifier) {
        this.type = type;
        this.color = color;
        this.identifier = identifier;
    }

    public Type getType() {
        return type;
    }

    public TextColor getColor() {
        return color;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static void fillLine(Tile[][] map, Position start, Position end, Type type, TextColor color) {
        if (start.getX() == end.getX()) {  // Vertical line
            for (int y = start.getY(); y <= end.getY(); y++) {
                map[y][start.getX()] = new Tile(type, color);
                for (int i = 1; i < 25; i++) {
                    if (start.getX() + i < map[0].length) {
                        map[y][start.getX() + i] = new Tile(type, color);
                    }
                }
            }
        } else if (start.getY() == end.getY()) {  // Horizontal line
            for (int x = start.getX(); x <= end.getX(); x++) {
                map[start.getY()][x] = new Tile(type, color);
                for (int i = 1; i < 25; i++) {
                    if (start.getY() + i < map.length) {
                        map[start.getY() + i][x] = new Tile(type, color);
                    }
                }
            }
        }
    }
}