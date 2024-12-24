package com.t01g02.project.viewer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LetterSpritesTest {
    @Test
    public void testGetSpriteS() {
        String[] expected = {
                " █████ ",
                "█     █",
                "█      ",
                " █████ ",
                "      █",
                "█     █",
                " █████ "
        };

        String[] result = LetterSprites.getSprite('S');
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetSpriteT() {
        String[] expected = {
                "███████",
                "   █   ",
                "   █   ",
                "   █   ",
                "   █   ",
                "   █   ",
                "   █   ",
        };

        String[] result = LetterSprites.getSprite('T');
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetSpriteDefault() {
        String[] expected = {
                "       ",
                "       ",
                "       ",
                "       ",
                "       ",
                "       ",
                "       "
        };

        String[] result = LetterSprites.getSprite('Z');
        assertArrayEquals(expected, result);
    }

}
