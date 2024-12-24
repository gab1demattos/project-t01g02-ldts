package com.t01g02.project.model;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CityModelTest {
    private CityModel cityModel;
    private Zone zone;

    @BeforeEach
    void setUp() {
        cityModel = new CityModel(345, 195);
        cityModel.initializeRoads();
    }

    @Test
    void testCityModelDimensions() {
        assertEquals(345, cityModel.getWidth());
        assertEquals(195, cityModel.getHeight());
    }

    @Test
    void testInitializeRoads() {
        List<Road> roads = cityModel.getRoads();
        assertNotNull(roads);
        assertEquals(10, roads.size());

        Road road = roads.get(0);
        assertEquals(new Position(0, 0), road.getStart());
        assertEquals(new Position(0, 179), road.getEnd());
        Assertions.assertEquals(Tile.Type.ROAD, road.getType());
        assertEquals(TextColor.Factory.fromString("#222222"), road.getColor());

        Tile roadTile = cityModel.getTile(0, 0);
        assertNotNull(roadTile);
        assertEquals(Tile.Type.ROAD, roadTile.getType());
    }

    @Test
    void testGetTileWithinBounds() {
        Tile tile = cityModel.getTile(0, 0);
        assertNotNull(tile);
    }
    @Test
    void testGetColor() {
        TextColor color = TextColor.Factory.fromString("#FFFFFF");
        Tile tile = new Tile(Tile.Type.ROAD, color);
        assertEquals(color, tile.getColor());

        tile = new Tile(Tile.Type.ROAD, null);
        assertNull(tile.getColor());
    }
    @Test
    void testGetIdentifier() {
        String identifier = "testIdentifier";
        Tile tile = new Tile(Tile.Type.ROAD, TextColor.Factory.fromString("#FFFFFF"), identifier);
        assertEquals(identifier, tile.getIdentifier());

        tile = new Tile(Tile.Type.ROAD, TextColor.Factory.fromString("#FFFFFF"), "");
        assertEquals("", tile.getIdentifier());

        tile = new Tile(Tile.Type.ROAD, TextColor.Factory.fromString("#FFFFFF"), null);
        assertNull(tile.getIdentifier());
    }

    @Test
    void testGetTileOutOfBounds() {
        Tile tile = cityModel.getTile(-1, -1);
        assertNull(tile);

        tile = cityModel.getTile(1000, 1000);
        assertNull(tile);
    }

    @Test
    void testGetRoads() {
        List<Road> roads = cityModel.getRoads();

        assertNotNull(roads);
        assertEquals(10, roads.size());

        Road road = roads.get(1);
        assertEquals(new Position(65, 0), road.getStart());
        assertEquals(new Position(65, 179), road.getEnd());
    }

    @AfterEach
    void tearDown() {
        List<Road> roads = new ArrayList<>();
    }

}

