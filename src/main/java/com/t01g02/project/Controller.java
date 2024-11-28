package com.t01g02.project;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.Element;
import com.t01g02.project.model.Position;
import com.t01g02.project.viewer.CharacterViewer;

import java.io.IOException;
import java.util.List;

public class Controller {
    private CharacterModel hellokitty;
    private final CharacterViewer characterViewer;
    private final Screen screen;


    public Controller(Screen screen, CharacterModel hellokitty) throws IOException {
        this.hellokitty = CharacterModel.getHellokitty();
        this.screen = screen;
        this.characterViewer =new CharacterViewer(screen);
    }

    public void processInput(KeyStroke keyStroke) {
        Position currentPosition = CharacterModel.getHellokitty().getPosition();

        Position newPosition = new Position(currentPosition.getX(), currentPosition.getY());
        switch (keyStroke.getKeyType()) {
            case ArrowUp:
                newPosition = new Position(currentPosition.getX(), currentPosition.getY() - 3);
                break;
            case ArrowDown:
                newPosition = new Position(currentPosition.getX(), currentPosition.getY() + 3);
                break;
            case ArrowLeft:
                newPosition = new Position(currentPosition.getX() - 3, currentPosition.getY());
                break;
            case ArrowRight:
                newPosition = new Position(currentPosition.getX() + 3, currentPosition.getY());
                break;
            default:
                break;
        }


        CharacterModel.getHellokitty().setPosition(newPosition);
    }

}

