package com.t01g02.project.viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class CityViewerTest {
    private CityModel stubCity;
    private Screen stubScreen;
    private TextGraphics stubTextGraphics;
    private Sprite stubSprite;
    private CityViewer cityViewer;

    @BeforeEach
    void setUp() throws IOException {
        stubCity = mock(CityModel.class);
        stubScreen = mock(Screen.class);
        stubTextGraphics = mock(TextGraphics.class);
        stubSprite = mock(Sprite.class);

        when(stubScreen.newTextGraphics()).thenReturn(stubTextGraphics);
        when(stubCity.getWidth()).thenReturn(345);
        when(stubCity.getHeight()).thenReturn(180);
        when(stubCity.getHousePositions()).thenReturn(Collections.emptyList());
        when(stubCity.getTreePositions()).thenReturn(Collections.emptyList());
        when(stubCity.getLighttreePositions()).thenReturn(Collections.emptyList());
        when(stubCity.getYellowHousePositions()).thenReturn(Collections.emptyList());
        when(stubCity.getBlueHousePositions()).thenReturn(Collections.emptyList());
        when(stubCity.getPinkHousePositions()).thenReturn(Collections.emptyList());

        cityViewer = spy(new CityViewer(stubCity, stubScreen));
    }

    @Test
    void testInitializeCityImage() {
        when(stubCity.getWidth()).thenReturn(345);
        when(stubCity.getHeight()).thenReturn(180);

        cityViewer.initializeCityImage();
    }

    @Test
    void testDrawingHousesAndTrees() throws IOException {
        List<Position> positions = List.of(new Position(10, 10), new Position(20, 20));
        doNothing().when(stubSprite).drawImage(any(Position.class));

        cityViewer.drawingHousesAndTrees(stubSprite, positions);

        verify(stubSprite, times(positions.size())).drawImage(any(Position.class));
    }

    @Test
    void testDraw() throws IOException {
        when(stubScreen.getTerminalSize()).thenReturn(new TerminalSize(80, 24));
        doNothing().when(cityViewer).drawingHousesAndTrees(any(Sprite.class), anyList());

        cityViewer.draw();

        verify(stubTextGraphics).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 24), ' ');

        verify(stubTextGraphics).drawImage(eq(new TerminalPosition(0, 0)), any());

        verify(cityViewer, times(6)).drawingHousesAndTrees(any(Sprite.class), anyList());
    }
}
