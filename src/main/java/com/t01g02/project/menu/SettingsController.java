package com.t01g02.project.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class SettingsController implements IController {
    private SettingsModel model;
    private SettingsView view;
    private final Screen screen;
    private final Music music;
    private GameMenuView mainMenuView;
    private GameMenuController mainMenuController;
    private boolean inSubMenu = false;  //access ON and OFF buttons


    public SettingsController(SettingsView view, Screen screen, SettingsModel model, Music music, GameMenuView mainMenuView,GameMenuController mainMenuController){
        this.view = view;
        this.screen = screen;
        this.model=model;
        this.music = music;
        this.mainMenuView = mainMenuView;
        this.mainMenuController = mainMenuController;
    }

    @Override
    public void processInput() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        KeyStroke input = screen.readInput();
        if (input != null) {
            switch (input.getKeyType()) {
                case Escape:
                    mainMenuController.setInSettings(false);
                    mainMenuController.updateView();
                    break;
                case ArrowLeft:
                    handleArrowLeft();
                    break;
                case ArrowRight:
                    handleArrowRight();
                    break;
                case Enter:
                    try {
                        handleEnterKey();
                        updateView();
                    } catch (UnsupportedAudioFileException e) {
                        throw new RuntimeException(e);
                    } catch (LineUnavailableException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case Character:
                    if (input.getCharacter() == 'b') {
                        inSubMenu = false;
                        screen.refresh();
                    }
                default:
                    break;
            }
            view.redrawButtons();
        }

    }

    private void handleArrowLeft(){
        if (inSubMenu){
            int selectedOption = model.getSelectedOption();
            if (selectedOption == 0){
                model.setMusicSelectedOption((model.getMusicSelectedOption() - 1 + model.getMusicOptions().length) % model.getMusicOptions().length);

            }else if (selectedOption == 1){
                model.setSoundSelectedOption((model.getSoundSelectedOption() - 1 + model.getSoundOptions().length) % model.getSoundOptions().length);
            }
        }else {
            model.setSelectedOption((model.getSelectedOption() - 1 + model.getOptions().length) % model.getOptions().length);
        }

    }

    private void handleArrowRight(){
        if (inSubMenu){
            int selectedOption = model.getSelectedOption();
            if (selectedOption == 0){
                model.setMusicSelectedOption((model.getMusicSelectedOption() - 1 + model.getMusicOptions().length) % model.getMusicOptions().length);

            }else if (selectedOption == 1){
                model.setSoundSelectedOption((model.getSoundSelectedOption() - 1 + model.getSoundOptions().length) % model.getSoundOptions().length);
            }
        }else {
            model.setSelectedOption((model.getSelectedOption() - 1 + model.getOptions().length) % model.getOptions().length);
        }
    }

    private void handleEnterKey() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (inSubMenu){
            int selectedOption = model.getSelectedOption();
            switch (model.getSelectedOption()){
                case 0:
                    toggleMusic();
                    break;
                case 1:
                    toggleSound();
                    break;
                default:
                    break;
            }
        }inSubMenu=true;
    }

    private void toggleMusic() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (model.getMusicSelectedOption()==0){
            if (!music.isPlaying()){
                music.play("/audio/menuSong.wav",true);
            }
            model.setMusicOn(true);
        }else{
            if (music.isPlaying()){
                music.stop();
            }
            model.setMusicOn(false);
        }
    }

    private void toggleSound(){

    }

    @Override
    public void updateView() {
        view.redrawScreen();
        view.drawBInfo(inSubMenu);
        if (screen.doResizeIfNecessary() != null) {
            view.redrawScreen();
        }

    }
}
