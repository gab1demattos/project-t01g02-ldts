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
    private final CityModel city;
    private PopUpsModel star;
    private Random random = new Random();

    public StarController(CityModel city, PopUpsModel star) {
        Position position = star.getPosition();
        this.city = city;
        this.star = star;

    }
    public void moveStar(Position kittyPosition, CityModel city) throws IOException {
        Position position = star.getPosition();

        int dx = position.getX() - kittyPosition.getX();
        int dy = position.getY() - kittyPosition.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        List<Position> validMoves = getValidMoves(position);

        Position newPosition;

        if (distance < 200) {
            newPosition = getEscapeMove(validMoves, kittyPosition);
        }
        else {
            newPosition = getRandomMove(validMoves);
        }
        if(newPosition != null) {
            star.setPosition(newPosition);
        }
    }

    private List<Position> getValidMoves(Position currentPosition) {
        List<Position> moves = new ArrayList<>();

        // Possible movement directions (up, down, left, right)
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] dir : directions) {
            Position newPos = new Position(currentPosition.getX() + dir[0] , currentPosition.getY() + dir[1]);
            if (isValidMove(newPos)) {
                moves.add(newPos);
            }
        }

        return moves;
    }
    private Position getEscapeMove(List<Position> validMoves, Position kittyPosition) {
        if (validMoves.isEmpty()) return null;

        Position bestMove = null;
        double maxDistance = -1;

        for (Position move : validMoves) {
            double distance = getDistance(move, kittyPosition);
            if (distance > maxDistance) {
                maxDistance = distance;
                bestMove = move;
            }
        }

        return bestMove;
    }
    private Position getRandomMove(List<Position> validMoves) {
        if (validMoves.isEmpty()) return null;

        return validMoves.get(random.nextInt(validMoves.size()));
    }

    private boolean isValidMove(Position position) {
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
    private double getDistance(Position position, Position kittyPosition) {
        int distanceX = position.getX() - kittyPosition.getX();
        int distanceY = position.getY() - kittyPosition.getY();
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }
}
