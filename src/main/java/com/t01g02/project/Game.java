package com.t01g02.project;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorColorConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorPalette;

import java.awt.*;
import java.io.IOException;

public class Game {
    private final Screen screen;
    private final City city;
    private final Viewer viewer;

    //private final Controller controller;

    public Game() throws IOException {
        this.city = new City(200, 60);

        TerminalSize terminalSize = new TerminalSize(city.getWidth() , city.getHeight());
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setTerminalEmulatorTitle("Hello Kitty Game!"); //Sets the title of the terminal;
        //terminalFactory.setTerminalEmulatorFontConfiguration(); fonte
        //TerminalEmulatorColorConfiguration colorConfiguration = new TerminalEmulatorPalette(); //pallette interesting
        Terminal terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        //screen.doResizeIfNecessary(); // resize screen if necessary

        this.viewer = new Viewer(city, screen); // initializa o viewer

        //this.controller = new Controller(screen, city, viewer);


    }

    public void run() throws IOException{ //this is part of View??
        while(true){
            viewer.draw();

            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q' || key.getKeyType() == KeyType.EOF){
                System.exit(0);
            }
        }
    }

}
