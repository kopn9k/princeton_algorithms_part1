package Percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] fractions;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        this.trials = trials;
        this.fractions = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int openSites = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    openSites++;
                }
            }
            double fraction = ((double) openSites / (n * n));
            this.fractions[i] = fraction;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev() / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.println("mean =                    " + percolationStats.mean());
        StdOut.println("sttdev =                  " + percolationStats.stddev());
        StdOut.println("95% confidence interval = " + percolationStats.confidenceLo() +
                ", " + percolationStats.confidenceHi());
    }

}

