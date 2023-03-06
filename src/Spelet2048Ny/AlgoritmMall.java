package Spelet2048Ny;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class AlgoritmMall {



    private GUI gui;
    private Board2048 board;
    Timer timer = new Timer(1000, e -> {
        CalculatePosition calc = new CalculatePosition(board.getBoardSize(),1,2,1);

                //Liten workaround kring swich att kräver statiska tal
        int largestnr=0;
        try {

            int left = calc.SimulateMoves(board, 0);
            int biggest = left;
            int up = calc.SimulateMoves(board, 1);
            if (up > biggest) {
                biggest = up;
                largestnr = 1;
            }

            int right = calc.SimulateMoves(board, 2);
            if (right > biggest) {
                biggest = right;
                largestnr = 2;
            }
            int down = calc.SimulateMoves(board, 3);
            if (down > biggest) {
                biggest = down;
                largestnr = 3;
            }
        } catch (Exception exe){ System.out.println(exe);}



        switch (largestnr) {
            case 0:
                gui.directionsInput(KeyEvent.VK_LEFT);
                break;
            case 1:
                gui.directionsInput(KeyEvent.VK_UP);
                break;
            case 2:
                gui.directionsInput(KeyEvent.VK_RIGHT);
                break;
            case 3:
                gui.directionsInput(KeyEvent.VK_DOWN);
                break;
        }
    });

    public AlgoritmMall(GUI gui,Board2048 board) {
        this.gui = gui;
        this.board=board;
    }

        public void startTimer() {
            timer.start();
        }

        public void stopTimer() {
        timer.stop();
    }


}
