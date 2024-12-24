package com.t01g02.project.model;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZoneTest {
    private Zone zone;

    @BeforeEach
    void setUp() {
        Position startPosition = new Position(0, 0);
        Position endPosition = new Position(0, 10);
        Tile.Type type = Tile.Type.ROAD;
        TextColor color = TextColor.Factory.fromString("#FFFFFF");
        CharacterModel associatedFriend = new CharacterModel(null, null, "Kuromi");

        zone = new Zone(startPosition, endPosition, "zone1", type, color, associatedFriend);
    }

    @Test
    void GetZoneStartposition() {
        assertEquals(new Position(0, 0), zone.getStartposition());
    }

    @Test
    void GetZoneEndposition() {
        assertEquals(new Position(0, 10), zone.getEndposition());
    }

    @Test
    void GetZoneIdentifier() {
        assertEquals("zone1", zone.getIdentifier());
    }

    @Test
    void testZoneGetType() {
        assertEquals(Tile.Type.ROAD, zone.getType());
    }

    @Test
    void GetZoneColor() {
        assertEquals(TextColor.Factory.fromString("#FFFFFF"), zone.getColor());
    }

    @Test
    void GetZoneAssociatedFriend() {
        assertNotNull(zone.getAssociatedFriend());
    }
    @Test
    void testIsWithin() {
        Position startPosition = new Position(0, 0);
        Position endPosition = new Position(10, 10);
        Zone zone = new Zone(startPosition, endPosition, "zone1", Tile.Type.ROAD, TextColor.Factory.fromString("#222222"), null);

        Position insidePosition = new Position(0, 5);
        assertFalse(zone.isWithin(insidePosition));

        Position outsidePositionX = new Position(15, 5);
        assertFalse(zone.isWithin(outsidePositionX));

        Position outsidePositionY = new Position(0, 40);
        assertFalse(zone.isWithin(outsidePositionY));


    }

}
