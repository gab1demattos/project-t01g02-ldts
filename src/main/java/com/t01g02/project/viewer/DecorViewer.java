package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.Position;

import java.io.IOException;
import java.util.List;

public class DecorViewer {
    private final Sprite house, tree, lighttree, yellowhouse, bluehouse, pinkhouse, pinkflower, yellowflower, groundright,groundleft,groundleft2, groundhouse, partyground, lamp;


    public DecorViewer(Screen screen) throws IOException {
        this.house = new Sprite(screen, "src/main/resources/extras/house.png");
        this.tree = new Sprite(screen, "src/main/resources/extras/tree.png");
        this.lighttree = new Sprite(screen, "src/main/resources/extras/lighttree.png");
        this.yellowhouse = new Sprite(screen, "src/main/resources/extras/yellowhouse.png");
        this.bluehouse = new Sprite(screen, "src/main/resources/extras/bluehouse.png");
        this.pinkhouse = new Sprite(screen, "src/main/resources/extras/pinkhouse.png");
        this.pinkflower = new Sprite(screen, "src/main/resources/extras/pinkflower.png");
        this.groundright = new Sprite(screen, "src/main/resources/extras/ground.png");
        this.groundleft = new Sprite(screen, "src/main/resources/extras/groundleft.png");
        this.groundleft2 = new Sprite(screen, "src/main/resources/extras/groundleft2.png");
        this.yellowflower = new Sprite(screen, "src/main/resources/extras/yellowflower.png");
        this.groundhouse = new Sprite(screen, "src/main/resources/extras/groundhouse.png");
        this.partyground = new Sprite(screen, "src/main/resources/extras/partyground.png");
        this.lamp = new Sprite(screen, "src/main/resources/extras/lamp.png");
    }

    private final List<Position> housePositions = List.of(
            new Position(34, 20),
            new Position(91, 121),
            new Position(279, 20),
            new Position(164, 71)
    );

    private final List<Position> treePositions = List.of(
            new Position(23, 24),
            new Position(306, 34),
            new Position(119, 129),
            new Position(155, 78),
            new Position(155, 87)
    );

    private final List<Position> yellowHousePositions = List.of(
            new Position(95, 71),
            new Position(224, 20),
            new Position(154, 121)
    );

    private final List<Position> blueHousePositions = List.of(
            new Position(95, 20),
            new Position(189, 121),
            new Position(224, 71)
    );

    private final List<Position> pinkHousePositions = List.of(
            new Position(154, 20),
            new Position(30, 121),
            new Position(224,121)
    );

    private final List<Position> lighttreePositions = List.of(
            new Position(23, 35),
            new Position(182, 30),
            new Position(151, 83),
            new Position(117, 136)
    );

    private final List<Position> pinkflowerPositions = List.of(
            new Position(169, 130));
    private final List<Position> groundRightPositions = List.of(
            new Position(150, 21),
            new Position(275, 21)

    );
    private final List<Position> groundLeftPositions = List.of(
            new Position(150, 75)

    );
    private final List<Position> groundLeft2Positions = List.of(
            new Position(20, 21)
    );
    private final List<Position> yellowflowerPositions = List.of(
            new Position(204, 130)
    );
    private final List<Position> groundHousePositions = List.of(
            new Position(20, 75)
    );
    private final List<Position> partygroundPositions = List.of(
            new Position(275, 75)
    );
    private final List<Position> lampPositions = List.of(
            new Position(78, 28),
            new Position(208, 28),
            new Position(208, 79),
            new Position(78, 79)
    );

    public List<Position> getHousePositions() {
        return housePositions;
    }
    public List<Position> getBlueHousePositions() {
        return blueHousePositions;
    }

    public List<Position> getLighttreePositions() {
        return lighttreePositions;
    }

    public List<Position> getPinkHousePositions() {
        return pinkHousePositions;
    }

    public List<Position> getTreePositions() {
        return treePositions;
    }

    public List<Position> getYellowHousePositions() {
        return yellowHousePositions;
    }

    public void drawElements(Sprite sprite, List<Position> positions) {
        for (Position position : positions) {
            sprite.drawImage(position);
        }
    }
    public void drawDecorations() throws IOException {
        drawElements(house, housePositions);
        drawElements(tree, treePositions);
        drawElements(lighttree, lighttreePositions);
        drawElements(yellowhouse, yellowHousePositions);
        drawElements(bluehouse, blueHousePositions);
        drawElements(pinkhouse, pinkHousePositions);
        drawElements(pinkflower, pinkflowerPositions);
        drawElements(yellowflower, yellowflowerPositions);
        drawElements(groundhouse, groundHousePositions);
        drawElements(partyground, partygroundPositions);
        drawElements(lamp,lampPositions);
        drawElements(groundright, groundRightPositions);
        drawElements(groundleft,groundLeftPositions);
        drawElements(groundleft2,groundLeft2Positions);
    }

}
