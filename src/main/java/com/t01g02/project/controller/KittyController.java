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

            switch (key.getKeyType()) {
                case ArrowUp:
                    newPosition = new Position(currentPosition.getX(), currentPosition.getY() - 2);
                    break;
                case ArrowDown:
                    newPosition = new Position(currentPosition.getX(), currentPosition.getY() + 2);
                    break;
                case ArrowLeft:
                    newPosition = new Position(currentPosition.getX() - 2, currentPosition.getY());
                    break;
                case ArrowRight:
                    newPosition = new Position(currentPosition.getX() + 2, currentPosition.getY());
                    break;
                default:
                    return;
            }
        }


        if (newPosition != null && canMove(newPosition)) {
            CharacterModel.getHellokitty().kittysetPosition(newPosition);
            Tile tile = cityModel.getTile(newPosition.getX(), newPosition.getY());

            if (tile != null){
                System.out.println(tile.getType());
            }
        }
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
            if (tile.getType() != Tile.Type.ROAD && tile.getType() != Tile.Type.PICKUP && tile.getType() != Tile.Type.DROPOFF) {
                return false;
            }
        }
        return true;
    }

    private boolean isWithinZone(Position position, Zone zone) {
        int tileX = position.getX();
        int tileY = position.getY();

        int zoneStartX = zone.getStartposition().getX();
        int zoneEndX = zone.getEndposition().getX()-20;
        int zoneStartY = zone.getStartposition().getY();
        int zoneEndY = zone.getEndposition().getY()+25;

        return tileX >= zoneStartX && tileX <= zoneEndX &&
                tileY >= zoneStartY && tileY <= zoneEndY;
    }

    public void checkPickup() {
        Position kittyPosition = CharacterModel.getHellokitty().getPosition();

        List<Zone> zones = cityModel.getZones();

        for (Zone zone : zones) {
            System.out.println("checking " + zone.getType());
            System.out.println("Hellokitty position:"  + kittyPosition.getX() + " " + kittyPosition.getY() );
            System.out.println("Zone position" + zone.getStartposition().getX() + " " + zone.getStartposition().getY() + " " + zone.getEndposition().getX() + " " + zone.getEndposition().getY());
            if (zone.getType() == Tile.Type.PICKUP && isWithinZone(kittyPosition, zone)) {
                System.out.println("Hellokitty is within the Pickup zone: " + zone.getIdentifier());

                CharacterModel friend = zone.getAssociatedFriend();
                if (friend != null && !friend.isFollowing()) {
                    friend.setFollowing(true);
                    System.out.println(friend.getName() + " is now following Hello Kitty!");
                    // Notify score manager
                }
            }
        }
    }
    public void checkDropoff() {
        Position kittyPosition = CharacterModel.getHellokitty().getPosition();

        for (Zone zone : cityModel.getZones()) {

            if (zone.getType() == Tile.Type.DROPOFF && isWithinZone(kittyPosition, zone)) {
                for (CharacterModel friend : CharacterModel.friends) {
                    if (friend.isFollowing()) {
                        friend.setFollowing(false);
                        System.out.println(friend.getName() + " has been dropped off!");
                        // Notify score manager
                    }
                }
            }
        }
    }
    public void moveFollowingCharacters() {
        for (CharacterModel friend : CharacterModel.friends) {
            if (friend.isFollowing()) {
                follow();
            }
        }
    }

    private void follow(){

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

