package Spelet2048;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class PrioriteringsAlgoritm {
    private GUI gui;
    private boolean prioritering = true;
    Timer timer = new Timer(10, e -> {
        if (prioritering) {
            prioritering = gui.directionsInput(KeyEvent.VK_RIGHT);}
        if (!prioritering) {
            prioritering = gui.directionsInput(KeyEvent.VK_DOWN);}
        if (!prioritering) {
            prioritering = gui.directionsInput(KeyEvent.VK_UP);}
        if (!prioritering) {
            gui.directionsInput(KeyEvent.VK_LEFT);}
    });

    public PrioriteringsAlgoritm(GUI gui) {
        this.gui = gui;
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}
