package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.PopUpsModel;
import com.t01g02.project.model.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PopUpsViewerTest {
    private Screen stubScreen;
    private Sprite stubSprite;
    private PopUpsViewer popUpsViewer;

    @BeforeEach
    void setUp() throws IOException {
        stubScreen = mock(Screen.class);
        stubSprite = mock(Sprite.class);

        popUpsViewer = new PopUpsViewer(stubScreen);

        PopUpsModel.speedpopups = new ArrayList<>();
        PopUpsModel.mudpopups = new ArrayList<>();
        PopUpsModel.blockpopups = new ArrayList<>();
        PopUpsModel.star = null;

        CharacterModel.friends = new ArrayList<>();
    }

    @Test
    void testInitializePopUps() throws IOException {
        popUpsViewer.initializePopUps();

        assertNotNull(PopUpsModel.speedpopups);
        assertNotNull(PopUpsModel.mudpopups);
        assertNotNull(PopUpsModel.blockpopups);
        assertNotNull(PopUpsModel.getStar());
    }

//    @Test
//    void testDrawWithPopUps() throws IOException {
//        PopUpsModel speedPopUpMock = new PopUpsModel(stubSprite, new Position(10, 10), null);
//        PopUpsModel mudPopUpMock = new PopUpsModel(stubSprite, new Position(20, 20), null);
//        PopUpsModel blockPopUpMock = new PopUpsModel(stubSprite, new Position(30, 30), null);
//        PopUpsModel starMock = new PopUpsModel(stubSprite, new Position(40, 40), null);
//
//        PopUpsModel.speedpopups.add(speedPopUpMock);
//        PopUpsModel.mudpopups.add(mudPopUpMock);
//        PopUpsModel.blockpopups.add(blockPopUpMock);
//        PopUpsModel.star = starMock;
//
//        CharacterModel.friends.add(mock(CharacterModel.class));
//        CharacterModel.friends.add(mock(CharacterModel.class));
//
//        popUpsViewer.draw();
//
//        verify(speedPopUpMock.getSprite(), times(1)).drawImage(argThat(pos -> pos.hashCode() == new Position(10, 10).hashCode()));
//        verify(mudPopUpMock.getSprite(), times(1)).drawImage(argThat(pos -> pos.hashCode() == new Position(20, 20).hashCode()));
//        verify(blockPopUpMock.getSprite(), times(1)).drawImage(argThat(pos -> pos.hashCode() == new Position(30, 30).hashCode()));
//        verify(starMock.getSprite(), times(1)).drawImage(argThat(pos -> pos.hashCode() == new Position(40, 40).hashCode()));
//    }

    @Test
    void testDrawWithoutEnoughFriends() throws IOException {
        // Prepare mock popups
        PopUpsModel speedPopUpMock = new PopUpsModel(stubSprite, new Position(10, 10), null);
        PopUpsModel mudPopUpMock = new PopUpsModel(stubSprite, new Position(20, 20), null);
        PopUpsModel blockPopUpMock = new PopUpsModel(stubSprite, new Position(30, 30), null);
        PopUpsModel starMock = new PopUpsModel(stubSprite, new Position(40, 40), null);

        // Assign popups to the model's static fields
        PopUpsModel.speedpopups.add(speedPopUpMock);
        PopUpsModel.mudpopups.add(mudPopUpMock);
        PopUpsModel.blockpopups.add(blockPopUpMock);
        PopUpsModel.star = starMock;

        // Add fewer than 2 friends to ensure the star should NOT be drawn
        CharacterModel.friends.add(mock(CharacterModel.class)); // Only one friend

        // Call the draw method
        popUpsViewer.draw();

        // Verify that `drawImage` was called for each popup except the star
        verify(speedPopUpMock.getSprite(), times(1)).drawImage(speedPopUpMock.getPosition());
        verify(mudPopUpMock.getSprite(), times(1)).drawImage(mudPopUpMock.getPosition());
        verify(blockPopUpMock.getSprite(), times(1)).drawImage(blockPopUpMock.getPosition());
        verify(PopUpsModel.getStar().getSprite(), times(0)).drawImage(PopUpsModel.getStar().getPosition()); // Star should NOT be drawn
    }

}
