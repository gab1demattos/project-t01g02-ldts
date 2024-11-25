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
import com.t01g02.project.model.CityModel;
import com.t01g02.project.viewer.CharacterViewer;
import com.t01g02.project.viewer.CityViewer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URISyntaxException;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.io.IOException;

public class Game {
    private final Screen screen;
    private final CityModel city;
    private final CityViewer cityViewer;
    private CharacterViewer characterViewer;

    //private final Controller controller;

    public Game() throws IOException, FontFormatException, URISyntaxException {

        this.city = new CityModel(500, 250); //320/200

        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        if (resource == null) {
            throw new IOException("Font resource not found");
        }
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font newfont = font.deriveFont(Font.PLAIN, 4);
        AWTTerminalFontConfiguration cfg = AWTTerminalFontConfiguration.newInstance(newfont);

        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(city.getWidth(), city.getHeight()))
                .setTerminalEmulatorFontConfiguration(cfg)
                .setForceAWTOverSwing(true)
                .setTerminalEmulatorTitle("Hello Kitty Game!")
                .createTerminal();
        //fix the terminal size

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        //screen.doResizeIfNecessary(); // resize screen if necessary

        this.cityViewer = new CityViewer(city, screen); // initializa o viewer
        this.characterViewer = new CharacterViewer(screen);
        //this.controller = new Controller(screen, city, viewer);

        city.initializeRoads();
        characterViewer.initializeCharacters();

        ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public void run() throws IOException {//this is part of view??
        cityViewer.initializeCityImage();

        while (true) {
            screen.clear();

            cityViewer.draw();
            characterViewer.draw();

            screen.refresh();

            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q' || key.getKeyType() == KeyType.EOF) {
                System.exit(0);
            }
        }
    }
}

      