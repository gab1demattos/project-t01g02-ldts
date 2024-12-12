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
            Position kittyPosistionHistory = kittyPositionHistory.get(kittyPositionHistory.size() - 1); // Get the last position of Kitty

            // Move the friend towards Kitty's last known position
            if (friendPos.getX() < kittyPosistionHistory.getX()) {
                friend.setPosition(new Position(friendPos.getX() + 1, friendPos.getY())); // Move right
            } else if (friendPos.getX() > kittyPosistionHistory.getX()) {
                friend.setPosition(new Position(friendPos.getX() - 1, friendPos.getY())); // Move left
            }

            if (friendPos.getY() < kittyPosistionHistory.getY()) {
                friend.setPosition(new Position(friendPos.getX(), friendPos.getY() + 1)); // Move down
            } else if (friendPos.getY() > kittyPosistionHistory.getY()) {
                friend.setPosition(new Position(friendPos.getX(), friendPos.getY() - 1)); // Move up
            }
        }

        //if (Math.abs(xDiff) > 1) this.setPosition(new Position(this.getPosition().getX() + Integer.signum(xDiff), this.getPosition().getY()));
        //if (Math.abs(yDiff) > 1) this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() + Integer.signum(yDiff)));

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
