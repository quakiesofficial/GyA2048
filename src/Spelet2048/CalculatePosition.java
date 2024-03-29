package Spelet2048;

public class CalculatePosition {
    private final double totalSumMod;
    private final double emptyMod;
    private final double highestNumberMod;
    //Modifiers för hur mycket de olika faktorerna ska vara värda i jämförelse med varandra

    private final int size;
    private final int recursionLimit;
    private int recursionNumber;

    CalculatePosition(int size, int recursionLimit,int recursionNumber, double totalSumValue, double emptyspacevalue, double highestnumbervalue) {
        this.size = size;
        this.recursionLimit=recursionLimit;
        this.recursionNumber=recursionNumber;
        this.totalSumMod = totalSumValue;
        this.emptyMod = emptyspacevalue;
        this.highestNumberMod = highestnumbervalue;

    }
//Returnerar en int array
    // Platsernas betydelse: 0- Total score av alla(2or). 1- Total positioner som beräknats(2or).
    // 2-Totalt score av alla positioner med 4-or. 3- antal positioner med 4or som beräknats
    //4- Lägsta situationen som beräknats 5-Högsta situationen som hittats
    public int[] SimulateMoves(int[][] board2048, int direction) {
        CalculatePosition calc = new CalculatePosition(size,recursionLimit,recursionNumber++, totalSumMod, emptyMod, highestNumberMod);
        int[] finalReturn = new int[6];

        //Int direction för vilket håll som ska simuleras
        //0=vänster, 1=upp, 2=höger, 3=ner
        int[][] board1 = copyBoard(board2048);
        int totalTries2 = 0;
        int totalTries4 = 0;
        int scoreadd2 = 0;
        int scoreadd4 = 0;
        int lowestNumber=1000000000;
        int highestNumber=0;
        switch (direction) {
            case 0 -> {
                for (int i = 0; i < size; i++) {
                    for (int j = 1; j < size; j++) {
                        if (board1[i][j] != 0) {
                            int k = j;
                            while (k > 0 && board1[i][k - 1] == 0) {
                                k--;
                            }
                            if (k != j) {
                                board1[i][k] = board1[i][j];
                                board1[i][j] = 0;
                            }
                            if (k > 0 && board1[i][k - 1] == board1[i][k]) {
                                board1[i][k - 1] *= 2;
                                board1[i][k] = 0;
                            }}}}}

            case 1 -> {
                for (int j = 0; j < size; j++) {
                    for (int i = 1; i < size; i++) {
                        if (board1[i][j] != 0) {
                            int k = i;
                            while (k > 0 && board1[k - 1][j] == 0) {
                                k--;
                            }
                            if (k != i) {
                                board1[k][j] = board1[i][j];
                                board1[i][j] = 0;
                            }
                            if (k > 0 && board1[k - 1][j] == board1[k][j]) {
                                board1[k - 1][j] *= 2;
                                board1[k][j] = 0;
                            }}}}}

            case 2 -> {
                for (int i = 0; i < size; i++) {
                    for (int j = size - 2; j >= 0; j--) {
                        if (board1[i][j] != 0) {
                            int k = j;
                            while (k < size - 1 && board1[i][k + 1] == 0) {
                                k++;
                            }
                            if (k != j) {
                                board1[i][k] = board1[i][j];
                                board1[i][j] = 0;
                            }
                            if (k < size - 1 && board1[i][k + 1] == board1[i][k]) {
                                board1[i][k + 1] *= 2;
                                board1[i][k] = 0;
                            }}}}}

            case 3 -> {

                for (int i = size - 2; i >= 0; i--) {
                    for (int j = 0; j < size; j++) {
                        if (board1[i][j] != 0) {
                            int k = i;
                            while (k < size - 1 && board1[k + 1][j] == 0) {
                                k++;
                            }
                            if (k != i) {
                                board1[k][j] = board1[i][j];
                                board1[i][j] = 0;
                            }
                            if (k < size - 1 && board1[k + 1][j] == board1[k][j]) {
                                board1[k + 1][j] *= 2;
                                board1[k][j] = 0;
                            }}}}}}


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int[][] tempBoard = copyBoard(board1);
                if (board1[i][j] == 0) {
                    try {
                        totalTries2++;
                        if (recursionLimit>recursionNumber){
                            tempBoard[i][j] = 2;
                            for (int k = 0; k < 4; k++) {
                                int[] currentCalc = calc.SimulateMoves(copyBoard(tempBoard), k);
                                totalTries2 += currentCalc[1];
                                totalTries2--;
                                scoreadd2 += currentCalc[0];
                                if (currentCalc[4] < lowestNumber) lowestNumber = currentCalc[4];
                                if (currentCalc[5] > highestNumber) highestNumber = currentCalc[5];
                            }
                        }
                        else {
                            tempBoard[i][j] = 2;
                            int currentCalc = calculate(tempBoard);
                            scoreadd2 += currentCalc;
                            if (currentCalc>highestNumber)highestNumber=currentCalc;
                            if (currentCalc<lowestNumber)lowestNumber=currentCalc;
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }

                //samma sak fast med 4:or
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        int[][] tempBoard2 = copyBoard(board1);
                        if (board1[i][j]==0){
                            try {
                                totalTries4++;
                                if (recursionLimit>recursionNumber){
                                    tempBoard2[i][j] = 4;
                                    for (int k = 0; k < 4; k++) {
                                        int[] currentCalc = calc.SimulateMoves(copyBoard(tempBoard2), k);
                                        totalTries4 += currentCalc[3];
                                        totalTries2--;
                                        scoreadd2 += currentCalc[2];
                                        if (currentCalc[4] < lowestNumber) lowestNumber = currentCalc[4];
                                        if (currentCalc[5] > highestNumber) highestNumber = currentCalc[5];
                                    }
                                }
                                else {
                                    tempBoard2[i][j] = 4;
                                    int currentCalc = calculate(tempBoard2);
                                    scoreadd2 += currentCalc;
                                    if (currentCalc>highestNumber)highestNumber=currentCalc;
                                    if (currentCalc<lowestNumber)lowestNumber=currentCalc;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } } } }


        finalReturn[0]=scoreadd2;
        finalReturn[1]= totalTries2 > 0 ? totalTries2 : 1;
        finalReturn[2]=scoreadd4;
        finalReturn[3]=totalTries4 > 0 ? totalTries4 : 1;
        finalReturn[4]=lowestNumber;
        finalReturn[5]=highestNumber;
                return finalReturn;

    }


    private int calculate(int[][] board) {
        int totalscore = 0;
        int totalSumValue=0;
        int totalEmpty=0;
        int highest=0;
        boolean isLost=true;



        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                totalSumValue += board[i][j];

                if (board[i][j] == 0) {
                    totalEmpty++;
                    isLost=false;
                }

                if (board[i][j] > highest) highest = board[i][j];
                try {
                    if ((board[i][j] == board[i][j + 1]) || (board[j][i] == board[j + 1][i])) {
                        isLost = false;
                    }
                }catch (ArrayIndexOutOfBoundsException ignored){}

            }
        }

        totalscore+= totalSumValue * totalSumMod;
        totalscore+=totalEmpty* emptyMod;
        totalscore+=highest* highestNumberMod;
        if (totalEmpty==0)totalEmpty=1;
        if (isLost&&highest!=2048)totalscore=0;
        if (highest==2048)totalscore*=10;
        return totalscore;
    }

    private int[][] copyBoard(int[][] board) {
        int[][] board1=new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board[i], 0, board1[i], 0, size);
        }
        return board1;
    }}
