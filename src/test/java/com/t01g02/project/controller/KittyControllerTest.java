package com.t01g02.project.controller;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.menu.SettingsModel;
import com.t01g02.project.menu.Sound;
import com.t01g02.project.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.t01g02.project.model.CharacterModel.hellokitty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        Position initialPosition = new Position(5, 5);

        when(hellokitty.getPosition()).thenReturn(initialPosition);

        doAnswer(invocation -> {
            Position newPosition = invocation.getArgument(0);
            when(hellokitty.getPosition()).thenReturn(newPosition);
            return null;
        }).when(hellokitty).setPosition(any(Position.class));

        kittyController = new KittyController(screen, cityModel, sound, settingsModel, hellokitty);
        PopUpsModel.mudpopups.clear();
        PopUpsModel.speedpopups.clear();

    }
    private void mockCityModelTiles() {
        Tile[][] map = new Tile[35][35];

        for (int x = 0; x < 35; x++) {
            for (int y = 0; y < 35; y++) {
                map[y][x] = new Tile(Tile.Type.EMPTY, TextColor.Factory.fromString("#000000"));
            }
        }
        Position start = new Position(1, 1);
        Position end = new Position(1, 30);
        TextColor roadColor = TextColor.Factory.fromString("#222222");

        Tile.fillLine(map, start, end, Tile.Type.ROAD, roadColor);


        Position startHorizontal = new Position(1, 1);
        Position endHorizontal = new Position(30, 1);
        Tile.fillLine(map, startHorizontal, endHorizontal, Tile.Type.ROAD, roadColor);

        when(cityModel.getTile(anyInt(), anyInt())).thenAnswer(invocation -> {
            int x = invocation.getArgument(0); // Retrieve the x-coordinate
            int y = invocation.getArgument(1); // Retrieve the y-coordinate
            return (x >= 0 && y >= 0 && x < map.length && y < map[0].length) ? map[y][x] : null;
        });
    }

    @Test
    void testMoveKittyUp() throws Exception {
        KittyController kittyController = new KittyController(screen, cityModel, sound, settingsModel, hellokitty);
        mockCityModelTiles();

        Set<KeyStroke> keyStrokes = new HashSet<>();
        KeyStroke upArrow = mock(KeyStroke.class);
        when(upArrow.getKeyType()).thenReturn(KeyType.ArrowUp);
        keyStrokes.add(upArrow);

        Tile roadTile = mock(Tile.class);
        when(roadTile.getType()).thenReturn(Tile.Type.ROAD);


        Position initialPosition = new Position(5, 5);
        hellokitty.setPosition(initialPosition);


        kittyController.processInput(keyStrokes);


        Position expectedPosition = new Position(5, 3);
        Position newPosition = hellokitty.getPosition();

        assertEquals(expectedPosition.getX(), newPosition.getX());
        assertEquals(expectedPosition.getY(), newPosition.getY());
    }


    @Test
    void testCanMove() {
        mockCityModelTiles();

        Position validPosition = new Position(2, 2);
        assertTrue(kittyController.canMove(validPosition));

        Position invalidPosition = new Position(32, 32);
        assertFalse(kittyController.canMove(invalidPosition));

        Position outOfBounds = new Position(-5, -5);
        when(cityModel.getTile(-5, -5)).thenReturn(null);
        assertFalse(kittyController.canMove(outOfBounds));
    }

    @Test
    void testActivateMudPopUp() throws IOException {
        Position newPosition = new Position(2, 3);
        PopUpsModel mudPopup = mock(PopUpsModel.class);
        when(mudPopup.getPosition()).thenReturn(newPosition);

        PopUpsModel.mudpopups.add(mudPopup);
        when(settingsModel.isSoundOn()).thenReturn(true);

        kittyController.activatePopUps(newPosition);

        assertTrue(kittyController.isMudOn);
        assertFalse(kittyController.isSpeedOn);
        verify(sound).play("/audio/mudSound.wav");

        assertFalse(PopUpsModel.mudpopups.contains(mudPopup));
    }

    @Test
    void testActivateSpeedPopUps() throws IOException {
        Position newPosition = new Position(5, 6);
        PopUpsModel speedPopup = mock(PopUpsModel.class);
        when(speedPopup.getPosition()).thenReturn(newPosition);

        PopUpsModel.speedpopups.add(speedPopup);
        when(settingsModel.isSoundOn()).thenReturn(true);

        kittyController.activatePopUps(newPosition);

        assertTrue(kittyController.isSpeedOn);
        assertFalse(kittyController.isMudOn);
        verify(sound).play("/audio/boltSound.wav");

        assertFalse(PopUpsModel.speedpopups.contains(speedPopup));
    }

    @Test
    void testActivateStarPopUps_ShouldPickStar_WhenPositionMatches() throws IOException {
        Position starPosition = new Position(7, 8);
        Position newPosition = new Position(7, 8);

        PopUpsModel starPopup = mock(PopUpsModel.class);
        when(starPopup.getPosition()).thenReturn(starPosition);

        when(settingsModel.isSoundOn()).thenReturn(true);

        kittyController.activatePopUps(newPosition);

        assertTrue(kittyController.hasStarBeenPicked, "Star should be picked after activation");

        verify(sound).play("/audio/starSound.wav");

        assertNull(PopUpsModel.getStar(), "Star should be null after deletion");
    }


}


