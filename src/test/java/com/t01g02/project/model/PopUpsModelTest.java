package com.t01g02.project.model;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.viewer.PopUpsViewer;
import com.t01g02.project.viewer.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PopUpsModelTest {
    private Screen stubScreen;
    private Sprite stubSpeedSprite;
    private Sprite stubMudSprite;
    private Sprite stubBlockSprite;

    @BeforeEach
    void setUp() {
        stubScreen = mock(Screen.class);
        stubBlockSprite = mock(Sprite.class);
        stubMudSprite = mock(Sprite.class);
        stubSpeedSprite = mock(Sprite.class);
    }

    @Test
    void testInitializeStar() throws IOException {
        PopUpsModel.initializeStar(stubScreen);

        PopUpsModel star = PopUpsModel.getStar();
        assertNotNull(star);
        assertEquals("Star", star.getName());
    }

    @Test
    void testDeleteStar() throws IOException {
        PopUpsModel.initializeStar(stubScreen);

        PopUpsModel.deleteStar();

        PopUpsModel star = PopUpsModel.getStar();
        assertNull(star);
    }


    /*@Test
    void testInitializeSpeedPopUps() throws IOException {
        PopUpsModel.initializeSpeedPopUps(stubScreen);

        assertEquals(4, PopUpsModel.speedpopups.size());

        for (PopUpsModel speedpopup : PopUpsModel.speedpopups) {
            assertEquals("Speed", speedpopup.getName());
        }
    }*/

    /*@Test
    void testRandomPosition() {
        Position randomPosition = PopUpsModel.getRandomPosition();
        assertNotNull(randomPosition);
    }*/

    @Test
    void testAddSpeedPopUp() throws IOException {
        PopUpsModel.initializeSpeedPopUps(stubScreen);

        PopUpsModel.addSpeed(stubScreen);

        assertEquals(5, PopUpsModel.speedpopups.size());
    }

    @Test
    void testInitializeMudPopUps() throws IOException {
        PopUpsModel.initializeMudPopUps(stubScreen);

        assertEquals(3, PopUpsModel.mudpopups.size());

        for (PopUpsModel mudpopup : PopUpsModel.mudpopups) {
            assertEquals("Mud", mudpopup.getName());
        }
    }

    /*@Test
    void testAddMudPopUp() throws IOException {
        PopUpsModel.initializeMudPopUps(stubScreen);

        PopUpsModel.addMud(stubScreen);

        assertEquals(4, PopUpsModel.mudpopups.size());
    }*/

    @Test
    void testInitializeBlockPopUps() throws IOException {
        PopUpsModel.initializeBlockPopUps(stubScreen);

        assertEquals(3, PopUpsModel.blockpopups.size());

        for (PopUpsModel blockpopup : PopUpsModel.blockpopups) {
            assertEquals("Block", blockpopup.getName());
        }
    }

    @Test
    void testPopUpsPositionsNotEmptyAfterInitialization() {
        assertFalse(PopUpsModel.getPopupsPositions().isEmpty());
    }
}
