package hw2;

import java.util.Random;
import edu.princeton.cs.introcs.*;

public class PercolationStats {
    private int[] openedStats;
    private int time;
        public PercolationStats(int N, int T, PercolationFactory pf) {
            openedStats = new int[T];
            time = T;
            for (int i = 0; i < T; i++) {
                Percolation temp = pf.make(N);
                while (!temp.percolates()) {
                    int row = StdRandom.uniform(0, N);
                    int col = StdRandom.uniform(0, N);
                    temp.open(row, col);
                }
                openedStats[i] = temp.numberOfOpenSites();
            }
        }// perform T independent experiments on an N-by-N grid

        public double mean(){
                return StdStats.mean(openedStats);
            }// sample mean of percolation threshold

        public double stddev(){
               return StdStats.stddev(openedStats);
            }// sample standard deviation of percolation threshold
        public double confidenceLow() {
            return mean() - (1.96 * stddev()) / Math.sqrt(time);
        }// low endpoint of 95% confidence interval

        public double confidenceHigh() {
            return mean() + (1.96 * stddev()) / Math.sqrt(time);
        }                                // high endpoint of 95% confidence interval
    }

