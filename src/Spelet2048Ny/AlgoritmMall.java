package Spelet2048Ny;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class AlgoritmMall {



    private GUI gui;
    int lastlargestnr=0;
    Timer timer = new Timer(1000, e -> {
        Board2048 board= gui.board;
        CalculatePosition calc = new CalculatePosition(board.getBoardSize(),1,2,1);

                //Liten workaround kring swich att kräver statiska tal
        boolean anythingHappen=true;
        int largestnr=0;
        try {
            int left = calc.SimulateMoves(board, 0);
            int up = calc.SimulateMoves(board, 1);
            int biggest =0;

            if (!(lastlargestnr == 0 && !anythingHappen)){
                largestnr=0;
                biggest = left;
            }
            if (up > biggest) {
                if (!(lastlargestnr == 1 && !anythingHappen)){
                    largestnr=1;
                    biggest = up;
                }
            }

            int right = calc.SimulateMoves(board, 2);
            if (right > biggest) {

                if (!(lastlargestnr == 2 && !anythingHappen)){
                    largestnr=2;
                    biggest = right;
                }
            }
            int down = calc.SimulateMoves(board, 3);
            if (down > biggest) {
                if (!(lastlargestnr == 3 && !anythingHappen)){
                    largestnr=3;
                }
            }
        } catch (Exception exe){ System.out.println(exe);}

        lastlargestnr=largestnr;

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

    public AlgoritmMall(GUI gui) {this.gui = gui;}

        public void startTimer() {
            timer.start();
        }

        public void stopTimer() {
        timer.stop();
    }


}
