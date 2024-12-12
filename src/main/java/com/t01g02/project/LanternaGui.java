package com.t01g02.project;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGui {
    private Screen screen;
    private final AWTTerminalFrame terminalFrame;


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

        // Configure and create the terminal
        terminalFrame = (AWTTerminalFrame) new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(width, height))
                .setTerminalEmulatorFontConfiguration(fontConfig)
                .setForceAWTOverSwing(true)
                .setTerminalEmulatorTitle(title)
                .createTerminal();

        // Initialize the screen
        screen = new TerminalScreen(terminalFrame);
        screen.setCursorPosition(null); // Hide the cursor
        screen.startScreen(); // Start the screen

        ((AWTTerminalFrame)terminalFrame).addWindowListener(new WindowAdapter() {
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

    public void close() throws IOException {
        screen.stopScreen();
    }

    public AWTTerminalFrame getTerminalFrame() {
        return terminalFrame;
    }

}
