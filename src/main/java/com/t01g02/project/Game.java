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
    private LanternaGui gui;
    private  CityModel city;
    private CityViewer cityViewer;
    private GameKeyListener gameKeyListener;
    private CharacterViewer characterViewer;
    private KittyController kittyController;
    private Score score;
    private ScoreViewer scoreViewer;
    private FriendsController friendsController;
    private Timer timer;
    private TimerViewer timerViewer;
    private Speed speed;
    private StarController starController;
    private PopUpsViewer popUpsViewer;
    private PopUpsModel star;
    private final GameEndListener gameEndListener;


    public Game(GameEndListener gameEndListener) throws IOException, FontFormatException, URISyntaxException {
        this.gui = new LanternaGui(345, 195, "Hello Kitty Game!");
        this.gameEndListener = gameEndListener;
        initializeGameComponents();
        addKeyListener();

    }

    public void initializeGameComponents() throws IOException, FontFormatException, URISyntaxException{
        Sound sound = new Sound();
        SettingsModel settingsModel = new SettingsModel();
        this.city = new CityModel(345, 195);
        this.cityViewer = new CityViewer(city, gui.getScreen());
        this.characterViewer = new CharacterViewer(gui.getScreen());
        this.friendsController = new FriendsController(city, sound, settingsModel);
        this.timer = new Timer(5, 0);
        this.timerViewer =new TimerViewer(timer, gui.getScreen());
        this.popUpsViewer = new PopUpsViewer(gui.getScreen(), city);
        this.score = new Score(0);
        this.scoreViewer = new ScoreViewer(score, gui.getScreen());
        ScoreController scoreController = new ScoreController(score);

        city.initializeRoads();
        characterViewer.initializeCharacters();
        popUpsViewer.initializePopUps();
        city.initializeZones();

        this.star= PopUpsModel.getStar();
        this.starController = new StarController(city, star);
        this.kittyController = new KittyController(gui.getScreen(), CharacterModel.getHellokitty(), city,sound,settingsModel);
        kittyController.addObserver(scoreController);
        friendsController.addObserver(scoreController);


    }

    private void addKeyListener(){
        AWTTerminalFrame terminalFrame = gui.getTerminalFrame();
        this.gameKeyListener = new GameKeyListener(kittyController);
        terminalFrame.addKeyListener(this.gameKeyListener);
        terminalFrame.requestFocusInWindow();
    }

    public void run() throws IOException{

       /* try {
            resetGame();
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }*/

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


            if(timer.isTimeUp() || (friendsController.areAllFriendsInParty() && kittyController.HasStarBeenPicked()) ){

                System.out.println("Game Over!");
                setGameOver(friendsController.areAllFriendsInParty() && kittyController.HasStarBeenPicked(), score.getScore() );
                break;
            }

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void setGameOver(boolean isWin, int finalScore) {
        try {
            gui.getScreen().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gameEndListener.onGameOver(isWin, finalScore);

    }

    public void clearScene() {
        city = null;
        cityViewer = null;
        gameKeyListener = null;
        characterViewer = null;
        kittyController = null;
        score = null;
        scoreViewer = null;
        friendsController = null;
        timer = null;
        timerViewer = null;
        speed = null;
        starController = null;
        popUpsViewer = null;
        star = null;

        gui.getScreen().clear();
        gui.getTerminalFrame().removeKeyListener(this.gameKeyListener);

    }



}


      