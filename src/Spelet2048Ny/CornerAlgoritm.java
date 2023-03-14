package Spelet2048Ny;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class CornerAlgoritm {
    private GUI gui;
    private boolean everyOther = true;
    Timer timer = new Timer(10, e -> {
        boolean wasMovedCorner;
        if(everyOther) {
            wasMovedCorner = gui.directionsInput(KeyEvent.VK_RIGHT);}
            else {
            wasMovedCorner = gui.directionsInput(KeyEvent.VK_DOWN);}
        everyOther = !everyOther;

         if (!wasMovedCorner) {
             wasMovedCorner = gui.directionsInput(KeyEvent.VK_UP);
             everyOther = true;
         }
         if (!wasMovedCorner) {
             gui.directionsInput(KeyEvent.VK_DOWN);
             gui.directionsInput(KeyEvent.VK_LEFT);
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
