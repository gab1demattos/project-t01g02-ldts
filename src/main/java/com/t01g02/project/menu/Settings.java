package com.t01g02.project.menu;

public class Settings {
    private boolean musicOn;
    private boolean soundOn;

    public Settings() {
        this.musicOn = true;
        this.soundOn = true;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void musicSound() {
        this.musicOn = !musicOn;
    }

    public void toggleSound() {
        this.soundOn = !soundOn;
    }

}
