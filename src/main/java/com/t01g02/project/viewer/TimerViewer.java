package com.t01g02.project.viewer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.Timer;

public class TimerViewer {
    private final Timer timer;
    private final Screen screen;


    public TimerViewer (Timer timer, Screen screen){
        this.timer=timer;
        this.screen=screen;
    }

    public void drawStringSprite(String text, int startX, int startY, TextGraphics graphics) {
        String[] sprite = CharacterSprites.getStringSprite(text);
        graphics.setForegroundColor(new TextColor.RGB(183, 134, 141));
        graphics.setBackgroundColor(new TextColor.RGB(255, 240, 245));
        for (int i = 0; i < sprite.length; i++) {
                graphics.putString(startX, startY + i, sprite[i], SGR.BOLD);
        }
    }
    public void draw(int startY){
        TextGraphics graphics = screen.newTextGraphics();
        String formattedTime = timer.getFormattedTime();
        graphics.setForegroundColor(new TextColor.RGB(183, 134, 141));
        graphics.setBackgroundColor(new TextColor.RGB(255, 240, 245));

        TerminalSize terminalSize = screen.getTerminalSize();
        int startX = terminalSize.getColumns() - formattedTime.length() - 1;

        drawStringSprite("TIMER", startX-90, startY, graphics);
        drawStringSprite(formattedTime, startX -45, startY, graphics);


    }


}
