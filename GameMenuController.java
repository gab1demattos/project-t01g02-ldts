package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class GameMenuController implements IController{
    private boolean running = true;
    private final IView view;
    private IModel model;
    private final Screen screen;

    public GameMenuController(GameMenuView view, Screen screen,IModel model){
        this.view = view;
        this.screen=screen;
        this.model = model;
    }

    public boolean isRunning(){
        return running;
    }

    @Override
    public void processInput() throws IOException{
        KeyStroke input = screen.readInput();
            if(input!=null){
                switch (input.getKeyType()){
                    case Escape:
                        running = false;
                        System.exit(0);
                        break;
                    case ArrowLeft:
                        model.setSelectedOption((model.getSelectedOption() - 1 + model.getOptions().length) % model.getOptions().length);
                        view.redrawButtons();
                        break;
                    case ArrowRight:
                        model.setSelectedOption((model.getSelectedOption() + 1) % model.getOptions().length);
                        view.redrawButtons();
                        break;
                    case Enter:
                        executeSelectedOption();
                        break;
                    default:
                        break;
                }
            }else return;

    }

    void executeSelectedOption(){
        String selectedOption = model.getOptions()[model.getSelectedOption()];
        switch (selectedOption){
            case "Settings":
                openSettings();
                break;
            case "Play":
                startGame();
                break;
            default:
                break;
        }
    }

    private void openSettings(){
        System.out.println("Opening settings...");
    }

    private void startGame(){
        System.out.println("Game Starting...");
    }

    @Override
    public void updateView(){
        view.redrawScreen();
        if(screen.doResizeIfNecessary()!=null){
            view.redrawScreen();
        }
    }
}
