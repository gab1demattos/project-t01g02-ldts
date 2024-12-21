package com.t01g02.project.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class GameOver implements IView {
    private final Screen screen;
    private boolean isGameOver;
    private boolean isWin;
    private int finalScore;

    public GameOver(Screen screen){
        this.screen = screen;
        this.isGameOver = false;
    }

    public void setGameOver (boolean isWin, int finalScore){
        this.isGameOver = true;
        this.isWin = isWin;
        this.finalScore = finalScore;
    }
    @Override
    public void redrawScreen(){
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        graphics.setBackgroundColor(new TextColor.RGB(229, 168, 177));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows()), ' ');

        if (isGameOver){
            String message = isWin ? "Congratulations! You did it!" : "You didn´t make it on time :(";
            graphics.setForegroundColor(new TextColor.RGB(149, 88, 97));
            graphics.putString(new TerminalPosition(screen.getTerminalSize().getColumns() / 2 - message.length() / 2, screen.getTerminalSize().getRows() / 2 - 2), message, SGR.BOLD);

            String scoreMessage = "Final score: " + finalScore;
            graphics.putString(new TerminalPosition(screen.getTerminalSize().getColumns() / 2 - scoreMessage.length() / 2, screen.getTerminalSize().getRows() / 2), scoreMessage, SGR.BOLD);

            String menuMessage = "Press Esc to leave";
            graphics.putString(new TerminalPosition(screen.getTerminalSize().getColumns() / 2 - menuMessage.length() / 2, screen.getTerminalSize().getRows() / 2 + 2), menuMessage, SGR.BORDERED);

        }

        try {
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isWin() {
        return isWin;
    }

    @Override
    public void redrawButtons(){}
}
