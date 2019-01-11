import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;


public class Solver {
    private final Stack<Board> solution;

    private static class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int moves;
        private final int priority;
        private final SearchNode predecessor;

        public SearchNode(Board board, int numberOfMoves, SearchNode predecessor) {
            this.board = board;
            this.moves = numberOfMoves;
            this.priority = board.manhattan() + numberOfMoves;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode that) {
            return this.priority - that.priority;
        }
    }

    private static void addNeighbors(SearchNode current, MinPQ<SearchNode> moves) {
        for(Board neighbor: current.board.neighbors()) {
            if(current.predecessor == null || !neighbor.equals(current.predecessor.board)){
                moves.insert(new SearchNode(neighbor, current.moves+1, current));
            }
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if(initial == null) {
            throw new IllegalArgumentException();
        }

        MinPQ<SearchNode> allMoves = new MinPQ<SearchNode>();

        SearchNode best = new SearchNode(initial, 0, null);
        SearchNode twin = new SearchNode(initial.twin(), 0, null);
        addNeighbors(twin, allMoves);

        while(!best.board.isGoal()) {
            addNeighbors(best, allMoves);
            best = allMoves.delMin();
        }

        Stack<Board> reconstructed = new Stack<Board>();
        SearchNode next = best;
        while(next != null) {
            reconstructed.push(next.board);
            next = next.predecessor;
        }

        this.solution = (reconstructed.peek().equals(initial))? reconstructed : null;
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