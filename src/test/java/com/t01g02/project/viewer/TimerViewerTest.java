package com.t01g02.project.viewer;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.Timer;
import org.junit.jupiter.api.BeforeEach;
import com.googlecode.lanterna.SGR;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;


public class TimerViewerTest {
    private Timer timer;
    private Screen screen;
    private TextGraphics graphics;
    private TimerViewer timerViewer;

    @BeforeEach
    public void setUp() {
        timer = mock(Timer.class);
        screen = mock(Screen.class);
        graphics = mock(TextGraphics.class);

        when(timer.getFormattedTime()).thenReturn("00:01:23");

        when(screen.newTextGraphics()).thenReturn(graphics);

        timerViewer = new TimerViewer(timer, screen);
    }

    @Test
    public void testDrawStringSprite() {
        TerminalSize terminalSize = mock(TerminalSize.class);
        when(screen.getTerminalSize()).thenReturn(terminalSize);
        when(terminalSize.getColumns()).thenReturn(100);

        timerViewer.drawStringSprite("TIMER", 10, 5, graphics);

        String[] sprite = LetterSprites.getStringSprite("TIMER");
        for (int i = 0; i < sprite.length; i++) {
            verify(graphics).putString(10, 5 + i, sprite[i], SGR.BOLD);
        }
    }


}
