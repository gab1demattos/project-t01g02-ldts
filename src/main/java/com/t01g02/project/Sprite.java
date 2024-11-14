package com.t01g02.project;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Sprite {
    private BufferedImage image;
    private Screen screen;

    public Sprite(String filepath) throws IOException {
        // loads png image
        URL resource = getClass().getClassLoader().getResource(filepath);
        this.image = ImageIO.read(Objects.requireNonNull(resource));
    }

    // drawing a pixel
    public void drawPixel(double x, double y, TextColor color) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(color);
        textGraphics.setCharacter((int) x, (int) y, ' ');
    }

    // drawing image pixel by pixel
    public void drawImage(double x, double y) {
        for (int dx = 0; dx < image.getWidth(); dx++) {
            for (int dy = 0; dy < image.getHeight(); dy++) {
                int RGB = image.getRGB(dx, dy); // for each (dx, dy) position it returns the color in that position as int RGB
                if (getTransparency(RGB) == 0)  // pixel transparent
                    continue;
                drawPixel(x + dx, y + dy, getColor(RGB));
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
