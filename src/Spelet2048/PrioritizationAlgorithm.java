package Spelet2048;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class PrioritizationAlgorithm {
    private GUI gui;
    private boolean prioritization = true;
    Timer timer = new Timer(10, e -> {
        if (prioritization) {
            prioritization = gui.directionsInput(KeyEvent.VK_RIGHT);}
        if (!prioritization) {
            prioritization = gui.directionsInput(KeyEvent.VK_DOWN);}
        if (!prioritization) {
            prioritization = gui.directionsInput(KeyEvent.VK_UP);}
        if (!prioritization) {
            gui.directionsInput(KeyEvent.VK_LEFT);}
    });

    public PrioritizationAlgorithm(GUI gui) {
        this.gui = gui;
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}
