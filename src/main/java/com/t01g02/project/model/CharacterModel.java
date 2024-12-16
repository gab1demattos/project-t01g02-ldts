package com.t01g02.project.model;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.viewer.CharacterViewer;
import com.t01g02.project.viewer.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CharacterModel extends Element {
    public static CharacterModel hellokitty;
    private boolean isFollowing;
    private boolean isBeingFollowed;
    private boolean inParty;
    public static List<CharacterModel> friends;
    private List<Position> kittyLastPositions = new LinkedList<>();
    private boolean outOfHouse;
    private boolean pickedIndex;



    public CharacterModel(Sprite sprite, Position position, String name) {
        super(sprite, position, name);
        this.isFollowing = false;
        this.inParty = false;
        this.outOfHouse = false;
        this.pickedIndex = false;

    }

    public static void initializeCharacters(Screen screen) throws IOException {
        hellokitty = new CharacterModel(new Sprite(screen, "src/main/resources/characters/hellokitty.png"),
                new Position(313, 157), "HelloKitty");
        friends = List.of(
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/kuromi.png"),
                        new Position(168, 83), "Kuromi"),
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/purin.png"),
                        new Position(38, 36), "Purin"),
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/mymelody.png"),
                        new Position(94, 134), "MyMelody"),
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/cinnamoroll.png"),
                        new Position(283, 39), "Cinnamoroll")
        );

    }


    public static CharacterModel getHellokitty() {
        return hellokitty;
    }

    @Override
    public void setPosition(Position newPosition) {
        super.setPosition(newPosition);
        updateKittyPosition(newPosition);


    }

    public boolean isFollowing() {
        return isFollowing;
    }
    public void setFollowing(boolean following) {
        this.isFollowing = following;
    }
    public boolean isBeingFollowed() {
        return isBeingFollowed;
    }
    public void setBeingFollowed(boolean beingFollowed) {
        isBeingFollowed = beingFollowed;
    }
    public boolean isOutOfHouse() {
        return outOfHouse;
    }
    public void setOutOfHouse(boolean outOfHouse) {
        this.outOfHouse = outOfHouse;
    }

    public void updateKittyPosition(Position newPosition) {
        if (kittyLastPositions.size() >= 75) {
            kittyLastPositions.remove(0);
        }
        kittyLastPositions.add(newPosition);
    }

    public List<Position> getKittyLastPositions() {
        return kittyLastPositions;
    }

    public boolean isInParty() {
        return inParty;
    }
    public void setInParty(boolean inParty) {
        this.inParty = inParty;
    }
    public static int getFriendInPartyCount() {
        int c = 0;
        for (CharacterModel friend : friends) {
            if (friend.isInParty()) {
                c++;
            }
        }
        return c;
    }

    public boolean hasPickedIndex() {
        return pickedIndex;
    }
    public void setPickedIndex(boolean pickedIndex) {
        this.pickedIndex = pickedIndex;
    }
}
