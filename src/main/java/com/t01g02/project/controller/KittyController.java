package com.t01g02.project.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.*;
import com.t01g02.project.viewer.CharacterViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KittyController {
    private List<KittyObserver> observers = new ArrayList<KittyObserver>();
    private CharacterModel hellokitty;
    private final CharacterViewer characterViewer;
    private final Screen screen;
    private final CityModel cityModel;
    private boolean isSpeedOn = false;




    public KittyController(Screen screen, CharacterModel hellokitty, CityModel cityModel) throws IOException {
        this.hellokitty = CharacterModel.getHellokitty();
        this.screen = screen;
        this.characterViewer =new CharacterViewer(screen);
        this.cityModel = cityModel;
    }

    public void processInput(Set<KeyStroke> keys) {
        Position currentPosition = CharacterModel.getHellokitty().getPosition();
        Position newPosition = null;

        for (KeyStroke key: keys) {

            int speed = isSpeedOn ? 4 : 2;

            switch (key.getKeyType()) {
                case ArrowUp:
                    newPosition = new Position(currentPosition.getX(), currentPosition.getY() - speed);
                    break;
                case ArrowDown:
                    newPosition = new Position(currentPosition.getX(), currentPosition.getY() + speed);
                    break;
                case ArrowLeft:
                    newPosition = new Position(currentPosition.getX() - speed, currentPosition.getY());
                    break;
                case ArrowRight:
                    newPosition = new Position(currentPosition.getX() + speed, currentPosition.getY());
                    break;
                default:
                    return;
            }
        }


        if (newPosition != null && canMove(newPosition)) {

            /* for (CharacterModel popup : CharacterModel.popups) {
                if (newPosition.equals(popup.getPosition()) && popup.getName().equals("Speed")) {
                    activateSpeed();
                }
            }*/

            CharacterModel.getHellokitty().setPosition(newPosition);
            FriendsController.moveFollowingCharacters();
            //activateSpeed();
        }
    }

    private void activateSpeed() {
        isSpeedOn = true;
    }

    private boolean canMove(Position newPosition){
        List<Position> corners = new ArrayList<>();
        corners.add(new Position(newPosition.getX()+3, newPosition.getY()+2)); //upperleft
        corners.add(new Position(newPosition.getX() + 23, newPosition.getY()+2)); // upper right
        corners.add(new Position(newPosition.getX()+3, newPosition.getY() + 17)); //lower left
        corners.add(new Position(newPosition.getX() + 23, newPosition.getY() + 17)); //lower right

        for (Position corner : corners) {
            Tile tile = cityModel.getTile(corner.getX(), corner.getY());
            if (tile == null) {
                return false;
            }
            if (tile.getType() != Tile.Type.ROAD && tile.getType() != Tile.Type.PICKUP && tile.getType() != Tile.Type.DROPOFF && tile.getType() != Tile.Type.SPEED) {
                return false;
            }
            if (tile.getType() == Tile.Type.SPEED) {
                activateSpeed();
            }
        }
        return true;
    }


    public void addObserver(KittyObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(KittyObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for (KittyObserver observer : observers) {
            /*if (isHappyHour()) observer.happyHourStarted(this);
            else observer.happyHourEnded(this);*/
        }
    }

}

