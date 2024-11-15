package com.t01g02.project;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Sprite {
    private BufferedImage image;
    private Screen screen;

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
    public void drawImage(Position startPos, int targetWidth, int targetHeight) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        // Get the terminal size (columns and rows)
        int terminalWidth = screen.getTerminalSize().getColumns();
        int terminalHeight = screen.getTerminalSize().getRows();

        // Calculate scaling factors based on target dimensions
        Position scalePos = new Position(targetWidth / imageWidth, targetHeight / imageHeight);

        for (int dx = 0; dx < image.getWidth(); dx++) {
            for (int dy = 0; dy < image.getHeight(); dy++) {
                int argb = image.getRGB(dx, dy); // For each (dx, dy) position it returns the color in that position as int RGB
                if (getTransparency(argb) == 0)  // Skip transparent pixels
                    continue;
                // Calculate scaled coordinates based on the target size
                Position scaledPos = new Position(startPos.getX() +  (dx * scalePos.getX()), startPos.getY() +  (dy * scalePos.getY()));


                // Ensure the scaled coordinates are within the terminal bounds
                if (scaledPos.getX() < terminalWidth && scaledPos.getY() < terminalHeight) {
                    drawPixel(scaledPos, getColor(argb));
                }
            }
        }
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
