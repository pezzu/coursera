import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

public class PointSETTest {
    @Test
    public void isEmpty() {
        PointSET ps = new PointSET();
        assertTrue(ps.isEmpty());

        ps.insert(new Point2D(0, 0));
        assertFalse(ps.isEmpty());
    }

    @Test
    public void size() {
        PointSET ps = new PointSET();
        assertEquals(ps.size(), 0);

        ps.insert(new Point2D(0, 0));
        ps.insert(new Point2D(1, 0));
        ps.insert(new Point2D(0, 1));
        assertEquals(ps.size(), 3);
    }

    @Test
    public void insert() {
        PointSET ps = new PointSET();
        ps.insert(new Point2D(0, 0));

        assertEquals(ps.size(), 1);
        assertFalse(ps.isEmpty());
    }

    @Test
    public void insertSame() {
        PointSET ps = new PointSET();
        ps.insert(new Point2D(0, 0));
        ps.insert(new Point2D(0, 0));

        assertEquals(ps.size(), 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void insertNull() {
        PointSET ps = new PointSET();
        ps.insert(null);
    }

    @Test
    public void rangeEmpty() {
        PointSET ps = new PointSET();

        Iterable<Point2D> it = ps.range(new RectHV(0, 0, 10, 10));
        assertFalse(it.iterator().hasNext());
    }

    @Test
    public void rangeZeroSize() {
        PointSET ps = new PointSET();
        ps.insert(new Point2D(1, 0));
        ps.insert(new Point2D(0, 1));

        Iterable<Point2D> it = ps.range(new RectHV(0, 0, 0, 0));
        assertFalse(it.iterator().hasNext());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void rangeNull() {
        PointSET ps = new PointSET();
        ps.insert(new Point2D(0, 0));

        ps.range(null);
    }

    @Test
    public void rangeContainsAll() {
        PointSET ps = new PointSET();

        Point2D point1 = new Point2D(0, 0);
        Point2D point2 = new Point2D(0, 1);
        Point2D point3 = new Point2D(1, 0);
        Point2D point4 = new Point2D(3, 0);
        Point2D point5 = new Point2D(0, 3);

        ps.insert(point1);
        ps.insert(point2);
        ps.insert(point3);
        ps.insert(point4);
        ps.insert(point5);

        Iterator<Point2D> it = ps.range(new RectHV(-1, -1, 10, 10)).iterator();

        assertEquals(it.next(), point1);
        assertEquals(it.next(), point2);
        assertEquals(it.next(), point3);
        assertEquals(it.next(), point4);
        assertEquals(it.next(), point5);
        assertFalse(it.hasNext());
    }

    @Test
    public void rangeContainsPart() {
        PointSET ps = new PointSET();

        Point2D point1 = new Point2D(0, 0);
        Point2D point2 = new Point2D(0, 1);
        Point2D point3 = new Point2D(1, 0);
        Point2D point4 = new Point2D(3, 0);
        Point2D point5 = new Point2D(0, 3);

        ps.insert(point1);
        ps.insert(point2);
        ps.insert(point3);
        ps.insert(point4);
        ps.insert(point5);

        Iterator<Point2D> it = ps.range(new RectHV(-1, -1, 2, 2)).iterator();

        assertEquals(it.next(), point1);
        assertEquals(it.next(), point2);
        assertEquals(it.next(), point3);
        assertFalse(it.hasNext());
    }

    @Test
    public void rangeNotContains() {
        PointSET ps = new PointSET();

        Point2D point1 = new Point2D(0, 0);
        Point2D point2 = new Point2D(0, 1);
        Point2D point3 = new Point2D(1, 0);
        Point2D point4 = new Point2D(3, 0);
        Point2D point5 = new Point2D(0, 3);

        ps.insert(point1);
        ps.insert(point2);
        ps.insert(point3);
        ps.insert(point4);
        ps.insert(point5);

        Iterator<Point2D> it = ps.range(new RectHV(-2, -2, -1, -1)).iterator();

        assertFalse(it.hasNext());
    }

    @Test
    public void rangeOnBorder() {
        PointSET ps = new PointSET();

        Point2D point1 = new Point2D(0, 0);
        Point2D point2 = new Point2D(0, 1);
        Point2D point3 = new Point2D(1, 0);
        Point2D point4 = new Point2D(3, 0);
        Point2D point5 = new Point2D(0, 3);

        ps.insert(point1);
        ps.insert(point2);
        ps.insert(point3);
        ps.insert(point4);
        ps.insert(point5);

        Iterator<Point2D> it = ps.range(new RectHV(0, 0, 1, 3)).iterator();

        assertEquals(it.next(), point1);
        assertEquals(it.next(), point2);
        assertEquals(it.next(), point3);
        assertEquals(it.next(), point5);
        assertFalse(it.hasNext());
    }

    @Test
    public void nearestEmpty() {
        PointSET ps = new PointSET();
        assertNull(ps.nearest(new Point2D(0, 0)));
    }

    @Test
    public void nearestOnePoint() {
        PointSET ps = new PointSET();
        Point2D point = new Point2D(0, 0);

        ps.insert(point);

        assertEquals(ps.nearest(new Point2D(1, 0)), point);
    }

    @Test
    public void nearestSamePoint() {
        PointSET ps = new PointSET();
        Point2D point = new Point2D(0, 0);

        ps.insert(point);

        assertEquals(ps.nearest(point), point);
    }

    @Test
    public void nearestSeveralPoints() {
        PointSET ps = new PointSET();
        Point2D point1 = new Point2D(1, 1);
        Point2D point2 = new Point2D(2, 2);
        Point2D point3 = new Point2D(3, 3);
        Point2D point4 = new Point2D(4, 4);

        ps.insert(point1);
        ps.insert(point2);
        ps.insert(point3);
        ps.insert(point4);

        assertEquals(ps.nearest(new Point2D(0, 0)), point1);
        assertEquals(ps.nearest(new Point2D(5, 5)), point4);
    }

    @Test
    public void nearestSameDistance() {
        PointSET ps = new PointSET();
        Point2D point00 = new Point2D(0, 0);
        Point2D point01 = new Point2D(0, 1);
        Point2D point10 = new Point2D(1, 0);

        ps.insert(point01);
        ps.insert(point10);

        assertTrue(ps.nearest(point00).equals(point01) || ps.nearest(point00).equals(point10));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void nearestNull() {
        PointSET ps = new PointSET();
        ps.insert(new Point2D(0, 0));

        ps.nearest(null);
    }
}
