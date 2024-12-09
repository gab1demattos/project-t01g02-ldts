package com.t01g02.project;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    private final Controller controller;

    public GameKeyListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("UP key pressed");
                controller.processInput(new KeyStroke(KeyType.ArrowUp, false, false));
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN key pressed");
                controller.processInput(new KeyStroke(KeyType.ArrowDown, false, false));
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT key pressed");
                controller.processInput(new KeyStroke(KeyType.ArrowLeft, false, false));
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT key pressed");
                controller.processInput(new KeyStroke(KeyType.ArrowRight, false, false));
                break;
            case KeyEvent.VK_Q: // Quit game
                System.exit(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Optional: Handle key releases if needed
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Optional: Handle typed keys if needed

    }
}

