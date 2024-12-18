package com.t01g02.project.model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CityModelTest {
    private CityModel city;
    private Screen screen;

    @BeforeEach
    void setUp() throws IOException {
        CharacterModel.initializeCharacters(screen);
        city = new CityModel(345, 195);
        city.initializeRoads();
        city.initializeZones();
    }

    @Test
    void testCityInitialization() {
        assertEquals(345, city.getWidth());
        assertEquals(195, city.getHeight());
    }

    @Test
    void testTilePlacement() {
        Position start = new Position(0, 0);
        Position end = new Position(10, 0);
        Tile.fillLine(city.getMap(), start, end, Tile.Type.ROAD, TextColor.Factory.fromString("#000000"));

        for (int x = 0; x <= 10; x++) {
            Tile tile = city.getTile(x, 0);
            assertNotNull(tile);
            assertEquals(Tile.Type.ROAD, tile.getType());
        }
    }

    @Test
    void testInitializedRoads() {
        List<Position> roadCheckPositions = List.of(
                new Position(0, 0),
                new Position(65, 0),
                new Position(130, 0),
                new Position(255, 179)
        );

        for (Position position : roadCheckPositions) {
            Tile tile = city.getTile(position.getX(), position.getY());
            assertNotNull(tile);
            assertEquals(Tile.Type.ROAD, tile.getType());
            assertEquals(TextColor.Factory.fromString("#222222"), tile.getColor());
        }
    }

    @Test
    void testInitializedZones() {
        List<Zone> zones = city.getZones();

        assertNotNull(zones);
        assertEquals(5, zones.size());
    }

    @Test
    void testObjectPositions() {
        List<Position> housePositions = city.getHousePositions();
        List<Position> treePositions = city.getTreePositions();

        assertNotNull(housePositions);
        assertNotNull(treePositions);

        assertTrue(housePositions.contains(new Position(34, 20)));
        assertTrue(treePositions.contains(new Position(26, 18)));
    }*/

    @Test
    void testOutOfBoundsTileAccess() {
        assertNull(city.getTile(-1, -1));
        assertNull(city.getTile(501, 301));
    }

    /*@Test
    void testZonesTileMapping() {
        for (Zone zone : city.getZones()) {
            for (int y = zone.getStartposition().getY(); y <= zone.getEndposition().getY(); y++) {
                for (int x = zone.getStartposition().getX(); x <= zone.getEndposition().getX(); x++) {
                    Tile tile = city.getTile(x, y);
                    assertNotNull(tile);
                    assertEquals(zone.getType(), tile.getType());
                }
            }
        }
    } */
}
