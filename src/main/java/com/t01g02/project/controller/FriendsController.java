package com.t01g02.project.controller;

import com.t01g02.project.menu.SettingsModel;
import com.t01g02.project.menu.Sound;
import com.t01g02.project.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.t01g02.project.model.CharacterModel.friends;
import static com.t01g02.project.model.CharacterModel.hellokitty;

public class FriendsController {
    private static CityModel cityModel;
    private List<KittyObserver> observers = new ArrayList<KittyObserver>();
    private Sound sound;
    private SettingsModel settingsModel;
    private int targetPosIndex = 0;



    public FriendsController(CityModel cityModel, Sound sound,SettingsModel settingsModel) {
        FriendsController.cityModel = cityModel;
        this.sound=sound;
        this.settingsModel= settingsModel;
    }

    private static boolean isWithinZone(Position position, Zone zone) {
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
                if (friend != null && !friend.isFollowing() && !hellokitty.isBeingFollowed() && !friend.isInParty()) {
                    friend.setFollowing(true);
                    hellokitty.setBeingFollowed(true);
                    if (settingsModel.isSoundOn()){
                        sound.play("/audio/pickupSound.wav");
                    }
                    notifyPickedUp();

                }
            }
        }

    }

    public void checkDropoff() {

        for (Zone zone : cityModel.getZones()) {
            for (CharacterModel friend : friends){
                if (friend.isFollowing()) {
                    if (zone.getType() == Tile.Type.DROPOFF && isWithinZone(friend.getPosition(), zone)) {
                        friend.setFollowing(false);
                        hellokitty.setBeingFollowed(false);
                        friend.setInParty(true);
                        if (settingsModel.isSoundOn()){
                            sound.play("/audio/dropoffSound.wav");
                        }
                        notifyDroppedOff();
                        enterHouse(friends.indexOf(friend));
                        friend.setInParty(true);


                    }
                }
            }
        }
    }
    public static void enterHouse(int friendId) {
        CharacterModel friend = friends.get(friendId);
        for(int i = 0; i<10; i++){
            friend.setPosition(new Position(friend.getPosition().getX(),friend.getPosition().getY()-2));
        }

    }
    public  void leaveHouse(int friendId) {
        int speed = KittyController.speed.getSpeed();

        CharacterModel friend = friends.get(friendId);
        if (!isWithinZone(hellokitty.getPosition(), cityModel.getZones().get(friendId))) {
            friend.setPosition(new Position(friend.getPosition().getX(), friend.getPosition().getY() + speed));

        }
        if(hellokitty.getPosition().getY()<=friend.getPosition().getY()){
            friend.setOutOfHouse(true);
            getCorrectIndex(friendId);

        }

    }
    public void moveFollowingCharacters() {
        for (int i = 0; i < friends.size(); i++) {
            CharacterModel friend = friends.get(i);
            if(friend.isFollowing() && !friend.isOutOfHouse()){
                leaveHouse(i);
            }
            if (friend.isFollowing() && friend.isOutOfHouse()) {
                follow(i);
            }
        }

    }

    public  void getCorrectIndex(int friendId) {
        Position kittyPos = hellokitty.getPosition();
        Position friendPos = friends.get(friendId).getPosition();
        CharacterModel friend = friends.get(friendId);
        List<Position> kittyPositionHistory = hellokitty.getKittyLastPositions();

        for (int i = 0; i< kittyPositionHistory.size() - 1; i++) {
            Position historyPos = kittyPositionHistory.get(i);

            if (checkSurrounding(historyPos, friendPos)) {
                targetPosIndex = i;
                break;
            }
        }
    }
    private boolean checkSurrounding(Position p1, Position p2) {
        int deltaX = Math.abs(p1.getX() - p2.getX());
        int deltaY = Math.abs(p1.getY() - p2.getY());
        return deltaX <= 2 && deltaY <= 2;
    }

    private void follow(int friendId) {
        CharacterModel friend = friends.get(friendId);
        int speed = KittyController.speed.getSpeed();
        List<Position> kittyPositionHistory = hellokitty.getKittyLastPositions();

        Position kittyLastPosition = kittyPositionHistory.get(targetPosIndex);

        friend.setPosition(kittyLastPosition);
    }


    public void addObserver(KittyObserver observer) {
        observers.add(observer);
    }
    public void notifyPickedUp() {
        for (KittyObserver observer : observers) {
            observer.friendPickedUp();
        }
    }

    public void notifyDroppedOff() {
        for (KittyObserver observer : observers) {
            observer.friendDroppedOff();
        }
    }

}
