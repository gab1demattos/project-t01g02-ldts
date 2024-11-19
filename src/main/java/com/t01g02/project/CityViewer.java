package com.t01g02.project;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class CityViewer {
    private final City city;
    private final Screen screen;
    //private GameTimer timer;

    public CityViewer(City city, Screen screen) {
        this.city = city;
        this.screen = screen;
        /*GameTimer timer = new GameTimer();
        timer.start();*/
    }

    /*public int getRemainingTime() {
        return timer.getRemainingSeconds();
    }

    public void drawTimer(TextGraphics graphics) {
        int seconds = timer.getRemainingSeconds();
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(0, 0, "Time Remaining: " + formatTime(seconds));  // Draw timer at top row
    }



    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }*/


    public void draw() throws IOException {
        screen.clear();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFE1EA"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows()), ' ');

        for (int y = 0; y < city.getHeight(); y++) {
            for (int x = 0; x < city.getWidth(); x++) {
                Tile tile = city.getTileAt(x, y);
                if (tile != null) {
                    graphics.setForegroundColor(tile.getColor());
                    char displayChar = tile.getType() == Tile.Type.ROAD ? '█' : ' ';
                    graphics.putString(new TerminalPosition(x, y), String.valueOf(displayChar));
                }
            }
        }


        //drawTimer(graphics);


        Sprite hellokitty = new Sprite(screen, "src/main/resources/characters/hellokitty.png"); //26*17 ??
        hellokitty.drawImage(new Position(340, 127));

        Sprite kuromi = new Sprite(screen, "src/main/resources/characters/kuromi.png"); // 25*25
        kuromi.drawImage(new Position(273, 226));

        Sprite purin = new Sprite(screen, "src/main/resources/characters/purin.png"); //25*16
        purin.drawImage(new Position(28, 41));

        Sprite mymelody = new Sprite(screen, "src/main/resources/characters/mymelody.png"); //25*19
        mymelody.drawImage(new Position(42, 177));

        Sprite cinnamoroll = new Sprite(screen, "src/main/resources/characters/cinnamoroll.png"); // 25*20
        cinnamoroll.drawImage(new Position(222, 73));

        Sprite house = new Sprite(screen, "src/main/resources/extras/partyhouse.png"); // 25*20
        house.drawImage(new Position(425, 20)); // vou aumentar a casa

        Sprite tree = new Sprite(screen, "src/main/resources/extras/tree.png"); // 25*20
        tree.drawImage(new Position(180, 40)); // maybe resize??



        screen.refresh();

    }
}


