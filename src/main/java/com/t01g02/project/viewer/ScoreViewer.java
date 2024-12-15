package com.t01g02.project.viewer;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t01g02.project.model.Position;
import com.t01g02.project.model.Score;

import java.io.IOException;

public class ScoreViewer {
    private final Score score;
    private final Screen screen;

    public ScoreViewer(Score score, Screen screen){
        this.score=score;
        this.screen=screen;
    }
    // cor adicionada
    public void drawStringSprite(String text, int startX, int startY, TextGraphics graphics) {
        String[] sprite = CharacterSprites.getStringSprite(text);
        graphics.setForegroundColor(new TextColor.RGB(183, 134, 141));
        graphics.setBackgroundColor(new TextColor.RGB(255, 240, 245));
        for (int i = 0; i < sprite.length; i++) {
            for (int j = 0; j<sprite[i].length();j++){
                char c = sprite[i].charAt(j);
                graphics.putString(startX, startY + i, sprite[i], SGR.BOLD);
            }
        }
    }
    public int getScoreEndPos(String text, int startX){
        String[] sprite = CharacterSprites.getStringSprite(text);
        int maxLength = 0;
        for (String line : sprite){
            if (line.length() > maxLength){
                maxLength = line.length();
            }
        }
        return startX + maxLength;
    }

    public void draw(int startX, int startY) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        for (int y = 180; y < 195; y++) {
            for (int x = 0; x < 345; x++){
                TextCharacter pinkChar;
                if (y==180){
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#B7868D"));
                }else{
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#FFF0F5"));
                }
                graphics.putString(x, y, " ");
            }
        }
        graphics.setForegroundColor(new TextColor.RGB(183, 134, 141));
        graphics.setBackgroundColor(new TextColor.RGB(255, 240, 245));

        drawStringSprite("SCORE", startX, startY, graphics);

        String scoreText = String.valueOf(score.getScore());
        int endX = getScoreEndPos("SCORE", startX);
        drawStringSprite(scoreText, endX + 5, startY, graphics);

    }
}
