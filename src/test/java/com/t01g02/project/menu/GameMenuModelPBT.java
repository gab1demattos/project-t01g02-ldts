package com.t01g02.project.menu;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;

public class GameMenuModelPBT {

    @Property
    void testGetGreetings(){
        GameMenuModel model = new GameMenuModel();
        String greetings = model.getGreetings();
        Assertions.assertTrue(greetings.equals("Hey there, thanks for stopping by!\n"),"Unexpected greetings: " + greetings);
    }
    @Property void testGetInfoText() {
        GameMenuModel model = new GameMenuModel();
        String infoText = model.getInfoText();
        Assertions.assertTrue(infoText.contains("Can you help Kitty pick up her friends for her party?"), "Unexpected infoText: " + infoText);
    }

    @Property void testGetExitInfo() {
        GameMenuModel model = new GameMenuModel();
        String exitInfo = model.getExitInfo(); Assertions.assertTrue(exitInfo.equals("Esc to exit"), "Unexpected exitInfo: " + exitInfo);
    }

    @Property void testGetOptions() {
        GameMenuModel model = new GameMenuModel();
        String[] options = model.getOptions();
        Assertions.assertTrue(options.length == 2 && options[0].equals("Settings") && options[1].equals("Play"), "Unexpected options: " + java.util.Arrays.toString(options));
    }

    @Property void testSetSelectedOption(@ForAll("validSelectedIndex") int selectedOption) {
        GameMenuModel model = new GameMenuModel(); int[] validIndices = {0, 1};
        selectedOption = validIndices[selectedOption % validIndices.length];
        model.setSelectedOption(selectedOption); Assertions.assertTrue(model.getSelectedOption() == selectedOption, "Unexpected selected option: " + model.getSelectedOption());
    }

    @Provide
    Arbitrary<Integer> validSelectedIndex() {
        return Arbitraries.integers().between(0, 1);
    }
}
