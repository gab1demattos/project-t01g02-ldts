package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.Element;
import com.t01g02.project.model.PopUpsModel;

import java.io.IOException;

public class PopUpsViewer {
    private final Screen screen;

    public PopUpsViewer(Screen screen) throws IOException {
        this.screen = screen;
    }

    public void initializePopUps() throws IOException{
        PopUpsModel.initializeSpeedPopUps(screen);
    }

    public void draw() throws IOException {
        for (PopUpsModel speedpopup : PopUpsModel.speedpopups) {
            speedpopup.getSprite().drawImage(speedpopup.getPosition());
        }
    }

}
