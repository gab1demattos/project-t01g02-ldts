package com.t01g02.project.menu;

import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameMenuControllerTest {
    @Mock private IView mockView;
    @Mock private IModel mockModel;
    @Mock private Screen mockScreen;
    @Mock private SettingsModel mockSettingsModel;
    @Mock private SettingsView mockSettingsView;
    @Mock private Music mockMusic;
    @Mock private Sound mockSound;
    @Mock private GameOver mockGameOverView;
    @Mock private SettingsController mockSettingsController;
    @Mock private GameOverController mockGameOverController;

    private GameMenuController controller;

    @BeforeEach
    public void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException{
        MockitoAnnotations.openMocks(this);
        controller = new GameMenuController((GameMenuView)mockView, mockScreen,mockModel,mockSettingsModel,mockSettingsView,mockMusic,mockSound,mockGameOverView);
    }

    @Test
    public void testProcessInputEscape() throws IOException, URISyntaxException, FontFormatException, InterruptedException{

    }

}
