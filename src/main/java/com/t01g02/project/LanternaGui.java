package com.t01g02.project;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.t01g02.project.controller.GameKeyListener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

public class LanternaGui implements GUI {
    private final Screen screen;
    private final AWTTerminalFrame terminalFrame;
    private GameKeyListener keyListener;


    public LanternaGui(int width, int height, String title) throws IOException, URISyntaxException, FontFormatException {
        // Load custom font
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        if (resource == null) {
            throw new IOException("Font resource not found");
        }
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font derivedFont = font.deriveFont(Font.PLAIN, 4); // Derive smaller font
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(derivedFont);

        terminalFrame = (AWTTerminalFrame) new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(width, height))
                .setTerminalEmulatorFontConfiguration(fontConfig)
                .setForceAWTOverSwing(true)
                .setTerminalEmulatorTitle(title)
                .createTerminal();

        screen = new TerminalScreen(terminalFrame);
        screen.setCursorPosition(null); // Hide the cursor
        screen.startScreen(); // Start the screen

        (terminalFrame).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public Screen getScreen() {
        return screen;
    }

    public TextGraphics getTextGraphics() {
        return screen.newTextGraphics();
    }

    @Override
    public void clear() {
        screen.clear();
    }
    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }
    @Override
    public void close() throws IOException {
        screen.stopScreen();
    }

    public AWTTerminalFrame getTerminalFrame() {
        return terminalFrame;
    }

    void addKeyListener(GameKeyListener keyListener) {
        this.keyListener = keyListener;
        terminalFrame.addKeyListener(keyListener);
        terminalFrame.requestFocusInWindow();
    }
    public Set<KeyStroke> getKeyListener() {
        return (Set<KeyStroke>) keyListener;
    }

}
