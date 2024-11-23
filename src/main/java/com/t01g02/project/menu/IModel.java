package com.t01g02.project.menu;

public interface IModel {
    String getGreetings();
    String getInfoText();
    String getExitInfo();
    String[] getOptions();
    int getSelectedOption();
    void setSelectedOption(int selectedOption);


}
