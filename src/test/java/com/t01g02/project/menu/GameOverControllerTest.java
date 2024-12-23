package com.t01g02.project.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class GameOverControllerTest {
    private GameOverController controller;

    @Mock private GameOver gameOver;
    @Mock private Screen screen;
    @Mock private  SettingsModel settingsModel;
    @Mock private Sound sound;

    @BeforeEach
    void setup() {
        gameOver = mock(GameOver.class);
        screen = mock(Screen.class);
        settingsModel = mock(SettingsModel.class);
        sound = mock(Sound.class);

        controller = new GameOverController(gameOver, screen, settingsModel, sound);
    }

    @Test
    void testWin() throws InterruptedException {
        when(settingsModel.isSoundOn()).thenReturn(true);

        controller.setGameOverState(true, 100);

        verify(gameOver, times(1)).setGameOver(true, 100);
        verify(sound, times(1)).play("/audio/winSound.wav");
    }

    @Test
    void testLoss() throws InterruptedException {
        when(settingsModel.isSoundOn()).thenReturn(true);

        controller.setGameOverState(false, 50);

        verify(gameOver, times(1)).setGameOver(false, 50);
        verify(sound, times(1)).play("/audio/loseSound.wav");
    }

    @Test
    void testSoundOff() throws InterruptedException {
        when(settingsModel.isSoundOn()).thenReturn(false);

        controller.setGameOverState(true, 100);

        verify(gameOver, times(1)).setGameOver(true, 100);
        verify(sound, never()).play(anyString());
    }

    @Test
    void testEscape() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        KeyStroke escapeKey = mock(KeyStroke.class);
        when(escapeKey.getKeyType()).thenReturn(KeyType.Escape);
        when(screen.readInput()).thenReturn(escapeKey);

        controller.processInput();

        verify(screen, times(1)).readInput();
    }

    @Test
    void testUpdateView() {
        controller.updateView();

        verify(gameOver, times(1)).redrawScreen();
    }
}
