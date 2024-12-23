package com.t01g02.project.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.SGR;
import com.t01g02.project.model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ScoreViewerTest {

    private Score scoreMock;
    private Screen screenMock;
    private TextGraphics textGraphicsMock;
    private ScoreViewer scoreViewer;

    @BeforeEach
    void setUp() {
        scoreMock = mock(Score.class);
        screenMock = mock(Screen.class);
        textGraphicsMock = mock(TextGraphics.class);

        when(screenMock.newTextGraphics()).thenReturn(textGraphicsMock);

        scoreViewer = new ScoreViewer(scoreMock, screenMock);
    }

    @Test
    void testDrawScore() throws IOException {
        when(scoreMock.getScore()).thenReturn(123);

        scoreViewer.draw(10, 5);

        verify(textGraphicsMock, atLeastOnce()).setForegroundColor(new TextColor.RGB(133, 78, 96));
        verify(textGraphicsMock, atLeastOnce()).setBackgroundColor(new TextColor.RGB(255, 240, 245));

        verify(textGraphicsMock, atLeastOnce()).putString(anyInt(), anyInt(), anyString(), eq(SGR.BOLD));
        verify(scoreMock, times(1)).getScore();
    }
}