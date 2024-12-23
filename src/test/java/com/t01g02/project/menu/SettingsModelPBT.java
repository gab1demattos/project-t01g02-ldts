package com.t01g02.project.menu;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;

public class SettingsModelPBT {
    @Property
    void toggleMusic(@ForAll boolean musicOn){
        SettingsModel model = new SettingsModel();

        model.setMusicOn(musicOn);

        Assertions.assertEquals(musicOn,model.isMusicOn());
    }

    @Property
    void toggleSound(@ForAll boolean soundOn){
        SettingsModel model = new SettingsModel();

        model.setSoundOn(soundOn);

        Assertions.assertEquals(soundOn,model.isSoundOn());
    }

    @Property
    void validOptionSelection(@ForAll @IntRange(min = 0, max = 1) int selectedOption) {
        SettingsModel model = new SettingsModel();

        model.setSelectedOption(selectedOption);

        Assertions.assertEquals(selectedOption, model.getSelectedOption());
    }


    @Property
    void MusicSelection(@ForAll @IntRange(min = 0, max = 1) int musicOption) {
        SettingsModel model = new SettingsModel();

        model.setMusicSelectedOption(musicOption);

        Assertions.assertEquals(musicOption, model.getMusicSelectedOption());
    }

    @Property
    void SoundSelection(@ForAll @IntRange(min = 0, max = 1) int soundOption) {
        SettingsModel model = new SettingsModel();

        model.setSoundSelectedOption(soundOption);

        Assertions.assertEquals(soundOption, model.getSoundSelectedOption());
    }

    @Property
    void lastOptionConsistency(@ForAll @IntRange(min = 0, max = 1) int lastOption) {
        SettingsModel model = new SettingsModel();

        model.setLastMusicSelectedOption(lastOption);
        model.setLastSoundSelectedOption(lastOption);

        Assertions.assertEquals(lastOption, model.getLastMusicSelectedOption());
        Assertions.assertEquals(lastOption, model.getLastSoundSelectedOption());
    }
}
