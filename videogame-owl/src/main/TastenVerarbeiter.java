package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TastenVerarbeiter implements KeyListener {
     public boolean links, rechts, schuss;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            links = true;
        } 
        else if (code == KeyEvent.VK_RIGHT) {
            rechts = true;
        }
        else if (code == KeyEvent.VK_S) {
        	schuss = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            links = false;
        } else if (code == KeyEvent.VK_RIGHT) {
            rechts = false;
        }
        else if (code == KeyEvent.VK_S) {
        	schuss = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
