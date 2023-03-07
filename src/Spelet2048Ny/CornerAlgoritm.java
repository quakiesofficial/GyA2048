package Spelet2048Ny;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class CornerAlgoritm {
    private GUI gui;
    private int everyOther = 0;
    Timer timer = new Timer(500, e -> {
                everyOther++;
                        if(everyOther % 2 == 0) gui.directionsInput(KeyEvent.VK_RIGHT);
                        else gui.directionsInput(KeyEvent.VK_DOWN);

                //gui.directionsInput(KeyEvent.VK_LEFT);
                //gui.directionsInput(KeyEvent.VK_UP);
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
