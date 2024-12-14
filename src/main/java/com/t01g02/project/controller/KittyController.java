package com.t01g02.project.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.menu.SettingsModel;
import com.t01g02.project.menu.Sound;
import com.t01g02.project.model.*;
import com.t01g02.project.viewer.CharacterViewer;
import com.t01g02.project.viewer.PopUpsViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KittyController {
    private List<KittyObserver> observers = new ArrayList<KittyObserver>();
    private CharacterModel hellokitty;
    private final CharacterViewer characterViewer;
    private final Screen screen;
    private final CityModel cityModel;
    private boolean isSpeedOn = false;
    private Sound sound;
    private SettingsModel settingsModel;
    private boolean isMudOn = false;
    private final PopUpsViewer popUpsViewer;
    private Speed speed;
    private Set<Position> activatedPopUps = new HashSet<>(); // Track activated popups

    private long speedtimerstart = 0;
    private  final long speedtimerduration = 5000;


    public KittyController(Screen screen, CharacterModel hellokitty, CityModel cityModel, Sound sound, SettingsModel settingsModel ) throws IOException {
        this.hellokitty = CharacterModel.getHellokitty();
        this.screen = screen;
        this.characterViewer =new CharacterViewer(screen);
        this.cityModel = cityModel;
        this.sound = sound;
        this.settingsModel = settingsModel;
        this.popUpsViewer = new PopUpsViewer(screen, cityModel);
        this.speed = new Speed();
    }

    public void processInput(Set<KeyStroke> keys) {
        if (isSpeedOn && System.currentTimeMillis() - speedtimerstart >= speedtimerduration || isMudOn && System.currentTimeMillis() - speedtimerstart >= speedtimerduration) {
            speed.resetSpeed();
           if (isSpeedOn) {deactivateSpeed();}
           if (isMudOn) {deactivateMud();}
        }

        Position currentPosition = CharacterModel.getHellokitty().getPosition();
        Position newPosition = null;

        for (KeyStroke key: keys) {

            if (isSpeedOn) {speed.increaseSpeed();}

            if (isMudOn) { speed.decreaseSpeed();}

            switch (key.getKeyType()) {
                case ArrowUp:
                    newPosition = new Position(currentPosition.getX(), currentPosition.getY() - speed.getSpeed());
                    break;
                case ArrowDown:
                    newPosition = new Position(currentPosition.getX(), currentPosition.getY() + speed.getSpeed());
                    break;
                case ArrowLeft:
                    newPosition = new Position(currentPosition.getX() - speed.getSpeed(), currentPosition.getY());
                    break;
                case ArrowRight:
                    newPosition = new Position(currentPosition.getX() + speed.getSpeed(), currentPosition.getY());
                    break;
                default:
                    return;
            }
        }


        if (newPosition != null && canMove(newPosition)) {
            activatePopUps(newPosition);
            CharacterModel.getHellokitty().setPosition(newPosition);
            FriendsController.moveFollowingCharacters();
        }
    }

    private void activatePopUps(Position newPosition) {
        for (PopUpsModel mudpopup : PopUpsModel.mudpopups) {
            if (isPositionOnPopUp(newPosition, mudpopup.getPosition())) {
                if (settingsModel.isSoundOn() && !activatedPopUps.contains(mudpopup.getPosition()) ){
                    sound.play("/audio/mudSound.wav");
                    activatedPopUps.add(mudpopup.getPosition());
                }
                isMudOn = true;
                isSpeedOn = false;
                speedtimerstart = System.currentTimeMillis();
                activatedPopUps.add(mudpopup.getPosition());
            }
        }
        for (PopUpsModel speedpopup : PopUpsModel.speedpopups) {
            if (isPositionOnPopUp(newPosition, speedpopup.getPosition())) {
                if (settingsModel.isSoundOn() && !activatedPopUps.contains(speedpopup.getPosition()) ){
                    sound.play("/audio/boltSound.wav");
                    activatedPopUps.add(speedpopup.getPosition());
                }
                isSpeedOn = true;
                isMudOn = false;
                speedtimerstart = System.currentTimeMillis();
            }
        }
        if (PopUpsModel.getStar() != null && isPositionOnPopUp(newPosition, PopUpsModel.getStar().getPosition())) {
            pickedStar();
            if (settingsModel.isSoundOn() ){
                sound.play("/audio/starSound.wav");
            }
            PopUpsModel.deleteStar();
        }
    }

    private void deactivateSpeed() {
        isSpeedOn = false;
    }
    private void deactivateMud() {
        isMudOn = false;
    }

    private boolean isPositionOnPopUp(Position newposition, Position popupPosition) {
        int speedX = popupPosition.getX();
        int speedY = popupPosition.getY();
        return (newposition.getX() >= speedX - 10 && newposition.getX() <= speedX + 10) && (newposition.getY() >= speedY - 10 && newposition.getY() <= speedY + 10);
    }

    private boolean canMove(Position newPosition){

        for (PopUpsModel blockpopup : PopUpsModel.blockpopups) {
            if (isPositionOnPopUp(newPosition, blockpopup.getPosition())) {
                return false;
            }
        }

        List<Position> corners = new ArrayList<>();
        corners.add(new Position(newPosition.getX() + 3, newPosition.getY() + 2)); //upperleft
        corners.add(new Position(newPosition.getX() + 23, newPosition.getY() + 2)); // upper right
        corners.add(new Position(newPosition.getX() + 3, newPosition.getY() + 17)); //lower left
        corners.add(new Position(newPosition.getX() + 23, newPosition.getY() + 17)); //lower right

        for (Position corner : corners) {
            Tile tile = cityModel.getTile(corner.getX(), corner.getY());
            if (tile == null) {
                return false;
            }
            if (tile.getType() != Tile.Type.ROAD && tile.getType() != Tile.Type.PICKUP && tile.getType() != Tile.Type.DROPOFF) {
                return false;
            }
        }
        return true;
    }


    public void addObserver (KittyObserver observer){
        observers.add(observer);
    }

    public void removeObserver (KittyObserver observer){
        observers.remove(observer);
    }

    public void pickedStar () {
        for (KittyObserver observer : observers) {
            observer.pickedStar();
        }
    }

}

