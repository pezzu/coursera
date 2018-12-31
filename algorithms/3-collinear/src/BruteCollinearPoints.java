import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] unsafePoints) {
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

        Queue<LineSegment> temp = new Queue<LineSegment>();

        for(int i = 0; i < points.length; i++) {
            for(int j = i+1; j < points.length; j++) {
                for(int k = j+1; k < points.length; k++) {
                    for(int l = k+1; l < points.length; l++) {
                        if(isCollinear(points[i], points[j], points[k], points[l])) {
                            temp.enqueue(new LineSegment(points[i], points[l]));
                        }
                    }
                }
            }
        }

        segments = new LineSegment[temp.size()];
        for(int i = 0; i < segments.length; i++) {
            segments[i] = temp.dequeue();
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

    private boolean isCollinear(Point p1, Point p2, Point p3, Point p4) {
        double slope = p1.slopeTo(p2);
        return p1.slopeTo(p3) == slope && p1.slopeTo(p4) == slope;
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}