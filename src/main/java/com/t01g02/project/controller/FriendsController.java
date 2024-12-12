package com.t01g02.project.controller;

import com.t01g02.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static com.t01g02.project.model.CharacterModel.friends;
import static com.t01g02.project.model.CharacterModel.hellokitty;

public class FriendsController {
    private final CityModel cityModel;
    private List<KittyObserver> observers = new ArrayList<KittyObserver>();

    public FriendsController(CityModel cityModel) {
        this.cityModel = cityModel;
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
            if (zone.getType() == Tile.Type.PICKUP && isWithinZone(kittyPosition, zone)) {

                CharacterModel friend = zone.getAssociatedFriend();
                if (friend != null && !friend.isFollowing() && !hellokitty.isBeingFollowed()){
                    friend.setFollowing(true);
                    hellokitty.setBeingFollowed(true);
                    // Notify score manager
                }
            }
        }
    }
    public void checkDropoff() {
        Position kittyPosition = CharacterModel.getHellokitty().getPosition();

        for (Zone zone : cityModel.getZones()) {

            if (zone.getType() == Tile.Type.DROPOFF && isWithinZone(kittyPosition, zone)) {
                for (CharacterModel friend : friends) {
                    if (friend.isFollowing()) {
                        friend.setFollowing(false);
                        hellokitty.setBeingFollowed(false);
                        // Notify score manager
                    }
                }
            }
        }
    }
    public void moveFollowingCharacters() {
        for (int i = 0; i < friends.size(); i++) {
            CharacterModel friend = friends.get(i);
            if (friend.isFollowing()) {
                follow(i);
            }
        }

    }

    public void follow(int friendId) {
        Position kittyPos = hellokitty.getPosition();
        Position friendPos = friends.get(friendId).getPosition();
        CharacterModel friend = friends.get(friendId);
        List<Position> kittyPositionHistory = hellokitty.getKittyLastPositions();

        if (isWithinZone(kittyPos, cityModel.getZones().get(friendId))){
            return;
        }
        if (friendPos.getY() > kittyPositionHistory.get(0).getY()) {
            friend.setPosition(new Position(friendPos.getX(), friendPos.getY() - 1));
            return;
        }
        if (!kittyPositionHistory.isEmpty()) {
            // Get Kitty's last known position (last position in the list)
            Position kittyLastPosition = kittyPositionHistory.get(kittyPositionHistory.size() - 1);

            Position friendcurrentPos = friend.getPosition();

            // If the friend has not yet reached Kitty's last position, move towards it
            if (!friendcurrentPos.equals(kittyLastPosition)) {
                // Move the friend step by step towards Kitty's last position
                if (friendcurrentPos.getX() < kittyLastPosition.getX()) {
                    friend.setPosition(new Position(friendcurrentPos.getX() + 2, friendcurrentPos.getY())); // Move right
                } else if (friendcurrentPos.getX() > kittyLastPosition.getX()) {
                    friend.setPosition(new Position(friendcurrentPos.getX() - 2, friendcurrentPos.getY())); // Move left
                }

                if (friendcurrentPos.getY() < kittyLastPosition.getY()) {
                    friend.setPosition(new Position(friendcurrentPos.getX(), friendcurrentPos.getY() + 2)); // Move down
                } else if (friendcurrentPos.getY() > kittyLastPosition.getY()) {
                    friend.setPosition(new Position(friendcurrentPos.getX(), friendcurrentPos.getY() - 2)); // Move up
                }
            }

            // Once the friend reaches Kitty's last position, start mimicking the movement direction
            if (friend.getPosition().equals(kittyLastPosition)) {
                // Get the direction in which Kitty last moved
                if (kittyPositionHistory.size() > 1) {
                    Position previousKittyPosition = kittyPositionHistory.get(kittyPositionHistory.size() - 2);

                    // If Kitty moved right
                    if (kittyLastPosition.getX() > previousKittyPosition.getX()) {
                        friend.setPosition(new Position(friendcurrentPos.getX() + 2, friendcurrentPos.getY())); // Move right
                    }
                    // If Kitty moved left
                    else if (kittyLastPosition.getX() < previousKittyPosition.getX()) {
                        friend.setPosition(new Position(friendcurrentPos.getX() - 2, friendcurrentPos.getY())); // Move left
                    }

                    // If Kitty moved down
                    if (kittyLastPosition.getY() > previousKittyPosition.getY()) {
                        friend.setPosition(new Position(friendcurrentPos.getX(), friendcurrentPos.getY() + 2)); // Move down
                    }
                    // If Kitty moved up
                    else if (kittyLastPosition.getY() < previousKittyPosition.getY()) {
                        friend.setPosition(new Position(friendcurrentPos.getX(), friendcurrentPos.getY() - 2)); // Move up
                    }
                }
            }

        }

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
