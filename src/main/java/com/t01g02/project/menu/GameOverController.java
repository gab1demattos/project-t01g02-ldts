package com.t01g02.project.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameOverController implements IController{
    private GameOver gameOverView;
    private final Screen screen;
    private final SettingsModel settingsModel;
    private final Sound sound;


    public GameOverController (GameOver gameOverView, Screen screen , SettingsModel settingsModel , Sound sound){
        this.gameOverView=gameOverView;
        this.screen=screen;
        this.settingsModel=settingsModel;
        this.sound=sound;
    }

    public void setGameOverState(boolean isWin, int finalScore) {
        gameOverView.setGameOver(isWin, finalScore);
        if (settingsModel.isSoundOn()) {
            try {
                Thread.sleep(5);

            } catch (InterruptedException e) {throw new RuntimeException(e);}

            if (isWin) {
                sound.play("/audio/winSound.wav");
            } else {
                sound.play("/audio/loseSound.wav");
            }
        }
    }

    @Override
    public void processInput() throws IOException, URISyntaxException, FontFormatException, InterruptedException{
        KeyStroke input = screen.readInput();
        if (input != null) {
            switch (input.getKeyType()) {
                case Escape:
                    System.exit(0);
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public void updateView() {
        gameOverView.redrawScreen();
    }

}
