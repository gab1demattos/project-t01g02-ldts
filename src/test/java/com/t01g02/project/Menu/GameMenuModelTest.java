package com.t01g02.project.Menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameMenuModelTest {
    private GameMenuModel gameMenuModel;

    @BeforeEach
    void setUp() {
        gameMenuModel = new GameMenuModel();
    }

    @Test
    void testDefaultGreetings() {
        // check greetings message
        String expected = "Hey there, thanks for stopping by!\n";
        assertEquals(expected, gameMenuModel.getGreetings());
    }

    @Test
    void testDefaultInfoText() {
        String expected = """
                Can you help Kitty pick up her friends for her party?

                1. Use the arrows to move Kitty

                2.You can only pick up a friend at a time

                3. Avoid roadblocks

                4. Lookout for bonus points ;)

                5. Have fun, and remember to make it on time
                """;
        assertEquals(expected, gameMenuModel.getInfoText());
    }

    @Test
    void testDefaultExitInfo() {
        assertEquals("Esc to exit", gameMenuModel.getExitInfo());
    }

    @Test
    void testDefaultOptions() {
        String[] expectedOptions = {"Settings", "Play"};
        assertArrayEquals(expectedOptions, gameMenuModel.getOptions());
    }

    @Test
    void testDefaultSelectedOption() {
        assertEquals(1, gameMenuModel.getSelectedOption());
    }

    @Test
    void testSetSelectedOption() {
        gameMenuModel.setSelectedOption(0);
        assertEquals(0, gameMenuModel.getSelectedOption());

        gameMenuModel.setSelectedOption(1);
        assertEquals(1, gameMenuModel.getSelectedOption());
    }

    @Test
    void testModifyGreetings() {
        String newGreetings = "Welcome to the party!";
        gameMenuModel.setSelectedOption(0);
        assertEquals(0, gameMenuModel.getSelectedOption());

        gameMenuModel.setSelectedOption(1);
        assertEquals(1, gameMenuModel.getSelectedOption());
    }
}
