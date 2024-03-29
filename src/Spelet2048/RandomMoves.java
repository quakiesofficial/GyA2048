package Spelet2048;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class RandomMoves {

    int randomInput;

    private GUI gui;
    Timer timer = new Timer(10, e -> {
        randomInput = ((int) (Math.random() * 4) + 1);
        switch (randomInput) {
            case 1 -> gui.directionsInput(KeyEvent.VK_LEFT);
            case 2 -> gui.directionsInput(KeyEvent.VK_RIGHT);
            case 3 -> gui.directionsInput(KeyEvent.VK_UP);
            case 4 -> gui.directionsInput(KeyEvent.VK_DOWN);
        }
    });

    public RandomMoves(GUI gui) {
        this.gui = gui;
    }

        public void startTimer() {
            timer.start();
        }

        public void stopTimer() {
        timer.stop();
    }
}
