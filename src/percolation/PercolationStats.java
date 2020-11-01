package percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private static final double CONFIDENCE_COEFFICIENT = 1.96;
  private double[] values;
  private final int trials;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException();
    }

    values = new double[trials];
    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);
      while (!percolation.percolates()) {
        percolation.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
      }
      values[i] = 1.0 * percolation.numberOfOpenSites() / (n * n);
    }

    this.trials = trials;
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(values);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(values);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - CONFIDENCE_COEFFICIENT * stddev() / Math.sqrt(trials);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + CONFIDENCE_COEFFICIENT * stddev() / Math.sqrt(trials);
  }

  // test client (see below)
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats percolationStats = new PercolationStats(n, trials);

    StdOut.println("mean                    = " + percolationStats.mean());
    StdOut.println("stddev                  = " + percolationStats.stddev());
    StdOut.println(String
        .format("95%% confidence interval = [%f, %f]", percolationStats.confidenceLo(),
            percolationStats.confidenceHi()));
  }

}