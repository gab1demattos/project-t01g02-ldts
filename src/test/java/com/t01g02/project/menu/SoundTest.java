package com.t01g02.project.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.net.URL;

import static org.mockito.Mockito.*;

 public class SoundTest {
    private Sound sound;

    @Mock
    private Clip clip;

    @Mock
    private AudioInputStream audioInputStream;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        sound = new Sound();
    }

    @Test
    public void testPLay() throws Exception{

        URL url = getClass().getResource("/audio/selectSound.wav");
        Sound soundSpy = spy(sound);
        doReturn(url).when(soundSpy).getClass().getResource(anyString());
        when(AudioSystem.getClip()).thenReturn(clip);

        doNothing().when(clip).open(audioInputStream);
        doNothing().when(clip).start();

        FloatControl volumeControl = mock(FloatControl.class);
        when(clip.getControl(FloatControl.Type.MASTER_GAIN)).thenReturn(volumeControl);

        soundSpy.play("/audio/selectSound.wav");

        Thread.sleep(1000);

        verify(clip).open(audioInputStream);
        verify(volumeControl, times(3)).setValue(anyFloat());
        verify(clip).start();
    }
 }

