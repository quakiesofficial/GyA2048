package Spelet2048Ny;

public class CalculatePosition {
    private final double totalSumValue;
    private final double emptyspacevalue;
    private final double highestnumbervalue;
    //Modifiers för hur mycket de olika faktorerna ska vara värda i jämförelse med varandra

    private int size;
    private int recursionLimit;
    private int recursionNumber;

    CalculatePosition(int size, int recursionLimit,int recursionNumber, double totalSumValue, double emptyspacevalue, double highestnumbervalue) {
        this.size = size;
        this.recursionLimit=recursionLimit;
        this.recursionNumber=recursionNumber;
        this.totalSumValue = totalSumValue;
        this.emptyspacevalue = emptyspacevalue;
        this.highestnumbervalue = highestnumbervalue;

    }

    public int SimulateMoves(Board2048 board2048, int direction) {
        CalculatePosition calc = new CalculatePosition(size,recursionLimit,recursionNumber++,totalSumValue,emptyspacevalue,highestnumbervalue);

        //Int direction för vilket håll som ska simuleras
        //0=vänster, 1=upp, 2=höger, 3=ner
        int[][] board1 = copyBoard(board2048.getBoard());
        int totalTries = 0;
        int scoreadd = 0;
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
                            }
                        }
                    }
                }
            }
            case 1 -> {
                for (int j = 0; j < size; j++) {
                    for (int i = 1; i < size; i++) {
                        // Börjar från den rad som är näst längst upp (den som är längst upp ej kan röra sig uppåt)
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
                            }
                        }
                    }
                }
            }
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
                            }
                        }
                    }
                }

            }
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
                            }
                        }
                    }
                }
            }
        }


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int[][] tempBoard = board1;
                if (board1[i][j] == 0) {
                    try {
                        tempBoard[i][j] = 2;
                        totalTries++;
                        scoreadd += calculate(tempBoard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
               /*
                //samma sak fast med 4:or
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        int[][] tempBoard2 = board1;
                        if (board1[i][j]==0){
                            try {
                                tempBoard2[i][j]=4;
                                totalTries++;
                                scoreadd+=(calculate(tempBoard2))/10;
                            } catch (Exception e) {
                                e.printStackTrace();
                            } } } }

                */


        if (totalTries == 0) totalTries = 1;
        return scoreadd / totalTries;

    }


    private int calculate(int[][] board) throws Exception {
        int totalscore = calculateTotalSumValue(board) + calculateEmptySpaceValue(board) + calculateHighetsNumberValue(board);
        return totalscore;
    }

    private int calculateTotalSumValue(int[][] board) {
        int total = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                total += board[i][j];
            }
        }
        return (int) (total * totalSumValue);
    }

    private int calculateEmptySpaceValue(int[][] board) {
        int total = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) total++;
                //Kan ändras här lite också
            }
        }
        return (int) (total * emptyspacevalue);

    }

    private int calculateHighetsNumberValue(int[][] board) {
        int highest = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] > highest) highest = board[i][j];
            }
        }
        return (int) (highest * highestnumbervalue);

    }

    private int[][] copyBoard(int[][] board) {
        int[][] board1=new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board1[i][j] = board[i][j];
            }}
        return board1;
    }}
