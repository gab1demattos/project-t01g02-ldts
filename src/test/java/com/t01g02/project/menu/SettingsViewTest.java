package com.t01g02.project.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SettingsViewTest {
    @Mock private Screen screen;
    @Mock private TextGraphics textGraphics;
    @Mock private SettingsModel model;
    private SettingsView settingsView;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        when(screen.getTerminalSize()).thenReturn(new TerminalSize(80,24));

        when(model.getOptions()).thenReturn(new String[]{"Music", "Sound"});
        when(model.getMusicOptions()).thenReturn(new String[]{"ON", "OFF"});
        when(model.getSoundOptions()).thenReturn(new String[]{"ON", "OFF"});

        settingsView = new SettingsView(screen,model);
    }

    @Test
    public void testRedrawScreen() throws IOException {
        when(model.getExitSettingsInfo()).thenReturn("Exit");
        when(model.getEnterSettingsInfo()).thenReturn("Enter");
        settingsView.redrawScreen(); verify(screen).clear(); verify(textGraphics, times(3)).setBackgroundColor(new TextColor.RGB(255, 225, 237));
        verify(textGraphics).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 24), ' ');
        verify(textGraphics, times(5)).setBackgroundColor(new TextColor.RGB(229, 168, 177));
        verify(textGraphics, times(3)).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), eq(' '));
        verify(textGraphics, times(2)).setForegroundColor(new TextColor.RGB(217, 167, 164));
        verify(textGraphics, times(3)).setBackgroundColor(new TextColor.RGB(255, 225, 237));
        verify(screen).refresh();

    }

    @Test
    public void testDrawBInfo() throws IOException {
        when(screen.getTerminalSize()).thenReturn(new TerminalSize(80,24));
        when(model.getBSettingsInfo()).thenReturn("B Info");

        settingsView.drawBInfo(true);

        ArgumentCaptor<Integer> xCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> yCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<SGR> sgrCaptor = ArgumentCaptor.forClass(SGR.class);

        verify(textGraphics).putString(xCaptor.capture(), yCaptor.capture(), stringCaptor.capture(), sgrCaptor.capture());

        assertEquals(37, xCaptor.getValue());
        assertEquals(18, yCaptor.getValue());
        assertEquals("B Info", stringCaptor.getValue());
        assertEquals(SGR.ITALIC, sgrCaptor.getValue());
        verify(screen).refresh();
    }
    @Test
    public void testDrawBInfo_NotInSubMenu() throws IOException {
        when(screen.getTerminalSize()).thenReturn(new TerminalSize(80, 24));
        when(model.getBSettingsInfo()).thenReturn("B Info");

        settingsView.drawBInfo(false);

        verify(textGraphics, never()).putString(anyInt(), anyInt(), anyString(), any(SGR.class));
        verify(screen).refresh();
    }

    @Test
    public void testRedrawButtons() {
        when(model.getOptions()).thenReturn(new String[]{"Music", "Sound"});
        when(model.getMusicOptions()).thenReturn(new String[]{"ON", "OFF"});
        when(model.getSoundOptions()).thenReturn(new String[]{"ON", "OFF"});
        when(model.getSelectedOption()).thenReturn(0);
        when(model.getMusicSelectedOption()).thenReturn(0);
        when(model.getSoundSelectedOption()).thenReturn(1);

        when(screen.newTextGraphics()).thenReturn(textGraphics);
        when(screen.getTerminalSize()).thenReturn(new TerminalSize(80, 24));

        settingsView.redrawButtons();

        verify(textGraphics, atLeastOnce()).setForegroundColor(TextColor.ANSI.BLACK);
        verify(textGraphics, atLeastOnce()).setBackgroundColor(new TextColor.RGB(229, 212, 214));
        verify(textGraphics, atLeastOnce()).setBackgroundColor(new TextColor.RGB(229, 168, 177));
        verify(textGraphics, atLeastOnce()).putString(anyInt(), anyInt(), anyString(), eq(SGR.BOLD));
    }

}
