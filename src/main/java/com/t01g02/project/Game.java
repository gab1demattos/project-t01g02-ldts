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
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.io.IOException;

public class Game {
    private final Screen screen;
    private final City city;
    private final Viewer viewer;
    //private int remainingSeconds = 300;

    //private final Controller controller;

    public Game() throws IOException {
        this.city = new City(200, 60);

        TerminalSize terminalSize = new TerminalSize(city.getWidth() , city.getHeight());
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize).setTerminalEmulatorTitle("Hello Kitty Game!");
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
        /*public void start() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingSeconds <= 0) {
                    timer.cancel();  // Stop timer when time runs out
                    System.out.println("Game Over!");
                } else {
                    try {
                        viewer.draw(remainingSeconds);  // Update screen with timer
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    remainingSeconds--;  // Decrement time each second
                }
            }
        }, 0, 1000);  // Update every second
    }*/ // supposed timer, but still not working

}
