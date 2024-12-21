package com.t01g02.project.menu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Music {
    private Clip clip;
    protected AudioInputStream in;
    private String currentTrack;

    public void play(String filePath, boolean loop, boolean musicEnabled) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if (!musicEnabled) {
            stop();
            return;
        }

        if (clip != null && clip.isRunning() && filePath.equals(currentTrack)) {
            return;
        }

        stop();

        new Thread(() -> {
            try{
                URL url = getClass().getResource(filePath);
                if (url == null){
                    throw new FileNotFoundException("Audio file not found" + filePath);
                }
                in = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(in);

                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-10.0f);
                clip.start();
                if(loop){
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                currentTrack = filePath;
            }catch(Exception e){
                e.printStackTrace();
            }

        }).start();
    }

    public void stop(){
        if(clip!=null && clip.isRunning()){
            clip.stop();
            clip.close();
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }


}
