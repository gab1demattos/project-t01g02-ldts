package com.t01g02.project;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Tile {
    public enum Type {
        ROAD, BUILDING, EMPTY
    }

    private final Type type;
    private final TextColor color;

    public Tile(Type type, TextColor color) {
        this.type = type;
        this.color = color;
    }

    public Type getType() {
        return type;
    }

    public TextColor getColor() {
        return color;
    }

    // Static method to create a line of tiles in a specified orientation (vertical or horizontal)
    public static void fillLine(Tile[][] map, Position start, Position end, Type type, TextColor color) {
        if (start.getX() == end.getX()) {  // Vertical line
            for (int y = start.getY(); y <= end.getY(); y++) {
                // Fill  line
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