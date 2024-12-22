package com.t01g02.project.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
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
        view = new GameMenuView(screen,model);
    }

    // Test RedrawScreen

    @Test
    void testDrawMessages() {
        TerminalSize terminalSize = new TerminalSize(80, 24);
        TextGraphics textGraphics = mock(TextGraphics.class);

        when(screen.getTerminalSize()).thenReturn(terminalSize);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        when(model.getInfoText()).thenReturn("Info Line 1\nInfo Line 2");
        when(model.getGreetings()).thenReturn("Hey there!");
        when(model.getExitInfo()).thenReturn("Esc to Exit");

        view.redrawScreen();

        verify(textGraphics).putString(anyInt(), anyInt(), eq("Hey there!"), eq(SGR.BOLD));
        verify(textGraphics).putString(anyInt(), anyInt(), eq("Info Line 1"), eq(SGR.BOLD));
        verify(textGraphics).putString(anyInt(), anyInt(), eq("Info Line 2"), eq(SGR.BOLD));
        verify(textGraphics).putString(anyInt(), anyInt(), eq("Esc to Exit"), eq(SGR.BORDERED));
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
    void testButtonColorSelected(){
        view.buttonColor(textGraphics,10,10,"Test",true);

        InOrder inOrder = inOrder(textGraphics);

        inOrder.verify(textGraphics).setForegroundColor(TextColor.ANSI.BLACK);
        inOrder.verify(textGraphics).setBackgroundColor(new TextColor.RGB(229, 168, 177));
        inOrder.verify(textGraphics).putString(10, 10, "Test", SGR.BOLD);

    }

    @Test
    void testButtonColorUnselected(){
        view.buttonColor(textGraphics,10,10,"Test", false);
        InOrder inOrder = inOrder(textGraphics);
        inOrder.verify(textGraphics).setForegroundColor(TextColor.ANSI.BLACK);
        inOrder.verify(textGraphics).setBackgroundColor(new TextColor.RGB(255, 225, 237));
        inOrder.verify(textGraphics).putString(10, 10, "Test", SGR.BOLD);
    }

    @Test
    void testGetMaxLineLength(){
        String[] lines = {"A", "AAA", "AAAAAA"};
        int maxLength = view.getMaxLineLength(lines);

        assertEquals(6,maxLength);
    }

}
