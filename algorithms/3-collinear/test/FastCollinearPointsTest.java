import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FastCollinearPointsTest {

    private static <T> void assertArrayEquals(T[] expected, T[] actual) {
        assertEquals(expected.length, actual.length);

        List actualList = Arrays.asList(actual);
        for(int i = 0; i < expected.length; i++) {
            assertTrue(actualList.contains(expected[i]));
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void constructionNullArgument() {
        new FastCollinearPoints(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void constructionNullPoints() {
        Point[] points = {new Point(0,0), new Point(1,1), null, new Point(3,3)};
        new FastCollinearPoints(points);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void constructionEqualPoints() {
        Point[] points = {new Point(0,0), new Point(1,1), new Point(1,1), new Point(3,3)};
        new FastCollinearPoints(points);
    }

    @Test
    public void FastCollinearPointsCollinear6() {
        Point[] points = {
                new Point(19000, 10000),
                new Point(18000, 10000),
                new Point(32000, 10000),
                new Point(21000, 10000),
                new Point(1234, 5678),
                new Point(14000, 10000)
        };

        FastCollinearPoints collinear = new FastCollinearPoints(points);

        LineSegment[] expected = {
                new LineSegment(new Point(14000, 10000), new Point(32000, 10000))
        };

        assertArrayEquals(expected, collinear.segments());
        assertEquals(collinear.numberOfSegments(), 1);
    }

    @Test
    public void FastCollinearPointsCollinear8() {
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

        FastCollinearPoints collinear = new FastCollinearPoints(points);

        LineSegment[] expected = {
                new LineSegment(new Point(10000, 0), new Point(0, 10000)),
                new LineSegment(new Point(3000, 4000), new Point(20000, 21000))
        };

        assertArrayEquals(expected, collinear.segments());
        assertEquals(collinear.numberOfSegments(), 2);
    }

    @Test
    public void FastCollinearPointsCross() {
        Point[] points = {
                new Point(3, 2),
                new Point(4, 4),
                new Point(7, 4),
                new Point(5, 6),
                new Point(2, 9),
                new Point(3, 8),
                new Point(6, 8),
                new Point(7, 10)
        };

        FastCollinearPoints collinear = new FastCollinearPoints(points);

        LineSegment[] expected = {
                new LineSegment(new Point(3, 2), new Point(7, 10)),
                new LineSegment(new Point(2, 9), new Point(7, 4))
        };

        assertArrayEquals(expected, collinear.segments());
        assertEquals(collinear.numberOfSegments(), 2);
    }

    @Test
    public void FastCollinearPointsCollinearNone() {
        Point[] points = {
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 0),
                new Point(2, 3),
                new Point(3, 2)
        };

        FastCollinearPoints collinear = new FastCollinearPoints(points);

        assertEquals(collinear.segments().length, 0);
        assertEquals(collinear.numberOfSegments(), 0);
    }

    @Test
    public void FastCollinearPointsCollinearEquidistant() {
        Point[] points = {
                new Point(10000, 0),
                new Point(8000, 2000),
                new Point(2000, 8000),
                new Point(0,  10000),

                new Point(20000, 0),
                new Point(18000, 2000),
                new Point(2000, 18000),

                new Point(10000, 20000),
                new Point(30000, 0),
                new Point(0, 30000),
                new Point(20000, 10000),

                new Point(13000, 0),
                new Point(11000, 3000),
                new Point(5000, 12000),
                new Point(9000, 6000)
        };

        FastCollinearPoints collinear = new FastCollinearPoints(points);

        LineSegment[] expected = {
                new LineSegment(new Point(10000, 0), new Point(0, 10000)),
                new LineSegment(new Point(10000, 0), new Point(30000, 0)),
                new LineSegment(new Point(30000, 0), new Point(0, 30000)),
                new LineSegment(new Point(13000, 0), new Point(5000, 12000))
        };

        assertArrayEquals(expected, collinear.segments());
        assertEquals(collinear.numberOfSegments(), 4);
    }
}
