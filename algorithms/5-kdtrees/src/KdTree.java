import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {

    private class Node {
        private static final boolean VERTICAL = false;
        private static final boolean HORIZONTAL = true;

        private Point2D point;
        private Node left;
        private Node right;

        private boolean orientation;

        public Node(Point2D point, boolean orientation) {
            this.point = point;
            this.orientation = orientation;
        }
    }

    private Node root;
    private int count = 0;

    // construct an empty set of points
    public KdTree() {    }

    // is the set empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of points in the set
    public int size() {
        return count;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if(p == null) {
            throw new IllegalArgumentException();
        }

        root = insert(root, p, Node.VERTICAL);
    }

    private Node insert(Node parent, Point2D point, boolean orientation) {
        if(parent == null) {
            count++;
            return new Node(point, orientation);
        }

        double cmp = (parent.orientation == Node.VERTICAL)? parent.point.y() - point.y() : parent.point.x() - point.x();
        if(cmp < 0) {
            parent.left = insert(parent.left, point, !orientation);
        }
        else if(cmp > 0) {
            parent.right = insert(parent.right, point, !orientation);
        }
        else {
            if(!parent.point.equals(point)) {
                parent.left = insert(parent.left, point, !orientation);
            }
        }

        return parent;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if(p == null) {
            throw new IllegalArgumentException();
        }

        return contains(root, p);
    }

    private boolean contains(Node parent, Point2D point) {
        if(parent == null) {
            return false;
        }

        double cmp = (parent.orientation == Node.VERTICAL)? parent.point.y() - point.y() : parent.point.x() - point.x();
        if(cmp < 0) {
            return contains(parent.left, point);
        }
        else if(cmp > 0) {
            return contains(parent.right, point);
        }
        else {
            if(parent.point.equals(point)) {
                return true;
            }
            else {
                return contains(parent.left, point);
            }
        }
    }

    // draw all points to standard draw
    public void draw() {

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if(rect == null) {
            throw new IllegalArgumentException();
        }

        return null;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if(p == null) {
            throw new IllegalArgumentException();
        }

        return null;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
