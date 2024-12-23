package com.t01g02.project.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SettingsModelTest {
    private SettingsModel settingsModel;

    @BeforeEach
    void setUp(){ settingsModel = new SettingsModel();}

    @Test
    void testDeafultValues(){
        assertTrue(settingsModel.isMusicOn());
        assertTrue(settingsModel.isSoundOn());
        assertEquals(0, settingsModel.getSelectedOption());
        assertEquals(0, settingsModel.getMusicSelectedOption());
        assertEquals(0, settingsModel.getSoundSelectedOption());
    }

    @Test
    void testGetters() {
        assertArrayEquals(new String[]{"Music", "Sound"}, settingsModel.getOptions());
        assertArrayEquals(new String[]{"ON", "OFF"}, settingsModel.getMusicOptions());
        assertArrayEquals(new String[]{"ON", "OFF"}, settingsModel.getSoundOptions());
    }

    @Test
    void testDefaultText(){
        assertEquals("ESC to exit Settings", settingsModel.getExitSettingsInfo());
        assertEquals("Enter to select", settingsModel.getEnterSettingsInfo());
        assertEquals("B to go back to main options", settingsModel.getBSettingsInfo());
    }

    @Test
    void testSetters(){
        settingsModel.setMusicOn(false);
        assertFalse(settingsModel.isMusicOn());

        settingsModel.setSoundOn(false);
        assertFalse(settingsModel.isSoundOn());

        settingsModel.setSelectedOption(1);
        assertEquals(1, settingsModel.getSelectedOption());
    }

    @Test
    void testSelectedOptions(){
        settingsModel.setMusicSelectedOption(1);
        settingsModel.setLastMusicSelectedOption(settingsModel.getMusicSelectedOption());
        assertEquals(1, settingsModel.getLastMusicSelectedOption());

        settingsModel.setSoundSelectedOption(0);
        settingsModel.setLastSoundSelectedOption(settingsModel.getSoundSelectedOption());
        assertEquals(0, settingsModel.getLastSoundSelectedOption());
    }

    @Test
    void testStateChanges(){
        settingsModel.setMusicOn(false);
        assertFalse(settingsModel.isMusicOn());
        settingsModel.setMusicOn(true);
        assertTrue(settingsModel.isMusicOn());
    }
}
