package com.t01g02.project.controller;

import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.PopUpsModel;
import com.t01g02.project.model.Position;
import com.t01g02.project.model.Tile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StarController {
    private final Position position;
    private final CityModel city;
    private PopUpsModel star;

    public StarController(Position position, CityModel city, PopUpsModel star) {
        this.position = position;
        this.city = city;
        this.star = star;

    }
    public void moveStar(Position kittyPosition, CityModel city) throws IOException {
        Position position = star.getPosition();
        Random random = new Random();

        int dx = position.getX() - kittyPosition.getX();
        int dy = position.getY() - kittyPosition.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        Position newPosition;

        if (distance < 20) {
            int movex = dx > 0 ? 1 : -1;
            int movey = dy > 0 ? 1 : -1;
            newPosition = new Position(position.getX()+movex, position.getY() +movey);
        }
        else {
            int randomX = random.nextInt(3) - 1;
            int randomY = random.nextInt(3) - 1;
            newPosition = new Position(position.getX() + randomX, position.getY() + randomY);
        }
        if(isValidMove (newPosition, city)){
            star.setPosition(newPosition);
        }
    }
    private boolean isValidMove(Position position, CityModel city) {
        List<Position> corners = new ArrayList<>();
        corners.add(new Position(position.getX(), position.getY())); //upperleft
        corners.add(new Position(position.getX() + 25, position.getY() )); // upper right
        corners.add(new Position(position.getX(), position.getY() + 20)); //lower left
        corners.add(new Position(position.getX() + 25, position.getY() + 20)); //lower right

        for (Position corner : corners) {
            Tile tile = city.getTile(corner.getX(), corner.getY());
            if (tile == null) {
                return false;
            }
            if (tile.getType() != Tile.Type.ROAD) {
                return false;
            }
        }
        return true;
    }
}
