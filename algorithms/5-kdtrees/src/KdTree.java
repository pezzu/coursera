import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
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

        int cmp = compare(parent.point, point, parent.orientation);
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

        int cmp = compare(parent.point, point, parent.orientation);

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

    private int compare(Point2D p1, Point2D p2, boolean orientation) {
        return orientation == Node.VERTICAL? Point2D.Y_ORDER.compare(p1, p2) : Point2D.X_ORDER.compare(p1, p2);
    }

    // draw all points to standard draw
    public void draw() {

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if(rect == null) {
            throw new IllegalArgumentException();
        }

        Queue<Point2D> result = new Queue<Point2D>();
        Queue<Node> layer = new Queue<Node>();

        if(root != null) {
            layer.enqueue(root);
        }

        while(!layer.isEmpty()) {
            Node node = layer.dequeue();
            if(rect.distanceTo(node.point) == 0.0) {
                result.enqueue(node.point);
            }

            if(isOnTheLeft(rect, node.point, node.orientation)) {
                if(node.left != null) {
                    layer.enqueue(node.left);
                }
            }

            if(isOnTheRight(rect, node.point, node.orientation)) {
                if(node.right != null) {
                    layer.enqueue(node.right);
                }
            }
        }

        return result;
    }

    private boolean isOnTheLeft(RectHV rect, Point2D point, boolean orientation) {
        return (orientation == Node.VERTICAL)? rect.xmin() <= point.x() : rect.ymin() <= point.y();
    }

    private boolean isOnTheRight(RectHV rect, Point2D point, boolean orientation) {
        return (orientation == Node.VERTICAL)? rect.xmax() > point.x() : rect.ymax() > point.y();
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if(p == null) {
            throw new IllegalArgumentException();
        }

        if(size() == 0) {
            return null;
        }

        Node neighbor = nearest(root, root, p);

        return neighbor.point;
    }

    private Node nearest(Node node, Node champion, Point2D goal) {
        if(node == null) {
            return champion;
        }

        if(node.point.equals(goal)) {
            return node;
        }

        Node newChampion = (node.point.distanceTo(goal) < champion.point.distanceTo(goal))? node : champion;

        int cmp = compare(goal, node.point, node.orientation);
        if(cmp <= 0) {
            newChampion = nearest(node.left, newChampion, goal);
//            if(canBeCloser)
            newChampion = nearest(node.right, newChampion, goal);
        }
        else {
            newChampion = nearest(node.right, newChampion, goal);
//            if(canBeCloser)
            newChampion = nearest(node.left, newChampion, goal);
        }

        return newChampion;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
