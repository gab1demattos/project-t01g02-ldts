package com.t01g02.project;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private final BufferedImage image;
    private final Screen screen;

    public Sprite(Screen screen, String filepath) throws IOException {
        // Loads png image
        this.image = ImageIO.read(new File(filepath));
        this.screen = screen;
    }

    // Drawing a pixel
    public void drawPixel(Position pixelPos, TextColor color) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(color);
        textGraphics.setCharacter(pixelPos.getX(), pixelPos.getY(), ' '); // // We use a blank space to represent a pixel
    }

    // Drawing image pixel by pixel
    public void drawImage(Position position) {

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int argb = image.getRGB(x, y);
                Color pixelColor = new Color(argb, true);  // include alpha channel

                if (pixelColor.getAlpha() > 0) {  // Non-transparent pixel
                    TextColor color = new TextColor.RGB(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue());
                    drawPixel(new Position(position.getX() + x, position.getY() + y), color);
                }
            }
        }

    /*    // Get the terminal size (columns and rows)
        int terminalWidth = screen.getTerminalSize().getColumns();
        int terminalHeight = screen.getTerminalSize().getRows();

        for (int dx = 0; dx < image.getWidth(); dx++) {
            for (int dy = 0; dy < image.getHeight(); dy++) {
                int argb = image.getRGB(dx, dy); // For each (dx, dy) position it returns the color in that position as int RGB
                if (getTransparency(argb) == 0)  // Skip transparent pixels
                    continue;
                // Calculate final coordinates
                Position finalpos = new Position(position.getX() +  dx, position.getY() +  dy);


                // Ensure the scaled coordinates are within the terminal bounds
                if (finalpos.getX() < terminalWidth && finalpos.getY() < terminalHeight) {
                    drawPixel(finalpos, getColor(argb));
                }
            }
        }   */
    }

    private int getTransparency(int RGB) {
        return new Color(RGB, true).getAlpha();
    }

    private TextColor getColor(int RGB) {
        Color color = new Color(RGB, true);
        return new TextColor.RGB(color.getRed(), color.getGreen(), color.getBlue());
    }
}

// Ainda tenho que descobrir se ha outro jeito melhor de mudar o tamanho da imagem
// Tambem tenho de ver como vamos botar a imagem na city

