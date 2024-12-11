package com.t01g02.project.model;

import com.t01g02.project.viewer.Sprite;

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

}
