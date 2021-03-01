package hw4.puzzle;

import java.util.HashSet;
import java.util.Set;

public class Board implements WorldState {
        private final int N;
        private int[][] board;
        private int zeroPositionX;
        private int zeroPositionY;

        public Board(int[][] tiles) {
            N = tiles.length;
            board = new int[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    board[i][j] = tiles[i][j];
                    if (tiles[i][j] == 0) {
                        zeroPositionX = i;
                        zeroPositionY = j;
                    }
                }
            }
        }


        private int xyTo1A(int x, int y){
            return x * N + y;
        }

        public int tileAt(int i, int j) {
            if (i < 0 || i > N - 1 || j < 0 || j > N - 1){
                throw new IndexOutOfBoundsException();
            }
            return board[i][j];
        }

        public int size() {
            return N;
        }

        private Board exchange(int a, int b) {
            int[][] tmpBoard = board.clone();
            for(int i = 0; i < N; i++) {
                tmpBoard[i] = board[i].clone();
            }
            int tmp = tmpBoard[zeroPositionX][zeroPositionY];
            tmpBoard[zeroPositionX][zeroPositionY] = tileAt(zeroPositionX + a, zeroPositionY + b);
            tmpBoard[zeroPositionX + a][zeroPositionY + b] = tmp;
            Board newBoard = new Board(tmpBoard);
            newBoard.zeroPositionX = zeroPositionX + a;
            newBoard.zeroPositionY = zeroPositionY + b;
            return newBoard;
        }

        public Iterable<WorldState> neighbors() {
            Set<WorldState> neighbour = new HashSet<>();
            if (zeroPositionX > 0) {
                neighbour.add(exchange(-1, 0));
            }
            if (zeroPositionX < N - 1) {
                neighbour.add(exchange(1, 0));
            }
            if (zeroPositionY > 0) {
                neighbour.add(exchange(0, -1));
            }
            if (zeroPositionY < N - 1) {
                neighbour.add(exchange(0, 1));
            }
            return neighbour;
        }

        public int hamming() {
            int difference = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++) {
                    if (board[i][j] == 0) {
                        continue;
                    }
                    if (board[i][j] != i * N + j + 1) {
                        difference += 1;
                    }
                }
            }
            return difference;
        }

        public int manhattan() {
            int difference = 0;
            int differenceX;
            int differenceY;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++) {
                    if (board[i][j] == 0) {
                        continue;
                    }
                    differenceY = Math.abs(j - (board[i][j] - 1) % N);
                    differenceX = Math.abs(i - Math.floorDiv(board[i][j] - 1, N));
                    difference = difference + differenceX + differenceY;
                }
            }
            return difference;
        }
        public int estimatedDistanceToGoal() {
            return manhattan();
        }

        public boolean equals(Object y) {
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] != ((Board)y).board[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
        public String toString() {
            StringBuilder s = new StringBuilder();
            int N = size();
            s.append(N + "\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    s.append(String.format("%2d ", tileAt(i,j)));
                }
                s.append("\n");
            }
            s.append("\n");
            return s.toString();
        }
    }


