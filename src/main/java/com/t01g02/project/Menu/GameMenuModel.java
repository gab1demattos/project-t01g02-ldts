package com.t01g02.project.Menu;

public class GameMenuModel implements IModel {
    private String greetings = "Hey there, thanks for stopping by!\n";
    private String infoText = "Can you help Kitty pick up her friends for her party?\n\n" +
                                "1. Use the arrows to move Kitty\n" + "\n" +
                                "2. Avoid roadblocks\n" +"\n"+
                                "3. Lookout for bonus points ;)\n" + "\n"+
                                "4. Have fun, and remember to make it on time\n";
    private String exitInfo = "Esc to exit";
    private final String[] options = {"Settings", "Play"}; // Option buttons
    private int selectedOption = 1;


    @Override
    public String getGreetings() {
        return greetings;
    }

    @Override
    public String getInfoText(){
        return infoText;
    }

    @Override
    public String getExitInfo() {
        return exitInfo;
    }


    @Override
    public String[] getOptions() {
        return options;
    }

    @Override
    public int getSelectedOption() {
        return selectedOption;
    }

    @Override
    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }


}