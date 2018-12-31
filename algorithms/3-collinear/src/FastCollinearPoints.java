import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] unsafePoints) {
        if(unsafePoints == null) {
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < unsafePoints.length; i++) {
            if(unsafePoints[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Point[] points = Arrays.copyOf(unsafePoints, unsafePoints.length);

        Arrays.sort(points);
        for(int i = 1; i < points.length; i++) {
            if(points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        int numberOfSegments = 0;
        Point[][] tempSegments = new Point[points.length / 4][2];

        for(int i = 0; i < points.length-1; i++) {
            if(!isIncluded(points[i], tempSegments, numberOfSegments)) {
                Point[] tempPoints = copyOf(points, i, points.length);
                Arrays.sort(tempPoints, points[i].slopeOrder());

                int numberOfPoints = 2;
                double slope = points[i].slopeTo(tempPoints[1]);

                for (int j = 2; j < tempPoints.length; j++) {
                    if (points[i].slopeTo(tempPoints[j]) == slope) {
                        numberOfPoints++;
                    } else {
                        slope = points[i].slopeTo(tempPoints[j]);
                        if (numberOfPoints >= 4) {
                            tempSegments[numberOfSegments][0] = points[i];
                            tempSegments[numberOfSegments][1] = tempPoints[j - 1];
                            numberOfSegments++;
                        }
                        numberOfPoints = 2;
                    }
                }
                if (numberOfPoints >= 4) {
                    tempSegments[numberOfSegments][0] = points[i];
                    tempSegments[numberOfSegments][1] = tempPoints[tempPoints.length - 1];
                    numberOfSegments++;
                }
            }
        }

        segments = new LineSegment[numberOfSegments];
        for(int i = 0; i < numberOfSegments; i++) {
            segments[i] = new LineSegment(tempSegments[i][0], tempSegments[i][1]);
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

    private static Point[] copyOf(Point[] source, int from, int to) {
        Point[] result = new Point[to - from];
        for(int i = from; i < to; i++) {
            result[i - from] = source[i];
        }
        return result;
    }

    private static boolean isIncluded(Point point, Point[][] segments, int numberOfSegments) {
        for(int i = 0; i < numberOfSegments; i++) {
            if(point.slopeTo(segments[i][0]) == point.slopeTo(segments[i][1])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}