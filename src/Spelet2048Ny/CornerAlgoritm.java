package Spelet2048Ny;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class CornerAlgoritm {
    private GUI gui;


    Timer timer = new Timer(100, e -> {

                //gui.directionsInput(KeyEvent.VK_LEFT);
                gui.directionsInput(KeyEvent.VK_RIGHT);
                //gui.directionsInput(KeyEvent.VK_UP);
                gui.directionsInput(KeyEvent.VK_DOWN);
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
