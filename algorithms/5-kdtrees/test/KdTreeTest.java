import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

public class KdTreeTest {
    @Test
    public void isEmpty() {
        KdTree kd = new KdTree();
        assertTrue(kd.isEmpty());

        kd.insert(new Point2D(0, 0));
        assertFalse(kd.isEmpty());
    }

    @Test
    public void size() {
        KdTree kd = new KdTree();
        assertEquals(kd.size(), 0);

        kd.insert(new Point2D(0, 0));
        kd.insert(new Point2D(1, 0));
        kd.insert(new Point2D(0, 1));
        assertEquals(kd.size(), 3);
    }

    @Test
    public void insert() {
        KdTree kd = new KdTree();
        kd.insert(new Point2D(0, 0));

        assertEquals(kd.size(), 1);
        assertFalse(kd.isEmpty());
    }

    @Test
    public void insertSame() {
        KdTree kd = new KdTree();
        kd.insert(new Point2D(0, 0));
        kd.insert(new Point2D(0, 0));

        assertEquals(kd.size(), 1);
    }

    @Test
    public void insertSameX() {
        KdTree kd = new KdTree();
        kd.insert(new Point2D(0, 0));
        kd.insert(new Point2D(0, 1));

        assertEquals(kd.size(), 2);
        assertTrue(kd.contains(new Point2D(0, 0)));
        assertTrue(kd.contains(new Point2D(0, 1)));
    }

    @Test
    public void insertSameY() {
        KdTree kd = new KdTree();
        kd.insert(new Point2D(0, 0));
        kd.insert(new Point2D(1, 0));

        assertEquals(kd.size(), 2);
        assertTrue(kd.contains(new Point2D(0, 0)));
        assertTrue(kd.contains(new Point2D(1, 0)));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void insertNull() {
        KdTree kd = new KdTree();
        kd.insert(null);
    }

    @Test
    public void containsEmpty() {
        KdTree kd = new KdTree();
        assertFalse(kd.contains(new Point2D(0, 0)));
    }

    @Test
    public void containsNonEmpty() {
        KdTree kd = new KdTree();
        kd.insert(new Point2D(0, 0));
        assertFalse(kd.contains(new Point2D(1, 1)));
        assertTrue(kd.contains(new Point2D(0, 0)));
    }

    @Test
    public void contains() {
        KdTree kd = new KdTree();
        kd.insert(new Point2D(0.7, 0.2));
        kd.insert(new Point2D(0.5, 0.4));
        kd.insert(new Point2D(0.2, 0.3));
        kd.insert(new Point2D(0.4, 0.7));
        kd.insert(new Point2D(0.9, 0.6));
        assertTrue(kd.contains(new Point2D(0.5, 0.4)));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void containsNull() {
        KdTree kd = new KdTree();
        kd.contains(null);
    }

    @Test
    public void rangeEmpty() {
        KdTree kd = new KdTree();

        Iterable<Point2D> it = kd.range(new RectHV(0, 0, 10, 10));
        assertFalse(it.iterator().hasNext());
    }

    @Test
    public void rangeZeroSize() {
        KdTree kd = new KdTree();
        kd.insert(new Point2D(1, 0));
        kd.insert(new Point2D(0, 1));

        Iterable<Point2D> it = kd.range(new RectHV(0, 0, 0, 0));
        assertFalse(it.iterator().hasNext());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void rangeNull() {
        KdTree kd = new KdTree();
        kd.insert(new Point2D(0, 0));

        kd.range(null);
    }

    @Test
    public void rangeContainsAll() {
        KdTree kd = new KdTree();

        Point2D point1 = new Point2D(0, 0);
        Point2D point2 = new Point2D(0, 1);
        Point2D point3 = new Point2D(1, 0);
        Point2D point4 = new Point2D(3, 0);
        Point2D point5 = new Point2D(0, 3);

        kd.insert(point1);
        kd.insert(point2);
        kd.insert(point3);
        kd.insert(point4);
        kd.insert(point5);

        Iterator<Point2D> it = kd.range(new RectHV(-1, -1, 10, 10)).iterator();

        assertEquals(it.next(), point1);
        assertEquals(it.next(), point2);
        assertEquals(it.next(), point3);
        assertEquals(it.next(), point5);
        assertEquals(it.next(), point4);
        assertFalse(it.hasNext());
    }

    @Test
    public void rangeContainsPart() {
        KdTree kd = new KdTree();

        Point2D point1 = new Point2D(0, 0);
        Point2D point2 = new Point2D(0, 1);
        Point2D point3 = new Point2D(1, 0);
        Point2D point4 = new Point2D(3, 0);
        Point2D point5 = new Point2D(0, 3);

        kd.insert(point1);
        kd.insert(point2);
        kd.insert(point3);
        kd.insert(point4);
        kd.insert(point5);

        Iterator<Point2D> it = kd.range(new RectHV(-1, -1, 2, 2)).iterator();

        assertEquals(it.next(), point1);
        assertEquals(it.next(), point2);
        assertEquals(it.next(), point3);
        assertFalse(it.hasNext());
    }

    @Test
    public void rangeNotContains() {
        KdTree kd = new KdTree();

        Point2D point1 = new Point2D(0, 0);
        Point2D point2 = new Point2D(0, 1);
        Point2D point3 = new Point2D(1, 0);
        Point2D point4 = new Point2D(3, 0);
        Point2D point5 = new Point2D(0, 3);

        kd.insert(point1);
        kd.insert(point2);
        kd.insert(point3);
        kd.insert(point4);
        kd.insert(point5);

        Iterator<Point2D> it = kd.range(new RectHV(-2, -2, -1, -1)).iterator();

        assertFalse(it.hasNext());
    }

    @Test
    public void rangeOnBorder() {
        KdTree kd = new KdTree();

        Point2D point1 = new Point2D(0, 0);
        Point2D point2 = new Point2D(0, 1);
        Point2D point3 = new Point2D(1, 0);
        Point2D point4 = new Point2D(3, 0);
        Point2D point5 = new Point2D(0, 3);

        kd.insert(point1);
        kd.insert(point2);
        kd.insert(point3);
        kd.insert(point4);
        kd.insert(point5);

        Iterator<Point2D> it = kd.range(new RectHV(0, 0, 1, 3)).iterator();

        assertEquals(it.next(), point1);
        assertEquals(it.next(), point2);
        assertEquals(it.next(), point3);
        assertEquals(it.next(), point5);
        assertFalse(it.hasNext());
    }

    @Test
    public void nearestEmpty() {
        KdTree kd = new KdTree();
        assertNull(kd.nearest(new Point2D(0, 0)));
    }

    @Test
    public void nearestOnePoint() {
        KdTree kd = new KdTree();
        Point2D point = new Point2D(0, 0);

        kd.insert(point);

        assertEquals(kd.nearest(new Point2D(1, 0)), point);
    }

    @Test
    public void nearestSamePoint() {
        KdTree kd = new KdTree();
        Point2D point = new Point2D(0, 0);

        kd.insert(point);

        assertEquals(kd.nearest(point), point);
    }

    @Test
    public void nearestSeveralPoints() {
        KdTree kd = new KdTree();
        Point2D point1 = new Point2D(1, 1);
        Point2D point2 = new Point2D(2, 2);
        Point2D point3 = new Point2D(3, 3);
        Point2D point4 = new Point2D(4, 4);

        kd.insert(point1);
        kd.insert(point2);
        kd.insert(point3);
        kd.insert(point4);

        assertEquals(kd.nearest(new Point2D(0, 0)), point1);
        assertEquals(kd.nearest(new Point2D(5, 5)), point4);
    }

    @Test
    public void nearestSameDistance() {
        KdTree kd = new KdTree();
        Point2D point00 = new Point2D(0, 0);
        Point2D point01 = new Point2D(0, 1);
        Point2D point10 = new Point2D(1, 0);

        kd.insert(point01);
        kd.insert(point10);

        assertTrue(kd.nearest(point00).equals(point01) || kd.nearest(point00).equals(point10));
    }

    @Test
    public void nearest() {
        KdTree kd = new KdTree();
        Point2D point1 = new Point2D(0,2);
        Point2D point2 = new Point2D(3,0);
        Point2D point3 = new Point2D(-3,4);
        Point2D point4 = new Point2D(-5,-6);
        Point2D point5 = new Point2D(-7,3);
        Point2D point6 = new Point2D(-1,9);
        Point2D point7 = new Point2D(2,2);
        Point2D point8 = new Point2D(10,10);
        Point2D point9 = new Point2D(8,7);
        Point2D point10 = new Point2D(4,-7);

        kd.insert(point1);
        kd.insert(point2);
        kd.insert(point3);
        kd.insert(point4);
        kd.insert(point5);
        kd.insert(point6);
        kd.insert(point7);
        kd.insert(point8);
        kd.insert(point9);
        kd.insert(point10);

        assertEquals(kd.nearest(new Point2D(-9, 7)), point5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void nearestNull() {
        KdTree kd = new KdTree();
        kd.insert(new Point2D(0, 0));

        kd.nearest(null);
    }
}