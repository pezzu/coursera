import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

public class SolverTest {
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void constructionNullArgument() {
        new Solver(null);
    }

    @Test
    public void isSolvable() {
        int[][] blocks = new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Solver solver = new Solver(new Board(blocks));

        assertTrue(solver.isSolvable());
    }

    @Test
    public void isSolvableNone() {
        int[][] blocks = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0}
        };

        Solver solver = new Solver(new Board(blocks));

        assertFalse(solver.isSolvable());
    }

    @Test
    public void moves() {
        int[][] blocks = new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Solver solver = new Solver(new Board(blocks));
        assertEquals(solver.moves(), 4);
    }

    @Test
    public void movesNone() {
        int[][] blocks = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0}
        };

        Solver solver = new Solver(new Board(blocks));

        assertEquals(solver.moves(), -1);
    }

    @Test
    public void solution() {
        int[][] blocks = new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Solver solver = new Solver(new Board(blocks));

        Iterator<Board> it = solver.solution().iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][] {
                {1, 0, 3},
                {4, 2, 5},
                {7, 8, 6}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][] {
                {1, 2, 3},
                {4, 0, 5},
                {7, 8, 6}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][] {
                {1, 2, 3},
                {4, 5, 0},
                {7, 8, 6}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        }));

        assertFalse(it.hasNext());
    }

    @Test
    public void solutionNone() {
        int[][] blocks = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0}
        };

        Solver solver = new Solver(new Board(blocks));
        assertEquals(solver.solution(), null);
    }
}
