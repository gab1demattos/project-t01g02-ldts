package com.t01g02.project.menu;

import javax.sound.sampled.*;
import java.io.FileNotFoundException;
import java.net.URL;

public class Sound {
    private Clip clip;
    private AudioInputStream in;

    public void play(String filePath){
        new Thread(() -> {
            try{
                URL url = getClass().getResource(filePath);
                if (url == null){
                    throw new FileNotFoundException("Audio file not found: " + filePath);
                }

                in = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(in);
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); volumeControl.setValue(volumeControl.getMaximum());
                volumeControl.setValue(volumeControl.getMaximum());
                volumeControl.setValue(-5.0f);

                clip.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
