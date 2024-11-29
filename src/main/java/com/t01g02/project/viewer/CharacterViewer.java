package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.Element;
import com.t01g02.project.model.Position;

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
        Sprite party = new Sprite(screen, "/home/matilde/IdeaProjects/project-t01g02/src/main/resources/extras/party.png");
        party.drawImage(new Position(407, 20));
        CharacterModel.getHellokitty().getSprite().drawImage(CharacterModel.getHellokitty().getPosition());
        if (CharacterModel.friends != null){
            for (CharacterModel character : CharacterModel.friends) {
                character.getSprite().drawImage(character.getPosition());
            }
        }
    }
}
