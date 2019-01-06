import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

import java.util.Comparator;

public class Solver {
    private final Queue<Board> solution;
    
    private static final Comparator<Board> BY_MANHATTAN = new ByManhattan();

    private static class ByManhattan implements Comparator<Board> {
        public int compare(Board a, Board b) {
            return a.manhattan() - b.manhattan();
        }
    }

    private static void addNeighbors(Board current,  Board predecessor, MinPQ<Board> moves) {
        for(Board neighbor: current.neighbors()) {
            if(!neighbor.equals(predecessor)){
                moves.insert(neighbor);
            }
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if(initial == null) {
            throw new IllegalArgumentException();
        }

        Queue<Board> rightMoves = new Queue<Board>();

        Board best = initial;
        Board predecessor = null;

        Board twinBest = initial.twin();
        Board twinPredecessor = null;

        while(!best.isGoal() && !twinBest.isGoal()) {
            MinPQ<Board> allMoves = new MinPQ<Board>(BY_MANHATTAN);
            MinPQ<Board> twinMoves = new MinPQ<Board>(BY_MANHATTAN);

            addNeighbors(best, predecessor, allMoves);
            addNeighbors(twinBest, twinPredecessor, twinMoves);

            rightMoves.enqueue(best);

            predecessor = best;
            best = allMoves.delMin();

            twinPredecessor = twinBest;
            twinBest = twinMoves.delMin();

        }
        rightMoves.enqueue(best);

        if(twinBest.isGoal()) {
            this.solution = null;
        }
        else {
            this.solution = rightMoves;
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solution != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return (solution == null)? -1 : solution.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if(solution == null) {
            return null;
        }

        Queue<Board> result = new Queue<Board>();

        for(Board move: solution) {
            result.enqueue(move);
        }

        return result;
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