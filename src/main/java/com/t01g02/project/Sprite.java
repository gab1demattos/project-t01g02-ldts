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

    public Sprite(Screen screen, String filepath) throws IOException {
        // loads png image
        URL resource = getClass().getClassLoader().getResource(filepath);
        this.image = ImageIO.read(Objects.requireNonNull(resource));
        this.screen = screen;

    }

    // drawing a pixel
    public void drawPixel(double x, double y, TextColor color) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(color);
        textGraphics.setCharacter((int) x, (int) y, ' ');
    }

    // drawing image pixel by pixel
    public void drawImage(int x, int y) {
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
    /*public void scaleImage(int targetWidth, int targetHeight) {
        // Calculate the scale factor to maintain the aspect ratio
        double widthRatio = (double) targetWidth / image.getWidth();
        double heightRatio = (double) targetHeight / image.getHeight();
        double scaleFactor = Math.min(widthRatio, heightRatio);  // Choose the smaller ratio to fit

        // Calculate new dimensions
        int newWidth = (int) (image.getWidth() * scaleFactor);
        int newHeight = (int) (image.getHeight() * scaleFactor);

        // Create a new BufferedImage with the scaled dimensions
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        // Map pixels from the original image to the scaled image
        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
                // Find the nearest corresponding pixel in the original image
                int origX = (int) (x / scaleFactor);
                int origY = (int) (y / scaleFactor);

                // Copy the color from the original pixel
                int rgb = image.getRGB(origX, origY);
                scaledImage.setRGB(x, y, rgb);
            }
        }

        this.image = scaledImage;  // Replace the original image with the scaled version
    }*/ // probably delete this method, it's not really working correctly
}
