package com.t01g02.project.viewer;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.Position;

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

    public void drawPixel(Position pixelPos, TextColor color) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(color);
        textGraphics.setCharacter(pixelPos.getX(), pixelPos.getY(), ' ');
    }

    public void drawImage(Position position) {

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int argb = image.getRGB(x, y);
                Color pixelColor = new Color(argb, true);

                if (pixelColor.getAlpha() > 0) {  // Non-transparent pixel
                    TextColor color = new TextColor.RGB(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue());
                    drawPixel(new Position(position.getX() + x, position.getY() + y), color);
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

