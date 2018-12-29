import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class BruteCollinearPointsTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void constructionNullArgument() {
        BruteCollinearPoints collinear = new BruteCollinearPoints(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void constructionNullPoints() {
        Point[] points = {new Point(0,0), new Point(1,1), null, new Point(3,3)};
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void constructionEqualPoints() {
        Point[] points = {new Point(0,0), new Point(1,1), new Point(1,1), new Point(3,3)};
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    }

    @Test
    public void BruteCollinearPointsCollinear6() {
        Point[] points = {
                new Point(19000, 10000),
                new Point(18000, 10000),
                new Point(32000, 10000),
                new Point(21000, 10000),
                new Point(1234, 5678),
                new Point(14000, 10000)
        };

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        LineSegment[] expected = {
                new LineSegment(new Point(14000, 10000), new Point(32000, 10000))
        };

        assertArrayEquals(collinear.segments(), expected);
        assertEquals(collinear.numberOfSegments(), 1);
    }

    @Test
    public void BruteCollinearPointsCollinear8() {
        Point[] points = {
                new Point(10000, 0),
                new Point(0, 10000),
                new Point(3000, 7000),
                new Point(7000, 3000),
                new Point(20000, 21000),
                new Point(3000, 4000),
                new Point(14000, 15000),
                new Point(6000, 7000)
        };

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        LineSegment[] expected = {
                new LineSegment(new Point(10000, 0), new Point(0, 10000)),
                new LineSegment(new Point(3000, 4000), new Point(20000, 21000))
        };

        assertArrayEquals(collinear.segments(), expected);
        assertEquals(collinear.numberOfSegments(), 2);
    }

    @Test
    public void BruteCollinearPointsCollinearNone() {
        Point[] points = {
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 0),
                new Point(2, 3),
                new Point(3, 2)
        };

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        assertEquals(collinear.segments().length, 0);
        assertEquals(collinear.numberOfSegments(), 0);
    }
}
