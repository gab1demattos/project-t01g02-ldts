package com.t01g02.project.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.mockito.Mockito.*;

class SettingsControllerTest {

    @Mock private SettingsModel model;
    @Mock private SettingsView view;
    @Mock private Screen screen;
    @Mock private Music music;
    @Mock private Sound sound;
    @Mock private GameMenuController gameMenuController;

    private SettingsController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new SettingsController(view, screen, model, music, sound, gameMenuController);

        when(model.getOptions()).thenReturn(new String[]{"Music", "Sound"});
        when(model.getMusicOptions()).thenReturn(new String[]{"On", "Off"});
        when(model.getSoundOptions()).thenReturn(new String[]{"On", "Off"});
        when(model.getSelectedOption()).thenReturn(0);
        when(model.getMusicSelectedOption()).thenReturn(0);
        when(model.getSoundSelectedOption()).thenReturn(0);
    }

    @Test
    void testPressEsc() throws IOException {
        when(screen.readInput()).thenReturn(new KeyStroke(KeyType.Escape));
        when(model.isSoundOn()).thenReturn(true);

        controller.processInput();

        verify(sound).play("/audio/keyPressSound.wav");
        verify(gameMenuController).setInSettings(false);
        verify(gameMenuController).updateView();
    }

    @Test
    void testArrowLeft() throws IOException {
        when(screen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft));
        when(model.isSoundOn()).thenReturn(true);

        controller.processInput();

        verify(sound).play("/audio/arrowMenuSound.wav");
        verify(model).setSelectedOption(anyInt());
        verify(view).redrawScreen();
    }

    @Test
    void testArrowRight() throws IOException {
        when(screen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowRight));
        when(model.isSoundOn()).thenReturn(true);

        controller.processInput();

        verify(sound).play("/audio/arrowMenuSound.wav");
        verify(model).setSelectedOption(anyInt());
        verify(view).redrawScreen();
    }

    @Test
    void testPressB() throws IOException {
        when(screen.readInput()).thenReturn(new KeyStroke('b', false, false));
        when(model.isSoundOn()).thenReturn(true);
        when(model.getSelectedOption()).thenReturn(0);

        controller.processInput();

        verify(sound).play("/audio/keyPressSound.wav");
        verify(model).setMusicSelectedOption(anyInt());
        verify(view).redrawScreen();
    }

    @Test
    void testUpdateView() throws IOException {
        when(screen.doResizeIfNecessary()).thenReturn(null);

        controller.updateView();

        verify(view, times(1)).redrawScreen();
        verify(view).drawBInfo(anyBoolean());
    }
}

