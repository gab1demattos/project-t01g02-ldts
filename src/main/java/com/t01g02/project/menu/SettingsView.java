package com.t01g02.project.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class SettingsView implements IView{
    private final Screen screen;
    private final SettingsModel model;
    private TerminalSize lastKnownSize;

    public SettingsView(Screen screen, SettingsModel model){
        this.screen=screen;
        this.model=model;
    }

    @Override
    public void redrawScreen(){
        try{

            TerminalSize newSize = screen.getTerminalSize();
            if (lastKnownSize == null || !lastKnownSize.equals(newSize)) {
                lastKnownSize = newSize;
            }

            screen.clear();
            TextGraphics textGraphics = screen.newTextGraphics();

            drawBackground(textGraphics, newSize);
            drawMessages(textGraphics, newSize);
            redrawButtons();

            screen.refresh();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawBackground(TextGraphics textGraphics, TerminalSize newSize){
        textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), newSize, ' ');
    }
    private void drawMessages(TextGraphics textGraphics, TerminalSize newSize){
        int centerX = newSize.getColumns() / 2;
        int centerY = newSize.getRows() / 2;
        int boxWidth = 20;
        int boxHeight = 10;

        drawBox(textGraphics, centerX - boxWidth - 2, centerY - boxHeight / 2, boxWidth, boxHeight);
        drawBox(textGraphics, centerX + 2, centerY - boxHeight / 2, boxWidth, boxHeight);

        String exitInfo = model.getExitSettingsInfo();
        textGraphics.setForegroundColor(new TextColor.RGB(217, 167, 164));
        textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237));
        textGraphics.putString(centerX - exitInfo.length() / 2, centerY - boxHeight / 2 - 2, exitInfo, SGR.BOLD);

        String enterInfo = model.getEnterSettingsInfo();
        textGraphics.setForegroundColor(new TextColor.RGB(217, 167, 164));
        textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237));
        textGraphics.putString(centerX - enterInfo.length() / 2, centerY + 9, enterInfo, SGR.BOLD);

    }

    private void drawBox(TextGraphics textGraphics, int startX, int startY, int width, int height){
        textGraphics.setBackgroundColor(new TextColor.RGB(229, 168, 177));
        textGraphics.fillRectangle(new TerminalPosition(startX, startY), new TerminalSize(width, height), ' ');
    }

    public void drawBInfo (boolean show){
        TerminalSize newSize = screen.getTerminalSize();
        if (lastKnownSize == null || !lastKnownSize.equals(newSize)) {
            lastKnownSize = newSize;
        }
        int centerX = newSize.getColumns() / 2;
        int centerY = newSize.getRows() / 2;
        TextGraphics textGraphics = screen.newTextGraphics();
        String BInfo = model.getBSettingsInfo();
        if (show){
            textGraphics.setForegroundColor(new TextColor.RGB(217, 167, 164));
            textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237));
            textGraphics.putString(centerX - BInfo.length() / 2, centerY + 6, BInfo, SGR.ITALIC);
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void redrawButtons() {
        TerminalSize newSize = screen.getTerminalSize();
        TextGraphics textGraphics = screen.newTextGraphics();

        int centerX = newSize.getColumns() / 2;
        int centerY = newSize.getRows() / 2;
        int boxWidth = 20;
        int boxHeight = 10;
        int bottomBox = boxHeight - 3;
        int musicBoxX = (centerX*2 - boxWidth + 4)/2 ;
        int soundBoxX = centerX + 2;

        String[] options = model.getOptions();
        int[] buttonPositions = {
                musicBoxX - (boxWidth - "Music".length()) / 2,
                soundBoxX + (boxWidth - "Sound".length())/2
        };
        for (int i = 0; i < options.length; i++) {
            buttonColor(textGraphics, buttonPositions[i], boxHeight - 2, options[i], model.getSelectedOption() == i);
        }

        String[] musicOptions = model.getMusicOptions();
        int[] buttonPositionsMusic = {
                centerX - boxWidth/2 - 6,
                centerX - boxWidth/2 - 6 + musicOptions[0].length() +3
        };
        for (int i = 0; i < musicOptions.length; i++) {
            buttonColor(textGraphics, buttonPositionsMusic[i], centerY + bottomBox - 4, musicOptions[i], model.getMusicSelectedOption() == i);
        }

        String[] soundOptions = model.getSoundOptions();
        int[] buttonPositionsSound = {
                centerX - boxWidth/2 + 18,
                centerX - boxWidth/2 + 18 + soundOptions[0].length()+3
        };
        for (int i = 0; i < soundOptions.length; i++) {
            buttonColor(textGraphics, buttonPositionsSound[i], centerY + bottomBox - 4, soundOptions[i], model.getSoundSelectedOption() == i);
        }

    }

    private void buttonColor(TextGraphics textGraphics, int x, int y, String name, boolean isSelected) {
        textGraphics.setForegroundColor(TextColor.ANSI.BLACK);

        if (isSelected) {
            textGraphics.setBackgroundColor(new TextColor.RGB(229, 212, 214));
        } else {
            textGraphics.setBackgroundColor(new TextColor.RGB(229, 168, 177));

        }
        textGraphics.putString(x, y, name, SGR.BOLD);
    }


}
