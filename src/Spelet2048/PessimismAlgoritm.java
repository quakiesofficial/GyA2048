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
        int[] LBA = new int[4];

        int[] leftAr = calc.SimulateMoves(board.getBoard(), 0);
        LBA[0] = leftAr[4];

        int upAr[] = calc.SimulateMoves(board.getBoard(), 1);
        LBA[1] = upAr[4];

        int rightAr[] = calc.SimulateMoves(board.getBoard(), 2);
        LBA[2]= rightAr[4];

        int[] downAr = calc.SimulateMoves(board.getBoard(), 3);
        LBA[3] = downAr[4];

        int[] extraLBA =copyArray(LBA);
        int leastBad=0;
        Arrays.sort(LBA);
        //LBA är värdera med Value
        //Kontroll för avsaknad av repetititon
        for (int i = LBA.length-1; i >= 0; i--) {
            if (!contains(lastlargestnr,findInArray(extraLBA, LBA[i]))){
                leastBad=findInArray(extraLBA, LBA[i]);
                break;
            }

        }


        if (anythingHappen)Arrays.fill(lastlargestnr,-1);

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
    private int[] copyArray(int[] array){
        int[] tempArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            tempArray[i]=array[i];
        }
        return tempArray;
    }
    private int findInArray(int[] array, int number){
        for (int i = 0; i < array.length; i++) {
            if (array[i]==number){return i;}
        }
        return -1;
    }

}
