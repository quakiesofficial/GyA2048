package Spelet2048;

import java.awt.event.KeyEvent;

public class Board2048 {

    private int score;

    private int[][] board;

    private final int boardSize;
    private boolean wasMoved;



    public Board2048(int size) {
        this.boardSize = size;
        this.board = new int[boardSize][boardSize];
        placeRandomTile();
        placeRandomTile();


        System.out.println(this);

    }

    public int getScore() {
        return score;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }


    public boolean move(int direction) {
        boolean wasMoved = false;
        if (direction == KeyEvent.VK_LEFT) {
            wasMoved =moveLeft();
        }
        if (direction == KeyEvent.VK_RIGHT) {
            wasMoved =moveRight();
        }
        if (direction == KeyEvent.VK_UP) {
            wasMoved =moveUp();
        }
        if (direction == KeyEvent.VK_DOWN) {
            wasMoved =moveDown();
        }
        return wasMoved;
    }

    public boolean moveDown() {
        wasMoved = false;
        for (int i = boardSize - 2; i >= 0; i--) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] != 0) {
                    int k = i;
                    while (k < boardSize - 1 && board[k + 1][j] == 0) {
                        k++;
                        wasMoved = true;
                    }
                    if (k != i) {
                        board[k][j] = board[i][j];
                        board[i][j] = 0;
                        wasMoved = true;
                    }
                    if (k < boardSize - 1 && board[k + 1][j] == board[k][j]) {
                        board[k + 1][j] *= 2;
                        score += board[k+1][j];
                        board[k][j] = 0;
                        wasMoved = true;
                    }
                }
            }
        }
        if (wasMoved) placeRandomTile();
        System.out.println(this);
        return wasMoved;
    }

    public boolean moveUp() {
        boolean wasMoved = false;
        for (int j = 0; j < boardSize; j++) {
            for (int i = 1; i < boardSize; i++) {
                // Börjar från den rad som är näst längst upp (den som är längst upp ej kan röra sig uppåt)
                if (board[i][j] != 0) {
                    int k = i;
                    while (k > 0 && board[k - 1][j] == 0) {
                        k--;
                        wasMoved = true;
                    }
                    if (k != i) {
                        board[k][j] = board[i][j];
                        board[i][j] = 0;
                        wasMoved = true;
                    }
                    if (k > 0 && board[k - 1][j] == board[k][j]) {
                        board[k - 1][j] *= 2;
                        score += board[k-1][j];
                        board[k][j] = 0;
                        wasMoved = true;
                    }
                }
            }
        }
        if (wasMoved) placeRandomTile();
        System.out.println(this);
        return wasMoved;
    }

    public boolean moveRight() {
        wasMoved = false;
        for (int i = 0; i < boardSize; i++) {
            for (int j = boardSize - 2; j >= 0; j--) {
                if (board[i][j] != 0) {
                    int k = j;
                    while (k < boardSize - 1 && board[i][k + 1] == 0) {
                        k++;
                        this.wasMoved = true;
                    }
                    if (k != j) {
                        board[i][k] = board[i][j];
                        board[i][j] = 0;
                        this.wasMoved = true;
                    }
                    if (k < boardSize - 1 && board[i][k + 1] == board[i][k]) {
                        board[i][k + 1] *= 2;
                        score += board[i][k+1];
                        board[i][k] = 0;
                        this.wasMoved = true;
                    }
                }
            }
        }
        if (this.wasMoved) placeRandomTile();
        System.out.println(this);
        return this.wasMoved;
    }

    public boolean moveLeft() {
        wasMoved = false;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 1; j < boardSize; j++) {
                if (board[i][j] != 0) {
                    int k = j;
                    while (k > 0 && board[i][k - 1] == 0) {
                        k--;
                        this.wasMoved = true;
                    }
                    if (k != j) {
                        board[i][k] = board[i][j];
                        board[i][j] = 0;
                        this.wasMoved = true;
                    }
                    if (k > 0 && board[i][k - 1] == board[i][k]) {
                        board[i][k - 1] *= 2;
                        score += board[i][k-1];
                        board[i][k] = 0;
                        this.wasMoved = true;
                    }
                }
            }
        }
        if (this.wasMoved) placeRandomTile();
        System.out.println(this);
        return this.wasMoved;

}
    private void placeRandomTile() {
        while (true) {
            if (isFull())
                break;
            
            int twoOrFour = (int) (Math.random()*10);
            int randomColumn = (int) (Math.random()*boardSize);
            int randomRow = (int) (Math.random()*boardSize);

            if(board[randomColumn][randomRow] != 0) continue;
            if (twoOrFour != 9)
                board[randomColumn][randomRow] = 2;
            else
                board[randomColumn][randomRow] = 4;
            break;
        }
    }

    public boolean isFull() {
        for (int i=0; i<boardSize; i++) {
            for (int j=0; j<boardSize; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean isGameLost() {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length - 1; j++) {
                    if ((board[i][j]==0)||(board[i][j] == board[i][j + 1])||(board[j][i] == board[j + 1][i])) {
                        return false;
                    }
                }
            }
            return true;
    }
    public String toString() {
        String result = "";
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                result += board[i][j];
                result += "\t";
            }
            result += "\n";
        }
        return result;
    }

    public int getHighestTile() {
        int largest = 0;
        for (int i = 0; i<boardSize; i++) {
            for (int j = 0; j<boardSize; j++) {
                if (largest<board[i][j])
                    largest = board[i][j];
            }
        }
        return largest;
    }
}
