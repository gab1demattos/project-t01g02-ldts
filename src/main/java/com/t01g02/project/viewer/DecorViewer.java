package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.Position;

import java.io.IOException;
import java.util.List;

public class DecorViewer {
    private final Sprite house, tree, lighttree, yellowhouse, bluehouse, pinkhouse, pinkflower, yellowflower, blueflower, groundhouse, partyground;


    public DecorViewer(Screen screen) throws IOException {
        this.house = new Sprite(screen, "src/main/resources/extras/house.png");
        this.tree = new Sprite(screen, "src/main/resources/extras/tree.png");
        this.lighttree = new Sprite(screen, "src/main/resources/extras/lighttree.png");
        this.yellowhouse = new Sprite(screen, "src/main/resources/extras/yellowhouse.png");
        this.bluehouse = new Sprite(screen, "src/main/resources/extras/bluehouse.png");
        this.pinkhouse = new Sprite(screen, "src/main/resources/extras/pinkhouse.png");
        this.pinkflower = new Sprite(screen, "src/main/resources/extras/pinkflower.png");
        this.blueflower = new Sprite(screen, "src/main/resources/extras/blueflower.png");
        this.yellowflower = new Sprite(screen, "src/main/resources/extras/yellowflower.png");
        this.groundhouse = new Sprite(screen, "src/main/resources/extras/groundhouse.png");
        this.partyground = new Sprite(screen, "src/main/resources/extras/partyground.png");
    }

    private final List<Position> housePositions = List.of(
            new Position(34, 20),
            new Position(91, 121),
            new Position(279, 20),
            new Position(164, 71)
    );

    private final List<Position> treePositions = List.of(
            new Position(307, 20),
            new Position(307, 34),
            new Position(26, 18),
            new Position(24, 38),
            new Position(151, 73),
            new Position(119, 129),
            new Position(155, 78),
            new Position(155, 87)
    );

    private final List<Position> yellowHousePositions = List.of(
            new Position(95, 71),
            new Position(222, 20),
            new Position(155, 121)
    );

    private final List<Position> blueHousePositions = List.of(
            new Position(95, 20),
            new Position(188, 121),
            new Position(222, 71)
    );

    private final List<Position> pinkHousePositions = List.of(
            new Position(160, 20),
            new Position(30, 121),
            new Position(222,121)
    );

    private final List<Position> lighttreePositions = List.of(
            new Position(310, 27),
            new Position(22, 27),
            new Position(151, 83),
            new Position(117, 136)
    );

    private final List<Position> pinkflowerPositions = List.of(
            new Position(10, 130),
            new Position(140, 30)
            );
    private final List<Position> blueflowerPositions = List.of(
            new Position(75, 30)
    );
    private final List<Position> yellowflowerPositions = List.of(
            new Position(75, 80)
    );
    private final List<Position> groundPositions = List.of(
            new Position(20, 75)
    );
    private final List<Position> partygroundPositions = List.of(
            new Position(275, 75)
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
        drawElements(blueflower, blueflowerPositions);
        drawElements(yellowflower, yellowflowerPositions);
        drawElements(groundhouse, groundPositions);
        drawElements(partyground, partygroundPositions);
    }

}
