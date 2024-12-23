package com.t01g02.project.menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MusicTest {
    private Music music;
    private Clip mockClip;
    private AudioInputStream mockAudioStream;

    @BeforeEach
    void setup(){
        music = new Music();
        mockClip = mock(Clip.class);
        mockAudioStream = mock(AudioInputStream.class);

        music.setExecutor(Executors.newSingleThreadExecutor());

        try {
            var clipField = Music.class.getDeclaredField("clip");
            clipField.setAccessible(true);
            clipField.set(music, mockClip);

            var inField = Music.class.getDeclaredField("in");
            inField.setAccessible(true);
            inField.set(music, mockAudioStream);
        } catch (Exception e) {
            fail("Failed to inject mocks: " + e.getMessage());
        }
    }
//    @Test
//    void testPlayMusicEnabled() throws Exception {
//        String filePath = "/audio/menuSong.wav";
//        boolean loop = true;
//        boolean musicEnabled = true;
//
//        URL mockUrl = getClass().getResource(filePath);
//        assertNotNull(mockUrl, "Mock URL cannot be null for the test");
//
//        music.play(filePath, loop, musicEnabled);
//
//        Thread.sleep(1000);
//
//        verify(mockClip, times(1)).open(mockAudioStream);
//        verify(mockClip, times(1)).start();
//
//        if (loop) verify(mockClip, times(1)).loop(Clip.LOOP_CONTINUOUSLY);
//
//    }
//
//
//    @Test
//    void testPlayMusicDisabled() throws Exception {
//        String filePath = "/audio/menuSong.wav";
//        boolean loop = false;
//        boolean musicEnabled = false;
//
//        music.play(filePath, loop, musicEnabled);
//
//        verify(mockClip, timeout(1000)).stop();
//    }

    @Test
    void testStop() {
        when(mockClip.isRunning()).thenReturn(true);

        music.stop();

        verify(mockClip).stop();
        verify(mockClip).close();
    }

    @Test
    void testStopWhenNotRunning() {
        when(mockClip.isRunning()).thenReturn(false);

        music.stop();

        verify(mockClip, never()).stop();
        verify(mockClip, never()).close();
    }

    @Test
    void testIsPlaying() {
        when(mockClip.isRunning()).thenReturn(true);
        assertTrue(music.isPlaying());

        when(mockClip.isRunning()).thenReturn(false);
        assertFalse(music.isPlaying());
    }
}
