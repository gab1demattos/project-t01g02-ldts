package com.t01g02.project.model;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.viewer.CharacterViewer;
import com.t01g02.project.viewer.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CharacterModel extends Element {
    private Position position;
    public static CharacterModel hellokitty;
    private boolean isFollowing;
    private boolean isBeingFollowed;
    public static List<CharacterModel> friends;
    private List<Position> kittyLastPositions = new LinkedList<>();


    public CharacterModel(Sprite sprite, Position position, String name) {
        super(sprite, position, name);
        this.isFollowing = false;
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


    public void kittysetPosition(Position newPosition) {
        System.out.println("Setting position to: " + newPosition.getX() + ", " + newPosition.getY());
        hellokitty.setPosition(newPosition);
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

    public void updateKittyPosition(Position newPosition) {
        if (kittyLastPositions.size() >= 20) {
            kittyLastPositions.remove(0);
        }
        kittyLastPositions.add(newPosition); // Add the new position
    }

    public List<Position> getKittyLastPositions() {
        return kittyLastPositions;
    }
}
