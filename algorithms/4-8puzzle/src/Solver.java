import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

import java.util.Comparator;

public class Solver {

    private final int moves;
    private final Queue<Board> solution = new Queue<Board>();

    private static final Comparator<Board> BY_MANHATTAN = new ByManhattan();

    private static class ByManhattan implements Comparator<Board> {
        public int compare(Board a, Board b) {
            return a.manhattan() - b.manhattan();
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<Board> allMoves = new MinPQ<Board>(BY_MANHATTAN);
        allMoves.insert(initial);

        Board min = allMoves.delMin();
        Board predecessor = null;
        int numberOfMoves = 0;

        while(!min.isGoal()) {

            for(Board neighbor: min.neighbors()) {
                if(!neighbor.equals(predecessor)){
                    allMoves.insert(neighbor);
                }
            }

            predecessor = min;
            solution.enqueue(min);
            min = allMoves.delMin();
            numberOfMoves++;
        }
        solution.enqueue(min);

        this.moves = numberOfMoves;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return moves >= 0;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        // ToDo: defensive copy
        return solution;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}