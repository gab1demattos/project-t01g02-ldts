package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.Element;
import com.t01g02.project.model.PopUpsModel;

import java.io.IOException;

public class PopUpsViewer {
    private final Screen screen;
    private final CityModel city;

    public PopUpsViewer(Screen screen, CityModel city) throws IOException {
        this.screen = screen;
        this.city = city;
    }

    public void initializePopUps() throws IOException{
        PopUpsModel.initializeSpeedPopUps(screen);
        PopUpsModel.initializeMudPopUps(screen);
        PopUpsModel.initializeBlockPopUps(screen);
    }

    public void draw() throws IOException {
        for (PopUpsModel speedpopup : PopUpsModel.speedpopups) {
            speedpopup.getSprite().drawImage(speedpopup.getPosition());
        }
        for (PopUpsModel mudpopup : PopUpsModel.mudpopups) {
            mudpopup.getSprite().drawImage(mudpopup.getPosition());
        }
        for (PopUpsModel blockpopup : PopUpsModel.blockpopups) {
            blockpopup.getSprite().drawImage(blockpopup.getPosition());
        }
    }
}
