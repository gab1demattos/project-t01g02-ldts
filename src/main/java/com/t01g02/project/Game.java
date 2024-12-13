package com.t01g02.project;


import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.t01g02.project.controller.FriendsController;
import com.t01g02.project.controller.KittyController;
import com.t01g02.project.controller.GameKeyListener;
import com.t01g02.project.controller.ScoreController;
import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.Score;
import com.t01g02.project.viewer.CharacterViewer;
import com.t01g02.project.viewer.CityViewer;
import com.t01g02.project.viewer.LanternaGui;
import com.t01g02.project.model.Timer;

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
    private ScoreController scoreController;
    private FriendsController friendsController;
    private Timer timer;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        this.gui = new LanternaGui(345, 185, "Hello Kitty Game!");

        this.city = new CityModel(345, 185);
        this.cityViewer = new CityViewer(city, gui.getScreen());
        this.characterViewer = new CharacterViewer(gui.getScreen());
        this.scoreController = new ScoreController(score);
        this.friendsController = new FriendsController(city);
        this.timer = new Timer(5, 0);

        city.initializeRoads();
        characterViewer.initializeCharacters();
        characterViewer.initializePopUps();
        city.initializeZones();
        city.initializeSpeedZones();

        ScoreController scoreController = new ScoreController(score);

        this.kittyController = new KittyController(gui.getScreen(), CharacterModel.getHellokitty(), city);
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

            cityViewer.draw();
            characterViewer.draw();

            gui.getScreen().refresh();
            kittyController.processInput(gameKeyListener.getKeys());
            friendsController.checkPickup();
            friendsController.checkDropoff();


            timer.update(frameTime);


            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if(timer.isTimeUp()){
                System.out.println("Game Over! :( ");
            }

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
            }


        }
    }


      