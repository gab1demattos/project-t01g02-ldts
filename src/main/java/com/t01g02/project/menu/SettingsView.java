package com.t01g02.project.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class SettingsView implements IView{
    private Screen screen;
    private SettingsModel model;
    private TerminalSize lastKnownSize;

    public SettingsView(Screen screen, SettingsModel model){
        // default settings
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

            textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237)); // Pink
            textGraphics.fillRectangle(new TerminalPosition(0, 0), newSize, ' '); // Fill screen with background color

            int centerX = newSize.getColumns() / 2;
            int centerY = newSize.getRows() / 2;
            int boxWidth = 20;
            int boxHeight = 10;

            // Draw Background Squares
            drawBox(textGraphics, centerX - boxWidth - 2, centerY - boxHeight / 2, boxWidth, boxHeight);
            drawBox(textGraphics, centerX + 2, centerY - boxHeight / 2, boxWidth, boxHeight);

            // Exit Info
            String exitInfo = model.getExitSettingsInfo();
            textGraphics.setForegroundColor(new TextColor.RGB(217, 167, 164));
            textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237));
            textGraphics.putString(centerX - exitInfo.length() / 2, centerY - boxHeight / 2 - 2, exitInfo, SGR.BOLD);

            // Enter Info
            String enterInfo = model.getEnterSettingsInfo();
            textGraphics.setForegroundColor(new TextColor.RGB(217, 167, 164));
            textGraphics.setBackgroundColor(new TextColor.RGB(255, 225, 237));
            textGraphics.putString(centerX - enterInfo.length() / 2, centerY + 9, enterInfo, SGR.BOLD);


            redrawButtons();
            screen.refresh();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawBox(TextGraphics textGraphics, int startX, int startY, int width, int height){
        textGraphics.setBackgroundColor(new TextColor.RGB(229, 168, 177));
        textGraphics.fillRectangle(new TerminalPosition(startX, startY), new TerminalSize(width, height), ' ');
    }

    // 'B' Info
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
            textGraphics.putString(centerX - BInfo.length() / 2, centerY + 6, BInfo, SGR.BLINK);
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
        int buttonSpacing = 4;
        int musicBoxX = (centerX*2 - boxWidth + 4)/2 ;
        int soundBoxX = centerX + 2;

        // Define button positions and options
        String[] options = model.getOptions();
        int[] buttonPositions = {
                musicBoxX - (boxWidth - "Music".length()) / 2,  // Music X position
                soundBoxX + (boxWidth - "Sound".length())/2   // Sound X position
        };
        for (int i = 0; i < options.length; i++) {
            buttonColor(textGraphics, buttonPositions[i], boxHeight - 2, options[i], model.getSelectedOption() == i);
        }

        String[] musicOptions = model.getMusicOptions();
        int[] buttonPositionsMusic = {
                centerX - boxWidth/2 - 6,                               // Music ON X position
                centerX - boxWidth/2 - 6 + musicOptions[0].length() +3  // Music OFF X position
        };
        for (int i = 0; i < musicOptions.length; i++) {
            buttonColor(textGraphics, buttonPositionsMusic[i], centerY + bottomBox - 4, musicOptions[i], model.getMusicSelectedOption() == i);
        }

        String[] soundOptions = model.getSoundOptions();
        int[] buttonPositionsSound = {
                centerX - boxWidth/2 + 18,                               // Sound ON X position
                centerX - boxWidth/2 + 18 + soundOptions[0].length()+3   // Sound OFF X position
        };
        for (int i = 0; i < soundOptions.length; i++) {
            buttonColor(textGraphics, buttonPositionsSound[i], centerY + bottomBox - 4, soundOptions[i], model.getSoundSelectedOption() == i);
        }

    }

    private void buttonColor(TextGraphics textGraphics, int x, int y, String name, boolean isSelected) {
        if (isSelected) {
            textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
            textGraphics.setBackgroundColor(new TextColor.RGB(229, 212, 214));
        } else {
            textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
            textGraphics.setBackgroundColor(new TextColor.RGB(229, 168, 177));

        }
        textGraphics.putString(x, y, name, SGR.BOLD);
    }



}
