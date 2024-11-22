package com.t01g02.project;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CityModelTest {
    private CityModel city;

    @BeforeEach
    void setUp() {
        city = new CityModel(500, 300);
        city.initializeRoads(); // Initialize roads before tests
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
        // Test a specific road segment initialized in `initializeRoads()`
        Tile tile = city.getTile(2, 15);
        assertNotNull(tile, "Tile should not be null at road position");
        assertEquals(Tile.Type.ROAD, tile.getType(), "Tile type should be ROAD");
        assertEquals(TextColor.Factory.fromString("#888888"), tile.getColor(), "Tile color should match road color");
    }

    @Test
    void testOutOfBoundsTileAccess() {
        // Ensure out-of-bounds access returns null or throws exceptions
        assertNull(city.getTile(-1, -1), "Negative indices should return null");
        assertNull(city.getTile(501, 301), "Indices beyond boundaries should return null");
    }
}