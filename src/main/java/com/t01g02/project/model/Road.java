package com.t01g02.project.model;

import com.googlecode.lanterna.TextColor;

public class Road {
    private final Position start;
    private final Position end;
    private final Tile.Type type;
    private final TextColor color;
    public Position getCenter;

    public Road(Position start, Position end, Tile.Type type, TextColor color) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.color = color;
    }
    
    public Position getCenter(){
        if(start.getX()==end.getX()){
            return new Position(start.getX()+12,end.getY());
        }
        else if(start.getY()==end.getY()){
            return new Position(start.getX(),end.getY()+12);
        }
        return null;
    }
    

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public Tile.Type getType() {
        return type;
    }

    public TextColor getColor() {
        return color;
    }
}
