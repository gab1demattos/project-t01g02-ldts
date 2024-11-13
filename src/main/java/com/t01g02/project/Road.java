package com.t01g02.project;

import com.googlecode.lanterna.TerminalPosition;

import java.util.ArrayList;
import java.util.List;

public class Road {
    private final Position startPosition;
    private final Position endPosition;
    private final char symbol;

    public Road(Position startPosition, Position endPosition, char symbol){
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.symbol = symbol;
    }
    public Position getStartPosition(){ return startPosition; }

    public Position getEndPosition() { return endPosition; }

    public List<TerminalPosition> getPositions(){
        List <TerminalPosition> positions = new ArrayList<>();

        if(startPosition.getX() == endPosition.getX()){
            for (int y = startPosition.getY(); y <= endPosition.getY(); y++){
                positions.add(new TerminalPosition(startPosition.getX(), y));
            }
        }
        else if (startPosition.getY() == endPosition.getY()){
            for (int x = startPosition.getX(); x <= endPosition.getX(); x++){
                positions.add(new TerminalPosition(x, startPosition.getY()));
            }
        }
        return positions;
    }
    public char getSymbol() { return symbol; }
}
