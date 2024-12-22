package com.t01g02.project.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.Game;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameMenuController implements IController, GameEndListener {
    private boolean running = true;
    private final IView view;
    private final IModel model;
    private final Screen screen;
    private final SettingsModel settingsModel;
    private final SettingsView settingsView;
    private final Music music;
    private final Sound sound;
    private boolean inSettings;
    private final SettingsController settingsController;
    private final GameOver gameOverView;
    private final GameOverController gameOverController;
    private boolean inGameOver ;

    public GameMenuController(GameMenuView view, Screen screen, IModel model,SettingsModel settingsModel,SettingsView settingsView, Music music, Sound sound, GameOver gameOverView) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.view = view;
        this.screen = screen;
        this.model = model;
        this.settingsModel = settingsModel;
        this.settingsView = settingsView;
        this.music = music;
        this.sound = sound;
        this.gameOverView = gameOverView;

        setupMusic();

        this.settingsController = new SettingsController(settingsView, screen, settingsModel, music,sound, this);
        this.gameOverController = new GameOverController(gameOverView,screen,settingsModel,sound);
    }

    @Override
    public void processInput() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        KeyStroke input = screen.readInput();
        if (input != null && !inSettings) {
            switch (input.getKeyType()) {
                case Escape:
                    handleEscapeKey();
                    break;
                case ArrowLeft:
                    handleArrowLeft();
                    break;
                case ArrowRight:
                    handleArrowRight();
                    break;
                case Enter:
                    handleEnterKey();
                    executeSelectedOption();
                    break;
                default:
                    break;
            }

        } else if (inSettings){
            settingsController.processInput();

        }else if (inGameOver){
            gameOverController.processInput();
        }

        screen.refresh();
    }

    private void handleEscapeKey() {
        if (settingsModel.isSoundOn()) {
            sound.play("/audio/keyPressSound.wav");
        }
        if (!inSettings) {
            running = false;
            System.exit(0);
        } else {
            setInSettings(false);  // Switch back to the main menu
            updateView();
        }
    }

    private void handleArrowLeft() {
        if (settingsModel.isSoundOn()) {
            sound.play("/audio/arrowMenuSound.wav");
        }
        model.setSelectedOption((model.getSelectedOption() - 1 + model.getOptions().length) % model.getOptions().length);
        view.redrawButtons();
    }

    private void handleArrowRight() {
        if (settingsModel.isSoundOn()) {
            sound.play("/audio/arrowMenuSound.wav");
        }
        model.setSelectedOption((model.getSelectedOption() + 1) % model.getOptions().length);
        view.redrawButtons();
    }

    private void handleEnterKey() {
        if (settingsModel.isSoundOn()) {
            sound.play("/audio/selectSound.wav");
        }
        try {
            executeSelectedOption();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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


    private void startGame() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Thread.sleep(870); //slight delay so audio can play while still in settings

        try {
            playGameMusic();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        Game game = new Game(this,settingsModel);
        game.run();
    }

    @Override
    public void onGameOver(boolean isWin, int finalScore){
        inGameOver = true;
        gameOverView.setGameOver(isWin, finalScore);
        gameOverController.setGameOverState(isWin, finalScore);
        updateView();
    }

    private void setupMusic() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (settingsModel.isMusicOn()) {
            playMenuMusic();
        } else {
            music.stop();
        }
    }

    private void playMenuMusic() throws UnsupportedAudioFileException, IOException,LineUnavailableException{
        music.play("/audio/menuSong.wav",true, settingsModel.isMusicOn());
    }
    public void playGameMusic() throws UnsupportedAudioFileException, IOException,LineUnavailableException{
        music.stop();
        music.play("/audio/gameSong.wav",true, settingsModel.isMusicOn());
    }

    @Override
    public void updateView() {
        if (inSettings) {
            settingsView.redrawScreen();
        }else if (inGameOver){
            music.stop();
            gameOverView.redrawScreen();
        }
        else {
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



    public boolean isInSettings() {
        return inSettings;
    }
    public void setInSettings(boolean inSettings) {
        this.inSettings = inSettings;
    }

    public boolean isInGameOver(){
        return inGameOver;
    }

    public boolean isRunning() {
        return running;
    }


}
