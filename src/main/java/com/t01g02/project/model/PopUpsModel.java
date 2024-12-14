package com.t01g02.project.model;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.viewer.LanternaGui;
import com.t01g02.project.viewer.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopUpsModel extends Element {
    public static List<PopUpsModel> speedpopups, mudpopups, blockpopups;
    private static final Random RANDOM = new Random();
    private static final int TerminalWidth = 345;
    private static final int TerminalHeight = 185;

    public PopUpsModel(Sprite sprite, Position position, String name) {
        super(sprite, position, name);
    }

    private static Position randomPosition() {
        int rx = RANDOM.nextInt(TerminalWidth);
        int ry = RANDOM.nextInt(TerminalHeight);
        return new Position(rx, ry);
    }

    // need to fix this!!
    private static Position findValidRandomPosition(CityModel city) {
        Position position;
        do {
            position = randomPosition();
            if (position.getX() >= 0 || position.getX() < TerminalWidth || position.getY() >= 0 || position.getY() < TerminalHeight) {
                break;
            }
        } while (!canMove(position, city));
        return position;
    }

    private static boolean canMove(Position position, CityModel city) {
        Tile tile = city.getTile(position.getX(), position.getY());
        return (tile.getType() == Tile.Type.ROAD);
    }

    public static void initializeSpeedPopUps(Screen screen, CityModel city) throws IOException {
        speedpopups = List.of(
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/speed.png"), findValidRandomPosition(city), "Speed"),
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/speed.png"), findValidRandomPosition(city), "Speed"),
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/speed.png"), findValidRandomPosition(city), "Speed"),
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/speed.png"), findValidRandomPosition(city), "Speed")

        );
    }

    public static void initializeMudPopUps(Screen screen, CityModel city) throws IOException {
        mudpopups = List.of(
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/smallmud.png"), findValidRandomPosition(city), "Mud"),
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/smallmud.png"), findValidRandomPosition(city), "Mud"),
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/smallmud.png"), findValidRandomPosition(city), "Mud")

        );
    }

    public static void initializeBlockPopUps(Screen screen, CityModel city) throws IOException {
        blockpopups = List.of(
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/smallblock.png"), findValidRandomPosition(city), "Block"),
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/smallblock.png"), findValidRandomPosition(city), "Block")
        );
    }
}
