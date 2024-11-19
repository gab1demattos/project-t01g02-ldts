package com.t01g02.project;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorColorConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorPalette;

import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.io.IOException;

public class Game {
    private final Screen screen;
    private final City city;
    private final CityViewer cityViewer;
    //private int remainingSeconds = 300;

    //private final Controller controller;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        this.city = new City(500, 250); //320/200

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
        //terminalFactory.setTerminalEmulatorFontConfiguration(); fonte
        //TerminalEmulatorColorConfiguration colorConfiguration = new TerminalEmulatorPalette(); //pallette interesting

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        //screen.doResizeIfNecessary(); // resize screen if necessary

        this.cityViewer = new CityViewer(city, screen); // initializa o viewer

        //this.controller = new Controller(screen, city, viewer);


    }

    public void run() throws IOException {//this is part of view??

        while (true) {
            cityViewer.draw();

            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q' || key.getKeyType() == KeyType.EOF) {
                System.exit(0);
            }
        }
    }
}

      