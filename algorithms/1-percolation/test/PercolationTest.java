import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class PercolationTest {
    @Test
    public void testPercolates() {
        Percolation p = new Percolation(2);
        p.open(1, 1);
        p.open(2, 2);
        assertFalse(p.percolates());

        p.open(1, 2);
        assertTrue(p.percolates());
    }

    @Test
    public void testIsOpen() {
        Percolation p = new Percolation(5);
        assertFalse(p.isOpen(1, 1));
        assertFalse(p.isOpen(1, 2));
        assertFalse(p.isOpen(2, 1));
        assertFalse(p.isOpen(2, 2));
        assertFalse(p.isOpen(3, 3));
        assertFalse(p.isOpen(4, 4));
        assertFalse(p.isOpen(4, 5));

        p.open(1,1);
        assertTrue(p.isOpen(1, 1));

        p.open(1, 2);
        assertTrue(p.isOpen(1, 2));

        p.open(4, 5);
        assertTrue(p.isOpen(4, 5));
    }

    @Test
    public void testIsFull() {
        Percolation p = new Percolation(5);
        assertTrue(p.isFull(1, 1));
        assertTrue(p.isFull(1, 2));
        assertTrue(p.isFull(2, 1));
        assertTrue(p.isFull(2, 2));
        assertTrue(p.isFull(3, 3));
        assertTrue(p.isFull(4, 4));
        assertTrue(p.isFull(4, 5));

        p.open(1,1);
        assertFalse(p.isFull(1, 1));

        p.open(2, 1);
        assertFalse(p.isFull(2, 1));

        p.open(5, 4);
        assertFalse(p.isFull(5, 4));
    }

    @Test
    public void testNumberOfOpenSites() {
        Percolation p = new Percolation(3);
        assertEquals(p.numberOfOpenSites(), 0);

        p.open(1, 2);
        assertEquals(p.numberOfOpenSites(), 1);

        p.open(2, 1);
        assertEquals(p.numberOfOpenSites(), 2);

        p.open(2, 1);
        assertEquals(p.numberOfOpenSites(), 2);
    }

    @Test(expectedExceptions = java.lang.IllegalArgumentException.class)
    public void testConstructionIllegalSize() {
        Percolation p = new Percolation(0);
    }

    @Test(expectedExceptions = java.lang.IllegalArgumentException.class)
    public void testIsOpenIllegalArgs() {
        Percolation p = new Percolation(2);
        p.isOpen(0, 0);
    }

    @Test(expectedExceptions = java.lang.IllegalArgumentException.class)
    public void testIsFullIllegalArgs() {
        Percolation p = new Percolation(2);
        p.isOpen(3, 2);
    }

    @Test(expectedExceptions = java.lang.IllegalArgumentException.class)
    public void testOpenIllegalArgs() {
        Percolation p = new Percolation(2);
        p.isOpen(2, 3);
    }
}


