package com.t01g02.project;

import com.googlecode.lanterna.graphics.TextGraphics;

public class Element {
    protected Position position;
    public Element(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    //public abstract void draw(TextGraphics graphics);
}
