package com.t01g02.project.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class SettingsController  {
    private SettingsModel model;
    private SettingsView view;
    private final Screen screen;
    private final Music music;
    private GameMenuView mainMenuView;
    private GameMenuController mainMenuController;


    public SettingsController(SettingsView view, Screen screen, SettingsModel model, Music music, GameMenuView mainMenuView,GameMenuController mainMenuController){
        this.view = view;
        this.screen = screen;
        this.model=model;
        this.music = music;
        this.mainMenuView = mainMenuView;
        this.mainMenuController = mainMenuController;
    }


    public void processInput() throws IOException, URISyntaxException, FontFormatException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        KeyStroke input = screen.readInput();
        if (input != null) {
            switch (input.getKeyType()) {
                case Escape:
                    mainMenuController.setInSettings(false);
                    mainMenuController.updateView();
                    break;
                case ArrowLeft:
                    //model.setSelectedOption((model.getSelectedOption() - 1 + model.getMusicOoptions().length) % model.getMusicOoptions().length);
                    view.redrawButtons();
                    break;
                case ArrowRight:
                    //model.setSelectedOption((model.getSelectedOption() + 1) % model.getMusicOoptions().length);
                    view.redrawButtons();
                    break;
                case Enter:
                    //handleEnterKey();
                    break;
                default:
                    break;
            }
        }

    }

   /* private void handleEnterKey() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        switch (model.getSelectedOption()){
            case 0:
                toggleMusic();
                break;
            case 1:
                model.toggleSound();
                break;
            default:
                break;
        }
    }
    private void toggleMusic() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (music.isPlaying()){
            music.stop();
        }else{
            music.play("/audio/menuSong.wav",true);
        }
    }
    private void toggleSound(){

    }*/
}
