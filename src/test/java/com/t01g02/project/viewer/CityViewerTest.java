package com.t01g02.project.viewer;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.BasicTextImage;
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
    private TextGraphics graphics;
    private TerminalSize terminalSize;
    private DecorViewer decorViewer;
    private CityViewer cityViewer;

    @BeforeEach
    void setUp() throws IOException {
        cityModel = mock(CityModel.class);
        screen = mock(Screen.class);
        sprite = mock(Sprite.class);
        decorViewer = mock(DecorViewer.class);
        graphics = mock(TextGraphics.class);
        terminalSize = mock(TerminalSize.class);

        when(screen.newTextGraphics()).thenReturn(graphics);
        when(screen.getTerminalSize()).thenReturn(terminalSize);
        when(terminalSize.getColumns()).thenReturn(80);
        when(terminalSize.getRows()).thenReturn(24);

        when(cityModel.getWidth()).thenReturn(10);
        when(cityModel.getHeight()).thenReturn(5);

        cityViewer = spy(new CityViewer(cityModel, screen));
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

    @Test
    void testDrawingHouseAndTrees_MixedPos(){
        Position validPosition1 = new Position(2, 2);
        Position validPosition2 = new Position(3, 3);
        Position invalidPosition = new Position(-1, -1);

        List<Position> positions = Arrays.asList(validPosition1, validPosition2, invalidPosition);

        cityViewer.drawingHousesAndTrees(sprite, positions);

        verify(sprite, times(1)).drawImage(validPosition1);
        verify(sprite, times(1)).drawImage(validPosition2);
        verify(sprite, never()).drawImage(invalidPosition);
    }

    @Test
    void testDraw() throws IOException {
        when(screen.newTextGraphics()).thenReturn(graphics);
        cityViewer.draw();
        verify(graphics, times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 24), ' ');
        verify(graphics, atLeastOnce()).setCharacter(anyInt(), anyInt(), eq(' '));

    }
}



