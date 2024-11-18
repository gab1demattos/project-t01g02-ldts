package org.example;

public interface IModel {
    String getGreetings();
    String getInfoText();
    String getExitInfo();
    String[] getOptions();
    int getSelectedOption();
    void setSelectedOption(int selectedOption);


}
