package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private class Site {
        private int row;
        private int column;
        private boolean opened;
        public Site(int a, int b) {
            row = a;
            column = b;
            opened = false;
        }
    }

    private final Site[] grid;
    private int openSites;
    private final WeightedQuickUnionUF unionGrid;
    private final int width;
    private final WeightedQuickUnionUF noBackWash;

    public Percolation(int N) {
        width = N;
        grid = new Site[N * N];
        openSites = 0;
        unionGrid = new WeightedQuickUnionUF(N * N + 2);
        noBackWash = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid [i * N + j] = new Site(i, j);
            }
        }
        for (int i = 0; i < N; i++) {
            unionGrid.union(grid.length, i);
            unionGrid.union(grid.length + 1, grid.length - 1 - i);
            noBackWash.union(grid.length, i);
        }

    }                // create N-by-N grid, with all sites initially blocked

    private int[] Neighbour(int position) {
        int[] neighbour = new int[4];
        if (Math.floorDiv((position - 1),width) != Math.floorDiv(position, width) || position == 0) {
            neighbour[0] = -1;
        } else {neighbour[0] = position - 1;}
        if (Math.floorDiv((position + 1),width) != Math.floorDiv(position, width) || position == grid.length - 1){
            neighbour[1] = -1;
        } else {
            neighbour[1] = position + 1;
        }
        if (Math.floorDiv(position, width) == 0) {
            neighbour[2] = -1;
        } else {
            neighbour[2] = position - width;
        }
        if (Math.floorDiv(position, width) == width - 1) {
            neighbour[3] = -1;
        } else {
            neighbour[3] = position + width;
        }
        return neighbour;
    }

    private int xyTo1D(int row, int col) {
        return row * width + col;
    }

    private void Union(int position){
        int[] neighbours = Neighbour(position);
        for (int neighbour : neighbours) {
            if (neighbour != -1 ) {
                if (grid[neighbour].opened) {
                    unionGrid.union(position, neighbour);
                    noBackWash.union(position, neighbour);
                }
            }
        }
    }

    public void open(int row, int col){
        if(!isOpen(row, col)) {
            int position = xyTo1D(row, col);
            grid[position].opened = true;
            Union(position);
            openSites += 1;
        }
    }       // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col){
        return grid[xyTo1D(row, col)].opened;
    }  // is the site (row, col) open?

    public boolean isFull(int row, int col){
        int position = xyTo1D(row, col);
        return (noBackWash.connected(position, grid.length) && isOpen(row, col));
    }  // is the site (row, col) full?

    public int numberOfOpenSites(){
        return openSites;
    }           // number of open sites

    public boolean percolates(){
        return unionGrid.connected(grid.length + 1, grid.length);
    }              // does the system percolate?

    public static void main(String[] args){

    }   // use for unit testing (not required)
}
