package com.t01g02.project;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import com.t01g02.project.menu.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        GameMenuModel menu = new GameMenuModel();

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        if (terminal instanceof SwingTerminalFrame) {
            SwingTerminalFrame terminalFrame = (SwingTerminalFrame) terminal;
            terminalFrame.setTitle("Hello Kitty Game!");
            terminalFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        screen.stopScreen();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    System.exit(0);
                }
            });
        }

        GameMenuModel model = new GameMenuModel();
        GameMenuView view = new GameMenuView(screen, model);

        GameOverView gameOverView = new GameOverView(screen);

        SettingsModel settingsModel = new SettingsModel();
        SettingsView settingsView = new SettingsView(screen, settingsModel);

        Music music = new Music();
        Sound sound = new Sound();


        GameMenuController menuController = new GameMenuController(view,screen,model,settingsModel,settingsView,music, sound,gameOverView);
        SettingsController settingsController = new SettingsController(settingsView,screen,settingsModel,music,sound,view,menuController);
        GameOverController gameOverController = new GameOverController(gameOverView, screen, menuController,settingsModel,music,sound);



        long lastUpdateTime = System.currentTimeMillis();
        long updateInterval = 100; // Update every 100ms

        while (menuController.isRunning()) {
            if (menuController.isInSettings()){
                settingsController.processInput();
                settingsController.updateView();
            }else if(menuController.isInGameOver()){
                gameOverController.processInput();
                gameOverController.updateView();
            }
            else {
                menuController.updateView();
                menuController.processInput();
            }
        }

        screen.stopScreen();


    }
}
