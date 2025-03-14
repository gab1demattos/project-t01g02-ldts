package com.t01g02.project.model;

import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterModelTest {
    private Screen screen;

    @BeforeEach
    void setUp() throws IOException {
        CharacterModel.friends.clear();
        CharacterModel.initializeCharacters(screen);
    }

    @Test
    void testInitializeCharacters() {
        CharacterModel helloKitty = CharacterModel.getHellokitty();
        assertNotNull(helloKitty);
        assertEquals("HelloKitty", helloKitty.getName());
        assertEquals(new Position(313, 157), helloKitty.getPosition());

        assertNotNull(CharacterModel.friends);
        assertEquals(4, CharacterModel.friends.size());

        assertEquals("Kuromi", CharacterModel.friends.get(0).getName());
        assertEquals(new Position(168, 83), CharacterModel.friends.get(0).getPosition());

        assertEquals("Purin", CharacterModel.friends.get(1).getName());
        assertEquals(new Position(38, 36), CharacterModel.friends.get(1).getPosition());

        assertEquals("MyMelody", CharacterModel.friends.get(2).getName());
        assertEquals(new Position(94, 134), CharacterModel.friends.get(2).getPosition());

        assertEquals("Cinnamoroll", CharacterModel.friends.get(3).getName());
        assertEquals(new Position(283, 39), CharacterModel.friends.get(3).getPosition());
    }

    @Test
    void testSetPosition() {
        CharacterModel helloKitty = CharacterModel.getHellokitty();
        Position newPosition = new Position(400, 200);

        helloKitty.setPosition(newPosition);
        assertEquals(newPosition, helloKitty.getPosition());
    }

    @Test
    void testFriendsWithinParty() {
        assertEquals(0, CharacterModel.getFriendInPartyCount());

        CharacterModel.friends.get(0).setInParty(true);
        CharacterModel.friends.get(1).setInParty(true);

        assertEquals(2, CharacterModel.getFriendInPartyCount());
    }

    @Test
    void testKittyLastPositions() {
        CharacterModel helloKitty = CharacterModel.getHellokitty();
        helloKitty.setBeingFollowed(true);

        for (int i = 0; i < 15; i++) {
            helloKitty.setPosition(new Position(i, i));
        }

        assertEquals(10, helloKitty.getKittyLastPositions().size());

        for (int i = 0; i < 10; i++) {
            assertEquals(new Position(5 + i, 5 + i), helloKitty.getKittyLastPositions().get(i));
        }
    }
    @Test
    void testIsFollowing() {
        CharacterModel character = new CharacterModel(null, null, null);

        assertFalse(character.isFollowing());

        character.setFollowing(true);
        assertTrue(character.isFollowing());

        character.setFollowing(false);
        assertFalse(character.isFollowing());
    }
    @Test
    void testIsOutOfHouse() {
        CharacterModel character = new CharacterModel(null, null, null);

        assertFalse(character.isOutOfHouse());

        character.setOutOfHouse(true);
        assertTrue(character.isOutOfHouse());

        character.setOutOfHouse(false);
        assertFalse(character.isOutOfHouse());
    }
    @Test
    void testIsBeingFollowed() {
        CharacterModel character = new CharacterModel(null, null, null);

        assertFalse(character.isBeingFollowed());

        character.setBeingFollowed(true);
        assertTrue(character.isBeingFollowed());

        character.setBeingFollowed(false);
        assertFalse(character.isBeingFollowed());
    }
    @Test
    void testIsInParty() {
        CharacterModel character = new CharacterModel(null, null, null);
        assertFalse(character.isInParty());

        character.setInParty(true);
        assertTrue(character.isInParty());

        character.setInParty(false);
        assertFalse(character.isInParty());
    }
}