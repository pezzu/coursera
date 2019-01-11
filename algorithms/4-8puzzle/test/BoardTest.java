import edu.princeton.cs.algs4.Queue;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

public class BoardTest {

    @Test
    public void constructionBlank() {
        int[][] blocks = new int[][] {};

        Board board = new Board(blocks);

        assertEquals(board.dimension(), 0);
    }

    @Test
    public void dimension() {
        int[][] blocks = new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Board board3 = new Board(blocks);
        assertEquals(board3.dimension(), 3);

        blocks = new int[][] {
                { 0,  1,  3,  9},
                { 4,  2,  5, 10},
                { 7,  8,  6, 11},
                {15, 16, 13, 12}
        };

        Board board4 = new Board(blocks);
        assertEquals(board4.dimension(), 4);
    }

    @Test
    public void hamming() {
        int[][] blocks = new int[][] {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };
        Board board = new Board(blocks);
        assertEquals(board.hamming(), 5);
    }

    @Test
    public void manhattan() {
        int[][] blocks = new int[][] {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };
        Board board = new Board(blocks);
        assertEquals(board.manhattan(), 10);
    }

    @Test
    public void isGoalOK() {
        int[][] blocks = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        Board board = new Board(blocks);
        assertTrue(board.isGoal());
    }

    @Test
    public void isGoalNot() {
        int[][] blocks = new int[][] {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };
        Board board = new Board(blocks);
        assertFalse(board.isGoal());
    }

    @Test
    public void twin() {
        Board board1 = new Board(new int[][] {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        });

        assertEquals(board1.twin(), new Board(new int[][] {
                {1, 8, 3},
                {4, 0, 2},
                {7, 6, 5}
        }));


        Board board2 = new Board(new int[][] {
                {0, 1, 3},
                {4, 8, 2},
                {7, 6, 5}
        });

        assertEquals(board2.twin(), new Board(new int[][] {
                {0, 3, 1},
                {4, 8, 2},
                {7, 6, 5}
        }));

        Board board3 = new Board(new int[][] {
                {8, 0, 3},
                {4, 1, 2},
                {7, 6, 5}
        });

        assertEquals(board3.twin(), new Board(new int[][] {
                {4, 0, 3},
                {8, 1, 2},
                {7, 6, 5}
        }));

        Board board4 = new Board(new int[][] {
                {0, 1},
                {2, 3}
        });

        assertEquals(board4.twin(), new Board(new int[][] {
                {0, 3},
                {2, 1}
        }));

        Board board5 = new Board(new int[][] {
                {1, 0},
                {2, 3}
        });

        assertEquals(board5.twin(), new Board(new int[][] {
                {2, 0},
                {1, 3}
        }));
    }

    @Test
    public void neighbors1() {
        int[][] blocks = new int[][] {
                {8, 1, 3},
                {4, 2, 0},
                {7, 6, 5}
        };
        Board board = new Board(blocks);

        Iterator<Board> it = board.neighbors().iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][] {
                {8, 1, 0},
                {4, 2, 3},
                {7, 6, 5}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][] {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][] {
                {8, 1, 3},
                {4, 2, 5},
                {7, 6, 0}
        }));

        assertFalse(it.hasNext());
    }

    @Test
    public void neighbors2() {
        int[][] blocks = new int[][] {
                {1, 2, 3},
                {0, 7, 6},
                {5, 4, 8}
        };
        Board board = new Board(blocks);

        Iterator<Board> it = board.neighbors().iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][] {
                {0, 2, 3},
                {1, 7, 6},
                {5, 4, 8}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][] {
                {1, 2, 3},
                {7, 0, 6},
                {5, 4, 8}
        }));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Board(new int[][] {
                {1, 2, 3},
                {5, 7, 6},
                {0, 4, 8}
        }));

        assertFalse(it.hasNext());
    }

    @Test
    public void equalsTest() {
        int[][] blocks = new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Board board1 = new Board(blocks);
        Board board2 = new Board(blocks);

        blocks[0][0] = 1;
        blocks[0][1] = 0;
        Board board3 = new Board(blocks);

        int [][] blocks4 = new int[][] {
                { 0,  1,  3,  9},
                { 4,  2,  5, 10},
                { 7,  8,  6, 11},
                {15, 16, 13, 12}
        };

        Board board4 = new Board(blocks4);

        assertNotEquals(board1, null);
        assertEquals(board1, board2);
        assertNotEquals(board1, board3);
        assertNotEquals(board4, board1);
    }

    @Test
    public void toStringTest() {
        int[][] blocks = new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Board board = new Board(blocks);
        assertEquals(board.toString(), "3\n 0 1 3\n 4 2 5\n 7 8 6");
    }

    @Test
    public void immutable() {
        int[][] blocks = new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Board board = new Board(blocks);
        blocks[0][0] = 1;
        blocks[0][1] = 0;

        assertEquals(board, new Board(new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        }));

        Queue<Board> neighbors = (Queue<Board>) board.neighbors();
        neighbors.dequeue();

        assertEquals(((Queue<Board>) board.neighbors()).size(), 2);
    }
}
