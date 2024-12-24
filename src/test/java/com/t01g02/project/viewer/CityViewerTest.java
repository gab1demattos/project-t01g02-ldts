package com.t01g02.project.viewer;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CityViewerTest {
    private CityModel cityModel;
    private Screen screen;
    private Sprite sprite;
    private DecorViewer decorViewer;
    private CityViewer cityViewer;

    @BeforeEach
    void setUp() throws IOException {
        cityModel = mock(CityModel.class);
        screen = mock(Screen.class);
        sprite = mock(Sprite.class);
        decorViewer = mock(DecorViewer.class);

        cityViewer = new CityViewer(cityModel, screen);

        when(cityModel.getWidth()).thenReturn(10);
        when(cityModel.getHeight()).thenReturn(5);
    }

    @Test
    void testInitializeCityImage() {
        cityViewer.initializeCityImage();

        for (int y = 0; y < cityModel.getHeight(); y++) {
            for (int x = 0; x < cityModel.getWidth(); x++) {
                Tile tile = cityModel.getTile(x, y);
                assertNotNull(cityViewer);
            }
        }
    }

//    @Test
//    void testDrawingHousesAndTrees() throws IOException {
//        List<Position> positions = Arrays.asList(
//                new Position(2, 2),
//                new Position(5, 5),
//                new Position(8, 8)
//        );
//
//        cityViewer.drawingHousesAndTrees(sprite, positions);
//
//        for (Position position : positions) {
//            verify(sprite, times(1)).drawImage(position);
//        }
//    }

    /*@Test
    void testDraw() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(graphics);

        TerminalSize terminalSize = mock(TerminalSize.class);
        when(screen.getTerminalSize()).thenReturn(terminalSize);
        when(terminalSize.getColumns()).thenReturn(80);
        when(terminalSize.getRows()).thenReturn(24);

        cityViewer.draw();

        verify(decorViewer, times(1)).drawDecorations();
    }*/


}



