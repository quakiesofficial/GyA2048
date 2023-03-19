package Spelet2048;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class AverageAlgorithm extends AlgorithmAbstarct {



    private GUI gui;
    private boolean anythingHappen;
    private final int depth =3;
    private int[] lastlargestnr ={-1,-1,-1,-1};

    public int getDepth() {return depth;}

    Timer timer = new Timer(100, e -> {
        Board2048 board= gui.board;
        //Recursionlimit sätter hur djupt algoritmen tänker, recursionNumber ska ALLTID vara 0 här
        //men behövs så att den inte går oändligt djupt
        CalculatePosition calc = new CalculatePosition(board.getBoardSize(),depth,0,2,5,4);

                //Liten workaround kring switch att kräver statiska tal

        int largestnr=0;
        int[] averageArray=new int[4];
        try {
            int[] leftAr = calc.SimulateMoves(board.getBoard(), 0);
            averageArray[0] = (leftAr[0]/leftAr[1])+(leftAr[2]/leftAr[3]/10);

            int[] upAr = calc.SimulateMoves(board.getBoard(), 1);
            averageArray[1] = (upAr[0]/upAr[1])+(upAr[2]/upAr[3]/10);

            int[] rightAr = calc.SimulateMoves(board.getBoard(), 2);
            averageArray[2]= (rightAr[0]/rightAr[1])+(rightAr[2]/rightAr[3]/10);

            int[] downAr = calc.SimulateMoves(board.getBoard(), 3);
            averageArray[3] = (downAr[0]/downAr[1])+(downAr[2]/downAr[3]/10);

        } catch (Exception exe){ System.out.println(exe);}
            int biggest =0;

            if (!contains(lastlargestnr,0)){
                biggest = averageArray[0];
            }
            if (averageArray[1] > biggest) {
                if (!contains(lastlargestnr,1)){
                    largestnr=1;
                    biggest = averageArray[1];
                }
            }
            if (averageArray[2] > biggest) {

                if (!contains(lastlargestnr,2)){
                    largestnr=2;
                    biggest = averageArray[2];
                }
            }

            if (averageArray[3] > biggest) {
                if (!contains(lastlargestnr,3)){
                    largestnr=3;
                }
            }


        for (int i=0; i<lastlargestnr.length; i++) {
            if (lastlargestnr[i]==-1) {
                lastlargestnr[i] = largestnr;
                break;
            }
        }
        if (anythingHappen)Arrays.fill(lastlargestnr,-1);

        switch (largestnr) {
            case 0 -> anythingHappen = gui.directionsInput(KeyEvent.VK_LEFT);
            case 1 -> anythingHappen = gui.directionsInput(KeyEvent.VK_UP);
            case 2 -> anythingHappen = gui.directionsInput(KeyEvent.VK_RIGHT);
            case 3 -> anythingHappen = gui.directionsInput(KeyEvent.VK_DOWN);
        }
    });

    public AverageAlgorithm(GUI gui) {this.gui = gui;}

        public void startTimer() {
            timer.start();
        }

        public void stopTimer() {
        timer.stop();
    }
}
