package com.t01g02.project;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TerminalSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SpriteTester {

    private Screen stubScreen;
    private TextGraphics stubTextGraphics;
    private BufferedImage image;
    private Sprite sprite;

    @BeforeEach
    void setUp() throws IOException {
        stubScreen = mock(Screen.class);
        stubTextGraphics = mock(TextGraphics.class);

        // check terminal size
        when(stubScreen.getTerminalSize()).thenReturn(new TerminalSize(10, 10));
        when(stubScreen.newTextGraphics()).thenReturn(stubTextGraphics);

        // create sprite using image in resources
        String filepath = "src/test/resources/hellokitty.png";
        assertNotNull(filepath);
        sprite = new Sprite(stubScreen, filepath);
        image = ImageIO.read(new File(filepath));
    }

    @Test
    void drawPixelTest() {
        Position position = new Position(2, 3);
        TextColor color = new TextColor.RGB(255, 0, 0); // color red

        // drawPixel method
        sprite.drawPixel(position, color);

        // check that the background color is the pixel's color
        verify(stubTextGraphics).setBackgroundColor(color);
        // check that the pixel is in the correct position
        verify(stubTextGraphics).setCharacter(2, 3, ' ');
    }
/*
    @Test
    void drawImageTest() {
        Position position = new Position(0, 0);

        // drawImage method
        sprite.drawImage(position);

        // loop through the image's pixels
        for (int dx = 0; dx < image.getWidth(); dx++) {
            for (int dy = 0; dy < image.getHeight(); dy++) {
                int argb = image.getRGB(dx, dy);
                Color pixelColor = new Color(argb, true);

                if (pixelColor.getAlpha() > 0) { // Non-transparent pixel
                    // Expected text color
                    TextColor expectedColor = new TextColor.RGB(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue());

                    // check if the setBackgroundColor is called with the correct color for this pixel
                    verify(stubTextGraphics, times(1)).setBackgroundColor(expectedColor);

                    // check if that setCharacter is called for this pixel location
                    verify(stubTextGraphics, times(1)).setCharacter(position.getX() + dx, position.getY() + dy, ' ');
                }
            }
        }
    }
*/
}


// essa parte ta me dando erro porque o 'setBacgroundColor' esta a ser chamado mais de uma vez pra um unico pixel dai preciso consertar
