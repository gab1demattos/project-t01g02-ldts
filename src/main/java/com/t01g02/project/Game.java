package com.t01g02.project;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.Element;
import com.t01g02.project.viewer.CharacterViewer;
import com.t01g02.project.viewer.CityViewer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URISyntaxException;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import java.util.List;

public class Game {
    private final LanternaGui gui;
    private final CityModel city;
    private final CityViewer cityViewer;
    private final GameKeyListener gameKeyListener;
    private CharacterViewer characterViewer;
    private final Controller controller;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        this.gui = new LanternaGui(340, 180, "Hello Kitty Game!");
        this.city = new CityModel(340, 180); //320/200
        this.cityViewer = new CityViewer(city, gui.getScreen());
        this.characterViewer = new CharacterViewer(gui.getScreen());

        city.initializeRoads();
        characterViewer.initializeCharacters();
        city.initializeZones();

        this.controller = new Controller(gui.getScreen(), CharacterModel.getHellokitty(), city);
        this.gameKeyListener = new GameKeyListener(controller);

        AWTTerminalFrame terminalFrame = gui.getTerminalFrame();
        terminalFrame.addKeyListener(this.gameKeyListener);
        terminalFrame.requestFocusInWindow();
    }

    public void run() throws IOException, InterruptedException {
        cityViewer.initializeCityImage();
        int FPS = 10;
        int frameTime = 1000 / FPS;
        long totalElapsedTime = 0; //timer start


        while (true) {
            long startTime = System.currentTimeMillis();
            gui.getScreen().clear();

            cityViewer.draw();
            characterViewer.draw();

            gui.getScreen().refresh();
            controller.processInput(gameKeyListener.getKeys());
            controller.checkPickup();
            controller.moveFollowingCharacters();
            controller.checkDropoff();


            //this is a makeshift timer, we'll need to incorporate it in timer class thats model ithink?
            totalElapsedTime += frameTime;
            //System.out.println("Total elapsed time: " + totalElapsedTime / 1000 + " seconds");


            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
              //  System.out.println(sleepTime);
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
            }


        }
    }


      