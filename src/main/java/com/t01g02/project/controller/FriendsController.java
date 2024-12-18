package com.t01g02.project.controller;

import com.t01g02.project.menu.SettingsModel;
import com.t01g02.project.menu.Sound;
import com.t01g02.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static com.t01g02.project.model.CharacterModel.friends;
import static com.t01g02.project.model.CharacterModel.hellokitty;

public class FriendsController {
    private static CityModel cityModel;
    private final List<KittyObserver> observers = new ArrayList<>();
    private final Sound sound;
    private final SettingsModel settingsModel;

    public FriendsController(CityModel cityModel, Sound sound,SettingsModel settingsModel) {
        FriendsController.cityModel = cityModel;
        this.sound=sound;
        this.settingsModel= settingsModel;
    }


    public void checkPickup() {
        Position kittyPosition = CharacterModel.getHellokitty().getPosition();
        List<Zone> zones = cityModel.getZones();
        for (Zone zone : zones) {
            if (zone.getType() == Tile.Type.PICKUP && zone.isWithin(kittyPosition)) {
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
                    if (zone.getType() == Tile.Type.DROPOFF && zone.isWithin(friend.getPosition())) {
                        hellokitty.setBeingFollowed(false);
                        friend.setFollowing(false);
                        if (settingsModel.isSoundOn()){
                            sound.play("/audio/dropoffSound.wav");
                        }
                        hellokitty.eraseKittyPosition();
                        notifyDroppedOff();


                    }
                }
            }
        }
    }

    public static void enterHouse(int friendId) {
        CharacterModel friend = friends.get(friendId);
        friend.setPosition(new Position(friend.getPosition().getX(),friend.getPosition().getY()-2));
        if(friend.getPosition().getY() < 135){
            friend.setInParty(true);
        }
    }

    public  void leaveHouse(int friendId) {
        int speed = KittyController.speed.getSpeed();

        CharacterModel friend = friends.get(friendId);
        if (!cityModel.getZones().get(friendId).isWithin(hellokitty.getPosition())) {
            friend.setPosition(new Position(friend.getPosition().getX(), friend.getPosition().getY() + speed));
        }
        if(cityModel.getZones().get(friendId).isWithin(friend.getPosition())){
            friend.setOutOfHouse(true);
        }

    }

    public void updateFriendsPosition(){
        for (int i = 0; i< friends.size(); i++) {
            CharacterModel friend = friends.get(i);
            if(friend.isFollowing() && !friend.isOutOfHouse()){
                leaveHouse(i);
            }
            if(!friend.isFollowing() && friend.isOutOfHouse() && !friend.isInParty()){
                enterHouse(i);
            }
        }
    }

    public void moveFollowingCharacters() {
        for (int i = 0; i < friends.size(); i++) {
            CharacterModel friend = friends.get(i);
            if (friend.isFollowing() && friend.isOutOfHouse()) {
                follow(i);
            }
        }
    }

    void follow(int friendId) {
        CharacterModel friend = friends.get(friendId);
        List<Position> kittyPositionHistory = hellokitty.getKittyLastPositions();
        Position kittyPos = kittyPositionHistory.getFirst();
        System.out.println("fx: "+kittyPos.getX()+"fy: "+kittyPos.getY());

        friend.setPosition(kittyPos);
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
    public boolean areAllFriendsInParty() {
        for (CharacterModel friend : friends) {
            if (!friend.isInParty()) {
                return false;
            }
        }
        return true;
    }

}
