package com.t01g02.project.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.Game;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameMenuController implements IController {
    private boolean running = true;
    private final IView view;
    private IModel model;
    private  Screen screen;
    private final SettingsModel settingsModel;
    private final SettingsView settingsView;
    private final Music music;
    private boolean inSettings;
    private SettingsController settingsController;


    public GameMenuController(GameMenuView view, Screen screen, IModel model,SettingsModel settingsModel,SettingsView settingsView, Music music) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.view = view;
        this.screen = screen;
        this.model = model;
        this.settingsModel = settingsModel;
        this.settingsView = settingsView;
        this.music = music;


        if (settingsModel.isMusicOn()){
                playMenuMusic();
        }else{
            music.stop();
        }
        this.settingsController = new SettingsController(settingsView, screen, settingsModel, music, view, this);
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void processInput() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        KeyStroke input = screen.readInput();
        if (input != null && !inSettings) {
            switch (input.getKeyType()) {
                case Escape:
                    if (!inSettings){
                        running = false;
                        System.exit(0);
                        break;
                    }else{
                        setInSettings(false);  // Switch back to the main menu
                        updateView();
                        break;
                    }

                case ArrowLeft:
                    model.setSelectedOption((model.getSelectedOption() - 1 + model.getOptions().length) % model.getOptions().length);
                    view.redrawButtons();
                    break;
                case ArrowRight:
                    model.setSelectedOption((model.getSelectedOption() + 1) % model.getOptions().length);
                    view.redrawButtons();
                    break;
                case Enter:
                    executeSelectedOption();
                    break;
                default:
                    break;
            }
        } else if (inSettings){
            settingsController.processInput();
        }
        screen.refresh();
    }

    void executeSelectedOption() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        String selectedOption = model.getOptions()[model.getSelectedOption()];
        switch (selectedOption) {
            case "Settings":
                openSettings();
                break;
            case "Play":
                startGame();
                break;
            default:
                break;
        }
    }

    private void openSettings() {
        inSettings=true;
        updateView();
    }

    public boolean isInSettings() {
        return inSettings;
    }

    public void setInSettings(boolean inSettings) {
        this.inSettings = inSettings;
    }

    private void startGame() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        try {
            playGameMusic();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        Game game = new Game();
        game.run();
    }

    private void playMenuMusic() throws UnsupportedAudioFileException, IOException,LineUnavailableException{
        music.play("/audio/menuSong.wav",true, settingsModel.isMusicOn());
    }
    private void playGameMusic() throws UnsupportedAudioFileException, IOException,LineUnavailableException{
        music.stop();
        music.play("/audio/gameSong.wav",true, settingsModel.isMusicOn());
    }

    @Override
    public void updateView() {
        if (inSettings) {
            settingsView.redrawScreen();
        } else {
            view.redrawScreen();  // Redraw main menu view
        }

        if (screen.doResizeIfNecessary() != null) {
            if (inSettings) {
                settingsView.redrawScreen();
            } else {
                view.redrawScreen();
            }
        }
    }

}
