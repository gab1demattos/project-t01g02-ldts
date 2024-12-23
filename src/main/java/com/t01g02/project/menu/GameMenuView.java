package com.t01g02.project.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class GameMenuView implements IView {
    private final Screen screen;
    private final IModel model;
    private TerminalSize lastKnownSize;

    public GameMenuView(Screen screen, GameMenuModel model) {
        this.screen = screen;
        this.model = model;
    }

    @Override
    public void redrawScreen() {
        try {
            TerminalSize newSize = screen.getTerminalSize();

            if (lastKnownSize == null || !lastKnownSize.equals(newSize)) {
                lastKnownSize = newSize;
            }

            screen.clear();

            TextGraphics textGraphics = screen.newTextGraphics();
            drawBackground(textGraphics, newSize);
            drawMessages(textGraphics,newSize);

            redrawButtons();

            screen.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void drawBackground(TextGraphics textGraphics, TerminalSize newSize){
        textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237)); // Pink
        textGraphics.fillRectangle(new TerminalPosition(0, 0), newSize, ' '); // Fill screen with background color
    }

    void drawMessages(TextGraphics textGraphics, TerminalSize newSize){
        int centerX = newSize.getColumns() / 2;
        int centerY = newSize.getRows() / 2;
        String[] infoLines = model.getInfoText().split("\n");
        int messageWidth = Math.max(model.getGreetings().length(), getMaxLineLength(infoLines));
        int messageHeight = 2 + infoLines.length;

        int rectStartX = centerX - (messageWidth + 4) / 2;
        int rectStartY = centerY - (messageHeight + 2)/2;
        drawMessageBackground(textGraphics,rectStartX,rectStartY,messageWidth,messageHeight);
        drawGreetings(textGraphics,centerX,rectStartY);

        int infoStartY = rectStartY+3;
        drawInfoText(textGraphics,centerX,infoStartY,infoLines);

        String exitInfo = model.getExitInfo();
        drawExitInfo(textGraphics, rectStartX, rectStartY, messageWidth, exitInfo);
    }

    void drawMessageBackground(TextGraphics textGraphics, int rectStartX, int rectStartY, int messageWidth, int messageHeight){
        int rectWidth = messageWidth + 4; // Adding some leftover space
        int rectHeight = messageHeight + 2;
        textGraphics.setBackgroundColor(new TextColor.RGB(229, 168, 177));
        textGraphics.fillRectangle(new TerminalPosition(rectStartX, rectStartY), new TerminalSize(rectWidth, rectHeight), ' ');
    }

    void drawGreetings(TextGraphics textGraphics, int centerX, int rectStartY){
        String greetings = model.getGreetings();
        int greetingX = centerX - greetings.length() / 2;
        textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        textGraphics.putString(greetingX, rectStartY + 1, greetings, SGR.BOLD);
    }

    void drawInfoText(TextGraphics textGraphics, int centerX, int infoStartY, String[] infoLines){
        for (String line : infoLines) {
            int LineX = centerX - line.length() / 2;
            textGraphics.putString(LineX, infoStartY, line, SGR.BOLD);
            infoStartY++;
        }
    }

    void drawExitInfo (TextGraphics textGraphics, int centerX, int rectStartY, int messageWidth, String exitInfo){
        int exitX = centerX - messageWidth/2;
        textGraphics.setForegroundColor(new TextColor.RGB(217, 167, 164));
        textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237));
        textGraphics.putString(exitX, rectStartY - 1, exitInfo, SGR.BORDERED);
    }

    @Override
    public void redrawButtons() {
        TerminalSize newSize = screen.getTerminalSize();
        TextGraphics textGraphics = screen.newTextGraphics();

        int centerX = newSize.getColumns() / 2;
        int bottomRow = newSize.getRows() - 3;

        // Define button positions and options
        String[] options = model.getOptions();
        int[] buttonPositions = {
                centerX - options[0].length() - 5,  // Settings x position
                centerX + 5                         // Play x position
        };

        for (int i = 0; i < options.length; i++) {
            buttonColor(textGraphics, buttonPositions[i], bottomRow, options[i], model.getSelectedOption() == i);
        }

    }

    private void buttonColor(TextGraphics textGraphics, int x, int y, String name, boolean isSelected) {
        if (isSelected) {
            textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
            textGraphics.setBackgroundColor(new TextColor.RGB(229, 168, 177));
        } else {
            textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
            textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237));
        }
        textGraphics.putString(x, y, name, SGR.BOLD);
    }

    public int getMaxLineLength(String[] lines) {
        int maxLength = 0;
        for (String line : lines) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }
        return maxLength;
    }


}
