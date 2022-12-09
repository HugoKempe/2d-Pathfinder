package com.hugo.inputs;

import com.hugo.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //gamePanel.getGame().getPathManager().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //gamePanel.getGame().getPathManager().keyReleased(e);
    }
}
