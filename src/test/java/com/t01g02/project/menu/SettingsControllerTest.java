package com.t01g02.project.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class SettingsControllerTest {
    @Mock private Screen screen;
    @Mock private TextGraphics textGraphics;
    @Mock private SettingsModel model;
    @Mock private SettingsView view;
    private SettingsController controller;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        view = new SettingsView(screen,model);
    }
    @Test
    void testRedrawButtons() throws Exception {
        when(model.getOptions()).thenReturn(new String[]{"Music", "Sound"});
        when(model.getMusicOptions()).thenReturn(new String[]{"ON", "OFF"});
        when(model.getSoundOptions()).thenReturn(new String[]{"ON", "OFF"});
        when(model.getSelectedOption()).thenReturn(0);
        when(model.getMusicSelectedOption()).thenReturn(0);
        when(model.getSoundSelectedOption()).thenReturn(1);

        when(screen.getTerminalSize()).thenReturn(new TerminalSize(80, 24));

        view.redrawButtons();

        verify(textGraphics, atLeastOnce()).setForegroundColor(TextColor.ANSI.BLACK);
        verify(textGraphics, atLeastOnce()).setBackgroundColor(new TextColor.RGB(229, 212, 214));
        verify(textGraphics, atLeastOnce()).setBackgroundColor(new TextColor.RGB(229, 168, 177));

        verify(textGraphics, atLeastOnce()).putString(anyInt(), anyInt(), anyString(), eq(SGR.BOLD));
    }

    @Test
    void testRedrawScreen() throws Exception {
        when(model.getOptions()).thenReturn(new String[]{"Music", "Sound"});
        when(model.getMusicOptions()).thenReturn(new String[]{"ON", "OFF"});
        when(model.getSoundOptions()).thenReturn(new String[]{"ON", "OFF"});
        when(model.getSelectedOption()).thenReturn(0);
        when(model.getMusicSelectedOption()).thenReturn(0);
        when(model.getSoundSelectedOption()).thenReturn(1);

        when(screen.doResizeIfNecessary()).thenReturn(null);

        controller.updateView();

        verify(view, times(1)).redrawScreen();
        verify(view, times(1)).drawBInfo(false);
        verify(screen, atLeastOnce()).doResizeIfNecessary();
    }

    @Test
    void testDrawBInfo() throws Exception {
        when(screen.getTerminalSize()).thenReturn(new TerminalSize(80, 24));

        when(model.getBSettingsInfo()).thenReturn("Info");

        view.drawBInfo(true);

        verify(textGraphics).setForegroundColor(new TextColor.RGB(217, 167, 164));
        verify(textGraphics).setBackgroundColor(new TextColor.RGB(255, 225, 237));
        verify(textGraphics).putString(anyInt(), anyInt(), eq("Info"), eq(SGR.ITALIC));

        verify(screen).refresh();
    }

}
