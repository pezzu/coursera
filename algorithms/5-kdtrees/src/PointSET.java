import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Set;
import java.util.TreeSet;

public class PointSET {

    private final Set<Point2D> points = new TreeSet<Point2D>();

    // construct an empty set of points
    public PointSET() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if(p == null) {
            throw new IllegalArgumentException();
        }

        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if(p == null) {
            throw new IllegalArgumentException();
        }

        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for(Point2D point: points) {
            point.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if(rect == null) {
            throw new IllegalArgumentException();
        }

        Set<Point2D> result = new TreeSet<Point2D>();

        for(Point2D point: points) {
            if(isInside(point, rect)) {
                result.add(point);
            }
        }

        return result;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if(p == null) {
            throw new IllegalArgumentException();
        }

        double distance = Double.POSITIVE_INFINITY;
        Point2D min = null;

        for(Point2D point: points) {
            if(point.distanceTo(p) < distance) {
                min = point;
                distance = point.distanceTo(p);
            }
        }

        return min;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }

    private boolean isInside(Point2D p, RectHV r) {
        return p.x() >= r.xmin() && p.x() <= r.xmax() && p.y() >= r.ymin() && p.y() <= r.ymax();
    }
}
