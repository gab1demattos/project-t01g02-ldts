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

import static com.t01g02.project.model.PopUpsModel.star;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopUpsModelTest {
    private Screen stubScreen;

    @BeforeEach
    void setUp() {
        stubScreen = mock(Screen.class);
//        Sprite stubBlockSprite = mock(Sprite.class);
//        Sprite stubMudSprite = mock(Sprite.class);
//        Sprite stubSpeedSprite = mock(Sprite.class);
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


//    @Test
//    void testInitializeSpeedPopUps() throws IOException {
//        PopUpsModel.initializeSpeedPopUps(stubScreen);
//
//        assertEquals(4, PopUpsModel.speedpopups.size());
//
//        for (PopUpsModel speedpopup : PopUpsModel.speedpopups) {
//            assertEquals("Speed", speedpopup.getName());
//        }
//    }
//
//    @Test
//    void testRandomPosition() {
//        Position randomPosition = PopUpsModel.getRandomPosition();
//        assertNotNull(randomPosition);
//    }

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

//    @Test
//    void testAddMudPopUp() throws IOException {
//        PopUpsModel.initializeMudPopUps(stubScreen);
//
//        PopUpsModel.addMud(stubScreen);
//
//        assertEquals(4, PopUpsModel.mudpopups.size());
//    }

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
    @Test
    void testIsPositionOnPopUp() {
        Position popupPosition = new Position(50, 50);
        PopUpsModel popUp = new PopUpsModel(null, popupPosition, null);

        Position insidePositionX = new Position(50, 50);
        assertTrue(popUp.isPositionOnPopUp(insidePositionX));

        Position insidePositionXPositive = new Position(60, 50);
        assertTrue(popUp.isPositionOnPopUp(insidePositionXPositive));

        Position insidePositionXNegative = new Position(40, 50);
        assertTrue(popUp.isPositionOnPopUp(insidePositionXNegative));

        Position insidePositionYPositive = new Position(50, 60);
        assertTrue(popUp.isPositionOnPopUp(insidePositionYPositive));

        Position insidePositionYNegative = new Position(50, 40);
        assertTrue(popUp.isPositionOnPopUp(insidePositionYNegative));

        Position outsidePositionXPositive = new Position(61, 50);
        assertFalse(popUp.isPositionOnPopUp(outsidePositionXPositive));

        Position outsidePositionXNegative = new Position(39, 50);
        assertFalse(popUp.isPositionOnPopUp(outsidePositionXNegative));

        Position outsidePositionYPositive = new Position(50, 61);
        assertFalse(popUp.isPositionOnPopUp(outsidePositionYPositive));

        Position outsidePositionYNegative = new Position(50, 39);
        assertFalse(popUp.isPositionOnPopUp(outsidePositionYNegative));

        Position outsideBothXandY = new Position(70, 70);
        assertFalse(popUp.isPositionOnPopUp(outsideBothXandY));
    }
}
