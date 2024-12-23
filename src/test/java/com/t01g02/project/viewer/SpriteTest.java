package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import com.t01g02.project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class SpriteTest {
    private Screen stubScreen;
    private TextGraphics stubTextGraphics;
    private Sprite sprite;

    @BeforeEach
    void setUp() throws IOException {
        stubScreen = mock(Screen.class);
        stubTextGraphics = mock(TextGraphics.class);

        when(stubScreen.newTextGraphics()).thenReturn(stubTextGraphics);

        sprite = new Sprite(stubScreen, "src/test/resources/characters/hellokitty.png");
    }

    @Test
    void testDrawPixel() {
        Position pixelPos = new Position(5, 5);
        TextColor color = new TextColor.RGB(255, 0, 0);

        sprite.drawPixel(pixelPos, color);

        verify(stubTextGraphics, times(1)).setBackgroundColor(color);
        verify(stubTextGraphics, times(1)).setCharacter(pixelPos.getX(), pixelPos.getY(), ' ');
    }
}
