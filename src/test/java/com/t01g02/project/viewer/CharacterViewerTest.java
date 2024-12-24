package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CharacterViewerTest {
    private Screen stubScreen;
    private Sprite stubSprite;
    private CharacterViewer characterViewer;
    private  CharacterModel characterModel;
    @BeforeEach
    void setUp() throws IOException {
        stubScreen = mock(Screen.class);
        stubSprite = mock(Sprite.class);
        characterModel = mock(CharacterModel.class);
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

    @Test
    void testDraw() throws IOException {
        CharacterModel friend1 = mock(CharacterModel.class);
        CharacterModel friend2 = mock(CharacterModel.class);

        Sprite sprite1 = mock(Sprite.class);
        Sprite sprite2 = mock(Sprite.class);

        when(friend1.isInParty()).thenReturn(false);
        when(friend1.getSprite()).thenReturn(sprite1);
        when(friend1.getPosition()).thenReturn(new Position(100, 100));

        when(friend2.isInParty()).thenReturn(true);
        when(friend2.getSprite()).thenReturn(sprite2);
        when(friend2.getPosition()).thenReturn(new Position(200, 200));

        CharacterModel.friends.add(friend1);
        CharacterModel.friends.add(friend2);

        characterViewer.draw();

        verify(sprite1, times(1)).drawImage(new Position(100, 100));
        verify(sprite2, never()).drawImage(any());
        verify(CharacterModel.getHellokitty().getSprite(), times(1)).drawImage(CharacterModel.getHellokitty().getPosition());
    }

    @Test
    void testDraw_EmptyFriendsList() throws IOException {
        CharacterModel.friends.clear();
        characterViewer.draw();

        for(CharacterModel friend : CharacterModel.friends){
            verify(stubSprite, never()).drawImage(any(Position.class));
        }

        verify(CharacterModel.getHellokitty().getSprite(), times(1)).drawImage(CharacterModel.getHellokitty().getPosition());
    }

}
