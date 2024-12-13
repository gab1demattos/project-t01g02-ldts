package com.t01g02.project;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.t01g02.project.menu.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        GameMenuModel menu = new GameMenuModel();

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        GameMenuModel model = new GameMenuModel();
        GameMenuView view = new GameMenuView(screen, model);

        SettingsModel settingsModel = new SettingsModel();
        SettingsView settingsView = new SettingsView(screen, settingsModel);
        Music music = new Music();

        GameMenuController menuController = new GameMenuController(view,screen,model,settingsModel,settingsView,music);
        SettingsController settingsController = new SettingsController(settingsView,screen,settingsModel,music,view,menuController);

        long lastUpdateTime = System.currentTimeMillis();
        long updateInterval = 100; // Update every 100ms

        while (menuController.isRunning()) {
            if (menuController.isInSettings()){
                settingsController.processInput();
                settingsController.updateView();
            }else {
                menuController.updateView();
                menuController.processInput();
            }
        }

        screen.stopScreen();

    }
}
