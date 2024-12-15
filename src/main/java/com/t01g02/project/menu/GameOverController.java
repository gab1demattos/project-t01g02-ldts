package com.t01g02.project.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameOverController implements IController{
    private GameOverView gameOverView;
    private final Screen screen;
    private boolean inGameOver;
    private GameMenuController gameMenuController;
    private final Music music;
    private final SettingsModel settingsModel;
    private final Sound sound;


    public GameOverController (GameOverView gameOverView, Screen screen, GameMenuController gameMenuController, SettingsModel settingsModel, Music music,Sound sound){
        this.gameOverView=gameOverView;
        this.screen=screen;
        this.gameMenuController=gameMenuController;
        this.music=music;
        this.settingsModel=settingsModel;
        this.sound=sound;


    }

    public void setGameOverState(boolean isWin, int finalScore) {
        gameOverView.setGameOver(isWin, finalScore);

        if (settingsModel.isSoundOn()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (isWin) {
                System.out.println("Playing win sound...");
                sound.play("/audio/winSound.wav");
            } else {
                System.out.println("Playing lose sound...");
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

                case Character:
                    if (input.getCharacter() == 'b') {
                        gameMenuController.setInGameOver(false);
                        gameMenuController.updateView();
                        screen.refresh();
                    }

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
