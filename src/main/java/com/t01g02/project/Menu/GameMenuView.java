package com.t01g02.project.Menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class GameMenuView implements IView {
    private Screen screen;
    private IModel model;
    private TerminalSize lastKnownSize; //Make Terminal Resizable, First Save Size

    public GameMenuView(Screen screen, GameMenuModel model) {
        this.screen = screen;
        this.model = model;
    }

    @Override
    public void redrawScreen() {
        try {
            // Get the new size of the terminal
            TerminalSize newSize = screen.getTerminalSize();

            // Check if the terminal size has changed
            if (lastKnownSize == null || !lastKnownSize.equals(newSize)) {
                lastKnownSize = newSize;
            }

                // Clear the screen
                screen.clear();

                // Create TextGraphics to draw
                TextGraphics textGraphics = screen.newTextGraphics();

                // Set background color
                textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237)); // Pink
                textGraphics.fillRectangle(new TerminalPosition(0, 0), newSize, ' '); // Fill screen with background color

                //Calculate Text Positions
                int centerX = newSize.getColumns() / 2;
                int centerY = newSize.getRows() / 2;
                String[] infoLines = model.getInfoText().split("\n");
                int messageWidth = Math.max(model.getGreetings().length(), getMaxLineLength(infoLines));
                int messageHeight = 2 + infoLines.length;

                // Dark Pink Rectangle message background
                int rectWidth = messageWidth + 4; // Adding some leftover space
                int rectHeight = messageHeight + 2;
                int rectStartX = centerX - rectWidth / 2;
                int rectStartY = centerY - rectHeight / 2;
                textGraphics.setBackgroundColor(new TextColor.RGB(229, 168, 177));
                textGraphics.fillRectangle(new TerminalPosition(rectStartX, rectStartY), new TerminalSize(rectWidth, rectHeight), ' ');

                // Draw greetings message inside rectangle
                String greetings = model.getGreetings();
                int greetingX = centerX - greetings.length() / 2;
                textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
                textGraphics.putString(greetingX, rectStartY + 1, greetings, SGR.BOLD);

                //Draw info text bellow greetings message
                int infoStartY = rectStartY + 3;
                for (String line : infoLines) {
                    int LineX = centerX - line.length() / 2;
                    textGraphics.putString(LineX, infoStartY, line, SGR.BOLD);
                    infoStartY++;
                }

                // Draw Exit information
                String exitInfo = model.getExitInfo();
                textGraphics.setForegroundColor(new TextColor.RGB(217, 167, 164));
                textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237));
                textGraphics.putString(rectStartX, rectStartY - 1, exitInfo, SGR.BORDERED);

                // Draw the option buttons (Play and Settings) and highlight the selected one
                String[] options = model.getOptions();
                int bottomRow = newSize.getRows() - 3;

                // Position for "Settings" button
                int settingsX = centerX - options[0].length() - 5;
                int playX = centerX + 5;

                redrawButtons();

                // Refresh the screen
                screen.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void redrawButtons() {
        TerminalSize newSize = screen.getTerminalSize();
        // Create TextGraphics to draw
        TextGraphics textGraphics = screen.newTextGraphics();

        int centerX = newSize.getColumns() / 2;
        int centerY = newSize.getRows() / 2;

        // Draw the option buttons (Play and Settings) and highlight the selected one
        String[] options = model.getOptions();
        int bottomRow = newSize.getRows() - 3;

        // Position for "Settings" button
        int settingsX = centerX - options[0].length() - 5;
        int playX = centerX + 5;

        System.out.println(model.getSelectedOption());

        // Draw "Settings" button
        if (model.getSelectedOption() == 0) {
            textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
            textGraphics.setBackgroundColor(new TextColor.RGB(229, 168, 177));
            System.out.println("Drawing 'Settings' button as selected");
        } else {
            textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
            textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237));
            System.out.println("Drawing 'Settings' button as unselected");
        }
        textGraphics.putString(settingsX, bottomRow, options[0], SGR.BOLD);

        // Draw "Play" button
        if (model.getSelectedOption() == 1) {
            textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
            textGraphics.setBackgroundColor(new TextColor.RGB(229, 168, 177));
            System.out.println("Drawing 'Play' button as selected");
        } else {
            textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
            textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237));
            System.out.println("Drawing 'Play' button as unselected");
        }
        textGraphics.putString(playX, bottomRow, options[1], SGR.BOLD);
    }

    private int getMaxLineLength(String[] lines) {
        int maxLength = 0;
        for (String line : lines) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }
        return maxLength;
    }


}
