package com.t01g02.project.model;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.viewer.Sprite;

import java.io.IOException;
import java.util.List;

public class CharacterModel extends Element {
    private Sprite sprite;
    private Position position;
    private String name;

    public static List<CharacterModel> characters;

    public CharacterModel(Sprite sprite, Position position, String name) {
        super(sprite, position, name);
    }

    public static void initializeCharacters(Screen screen) throws IOException {
        characters = List.of(
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/hellokitty.png"), new Position(340, 127), "HelloKitty"), //26*17 ??
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/kuromi.png"), new Position(273, 226), "Kuromi"), // 25*25
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/purin.png"), new Position(28, 41), "Purin"), // 25*16
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/mymelody.png"), new Position(42, 177), "MyMelody"), // 25*19
                new CharacterModel(new Sprite(screen, "src/main/resources/characters/cinnamoroll.png"), new Position(222, 73), "Cinnamoroll") // 25*20
        );
    }
}
