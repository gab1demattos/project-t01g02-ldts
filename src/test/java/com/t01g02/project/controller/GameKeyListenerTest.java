package com.t01g02.project.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameKeyListenerTest {
    private GameKeyListener gameKeyListener;
    @BeforeEach
    void setUp(){
        gameKeyListener = new GameKeyListener();
    }
    @Test
    void testArrowKeyPressed(){
        KeyEvent upEvent = mock(KeyEvent.class);
        when(upEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);

        gameKeyListener.keyPressed(upEvent);

        Set<KeyStroke> keys = gameKeyListener.getKeys();
        assertTrue(keys.contains(new KeyStroke(KeyType.ArrowUp)));

        KeyEvent downEvent = mock(KeyEvent.class);
        when(downEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);

        gameKeyListener.keyPressed(downEvent);

        assertTrue(keys.contains(new KeyStroke(KeyType.ArrowDown)));
    }
    @Test
    void testArrowKeyReleased(){
        gameKeyListener.getKeys().add(new KeyStroke(KeyType.ArrowUp));
        gameKeyListener.getKeys().add(new KeyStroke(KeyType.ArrowDown));

        KeyEvent upReleaseEvent = mock(KeyEvent.class);
        when(upReleaseEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);

        gameKeyListener.keyReleased(upReleaseEvent);

        Set<KeyStroke> keys = gameKeyListener.getKeys();
        assertFalse(keys.contains(new KeyStroke(KeyType.ArrowUp)));
        assertTrue(keys.contains(new KeyStroke(KeyType.ArrowDown)));
    }
    @Test
    void testKeyPressed_InvalidKey() {
        KeyEvent invalidEvent = mock(KeyEvent.class);
        when(invalidEvent.getKeyCode()).thenReturn(KeyEvent.VK_A);

        gameKeyListener.keyPressed(invalidEvent);

        Set<KeyStroke> keys = gameKeyListener.getKeys();
        assertTrue(keys.isEmpty());
    }
}
