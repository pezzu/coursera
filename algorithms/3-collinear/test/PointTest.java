import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PointTest {
    @Test
    public void compareToByY() {
        Point p1 = new Point(5, 6);
        Point p2 = new Point(6, 7);

        assertEquals(p1.compareTo(p2), -1);
        assertEquals(p2.compareTo(p1), 1);
    }

    @Test
    public void compareToByX() {
        Point p1 = new Point(5, 8);
        Point p2 = new Point(6, 8);

        assertEquals(p1.compareTo(p2), -1);
        assertEquals(p2.compareTo(p1), 1);
    }

    @Test
    public void compareToEquals() {
        Point p1 = new Point(6, 8);
        Point p2 = new Point(6, 8);

        assertEquals(p1.compareTo(p2), 0);
        assertEquals(p2.compareTo(p1), 0);
    }

    @Test
    public void slopeTo() {
        Point p11 = new Point(-1, 0);
        Point p12 = new Point(0, 2);

        assertEquals(p11.slopeTo(p12), 2);
        assertEquals(p12.slopeTo(p11), 2);

        Point p21 = new Point(0, 0);
        Point p22 = new Point(1, -3);

        assertEquals(p21.slopeTo(p22), -3);
        assertEquals(p22.slopeTo(p21), -3);
    }

        @Test
    public void slopeToHorizontal() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(6, 0);

        assertEquals(p1.slopeTo(p2), +0.0f);
        assertEquals(p2.slopeTo(p1), +0.0f);
    }

    @Test
    public void slopeToVertical() {
        Point p1 = new Point(6, 0);
        Point p2 = new Point(6, 8);

        assertEquals(p1.slopeTo(p2), Double.POSITIVE_INFINITY);
        assertEquals(p2.slopeTo(p1), Double.POSITIVE_INFINITY);
    }

    @Test
    public void slopeToDegenerate () {
        Point p1 = new Point(6, 8);
        Point p2 = new Point(6, 8);

        assertEquals(p1.slopeTo(p2), Double.NEGATIVE_INFINITY);
        assertEquals(p2.slopeTo(p1), Double.NEGATIVE_INFINITY);
    }

    @Test
    public void slopeOrderEquals() {
        Point p0 = new Point(0,0);

        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 6);

        assertEquals(p0.slopeOrder().compare(p1, p2), 0);
    }

    @Test
    public void slopeOrderGreaterLess() {
        Point p0 = new Point(0,0);

        Point p1 = new Point(3, 6);
        Point p2 = new Point(6, 3);

        assertEquals(p0.slopeOrder().compare(p1, p2), 1);
        assertEquals(p0.slopeOrder().compare(p2, p1), -1);
    }
}
