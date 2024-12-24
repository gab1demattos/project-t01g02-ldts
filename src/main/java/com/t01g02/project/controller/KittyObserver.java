package com.t01g02.project.controller;

public abstract class KittyObserver {
    public abstract int getScore();

    void friendPickedUp() {};
    void friendDroppedOff() {};
    void pickedStar() {};
}
