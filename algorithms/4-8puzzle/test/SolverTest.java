import edu.princeton.cs.algs4.Queue;
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
                {2, 1, 3},
                {4, 5, 6},
                {8, 7, 0}
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
    public void solutionNone() {
        int[][] blocks = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0}
        };

        Solver solver = new Solver(new Board(blocks));
        assertNull(solver.solution());
    }

    @Test
    public void immutable() {
        Board board = new Board(new int[][]{
                {2,  0},
                {1,  3}
        });
        Solver solver = new Solver(board);

        Queue<Board> solution = (Queue<Board>) solver.solution();
        solution.dequeue();

        assertEquals(((Queue<Board>) solver.solution()).size(), 4);
    }

    @Test
    public void puzzle00() {
        Board board = new Board(new int[][]{
                {1,  2,  3,  4,  5,  6,  7,  8,  9,  10},
                {11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
                {41, 42, 43, 44, 45, 46, 47, 48, 49, 50},
                {51, 52, 53, 54, 55, 56, 57, 58, 59, 60},
                {61, 62, 63, 64, 65, 66, 67, 68, 69, 70},
                {71, 72, 73, 74, 75, 76, 77, 78, 79, 80},
                {81, 82, 83, 84, 85, 86, 87, 88, 89, 90},
                {91, 92, 93, 94, 95, 96, 97, 98, 99, 0}
        });

        Solver solver = new Solver(board);

        assertTrue(solver.isSolvable());
        assertEquals(solver.moves(), 0);

        Iterator<Board> it = solver.solution().iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next(), board);
        assertFalse(it.hasNext());
    }

    @Test
    public void puzzle01() {
        Board board = new Board(new int[][]{
                {1,  0},
                {3,  2}
        });

        Solver solver = new Solver(board);

        assertTrue(solver.isSolvable());
        assertEquals(solver.moves(), 1);

        Iterator<Board> it = solver.solution().iterator();
        assertTrue(it.hasNext());
        assertEquals(it.next(), board);

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][]{
                {1,  2},
                {3,  0}
        }));

        assertFalse(it.hasNext());
    }

    @Test
    public void puzzle03() {
        Board board = new Board(new int[][]{
                {2,  0},
                {1,  3}
        });
        Solver solver = new Solver(board);

        assertTrue(solver.isSolvable());
        assertEquals(solver.moves(), 3);

        Iterator<Board> it = solver.solution().iterator();
        assertTrue(it.hasNext());
        assertEquals(it.next(), board);

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][]{
                {0,  2},
                {1,  3}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][]{
                {1,  2},
                {0,  3}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][]{
                {1,  2},
                {3,  0}
        }));

        assertFalse(it.hasNext());
    }

    @Test
    public void puzzle04() {
        int[][] blocks = new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Solver solver = new Solver(new Board(blocks));

        assertTrue(solver.isSolvable());
        assertEquals(solver.moves(), 4);

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
    public void puzzle06() {
        Board board = new Board(new int[][]{
                {0,  1,  2,  3},
                {5,  6,  7,  4},
                {9,  10, 11, 8},
                {13, 14, 15, 12},
        });
        Solver solver = new Solver(board);

        assertTrue(solver.isSolvable());
        assertEquals(solver.moves(), 6);

        Iterator<Board> it = solver.solution().iterator();
        assertTrue(it.hasNext());
        assertEquals(it.next(), board);

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][]{
                {1,  0,  2,  3},
                {5,  6,  7,  4},
                {9,  10, 11, 8},
                {13, 14, 15, 12}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][]{
                {1,  2,  0,  3},
                {5,  6,  7,  4},
                {9,  10, 11, 8},
                {13, 14, 15, 12}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][]{
                {1,  2,  3,  0},
                {5,  6,  7,  4},
                {9,  10, 11, 8},
                {13, 14, 15, 12}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][]{
                {1,  2,  3,  4},
                {5,  6,  7,  0},
                {9,  10, 11, 8},
                {13, 14, 15, 12}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][]{
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9,  10, 11, 0},
                {13, 14, 15, 12}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][]{
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9,  10, 11, 12},
                {13, 14, 15, 0}
        }));

        assertFalse(it.hasNext());
    }

    @Test
    public void puzzle07() {
        Board board = new Board(new int[][]{
                {1,  2,  3},
                {0,  7,  6},
                {5,  4,  8}
        });

        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(solver.moves(), 7);
    }

    @Test
    public void puzzle08() {
        Board board = new Board(new int[][]{
                {2,  3,  5},
                {1,  0,  4},
                {7,  8,  6}
        });

        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        assertEquals(solver.moves(), 8);
    }


    @Test
    public void unsolvable2x2() {
        Solver solver = new Solver(new Board(new int[][]{
                {1,  0},
                {2,  3}
        }));

        assertFalse(solver.isSolvable());
        assertEquals(solver.moves(), -1);
        assertNull(solver.solution());

        solver = new Solver(new Board(new int[][]{
                {0,  1},
                {2,  3}
        }));

        assertFalse(solver.isSolvable());
        assertEquals(solver.moves(), -1);
        assertNull(solver.solution());

        solver = new Solver(new Board(new int[][]{
                {3,  2},
                {1,  0}
        }));

        assertFalse(solver.isSolvable());
        assertEquals(solver.moves(), -1);
        assertNull(solver.solution());
    }

    @Test
    public void unsolvable3x3() {
        Solver solver = new Solver(new Board(new int[][]{
                {1,  2,  3},
                {4,  6,  5},
                {7,  8,  0}
        }));

        assertFalse(solver.isSolvable());
        assertEquals(solver.moves(), -1);
        assertNull(solver.solution());

        solver = new Solver(new Board(new int[][]{
                {8,  6,  7},
                {2,  5,  4},
                {1,  3,  0}
        }));

        assertFalse(solver.isSolvable());
        assertEquals(solver.moves(), -1);
        assertNull(solver.solution());

        solver = new Solver(new Board(new int[][]{
                {1,  2,  3},
                {4,  5,  6},
                {8,  7,  0}
        }));

        assertFalse(solver.isSolvable());
        assertEquals(solver.moves(), -1);
        assertNull(solver.solution());
    }
}
