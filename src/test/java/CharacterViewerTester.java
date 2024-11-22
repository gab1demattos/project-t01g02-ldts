package com.t01g02.project;

import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CharacterViewerTester {
    private Screen stubScreen;
    private Sprite stubSprite;
    private CharacterViewer characterViewer;

    @BeforeEach
    void setUp() throws IOException {

        stubScreen = mock(Screen.class);
        stubSprite = mock(Sprite.class);

        // initialize a CharacterViewer with the mocked Screen
        characterViewer = new CharacterViewer(stubScreen);

        // set up mocked characters for testing
        CharacterModel.characters = List.of(
                new CharacterModel(stubSprite, new Position(340, 127), "HelloKitty"),
                new CharacterModel(stubSprite, new Position(273, 226), "Kuromi")
        );
    }

    @Test
    void initializeCharactersTest() throws IOException {
        characterViewer.initializeCharacters();

        // check if there are 5 characters
        assertNotNull(CharacterModel.characters);
        assertEquals(5, CharacterModel.characters.size());
    }


    @Test
    void drawTest() throws IOException {
        characterViewer.draw();

        // check that drawImage is called for each character's sprite with the correct position
        for (CharacterModel character : CharacterModel.characters) {
            verify(character.getSprite(), times(1)).drawImage(character.getPosition());
        }

        // making sure that the screen's refresh method is called
        verify(stubScreen, times(1)).refresh();
    }
}
