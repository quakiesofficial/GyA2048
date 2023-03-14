package Spelet2048;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class AverageAlgoritm {



    private GUI gui;
    private boolean anythingHappen;
    private int depth =3;
    private int lastlargestnr[]={-1,-1,-1,-1};

    public int getDepth() {return depth;}

    Timer timer = new Timer(100, e -> {
        Board2048 board= gui.board;
        //Recursionlimit sätter hur djupt algoritmen tänker, recursionNumber ska ALLTID vara 0 här
        //men behövs så att den inte går oändligt djupt
        CalculatePosition calc = new CalculatePosition(board.getBoardSize(),depth,0,2,5,4);

                //Liten workaround kring swich att kräver statiska tal

        int largestnr=0;
        if (anythingHappen)Arrays.fill(lastlargestnr,-1);
        try {
            int left = calc.SimulateMoves(board.getBoard(), 0)[0];
            int up = calc.SimulateMoves(board.getBoard(), 1)[0];
            int biggest =0;

            if (!contains(lastlargestnr,0)){
                largestnr=0;
                biggest = left;
            }
            if (up > biggest) {
                if (!contains(lastlargestnr,1)){
                    largestnr=1;
                    biggest = up;
                }
            }

            int right = calc.SimulateMoves(board.getBoard(), 2)[0];
            if (right > biggest) {

                if (!contains(lastlargestnr,2)){
                    largestnr=2;
                    biggest = right;
                }
            }
            int down = calc.SimulateMoves(board.getBoard(), 3)[0];
            if (down > biggest) {
                if (!contains(lastlargestnr,3)){
                    largestnr=3;
                }
            }
        } catch (Exception exe){ System.out.println(exe);}

        for (int i=0; i<lastlargestnr.length; i++) {
            if (lastlargestnr[i]==-1) {
                lastlargestnr[i] = largestnr;
                break;
            }
        }

        switch (largestnr) {
            case 0:
                anythingHappen=gui.directionsInput(KeyEvent.VK_LEFT);
                break;
            case 1:
                anythingHappen=gui.directionsInput(KeyEvent.VK_UP);
                break;
            case 2:
                anythingHappen=gui.directionsInput(KeyEvent.VK_RIGHT);
                break;
            case 3:
                anythingHappen=gui.directionsInput(KeyEvent.VK_DOWN);
                break;
        }
    });

    public AverageAlgoritm(GUI gui) {this.gui = gui;}

        public void startTimer() {
            timer.start();
        }

        public void stopTimer() {
        timer.stop();
    }

    private boolean contains(int[] arr, int element){
        boolean contains=false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==element)contains=true;
        }
        return contains;
    }


}