package com.t01g02.project.menu;

public class SettingsModel {
    private boolean musicOn;
    public boolean soundOn;
    private final String[] options = {"Music", "Sound"};
    private int selectedOption = 0;
    private final String[] musicOptions = {"ON", "OFF"};
    private int musicSelectedOption = 0;
    private int soundSelectedOption = 0;
    private final String[] soundOptions = {"ON", "OFF"};
    private int lastMusicSelectedOption;
    private int lastSoundSelectedOption;


    public SettingsModel() {
        this.musicOn = true;
        this.soundOn = true;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public String[] getOptions() {
        return options;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = (selectedOption + options.length) % options.length;
    }

    public String getExitSettingsInfo() {
        return "ESC to exit Settings";
    }

    public String getEnterSettingsInfo(){
        return "Enter to select";
    }
    public String getBSettingsInfo(){
        return "B to go back to main options";
    }

    public String[] getMusicOptions() {
        return musicOptions;
    }

    public String[] getSoundOptions() {
        return soundOptions;
    }

    public int getMusicSelectedOption() {
        return musicSelectedOption;
    }

    public void setMusicSelectedOption(int musicSelectedOption) {
        this.musicSelectedOption = musicSelectedOption;
    }

    public int getSoundSelectedOption() {
        return soundSelectedOption;
    }

    public void setSoundSelectedOption(int soundSelectedOption) {
        this.soundSelectedOption = soundSelectedOption;
    }

    public int getLastMusicSelectedOption() {
        return lastMusicSelectedOption;
    }

    public void setLastMusicSelectedOption(int option) {
        lastMusicSelectedOption = option;
    }

    public int getLastSoundSelectedOption() {
        return lastSoundSelectedOption;
    }

    public void setLastSoundSelectedOption(int option) {
        lastSoundSelectedOption = option;
    }
}
