package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.Element;
import com.t01g02.project.model.Position;

import java.io.IOException;



public class CharacterViewer {
    private final Screen screen;
    private final Sprite block;
    private final Sprite mud;
    private final Sprite star;
    private final Sprite speed;


    public CharacterViewer(Screen screen) throws IOException {
        this.screen = screen;
        this.block = new Sprite(screen, "src/main/resources/Pop-ups/block.png");
        this.mud = new Sprite(screen, "src/main/resources/Pop-ups/mud.png");
        this.star = new Sprite(screen, "src/main/resources/Pop-ups/star.png");
        this.speed = new Sprite(screen, "src/main/resources/Pop-ups/speed.png");
    }

    public void initializeCharacters() throws IOException {
        CharacterModel.initializeCharacters(screen);
    }


    public void draw() throws IOException {
        mud.drawImage(new Position(15, 20));
        block.drawImage(new Position(55, 62));
        speed.drawImage(new Position(100, 100));
        star.drawImage(new Position(150, 150));
        CharacterModel.getHellokitty().getSprite().drawImage(CharacterModel.getHellokitty().getPosition());
        if (CharacterModel.friends != null){
            for (CharacterModel character : CharacterModel.friends) {
                character.getSprite().drawImage(character.getPosition());
            }
        }
    }
}
