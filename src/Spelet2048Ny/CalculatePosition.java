package Spelet2048Ny;

import jdk.dynalink.Operation;

import java.lang.reflect.Method;

public class CalculatePosition {
    private double totalSumValue;
    private double emptyspacevalue;
    private double highestnumbervalue;
    //Modifiers för hur mycket de olika faktorerna ska vara värda i jämförelse med varandra

    int size;
    int[][] boardState;

    CalculatePosition(double totalSumValue, double emptyspacevalue, double highestnumbervalue) {
        this.totalSumValue=totalSumValue;
        this.emptyspacevalue=emptyspacevalue;
        this.highestnumbervalue=highestnumbervalue;

    }
    public int calculate(Board2048 board) throws Exception{
        size = board.getBoardSize();
        boardState = board.getBoard();
        int totalscore= calculateTotalSumValue()+calculateEmptySpaceValue()+calculateHighetsNumberValue();
        return totalscore;
    }

    private int calculateTotalSumValue() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                total+=boardState[i][j];
            }}
        return (int)(total*totalSumValue);
    }

    private int calculateEmptySpaceValue() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardState[i][j]==0)total++;
                //Kan ändras här lite också
            }}
        return (int) (total*emptyspacevalue);

    }

    private int calculateHighetsNumberValue() {
        int highets = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardState[i][j]>highets)highets=boardState[i][j];
            }}
        return (int)(highets*highestnumbervalue);

    }
}
