package com.t01g02.project.model;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.viewer.Sprite;

import java.io.IOException;
import java.util.List;

public class PopUpsModel extends Element {
    public static List<PopUpsModel> speedpopups;
    public static List<PopUpsModel> mudpopups;

    public PopUpsModel(Sprite sprite, Position position, String name) {
        super(sprite, position, name);
    }

    public static void initializeSpeedPopUps(Screen screen) throws IOException {
        speedpopups = List.of(
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/speed.png"), new Position(100, 100), "Speed"),
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/speed.png"), new Position(150, 160), "Speed")
        );
    }

    public static void initializeMudPopUps(Screen screen) throws IOException {
        mudpopups = List.of(
                new PopUpsModel(new Sprite(screen, "src/main/resources/Pop-ups/smallmud.png"), new Position(160, 105), "Mud")
        );
    }
}
