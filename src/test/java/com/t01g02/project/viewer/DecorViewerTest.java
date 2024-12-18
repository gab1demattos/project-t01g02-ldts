package com.t01g02.project.viewer;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DecorViewerTest {
    private Screen stubScreen;
    private TextGraphics stubTextGraphics;
    private Sprite stubSprite;
    private DecorViewer decorViewer;

    @BeforeEach
    void setUp() throws IOException {
        stubScreen = mock(Screen.class);
        stubTextGraphics = mock(TextGraphics.class);

        when(stubScreen.newTextGraphics()).thenReturn(stubTextGraphics);

        stubSprite = mock(Sprite.class);

        decorViewer = new DecorViewer(stubScreen);
    }

    @Test
    void testHousePositions() {
        List<Position> housePositions = decorViewer.getHousePositions();
        assertEquals(4, housePositions.size());
        assertTrue(housePositions.contains(new Position(34, 20)));
    }

    @Test
    void testTreePositions() {
        List<Position> treePositions = decorViewer.getTreePositions();
        assertEquals(8, treePositions.size());
        assertTrue(treePositions.contains(new Position(307, 20)));
    }

    @Test
    void testDrawElements() throws IOException {
        List<Position> positions = List.of(
                new Position(1, 1),
                new Position(2, 2)
        );

        decorViewer.drawElements(stubSprite, positions);

        verify(stubSprite, times(2)).drawImage(any(Position.class));
        verify(stubSprite).drawImage(new Position(1, 1));
        verify(stubSprite).drawImage(new Position(2, 2));
    }

    @Test
    void testDrawDecorations() throws IOException {
        Sprite mockHouse = mock(Sprite.class);
        Sprite mockTree = mock(Sprite.class);
        Sprite mockLightTree = mock(Sprite.class);
        Sprite mockYellowHouse = mock(Sprite.class);
        Sprite mockBlueHouse = mock(Sprite.class);
        Sprite mockPinkHouse = mock(Sprite.class);

        DecorViewer decorViewer = new DecorViewer(stubScreen) {
            @Override
            public void drawDecorations() throws IOException {
                drawElements(mockHouse, getHousePositions());
                drawElements(mockTree, getTreePositions());
                drawElements(mockLightTree, getLighttreePositions());
                drawElements(mockYellowHouse, getYellowHousePositions());
                drawElements(mockBlueHouse, getBlueHousePositions());
                drawElements(mockPinkHouse, getPinkHousePositions());
            }
        };

        decorViewer.drawDecorations();

        verify(mockHouse, times(decorViewer.getHousePositions().size())).drawImage(any(Position.class));
        verify(mockTree, times(decorViewer.getTreePositions().size())).drawImage(any(Position.class));
        verify(mockLightTree, times(decorViewer.getLighttreePositions().size())).drawImage(any(Position.class));
        verify(mockYellowHouse, times(decorViewer.getYellowHousePositions().size())).drawImage(any(Position.class));
        verify(mockBlueHouse, times(decorViewer.getBlueHousePositions().size())).drawImage(any(Position.class));
        verify(mockPinkHouse, times(decorViewer.getPinkHousePositions().size())).drawImage(any(Position.class));
    }
}

