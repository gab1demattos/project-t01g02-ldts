package com.t01g02.project.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class GameKeyListener implements KeyListener {
    private final KittyController controller;
    private final Set<KeyStroke> keys;

    public GameKeyListener(KittyController controller) {
        this.controller = controller;
        this.keys = new HashSet<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keys.add(new KeyStroke(KeyType.ArrowUp));
                System.out.println(keys);
                break;
            case KeyEvent.VK_DOWN:
                keys.add(new KeyStroke(KeyType.ArrowDown));
                break;
            case KeyEvent.VK_LEFT:
                keys.add(new KeyStroke(KeyType.ArrowLeft));
                break;
            case KeyEvent.VK_RIGHT:
                keys.add(new KeyStroke(KeyType.ArrowRight));
                break;
            case KeyEvent.VK_Q:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keys.remove(new KeyStroke(KeyType.ArrowUp));
                break;
            case KeyEvent.VK_DOWN:
                keys.remove(new KeyStroke(KeyType.ArrowDown));
                break;
            case KeyEvent.VK_LEFT:
                keys.remove(new KeyStroke(KeyType.ArrowLeft));
                break;
            case KeyEvent.VK_RIGHT:
                keys.remove(new KeyStroke(KeyType.ArrowRight));
                break;
            default:
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public Set<KeyStroke> getKeys() {
        return keys;
    }
}

