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
            int[] leftAr = calc.SimulateMoves(board.getBoard(), 0);
            int left = (leftAr[0]/leftAr[1])+(leftAr[2]/leftAr[3]/10);

            int upAr[] = calc.SimulateMoves(board.getBoard(), 1);
            int up = (upAr[0]/upAr[1])+(upAr[2]/upAr[3]/10);

            int rightAr[] = calc.SimulateMoves(board.getBoard(), 2);
            int right= (rightAr[0]/rightAr[1])+(rightAr[2]/rightAr[3]/10);

            int[] downAr = calc.SimulateMoves(board.getBoard(), 3);
            int down = (downAr[0]/downAr[1])+(downAr[2]/downAr[3]/10);

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
            if (right > biggest) {

                if (!contains(lastlargestnr,2)){
                    largestnr=2;
                    biggest = right;
                }
            }

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
