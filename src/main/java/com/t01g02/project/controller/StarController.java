package com.t01g02.project.controller;

import com.t01g02.project.model.*;

import java.util.Random;


public class StarController {
    private final CityModel city;
    private final PopUpsModel star;
    private boolean starPickedUp;
    private int dx;
    private int dy;
    private final Random random;
    private int stepsSinceLastChange;
    private final CharacterModel hellokitty;

    public StarController(CityModel city, PopUpsModel star) {
        this.city = city;
        this.star = star;
        this.starPickedUp = false;
        this.random = new Random();
        this.hellokitty = CharacterModel.getHellokitty();
        this.dx = random.nextBoolean() ? 1 : -1;
        this.dy = random.nextBoolean() ? 1 : -1;
        this.stepsSinceLastChange = 0;
    }

    public void moveStar() {
        if (starPickedUp) return;
        Position currentPosition = star.getPosition();

        Position nextPosition = new Position(
                currentPosition.getX() + dx,
                currentPosition.getY() + dy
        );

        if (nextPosition.getX() < 0 || nextPosition.getX() + 25 >= city.getWidth()) {
            dx *= -1;
            nextPosition = new Position(
                    currentPosition.getX() + dx,
                    currentPosition.getY() + dy
            );
        }
        if (isPosOnStar(hellokitty.getPosition())) {
            starPickedUp = true;
        }

        if (nextPosition.getY() < 0 || nextPosition.getY() + 20 >= city.getHeight()) {
            dy *= -1;
            nextPosition = new Position(
                    currentPosition.getX() + dx,
                    currentPosition.getY() + dy
            );
        }


        if (stepsSinceLastChange > 50 && random.nextInt(20) == 0) {
            dx = random.nextBoolean() ? 1 : -1;
            dy = random.nextBoolean() ? 1 : -1;
            stepsSinceLastChange = 0;
        } else {
            stepsSinceLastChange++;
        }

        star.setPosition(nextPosition);
    }

    private boolean isPosOnStar(Position kittyPosition){
        Position starPosition = star.getPosition();
        return kittyPosition.equals(starPosition);
    }
}
