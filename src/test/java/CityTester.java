package com.t01g02.project;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CityTester {

    private City city;

    @BeforeEach
    void setUp() {
        // initialize city
        city = new City(500, 300); // size for testing
    }

    @Test
    void cityInitializationTest() {
        // making sure the city dimensions match the specified size
        assertEquals(500, city.getWidth());
        assertEquals(300, city.getHeight());

        // check that all tiles are initially null
        for (int y = 0; y < city.getHeight(); y++) {
            for (int x = 0; x < city.getWidth(); x++) {
                assertNull(city.getTileAt(x, y));
            }
        }
    }

    @Test
    void initializeRoadsTest() {
        // initialize the roads
        city.initializeRoads();

        // color of the roads
        TextColor expectedRoadColor = TextColor.Factory.fromString("#888888");

        // check specific positions
            // should be a road, cannot be null and needs to have expected color
        assertNotNull(city.getTileAt(2, 15));
        assertEquals(Tile.Type.ROAD, city.getTileAt(2, 15).getType());
        assertEquals(expectedRoadColor, city.getTileAt(2, 15).getColor());

        assertNotNull(city.getTileAt(300, 200));
        assertEquals(Tile.Type.ROAD, city.getTileAt(300, 200).getType());

        // making sure empty tiles remain null
        assertNull(city.getTileAt(0, 0));
        assertNull(city.getTileAt(499, 299));
    }

    @Test
    void testOutOfBoundsAccess() {
        // trying to access tiles outside the city boundaries
            // make sure it's null
        assertNull(city.getTileAt(-1, 10));
        assertNull(city.getTileAt(10, -1));
        assertNull(city.getTileAt(500, 10));
        assertNull(city.getTileAt(10, 300));
    }
}
