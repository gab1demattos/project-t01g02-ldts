package com.t01g02.project;


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.t01g02.project.controller.*;
import com.t01g02.project.menu.*;
import com.t01g02.project.model.*;
import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.Score;
import com.t01g02.project.viewer.*;
import com.t01g02.project.model.Position;

import java.net.URISyntaxException;
import java.awt.*;
import java.io.IOException;

public class Game {
    private final LanternaGui gui;
    private final CityModel city;
    private final CityViewer cityViewer;
    private final GameKeyListener gameKeyListener;
    private CharacterViewer characterViewer;
    private final KittyController kittyController;
    private Score score;
    private ScoreViewer scoreViewer;
    private FriendsController friendsController;
    private Timer timer;
    private TimerViewer timerViewer;
    private Speed speed;
    private GameOverView gameOverView;
    private GameMenuController gameMenuController;
    private StarController starController;
    private PopUpsViewer popUpsViewer;
    private PopUpsModel star;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        this.gui = new LanternaGui(345, 195, "Hello Kitty Game!");

        Sound sound = new Sound();
        SettingsModel settingsModel = new SettingsModel();
        this.city = new CityModel(345, 195);
        this.cityViewer = new CityViewer(city, gui.getScreen());
        this.characterViewer = new CharacterViewer(gui.getScreen());
        this.friendsController = new FriendsController(city, sound, settingsModel);
        this.timer = new Timer(0, 5);
        this.timerViewer =new TimerViewer(timer, gui.getScreen());
        this.popUpsViewer = new PopUpsViewer(gui.getScreen(), city);
        this.score = new Score(0);
        this.scoreViewer = new ScoreViewer(score, gui.getScreen());
        ScoreController scoreController = new ScoreController(score);
        this.gameOverView = gameOverView;
        this.gameMenuController=gameMenuController;
        city.initializeRoads();
        characterViewer.initializeCharacters();
        popUpsViewer.initializePopUps();
        city.initializeZones();

        this.star= PopUpsModel.getStar();
        this.starController = new StarController(city, star);


        this.kittyController = new KittyController(gui.getScreen(), CharacterModel.getHellokitty(), city,sound,settingsModel);
        this.gameKeyListener = new GameKeyListener(kittyController);
        kittyController.addObserver(scoreController);
        friendsController.addObserver(scoreController);


        AWTTerminalFrame terminalFrame = gui.getTerminalFrame();
        terminalFrame.addKeyListener(this.gameKeyListener);
        terminalFrame.requestFocusInWindow();
    }

    public void run() throws IOException{
        cityViewer.initializeCityImage();
        int FPS = 10;
        int frameTime = 1000 / FPS;


        while (true) {
            long startTime = System.currentTimeMillis();
            gui.getScreen().clear();

            Position kittyPosition = CharacterModel.hellokitty.getPosition();

            starController.moveStar(kittyPosition, city);
            cityViewer.draw();
            popUpsViewer.draw();
            characterViewer.draw();
            scoreViewer.draw(10,185);
            timerViewer.draw(185);
            gui.getScreen().refresh();
            kittyController.processInput(gameKeyListener.getKeys());
            friendsController.checkPickup();
            friendsController.checkDropoff();

            timer.update(frameTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if(timer.isTimeUp()){
                System.out.println("Game Over!");
                setGameOver(friendsController.allFriendsPickedUp()&&starController.isStarPickedUp(), score.getScore());
                break;
            }

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }


        }

        //update here!!!!!

        private void setGameOver (boolean isWin, int finalScore) throws IOException{
            gameMenuController.setInGameOver(true);
            gameOverView.setGameOver(isWin,finalScore);
            gameOverView.redrawScreen();
            gui.getScreen().refresh();
            gameMenuController.updateView();
        }

    }


      