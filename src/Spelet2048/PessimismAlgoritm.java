package Spelet2048;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class PessimismAlgoritm {



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

        int leastBad =0;
        if (anythingHappen)Arrays.fill(lastlargestnr,-1);
        try {






        } catch (Exception exe){ System.out.println(exe);}

        for (int i=0; i<lastlargestnr.length; i++) {
            if (lastlargestnr[i]==-1) {
                lastlargestnr[i] = leastBad;
                break;
            }
        }

        switch (leastBad) {
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

    public PessimismAlgoritm(GUI gui) {this.gui = gui;}

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
