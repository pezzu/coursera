import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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

        int numberOfSegments = 0;
        Point[][] tempSegments = new Point[points.length / 4][2];

        for(int i = 0; i < points.length - 3; i++) {
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
        return segments;
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
}