import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BoardTest {

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

        assertFalse(board1.equals(null));
        assertTrue(board1.equals(board2));
        assertFalse(board1.equals(board3));
        assertFalse(board4.equals(board1));
    }

    @Test
    public void toStringTest() {
        int[][] blocks = new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Board board = new Board(blocks);
        assertEquals(board.toString(), "3\n0 1 3\n4 2 5\n7 8 6");
    }
}
