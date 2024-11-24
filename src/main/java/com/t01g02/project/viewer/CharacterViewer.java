package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CharacterModel;

import java.io.IOException;


public class CharacterViewer {
    private final Screen screen;


    public CharacterViewer(Screen screen) throws IOException {
        this.screen = screen;
    }

    public void initializeCharacters() throws IOException {
        CharacterModel.initializeCharacters(screen);
    }


    public void draw() throws IOException {
        if (CharacterModel.characters != null){
            for (CharacterModel character : CharacterModel.characters) {
                //System.out.println("Drawing character: " + character.getName() + " at position: " + character.getPosition().getX() + ", " + character.getPosition().getY());
                character.getSprite().drawImage(character.getPosition()); // Example size values

            }
        }
        screen.refresh();
    }
}
