package com.t01g02.project.model;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.viewer.CharacterViewer;
import com.t01g02.project.viewer.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharacterModel extends Element {
    private Position position;
    public static CharacterModel hellokitty;
    private boolean isFollowing;
    public static List<CharacterModel> friends;

    public CharacterModel(Sprite sprite, Position position, String name) {
        super(sprite, position, name);
        this.isFollowing = false;
    }

    public static void initializeCharacters(Screen screen) throws IOException {
        hellokitty = new CharacterModel(new Sprite(screen, "src/main/resources/characters/hellokitty.png"),
                new Position(340, 127), "HelloKitty");
        friends = List.of(
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/kuromi.png"),
                        new Position(273, 226), "Kuromi"),
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/purin.png"),
                        new Position(28, 41), "Purin"),
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/mymelody.png"),
                        new Position(42, 177), "MyMelody"),
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/cinnamoroll.png"),
                        new Position(222, 73), "Cinnamoroll")
        );

    }

    public static CharacterModel getHellokitty() {
        return hellokitty;
    }

    public boolean isFollowing() {
        return isFollowing;
    }
    public void setFollowing(boolean following) {
        this.isFollowing = following;
    }

    public void follow() {
            int xDiff = hellokitty.getPosition().getX()-this.getPosition().getX();
            int yDiff = hellokitty.getPosition().getY() - this.getPosition().getY();

        System.out.println("Following: " + this.getName() + " | X Diff: " + xDiff + ", Y Diff: " + yDiff);


        if (Math.abs(xDiff) > 1) this.setPosition(new Position(this.getPosition().getX() + Integer.signum(xDiff), this.getPosition().getY()));
            if (Math.abs(yDiff) > 1) this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() + Integer.signum(yDiff)));

    }

}
