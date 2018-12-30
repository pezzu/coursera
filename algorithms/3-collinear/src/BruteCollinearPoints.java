import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if(points == null) {
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < points.length; i++) {
            if(points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(points);
        for(int i = 1; i < points.length; i++) {
            if(points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        int n = 0;
        LineSegment[] temp = new LineSegment[combinations(points.length)];

        for(int i = 0; i < points.length; i++) {
            for(int j = i+1; j < points.length; j++) {
                for(int k = j+1; k < points.length; k++) {
                    for(int l = k+1; l < points.length; l++) {
                        if(isCollinear(points[i], points[j], points[k], points[l])) {
                            temp[n] = new LineSegment(points[i], points[l]);
                            n++;
                        }
                    }
                }
            }
        }

        segments = new LineSegment[n];
        for(int i = 0; i < n; i++) {
            segments[i] = temp[i];
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }

    private boolean isCollinear(Point p1, Point p2, Point p3, Point p4) {
        double slope = p1.slopeTo(p2);
        return p1.slopeTo(p3) == slope && p1.slopeTo(p4) == slope;
    }

    private int combinations(int n) {
        return factorial(n) / (factorial(4) * factorial(n - 4));
    }

    private int factorial(int n) {
        int result = 1;

        for(int c = 2; c <= n; c++) {
            result *= c;
        }

        return result;
    }
}