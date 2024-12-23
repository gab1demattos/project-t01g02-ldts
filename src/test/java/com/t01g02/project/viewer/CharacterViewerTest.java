package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CharacterViewerTest {
    private Screen stubScreen;
    private Sprite stubSprite;
    private CharacterViewer characterViewer;

    @BeforeEach
    void setUp() throws IOException {
        stubScreen = mock(Screen.class);
        stubSprite = mock(Sprite.class);

        characterViewer = new CharacterViewer(stubScreen);

        CharacterModel.friends = new ArrayList<>();

        CharacterModel.hellokitty = new CharacterModel(stubSprite, new Position(313, 157), "HelloKitty");
    }

    @Test
    void testInitializeCharacters() throws IOException {
        characterViewer.initializeCharacters();

        assertNotNull(CharacterModel.friends);
        assertNotNull(CharacterModel.getHellokitty());
        assertEquals(4, CharacterModel.friends.size());
    }

//    @Test
//    void testDraw() throws IOException {
//        characterViewer.draw();
//
//        for (CharacterModel character : CharacterModel.friends) {
//            verify(character.getSprite(), times(1)).drawImage(character.getPosition());
//        }
//
//        verify(CharacterModel.getHellokitty().getSprite(), times(1)).drawImage(CharacterModel.getHellokitty().getPosition());
//    }
}
