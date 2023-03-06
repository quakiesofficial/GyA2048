package Spelet2048Ny;

public class CalculatePosition {
    private double totalSumValue;
    private double emptyspacevalue;
    private double highestnumbervalue;
    //Modifiers för hur mycket de olika faktorerna ska vara värda i jämförelse med varandra

    int size;
    int[][] boardState;

    CalculatePosition(int size, double totalSumValue, double emptyspacevalue, double highestnumbervalue) {
        this.size=size;
        this.totalSumValue=totalSumValue;
        this.emptyspacevalue=emptyspacevalue;
        this.highestnumbervalue=highestnumbervalue;

    }

    public int SimulateMoves(Board2048 board2048, int direction){
        //Int direction för vilket håll som ska simuleras
        //0=vänster, 1=upp, 2=höger, 3=ner
        int[][] board=board2048.getBoard();
        int totalTries=0;
        int scoreadd =0;
        switch (direction){
            case 0:{
                for (int i = 0; i < size; i++) {
                    for (int j = 1; j < size; j++) {
                        if (board[i][j] != 0) {
                            int k = j;
                            while (k > 0 && board[i][k - 1] == 0) {
                                k--;
                            }
                            if (k != j) {
                                board[i][k] = board[i][j];
                                board[i][j] = 0;
                            } if (k > 0 && board[i][k - 1] == board[i][k]) {
                                board[i][k - 1] *= 2;
                                board[i][k] = 0;
                            }}}}
                break;
            }
            case 1: {
                for (int j = 0; j < size; j++) {
                    for (int i = 1; i < size; i++) {
                        // Börjar från den rad som är näst längst upp (den som är längst upp ej kan röra sig uppåt)
                        if (board[i][j] != 0) {
                            int k = i;
                            while (k > 0 && board[k - 1][j] == 0) {
                                k--;
                            }
                            if (k != i) {
                                board[k][j] = board[i][j];
                                board[i][j] = 0;
                            } if (k > 0 && board[k-1][j] == board[k][j]) {
                                board[k-1][j] *= 2;
                                board[k][j] = 0;
                            }}}}
                break;
            }
            case 2:{for (int i = 0; i < size; i++) {
                for (int j = size - 2; j >= 0; j--) {
                    if (board[i][j] != 0) {
                        int k = j;
                        while (k < size - 1 && board[i][k + 1] == 0) {
                            k++;
                        }
                        if (k != j) {
                            board[i][k] = board[i][j];
                            board[i][j] = 0;
                        }  if (k < size - 1 && board[i][k + 1] == board[i][k]) {
                            board[i][k + 1] *= 2;
                            board[i][k] = 0;
                        }}}}
                break;
            }
            case 3:{

                for (int i = size - 2; i >= 0; i--) {
                    for (int j = 0; j < size; j++) {
                        if (board[i][j] != 0) {
                            int k = i;
                            while (k < size - 1 && board[k + 1][j] == 0) {
                                k++;
                            }
                            if (k != i) {
                                board[k][j] = board[i][j];
                                board[i][j] = 0;
                            }
                            if (k < size-1 && board[k + 1][j] == board[k][j]) {
                                board[k + 1][j] *= 2;
                                board[k][j] = 0;
                            }}}}   break;
            }}



                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        int[][] tempBoard2 = board;
                        if (board[i][j]==0){
                            try {
                                tempBoard2[i][j]=2;
                                totalTries++;
                                scoreadd+=calculate(tempBoard2);
                            } catch (Exception e) {
                                e.printStackTrace();
                            } } } }
                //samma sak fast med 4:or
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        int[][] tempBoard2 = board;
                        if (board[i][j]==0){
                            try {
                                tempBoard2[i][j]=4;
                                totalTries++;
                                scoreadd+=(calculate(tempBoard2))/10;
                            } catch (Exception e) {
                                e.printStackTrace();
                            } } } }



        return scoreadd/totalTries;

    }












    public int calculate(int[][] board) throws Exception{
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
        int highest = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardState[i][j]>highest)highest=boardState[i][j];
            }}
        return (int)(highest*highestnumbervalue);

    }
}
