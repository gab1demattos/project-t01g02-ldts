package com.t01g02.project;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.Score;
import com.t01g02.project.viewer.CityViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class CityViewerTest {
    private CityModel stubCity;
    private Screen stubScreen;
    private TextGraphics stubTextGraphics;
    private CityViewer cityViewer;

    @BeforeEach
    void setUp() throws IOException {
        stubCity = mock(CityModel.class);
        stubScreen = mock(Screen.class);
        stubTextGraphics = mock(TextGraphics.class);

        when(stubScreen.newTextGraphics()).thenReturn(stubTextGraphics);
        Score score = new Score(0);
        cityViewer = new CityViewer(stubCity, stubScreen);
    }

    @Test
    void testDrawBackground() throws IOException {
        when(stubScreen.getTerminalSize()).thenReturn(new TerminalSize(80, 24));
        when(stubCity.getHeight()).thenReturn(0);
        when(stubCity.getWidth()).thenReturn(0);

        cityViewer.draw();

        verify(stubTextGraphics).setBackgroundColor(TextColor.Factory.fromString("#FFE1EA"));
        verify(stubTextGraphics).fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(80, 24),
                ' '
        );
        verify(stubScreen).refresh();
    }
}
