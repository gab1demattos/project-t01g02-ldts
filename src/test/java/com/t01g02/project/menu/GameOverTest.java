package com.t01g02.project.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameOverTest {
    @Mock
    private Screen screen;

    @Mock
    private TextGraphics graphics;

    private GameOver gameOver;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        when(screen.newTextGraphics()).thenReturn(graphics);
        when(screen.getTerminalSize()).thenReturn(new TerminalSize(80, 24)); // Mock screen size
        gameOver = new GameOver(screen);
    }
    @Test
    void testInitialState() {
        assertFalse(gameOver.isWin());
        assertFalse(gameOver.isGameOver());
    }

    @Test
    void testSetGameOverWin() {
        gameOver.setGameOver(true, 100);
        assertTrue(gameOver.isWin());
        assertTrue(gameOver.isGameOver());
        assertEquals(100,gameOver.getFinalScore());
    }

    @Test
    void testSetGameOverLose() {
        gameOver.setGameOver(false, 50);
        assertFalse(gameOver.isWin());
        assertTrue(gameOver.isGameOver());
        assertEquals(50,gameOver.getFinalScore());
    }

    @Test
    void testRedrawScreenGameOverWin() throws IOException {
        gameOver.setGameOver(true, 100);
        gameOver.redrawScreen();

        verify(screen).clear();
        verify(graphics).setBackgroundColor(new TextColor.RGB(229, 168, 177));
        verify(graphics).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 24), ' ');

        String winMessage = "Congratulations! You did it!";
        verify(graphics).setForegroundColor(new TextColor.RGB(149, 88, 97));
        verify(graphics).putString(new TerminalPosition(40 - winMessage.length() / 2, 12 - 2), winMessage, SGR.BOLD);

        String scoreMessage = "Final score: 100";
        verify(graphics).putString(new TerminalPosition(40 - scoreMessage.length() / 2, 12), scoreMessage, SGR.BOLD);

        String menuMessage = "Press Esc to leave";
        verify(graphics).putString(new TerminalPosition(40 - menuMessage.length() / 2, 12 + 2), menuMessage, SGR.BORDERED);

        verify(screen).refresh();
    }

    @Test
    void testRedrawScreenGameOverLose() throws IOException {
        gameOver.setGameOver(false, 50);

        gameOver.redrawScreen();

        verify(screen).clear();
        verify(graphics).setBackgroundColor(new TextColor.RGB(229, 168, 177));
        verify(graphics).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 24), ' ');

        String loseMessage = "You didn´t make it on time :(";
        verify(graphics).setForegroundColor(new TextColor.RGB(149, 88, 97));
        verify(graphics).putString(new TerminalPosition(40 - loseMessage.length() / 2, 12 - 2), loseMessage, SGR.BOLD);

        String scoreMessage = "Final score: 50";
        verify(graphics).putString(new TerminalPosition(40 - scoreMessage.length() / 2, 12), scoreMessage, SGR.BOLD);

        String menuMessage = "Press Esc to leave";
        verify(graphics).putString(new TerminalPosition(40 - menuMessage.length() / 2, 12 + 2), menuMessage, SGR.BORDERED);

        verify(screen).refresh();
    }

    @Test
    void testRedrawScreenIOException() throws IOException {
        doThrow(new IOException()).when(screen).refresh();

        gameOver.setGameOver(true, 100);

        assertThrows(RuntimeException.class, () -> gameOver.redrawScreen());
    }
    @Test
    void testRedrawButtons() {
        gameOver.redrawButtons();

        verifyNoInteractions(screen);
        verifyNoInteractions(graphics);
    }

}
