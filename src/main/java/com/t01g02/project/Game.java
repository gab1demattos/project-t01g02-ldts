package com.t01g02.project;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;

public class Game {
    private final Screen screen;
    private final City city;

    public Game() throws IOException {
        this.city = new City();

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Adjust terminal size based on screen resolution
        int terminalWidth = screenWidth / 20;  // Adjust the divisor to control terminal size
        int terminalHeight = screenHeight / 30; // Adjust the divisor for height
        TerminalSize terminalSize = new TerminalSize(screenHeight, screenWidth);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
    }
    private void draw() throws IOException{
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        screen.refresh();
    }
    public void run() throws IOException{
        draw();
    }

}
