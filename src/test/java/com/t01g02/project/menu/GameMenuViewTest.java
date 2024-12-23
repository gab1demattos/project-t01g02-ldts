package com.t01g02.project.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GameMenuViewTest {
    @Mock
    private Screen screen;

    @Mock
    private TextGraphics textGraphics;

    @Mock
    private GameMenuModel model;

    private GameMenuView view;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        screen = mock(Screen.class);
        model = mock(GameMenuModel.class);
        textGraphics = mock(TextGraphics.class);
        view = spy(new GameMenuView(screen,model));

    }

    @Test
    void testRedrawScreen() throws IOException {
        TerminalSize mockSize = new TerminalSize(80, 24);
        when(screen.getTerminalSize()).thenReturn(mockSize);

        when(model.getInfoText()).thenReturn("Info Line 1\nInfo Line 2");
        when(model.getGreetings()).thenReturn("Hey there!");
        when(model.getExitInfo()).thenReturn("Esc to exit");
        when(model.getOptions()).thenReturn(new String[]{"Settings", "Play"});
        when(model.getSelectedOption()).thenReturn(0);

        when(screen.newTextGraphics()).thenReturn(textGraphics);

        view.redrawScreen();

        verify(textGraphics, atLeastOnce()).setBackgroundColor(new TextColor.RGB(255, 225, 237));
        verify(textGraphics, atLeastOnce()).fillRectangle(new TerminalPosition(0, 0),mockSize, ' ');

        verify(textGraphics, atLeastOnce()).setBackgroundColor(new TextColor.RGB(229, 168, 177));
        verify(textGraphics, atLeastOnce()).fillRectangle(new TerminalPosition(33, 9), new TerminalSize(15, 6), ' ');

        verify(textGraphics, atLeastOnce()).setForegroundColor(TextColor.ANSI.BLACK);
        verify(textGraphics, atLeastOnce()).putString(35, 10, "Hey there!", SGR.BOLD);

        verify(textGraphics, atLeastOnce()).putString(35, 12, "Info Line 1", SGR.BOLD);
        verify(textGraphics, atLeastOnce()).putString(35, 13, "Info Line 2", SGR.BOLD);

        verify(textGraphics, atLeastOnce()).setForegroundColor(new TextColor.RGB(217, 167, 164));
        verify(textGraphics, atLeastOnce()).setBackgroundColor(new TextColor.RGB(255, 225, 237));
        verify(textGraphics, atLeastOnce()).putString(28, 8, "Esc to exit", SGR.BORDERED);

        verify(textGraphics, atLeastOnce()).setForegroundColor(TextColor.ANSI.BLACK);
        verify(textGraphics, atLeastOnce()).setBackgroundColor(new TextColor.RGB(229, 168, 177));
        verify(textGraphics, atLeastOnce()).setBackgroundColor(new TextColor.RGB(255, 225, 237));
        verify(textGraphics, atLeastOnce()).putString(27, 21, "Settings", SGR.BOLD);
        verify(textGraphics, atLeastOnce()).putString(45, 21, "Play", SGR.BOLD);

        verify(screen).clear();
        verify(screen).refresh();
    }


    @Test
    void testRedrawButtons(){
        TerminalSize terminalSize = new TerminalSize(80, 24);
        TextGraphics textGraphics = mock(TextGraphics.class);

        when(screen.getTerminalSize()).thenReturn(terminalSize);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        when(model.getOptions()).thenReturn(new String[]{"Settings", "Play"});
        when(model.getSelectedOption()).thenReturn(1);

        view.redrawButtons();

        verify(screen).getTerminalSize();
        verify(screen).newTextGraphics();

        verify(textGraphics).putString(anyInt(), anyInt(), eq("Settings"), eq(SGR.BOLD));
        verify(textGraphics).putString(anyInt(), anyInt(), eq("Play"), eq(SGR.BOLD));
    }

    @Test
    void testGetMaxLineLength(){
        String[] lines = {"A", "AAA", "AAAAAA"};
        int maxLength = view.getMaxLineLength(lines);

        assertEquals(6,maxLength);
    }

}
