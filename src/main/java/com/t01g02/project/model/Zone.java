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
}
