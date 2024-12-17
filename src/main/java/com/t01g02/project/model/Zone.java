package com.t01g02.project.model;

import com.googlecode.lanterna.TextColor;

public class Zone {
    private final Position startposition;
    private final Position endposition;
    private final String identifier;
    private final Tile.Type type;
    private final TextColor color;
    private CharacterModel associatedFriend;


    public Zone(Position startposition, Position endposition, String identifier, Tile.Type type, TextColor color, CharacterModel associatedFriend) {
        this.startposition = startposition;
        this.endposition = endposition;
        this.identifier = identifier;
        this.type = type;
        this.color = color;
        this.associatedFriend = associatedFriend;
    }
    public Position getStartposition() {
        return startposition;
    }
    public Position getEndposition() {
        return endposition;
    }

    public String getIdentifier() {
        return identifier;
    }
    public Tile.Type getType() {
        return type;
    }

    public TextColor getColor() {
        return color;
    }
    public CharacterModel getAssociatedFriend() {
        return associatedFriend;
    }

    public boolean isWithinZone(Position position) {
        int tileX = position.getX();
        int tileY = position.getY();

        int zoneStartX = this.getStartposition().getX();
        int zoneEndX = this.getEndposition().getX()-20;
        int zoneStartY = this.getStartposition().getY();
        int zoneEndY = this.getEndposition().getY()+25;

        return tileX >= zoneStartX && tileX <= zoneEndX &&
                tileY >= zoneStartY && tileY <= zoneEndY;
    }


}
