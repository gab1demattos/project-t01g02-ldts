package com.t01g02.project;

import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CharacterModelTester {
    private Screen stubScreen;
    private Sprite stubSprite;
    private City city;

    @BeforeEach
    void setUp() throws IOException {
        stubScreen = mock(Screen.class);
        stubSprite = mock(Sprite.class);
        city = new City(500, 300);
        // initialize the characters
        CharacterModel.initializeCharacters(stubScreen);
    }

    @Test
    void characterOutOfBoundsTest() {
        // making sure characters are within valid city boundaries
        for (CharacterModel character : CharacterModel.characters) {
            Position position = character.getPosition();
            assertTrue(position.getX() >= 0 && position.getX() < city.getWidth());
            assertTrue(position.getY() >= 0 && position.getY() < city.getHeight());
        }
    }
}
