package Spelet2048Ny;

import java.awt.event.KeyEvent;

public class Board2048 {
    private int[][] board;

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

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }


    public void move(int direction) {
        if (direction == KeyEvent.VK_LEFT) {
            moveLeft();
        }
        if (direction == KeyEvent.VK_RIGHT) {
            moveRight();
        }
        if (direction == KeyEvent.VK_UP) {
            moveUp();
        }
        if (direction == KeyEvent.VK_DOWN) {
            moveDown();
        }
    }

    public boolean moveDown() {
        boolean händenågot = false;
        for (int i = boardSize - 2; i >= 0; i--) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] != 0) {
                    int k = i;
                    while (k < boardSize - 1 && board[k + 1][j] == 0) {
                        k++;
                        händenågot = true;
                    }
                    if (k != i) {
                        board[k][j] = board[i][j];
                        board[i][j] = 0;
                        händenågot = true;
                    }
                    if (k < boardSize - 1 && board[k + 1][j] == board[k][j]) {
                        board[k + 1][j] *= 2;
                        board[k][j] = 0;
                        händenågot = true;
                    }
                }
            }
        }
        if (händenågot) placeRandomTile();
        System.out.println(this);
        return händenågot;
    }

    public boolean moveUp() {
        boolean händenågot = false;
        for (int j = 0; j < boardSize; j++) {
            for (int i = 1; i < boardSize; i++) {
                // Börjar från den rad som är näst längst upp (den som är längst upp ej kan röra sig uppåt)
                if (board[i][j] != 0) {
                    int k = i;
                    while (k > 0 && board[k - 1][j] == 0) {
                        k--;
                        händenågot = true;
                    }
                    if (k != i) {
                        board[k][j] = board[i][j];
                        board[i][j] = 0;
                        händenågot = true;
                    }
                    if (k > 0 && board[k - 1][j] == board[k][j]) {
                        board[k - 1][j] *= 2;
                        board[k][j] = 0;
                        händenågot = true;
                    }
                }
            }
        }
        if (händenågot) placeRandomTile();
        System.out.println(this);
        return händenågot;
    }

    public boolean moveRight() {
        boolean händenågot = false;
        for (int i = 0; i < boardSize; i++) {
            for (int j = boardSize - 2; j >= 0; j--) {
                if (board[i][j] != 0) {
                    int k = j;
                    while (k < boardSize - 1 && board[i][k + 1] == 0) {
                        k++;
                        händenågot = true;
                    }
                    if (k != j) {
                        board[i][k] = board[i][j];
                        board[i][j] = 0;
                        händenågot = true;
                    }
                    if (k < boardSize - 1 && board[i][k + 1] == board[i][k]) {
                        board[i][k + 1] *= 2;
                        board[i][k] = 0;
                        händenågot = true;
                    }
                }
            }
        }
        if (händenågot) placeRandomTile();
        System.out.println(this);
        return händenågot;
    }

    public boolean moveLeft() {
        boolean händenågot = false;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 1; j < boardSize; j++) {
                if (board[i][j] != 0) {
                    int k = j;
                    while (k > 0 && board[i][k - 1] == 0) {
                        k--;
                        händenågot = true;
                    }
                    if (k != j) {
                        board[i][k] = board[i][j];
                        board[i][j] = 0;
                        händenågot = true;
                    }
                    if (k > 0 && board[i][k - 1] == board[i][k]) {
                        board[i][k - 1] *= 2;
                        board[i][k] = 0;
                        händenågot = true;
                    }
                }
            }
        }
        if (händenågot) placeRandomTile();
        System.out.println(this);
        return händenågot;

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
       /* Gammal metod:
       if (isFull()) {
            int[][] boardCopy = new int[boardSize][boardSize];
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    boardCopy[i][j] = board[i][j];
                }
            }
            Board2048 board2048Copy = new Board2048(boardSize);
            board2048Copy.setBoard(boardCopy);
            board2048Copy.moveDown();
            board2048Copy.moveLeft();
            board2048Copy.moveUp();
            board2048Copy.moveRight();

            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (board[i][j] != board2048Copy.getBoard()[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }

        */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length - 1; j++) {
                if (board[i][j] == board[i][j+1]) {
                    return false;
                }
            }
        }

        for (int j = 0; j < board[0].length; j++) {
            for (int i = 0; i < board.length - 1; i++) {
                if (board[i][j] == board[i+1][j]) {
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
}
