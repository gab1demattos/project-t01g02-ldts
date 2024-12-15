package com.t01g02.project.model;

public class Speed {
    private static int speed;

    public Speed() {
        speed = 2;
    }

    public int getSpeed() {
        return speed;
    }
    public void increaseSpeed() {
        speed= 4;
    }
    public void decreaseSpeed() {
        speed  = 1;
    }
    public void resetSpeed() {
        speed = 2;
    }
}
