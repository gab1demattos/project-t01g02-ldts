package com.t01g02.project.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class SettingsViewPBTTest {
    @Mock private Screen screen;
    @Mock private TextGraphics textGraphics;
    @Mock private SettingsModel model;
    private SettingsView settingsView;

    @BeforeTry
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        when(model.getOptions()).thenReturn(new String[]{"Music", "Sound"});
        when(model.getMusicOptions()).thenReturn(new String[]{"ON", "OFF"});
        when(model.getSoundOptions()).thenReturn(new String[]{"ON", "OFF"});
        settingsView = new SettingsView(screen, model);
    }

    @Property
    void testRedrawScreen(@ForAll("randomTerminalSizes") TerminalSize terminalSize, @ForAll String exitInfo, @ForAll String enterInfo) throws IOException {
        when(screen.getTerminalSize()).thenReturn(terminalSize);
        when(model.getExitSettingsInfo()).thenReturn(exitInfo);
        when(model.getEnterSettingsInfo()).thenReturn(enterInfo);

        settingsView.redrawScreen();

        verify(screen).clear();
        verify(screen).refresh();
        verify(textGraphics, atLeastOnce()).setBackgroundColor(any(TextColor.class));
        verify(textGraphics, atLeastOnce()).fillRectangle(any(TerminalPosition.class), eq(terminalSize), eq(' '));
        verify(textGraphics, atLeastOnce()).putString(anyInt(), anyInt(), anyString(), any(SGR.class));
    }

    @Property
    void testDrawBInfo(@ForAll("randomTerminalSizes") TerminalSize terminalSize, @ForAll boolean showInfo, @ForAll String bInfo) throws IOException {
        when(screen.getTerminalSize()).thenReturn(terminalSize);
        when(model.getBSettingsInfo()).thenReturn(bInfo);

        settingsView.drawBInfo(showInfo);

        if (showInfo) {
            verify(textGraphics).putString(anyInt(), anyInt(), eq(bInfo), eq(SGR.ITALIC));
        } else {
            verify(textGraphics, never()).putString(anyInt(), anyInt(), anyString(), any(SGR.class));
        }
        verify(screen).refresh();
    }

    @Provide
    Arbitrary<TerminalSize> randomTerminalSizes() {
        return Arbitraries.integers().between(20, 200).flatMap(columns -> Arbitraries.integers().between(10, 100).map(rows -> new TerminalSize(columns, rows)));
    }
}
