import edu.princeton.cs.algs4.Queue;

public class Board {
    private final int [][] blocks;
    private final int size;

    private final int hamming;
    private final int manhattan;

    private Board exchange(int fromI, int fromJ, int toI, int toJ) {
        int [][] aBlocks = new int[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                aBlocks[i][j] = this.blocks[i][j];
            }
        }

        aBlocks[fromI][fromJ] = this.blocks[toI][toJ];
        aBlocks[toI][toJ] = this.blocks[fromI][fromJ];

        return new Board(aBlocks);
    }

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.size = blocks.length;
        this.blocks = new int[size][size];

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }


        int wrongNum = 0;
        int distance = 0;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                int N = blocks[i][j];
                if(N != 0) {
                    int ni = (N - 1) / size;
                    int nj = (N - 1) % size;
                    distance += Math.abs(i - ni) + Math.abs(j - nj);
                    if(ni != i || nj != j) {
                        wrongNum++;
                    }
                }
            }
        }

        this.hamming = wrongNum;
        this.manhattan = distance;
    }

    // board dimension n
    public int dimension() {
        return size;
    }

    // number of blocks out of place
    public int hamming() {
        return this.hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return this.manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.hamming == 0 && this.manhattan == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(blocks[i][j] != 0) {
                    if(j < size-1 && blocks[i][j+1] != 0) {
                        return exchange(i, j, i, j+1);
                    }
                    else if(i < size-1) {
                        return exchange(i, j, i+1, j );
                    }
                }
            }
        }
        return null;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if((y == null) || (this.getClass() != y.getClass())) {
            return false;
        }

        Board that = (Board)y;
        if(this.size != that.size) {
            return false;
        }

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(this.blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<Board>();

        int zi = 0, zj =0;

        while(blocks[zi][zj] != 0) {
            zj++;
            if(zj >= size) {
                zj = 0;
                zi++;
            }
        }

        if(zi > 0) {
            neighbors.enqueue(exchange(zi, zj, zi-1, zj));
        }
        if(zi < size -1) {
            neighbors.enqueue(exchange(zi, zj, zi+1, zj));
        }
        if(zj > 0) {
            neighbors.enqueue(exchange(zi, zj, zi, zj - 1));
        }
        if(zj < size - 1) {
            neighbors.enqueue(exchange(zi, zj, zi, zj + 1));
        }

        return neighbors;
    }

    // string representation of this board
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.size);
        for(int i = 0; i < size; i++) {
            result.append('\n');
            for(int j = 0; j < size; j++) {
                result.append(' ');
                result.append(blocks[i][j]);
            }
        }

        return result.toString();
    }
}