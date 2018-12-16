import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;

    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if( n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        double[] fractions = new double[trials];
        for(int i = 0; i < trials; i++) {
            fractions[i] = fraction(n);
        }

        mean = StdStats.mean(fractions);
        stddev = StdStats.stddev(fractions);

        confidenceLo = mean - CONFIDENCE_95*stddev / Math.sqrt(trials);
        confidenceHi = mean + CONFIDENCE_95*stddev / Math.sqrt(trials);
    }

    private double fraction(int n) {
        Percolation p = new Percolation(n);
        while(!p.percolates()) {
            p.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
        }

        return 1.0 * p.numberOfOpenSites() / (n*n);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, T);

        StdOut.printf("mean                    = %f\n", stats.mean());
        StdOut.printf("stddev                  = %f\n", stats.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi());
    }
}