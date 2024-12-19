package com.t01g02.project.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.menu.SettingsModel;
import com.t01g02.project.menu.Sound;
import com.t01g02.project.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.t01g02.project.model.CharacterModel.hellokitty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KittyControllerTest {
    private KittyController kittyController;
    private CityModel cityModel;
    private Screen screen;
    private Sound sound;
    private SettingsModel settingsModel;
    private Set<KeyStroke> keyStrokes;


    @BeforeEach
    void setUp() throws Exception {
        screen = mock(Screen.class);
        cityModel = mock(CityModel.class);
        sound = mock(Sound.class);
        settingsModel = mock(SettingsModel.class);

        hellokitty = mock(CharacterModel.class);

        kittyController = new KittyController(screen, cityModel, sound, settingsModel, hellokitty);

        keyStrokes = new HashSet<>();

        Position initialPosition = new Position(5, 5);
        when(hellokitty.getPosition()).thenReturn(initialPosition);

        KittyController.speed = mock(Speed.class);
        when(KittyController.speed.getSpeed()).thenReturn(2);
    }

    @Test
    void testMoveKittyUp() throws Exception {
        KeyStroke upArrow = mock(KeyStroke.class);
        when(upArrow.getKeyType()).thenReturn(KeyType.ArrowUp);
        keyStrokes.add(upArrow);
        kittyController.processInput(keyStrokes);

        Position expectedPosition = new Position(5, 5 - 2);

        Position newPosition = hellokitty.getPosition();

        assertEquals(expectedPosition.getX(), newPosition.getX());
        assertEquals(expectedPosition.getY(), newPosition.getY());
    }
    @Test
    void testCanMove() {
        Position validPosition = new Position(5, 5);
        Position invalidPosition = new Position(-1, -1);

        Tile roadTile = mock(Tile.class);
        when(roadTile.getType()).thenReturn(Tile.Type.ROAD);

        when(cityModel.getTile(5, 5)).thenReturn(roadTile);
        when(cityModel.getTile(-1, -1)).thenReturn(null);

        // Test for a valid position
        assertTrue(kittyController.canMove(validPosition), "Expected canMove to return true for a valid position");

        // Test for an invalid position
        assertFalse(kittyController.canMove(invalidPosition), "Expected canMove to return false for an invalid position");
    }
}


