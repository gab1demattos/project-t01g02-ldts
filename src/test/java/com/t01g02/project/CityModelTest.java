package com.t01g02.project;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CityModelTest {
    private CityModel city;

    @BeforeEach
    void setUp() {
        city = new CityModel(500, 300);
        city.initializeRoads();
    }

    @Test
    void testCityInitialization() {
        assertEquals(500, city.getWidth());
        assertEquals(300, city.getHeight());
    }

    @Test
    void testTilePlacement() {
        Position start = new Position(0, 0);
        Position end = new Position(10, 0);
        Tile.fillLine(city.getMap(), start, end, Tile.Type.ROAD, TextColor.Factory.fromString("#000000"));

        for (int x = 0; x <= 10; x++) {
            Tile tile = city.getTile(x, 0);
            assertNotNull(tile, "Tile should not be null");
            assertEquals(Tile.Type.ROAD, tile.getType(), "Tile type should be ROAD");
        }
    }

    @Test
    void testInitializedRoads() {

        Tile tile = city.getTile(2, 15);
        assertNotNull(tile, "Tile should not be null at road position");
        assertEquals(Tile.Type.ROAD, tile.getType(), "Tile type should be ROAD");
        assertEquals(TextColor.Factory.fromString("#888888"), tile.getColor(), "Tile color should match road color");
    }

    @Test
    void testOutOfBoundsTileAccess() {
        assertNull(city.getTile(-1, -1), "Negative indices should return null");
        assertNull(city.getTile(501, 301), "Indices beyond boundaries should return null");
    }

}