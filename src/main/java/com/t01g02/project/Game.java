package com.t01g02.project;


import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.t01g02.project.controller.*;
import com.t01g02.project.menu.*;
import com.t01g02.project.model.*;
import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.Score;
import com.t01g02.project.viewer.*;

import java.net.URISyntaxException;
import java.awt.*;
import java.io.IOException;

public class Game {
    private final LanternaGui gui;
    private CityViewer cityViewer;
    private GameKeyListener gameKeyListener;
    private CharacterViewer characterViewer;
    private KittyController kittyController;
    private Score score;
    private ScoreViewer scoreViewer;
    private FriendsController friendsController;
    private Timer timer;
    private TimerViewer timerViewer;
    private StarController starController;
    private PopUpsViewer popUpsViewer;
    private final GameEndListener gameEndListener;
    ScoreController scoreController;


    public Game(GameEndListener gameEndListener) throws IOException, FontFormatException, URISyntaxException {
        this.gui = new LanternaGui(345, 195, "Hello Kitty Game!");
        this.gameEndListener = gameEndListener;
        initializeGameComponents();
        addKeyListener();

    }

    public void initializeGameComponents() throws IOException, FontFormatException, URISyntaxException{
        Sound sound = new Sound();
        SettingsModel settingsModel = new SettingsModel();
        CityModel city = new CityModel(345, 180);
        this.cityViewer = new CityViewer(city, gui.getScreen());
        this.characterViewer = new CharacterViewer(gui.getScreen());
        this.friendsController = new FriendsController(city, sound, settingsModel);
        this.timer = new Timer(5, 0);
        this.timerViewer =new TimerViewer(timer, gui.getScreen());
        this.popUpsViewer = new PopUpsViewer(gui.getScreen());
        this.score = new Score(0);
        this.scoreViewer = new ScoreViewer(score, gui.getScreen());
        this.scoreController = new ScoreController(score);

        city.initializeRoads();
        System.out.println(city.getRoads());
        characterViewer.initializeCharacters();
        popUpsViewer.initializePopUps();
        city.initializeZones();

        PopUpsModel star = PopUpsModel.getStar();
        this.starController = new StarController(city, star);
        this.kittyController = new KittyController(gui.getScreen(), city,sound,settingsModel, CharacterModel.getHellokitty());
        kittyController.addObserver(scoreController);
        friendsController.addObserver(scoreController);

    }

    private void addKeyListener(){
        AWTTerminalFrame terminalFrame = gui.getTerminalFrame();
        this.gameKeyListener = new GameKeyListener();
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
            popUpsViewer.draw();
            characterViewer.draw();
            scoreViewer.draw(10,185);
            timerViewer.draw(185);
            gui.getScreen().refresh();
            kittyController.processInput(gameKeyListener.getKeys());
            friendsController.checkPickup();
            friendsController.updateFriendsPosition();
            friendsController.checkDropoff();
            starController.moveStar();


            timer.update(frameTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;


            if(isGameOver()){
                scoreController.incrementScore(timer.getRemainingSeconds());
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
    boolean isGameOver(){
        return timer.isTimeUp() || (friendsController.areAllFriendsInParty() && kittyController.HasStarBeenPicked());
    }

    private void setGameOver(boolean isWin, int finalScore) throws IOException {
        try {
            gui.getScreen().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gameEndListener.onGameOver(isWin, finalScore);

    }

//    private void resetGame() throws IOException {
//        CharacterModel.getHellokitty().setBeingFollowed(false);
//        CharacterModel.getHellokitty().getKittyLastPositions().clear();
//
//        for (CharacterModel friend : CharacterModel.friends) {
//            friend.setFollowing(false);
//            friend.setInParty(false);
//            friend.setOutOfHouse(false);
//        }
//        CharacterModel.initializeCharacters(gui.getScreen());
//        city.reset();
//        PopUpsModel.reset(gui.getScreen());
//        timer.resetTimer(5, 0);
//        score.resetScore();
//        run();
//
//    }

}


      