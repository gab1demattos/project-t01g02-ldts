package com.t01g02.project;

import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

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

        CharacterModel.characters = List.of(
                new CharacterModel(stubSprite, new Position(340, 127), "HelloKitty"),
                new CharacterModel(stubSprite, new Position(273, 226), "Kuromi")
        );
    }

    @Test
    void testInitializeCharacters() throws IOException {
        characterViewer.initializeCharacters();

        assertNotNull(CharacterModel.characters);
        assertEquals(5, CharacterModel.characters.size());
    }


    @Test
    void testDraw() throws IOException {
        characterViewer.draw();

        for (CharacterModel character : CharacterModel.characters) {
            verify(character.getSprite(), times(1)).drawImage(character.getPosition());
        }

        verify(stubScreen, times(1)).refresh();
    }
}
