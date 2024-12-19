package com.t01g02.project.viewer;

import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.PopUpsModel;

import java.io.IOException;


public class PopUpsViewer {
    private final Screen screen;

    public PopUpsViewer(Screen screen) throws IOException {
        this.screen = screen;
    }

    public void initializePopUps() throws IOException{
        PopUpsModel.initializeSpeedPopUps(screen);
        PopUpsModel.initializeMudPopUps(screen);
        PopUpsModel.initializeBlockPopUps(screen);
        PopUpsModel.initializeStar(screen);
    }



    public void draw() throws IOException {
        int friendsInPartyCount = CharacterModel.getFriendInPartyCount();

        for (PopUpsModel speedpopup : PopUpsModel.speedpopups) {
            speedpopup.getSprite().drawImage(speedpopup.getPosition());
        }
        for (PopUpsModel mudpopup : PopUpsModel.mudpopups) {
            mudpopup.getSprite().drawImage(mudpopup.getPosition());
        }
        for (PopUpsModel blockpopup : PopUpsModel.blockpopups) {
            blockpopup.getSprite().drawImage(blockpopup.getPosition());
        }
        if(PopUpsModel.getStar() != null && friendsInPartyCount >=2) {
            PopUpsModel.getStar().getSprite().drawImage(PopUpsModel.getStar().getPosition());
        }

    }


}
