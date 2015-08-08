package princeton.percolation;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
	private int T;
	private Percolation perc;
	private int currentRes = 0;
	private double[] results;

	public PercolationStats(int N, int T) {
		if (T <= 0) {
			throw new IllegalArgumentException();
		}
		this.T = T;
		perc = new Percolation(N);
		results = new double[T];
	}// perform T independent experiments on an N-by-N grid

	public double mean() {
		return StdStats.mean(results);
	}// sample mean of percolation threshold

	public double stddev() {
		return StdStats.stddev(results);
	}// sample standard deviation of percolation threshold

	public double confidenceLo() {
		return mean() - (1.96 * stddev()) / Math.sqrt(T);
	}// low endpoint of 95% confidence interval

	public double confidenceHi() {
		return mean() + (1.96 * stddev()) / Math.sqrt(T);
	}// high endpoint of 95% confidence interval

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats stats = new PercolationStats(N, T);
		for (int i = 0; i < T; i++) {
			stats.experiment(N);
		}

		System.out.println("mean                    = " + stats.mean());
		System.out.println("stddev                  = " + stats.stddev());
		System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
	}

	private void experiment(int n) {
		Percolation perc = new Percolation(n);
		int cnt = 0;
		while (!perc.percolates()) {
			int row = StdRandom.uniform(n) + 1;
			int col = StdRandom.uniform(n);
			if(!perc.isOpen(row, col)){
				perc.open(row, col);
				cnt++;
			}
		}
		results[currentRes] = (cnt * 1.0) / (n * n * 1.0);
		currentRes++;
	}

}
