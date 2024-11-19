package com.t01g02.project;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.lang.invoke.SwitchPoint;
import java.nio.channels.spi.SelectorProvider;

public abstract class Element {
    protected Position position;
    protected Sprite sprite;
    protected String name;


    public Element(Sprite sprite, Position position, String name) {
        this.sprite=sprite;
        this.position = position;
        this.name= name;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Sprite getSprite(){return sprite;}

    public String getName() {
        return name;
    }

    //public abstract void draw(TextGraphics graphics);
}
