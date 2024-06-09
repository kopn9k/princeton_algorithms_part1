package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private QuickUnionFindPassCompression uf; //for assessment should be replaced with implementation from algs4 library
    private int[][] grid;
    private int nSize;
    private final int topElement = 0;
    private int bottomElement;
    private int amountOfOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.uf = new QuickUnionFindPassCompression(n * n + 2);
        this.grid = new int[n][n];
        this.nSize = n;
        this.bottomElement = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = 1;
            unionWithNeighbors(row, col);
            amountOfOpenSites++;
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.find(determineIndex(row, col)) == uf.find(topElement);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return amountOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(topElement) == uf.find(bottomElement);
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > nSize || col <= 0 || col > nSize)
            throw new IllegalArgumentException();
    }

    private int determineIndex(int row, int column) {
        return (row - 1) * nSize + (column);
    }

    private void unionWithNeighbors(int row, int col) {
        int index = determineIndex(row, col);

        if (row == 1) // top
            uf.union(topElement, index);
        else if (isOpen(row - 1, col))
            uf.union(index - nSize, index);

        if (col != 1 && isOpen(row, col - 1))  // left
            uf.union(index, index - 1);

        if (col != nSize && isOpen(row, col + 1)) // right
            uf.union(index, index + 1);

        if (row == nSize) // bottom
            uf.union(bottomElement, index);
        else if (isOpen(row + 1, col))
            uf.union(index + nSize, index);
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}

