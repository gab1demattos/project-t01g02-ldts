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

    public static List<CharacterModel> friends;

    public CharacterModel(Sprite sprite, Position position, String name) {
        super(sprite, position, name);
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
                        new Position(222, 73), "Cinnamonroll")
        );

    }

    public static CharacterModel getHellokitty() {
        return hellokitty;
    }

}
