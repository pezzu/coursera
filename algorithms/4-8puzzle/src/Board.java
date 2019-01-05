public class Board {
    private final int [][] blocks;
    private final int size;

    private final int hamming;
    private final int manhattan;

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

        this.hamming = calcHamming();
        this.manhattan = calcManhattan();
    }

    private int calcHamming() {
        int num = 0;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(!isPositionCorrect(i, j)) {
                    num++;
                }
            }
        }

        return num;
    }

    private boolean isPositionCorrect(int i, int j) {
        int ni = (blocks[i][j] - 1) / size;
        int nj = (blocks[i][j] - 1) % size;

        return (blocks[i][j] == 0) || (ni == i && nj == j);
    }

    private int calcManhattan() {
        int sum = 0;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                sum += calcManhattanDistance(i, j);
            }
        }

        return sum;
    }

    private int calcManhattanDistance(int i, int j) {
        if(blocks[i][j] == 0) {
            return 0;
        }
        int ni = (blocks[i][j] - 1) / size;
        int nj = (blocks[i][j] - 1) % size;
        return Math.abs(i - ni) + Math.abs(j - nj);
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
        return null;
    }

    // string representation of this board
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.size);
        result.append('\n');
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                result.append(blocks[i][j]);
                if(j < size-1) {
                    result.append(' ');
                }
            }
            if(i < size-1) {
                result.append('\n');
            }
        }

        return result.toString();
    }
}