import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private int size;
    private WeightedQuickUnionUF sites;
    private boolean[] opened;

    private void checkArgs(int row, int col) {
        if( row <= 0 || col <= 0 || row > size || col > size ) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    private void connect(int row1, int col1, int row2, int col2) {
        sites.union((row1 - 1) * size + col1, (row2 - 1) * size + col2);
    }

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        size = n;
        opened = new boolean[size*size];
        for(int i = 0; i < opened.length; i++) {
            opened[i] = false;
        }

        sites = new WeightedQuickUnionUF(size*size + 2);
        for(int i = 1; i <= size; i++) {
            sites.union(0, i);
            sites.union(size * (size - 1) + i, size*size + 1);
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkArgs(row, col);
        opened[(row-1) * size + col - 1] = true;
        if(row > 1 && isOpen(row - 1, col)) {
            connect(row, col, row - 1, col);
        }
        if(row < size && isOpen(row + 1, col)) {
            connect(row, col, row + 1, col);
        }
        if(col > 1 && isOpen(row, col - 1)) {
            connect(row, col, row, col - 1);
        }
        if(col < size && isOpen(row, col + 1)) {
            connect(row, col, row, col + 1);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkArgs(row, col);
        return opened[(row-1)*size + col - 1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return !isOpen(row, col);
    }

    // number of open sites
    public int numberOfOpenSites() {
        int num = 0;
        for(int i=0; i < opened.length; i++) {
            if(opened[i]) {
                num++;
            }
        }
        return num;
    }

    // does the system percolate?
    public boolean percolates() {
        return sites.connected(0, size*size + 1);
    }
}