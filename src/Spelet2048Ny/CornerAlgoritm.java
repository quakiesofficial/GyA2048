package Spelet2048Ny;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class CornerAlgoritm {
    private GUI gui;
    Timer timer = new Timer(500, e -> {
        int move = (int) (Math.random()*5);
        switch (move) {
            case 1:
                gui.directionsInput(KeyEvent.VK_LEFT);
                break;
            case 2:
                gui.directionsInput(KeyEvent.VK_RIGHT);
                break;
            case 3:
                gui.directionsInput(KeyEvent.VK_UP);
                break;
            case 4:
                gui.directionsInput(KeyEvent.VK_DOWN);
                break;
        }
    });

    public CornerAlgoritm(GUI gui) {
        this.gui = gui;
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}
