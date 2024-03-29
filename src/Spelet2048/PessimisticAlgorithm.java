package Spelet2048;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class PessimisticAlgorithm extends AlgorithmAbstarct {

    private GUI gui;
    private boolean anythingHappen;
    private int[] lastDirections ={-1,-1,-1,-1};

    Timer timer = new Timer(100, e -> {
        Board2048 board= gui.board;
        //Recursionlimit sätter hur djupt algoritmen tänker, recursionNumber ska ALLTID vara 0 här
        //men behövs så att den inte går oändligt djupt
        int depth = 3;
        CalculatePosition calc = new CalculatePosition(board.getBoardSize(), depth,0,1.2,5,10);
        int[] LBA = new int[4];

        int[] leftAr = calc.SimulateMoves(board.getBoard(), 0);
        LBA[0] = leftAr[4];

        int[] upAr = calc.SimulateMoves(board.getBoard(), 1);
        LBA[1] = upAr[4];

        int[] rightAr = calc.SimulateMoves(board.getBoard(), 2);
        LBA[2]= rightAr[4];

        int[] downAr = calc.SimulateMoves(board.getBoard(), 3);
        LBA[3] = downAr[4];

        int direction=-1;
        int[] extraLBA =copyArray(LBA);
        Arrays.sort(LBA);
        //LBA är värdera med Value
        //Kontroll för avsaknad av repetititon
        int loopNr=3;
        while (contains(lastDirections,direction)){
            if (loopNr==-1){
                direction=1;
                break;
            }
            direction=findInArray(extraLBA,LBA[loopNr]);
            loopNr--;
        }
        for (int i = 0; i< 4; i++) {
            if (lastDirections[i]==-1) {
                lastDirections[i] = direction;
                break;
            }
        }
        System.out.println(Arrays.toString(lastDirections));
        switch (direction) {
            case 0 -> anythingHappen = gui.directionsInput(KeyEvent.VK_LEFT);
            case 1 -> anythingHappen = gui.directionsInput(KeyEvent.VK_UP);
            case 2 -> anythingHappen = gui.directionsInput(KeyEvent.VK_RIGHT);
            case 3 -> anythingHappen = gui.directionsInput(KeyEvent.VK_DOWN);
        }
        if (anythingHappen)Arrays.fill(lastDirections,-1);
    });

    public PessimisticAlgorithm(GUI gui) {this.gui = gui;}

        public void startTimer() {
            timer.start();
        }

        public void stopTimer() {
        timer.stop();
    }


}
