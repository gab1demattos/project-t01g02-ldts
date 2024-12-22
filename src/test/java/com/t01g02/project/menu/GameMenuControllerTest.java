package com.t01g02.project.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class GameMenuControllerTest {
    @Mock private IModel mockModel;
    @Mock private Screen mockScreen;
    @Mock private SettingsModel mockSettingsModel;
    @Mock private SettingsView mockSettingsView;
    @Mock private Music mockMusic;
    @Mock private Sound mockSound;
    @Mock private GameOver mockGameOverView;
    @Mock private GameMenuView mockView;
    @Mock private GameOverController mockGameOverController;

    private GameMenuController controller;

    @BeforeEach
    public void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException{
        MockitoAnnotations.openMocks(this);
        String[] mockOptions = {"Settings","Play"};
        when(mockModel.getOptions()).thenReturn(mockOptions);
        controller = new GameMenuController(mockView, mockScreen,mockModel,mockSettingsModel,mockSettingsView,mockMusic,mockSound,mockGameOverView);
    }

    @Test
    public void testProcessInputEscape() throws IOException, URISyntaxException, FontFormatException, InterruptedException{
        when(mockScreen.readInput()).thenReturn(new KeyStroke(KeyType.Escape));
        controller.processInput();
        verify(mockScreen).refresh();
    }

    @Test
    public void testProcessInputArrowLeft() throws IOException, URISyntaxException, FontFormatException, InterruptedException{
        when(mockScreen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft));
        when(mockSettingsModel.isSoundOn()).thenReturn(true);

        controller.processInput();

        verify(mockSound).play("/audio/arrowMenuSound.wav");
        verify(mockModel).setSelectedOption(anyInt());
        verify(mockView).redrawButtons();
        verify(mockScreen).refresh();
    }

    @Test
    public void testProcessInputArrowRight() throws IOException, URISyntaxException, FontFormatException, InterruptedException{
        when(mockScreen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowRight));
        when(mockSettingsModel.isSoundOn()).thenReturn(true);

        controller.processInput();

        verify(mockSound).play("/audio/arrowMenuSound.wav");
        verify(mockModel).setSelectedOption(anyInt());
        verify(mockView).redrawButtons();
        verify(mockScreen).refresh();
    }

    @Test
    public void testProcessInputEnter() throws IOException, URISyntaxException, FontFormatException, InterruptedException{
        when(mockScreen.readInput()).thenReturn(new KeyStroke(KeyType.Enter));
        when(mockSettingsModel.isSoundOn()).thenReturn(true);

        controller.processInput();

        verify(mockSound).play("/audio/selectSound.wav");
        verify(mockScreen).refresh();
    }

    @Test
    public void testExecuteSelectedOption() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        when(mockModel.getOptions()).thenReturn(new String[]{"Settings", "Play"});
        when(mockModel.getSelectedOption()).thenReturn(0);

        controller.executeSelectedOption();

        assertTrue(controller.isInSettings());
        mockView.redrawScreen();
    }

    @Test
    public void testOptionPlay() throws IOException, URISyntaxException, FontFormatException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        when(mockModel.getOptions()).thenReturn(new String[]{"Settings", "Play"});
        when(mockModel.getSelectedOption()).thenReturn(1);

        doNothing().when(mockMusic).play(anyString(), anyBoolean(), anyBoolean());
        doNothing().when(mockMusic).stop();
        doNothing().when(mockScreen).refresh();

        controller.executeSelectedOption();

        verify(mockMusic).play("/audio/gameSong.wav", true, true);
        verify(mockScreen).refresh();
    }

    @Test
    public void testOptionSelected() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        when(mockModel.getOptions()).thenReturn(new String[]{"Settings", "Play"});
        when(mockModel.getSelectedOption()).thenReturn(0);

        controller.executeSelectedOption();

        assertTrue(controller.isInSettings());
        mockView.redrawScreen();

    }

    @Test
    public void testOnGameOver(){
        controller.onGameOver(true,100);

        assertTrue(controller.isInGameOver());

        ArgumentCaptor<Boolean> winCaptor = ArgumentCaptor.forClass(Boolean.class);
        ArgumentCaptor<Integer> scoreCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(mockGameOverView, times(2)).setGameOver(winCaptor.capture(), scoreCaptor.capture());
        assertEquals(true, winCaptor.getAllValues().get(0));
        assertEquals(true, winCaptor.getAllValues().get(1));
        assertEquals(100, scoreCaptor.getAllValues().get(0));
        assertEquals(100, scoreCaptor.getAllValues().get(1));

        mockView.redrawScreen();
    }

    @Test
    public void testIsRunning(){
        assertTrue(controller.isRunning());
    }

}
