package Spelet2048Ny;

import java.awt.event.KeyEvent;

public class Board2048 {
    private final int[][] board;

    private final int boardSize;

    public Board2048(int size) {
        this.boardSize = size;
        this.board = new int[boardSize][boardSize];
        placeRandomTile();
        placeRandomTile();



        System.out.println(this);

    }

    public int getBoardSize() {
        return boardSize;
    }

    public int[][] getBoard() {
        return board;
    }


    public void move(int direction) {
        if (direction == KeyEvent.VK_LEFT){moveLeft();}
        if (direction == KeyEvent.VK_RIGHT){moveRight();}
        if (direction == KeyEvent.VK_UP){moveUp();}
        if (direction == KeyEvent.VK_DOWN){moveDown();}
    }

    public void moveDown() {
        for (int i = boardSize - 2; i >= 0; i--) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] != 0) {
                    int k = i;
                    while (k < boardSize - 1 && board[k + 1][j] == 0) {
                        k++;
                    }
                    if (k != i) {
                        board[k][j] = board[i][j];
                        board[i][j] = 0;
                    }
                    if (k < boardSize-1 && board[k + 1][j] == board[k][j]) {
                        board[k + 1][j] *= 2;
                        board[k][j] = 0;
                    }
                }
            }
        }
       placeRandomTile();
        System.out.println(this);
    }

    public void moveUp() {
        for (int j = 0; j < boardSize; j++) {
            for (int i = 1; i < boardSize; i++) {
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
                    }
                }
            }
        }
        placeRandomTile();

        System.out.println(this);
    }

    public void moveRight() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = boardSize - 2; j >= 0; j--) {
                if (board[i][j] != 0) {
                    int k = j;
                    while (k < boardSize - 1 && board[i][k + 1] == 0) {
                        k++;
                    }
                    if (k != j) {
                        board[i][k] = board[i][j];
                        board[i][j] = 0;
                    }  if (k < boardSize - 1 && board[i][k + 1] == board[i][k]) {
                        board[i][k + 1] *= 2;
                        board[i][k] = 0;
                    }
                }
            }
        }

        placeRandomTile();
        System.out.println(this);
    }

    public void moveLeft() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 1; j < boardSize; j++) {
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
                        }
                    }
                }
            }

         placeRandomTile();
        System.out.println(this);
    }

    private void placeRandomTile() {
        while (true) {
            if (isFull())
                break;
            int randomColumn = (int) (Math.random()*boardSize);
            int randomRow = (int) (Math.random()*boardSize);
            if(board[randomColumn][randomRow] != 0) continue;
            board[randomColumn][randomRow] = 1;
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
}