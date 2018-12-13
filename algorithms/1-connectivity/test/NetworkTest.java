import com.sun.source.tree.NewArrayTree;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


public class NetworkTest {

    @Test
    public void testNotConnectedByDefault() {
        Network net = new Network(10);
        assertFalse(net.isConnected(0, 1));
    }

    @Test
    public void testConnection() {
        Network net = new Network(10);
        net.connect(1, 2);
        assertTrue(net.isConnected(1, 2));
    }

    @Test
    public void testReflexive() {
        Network net = new Network(10);
        assertTrue(net.isConnected(1, 1));
    }

    @Test
    public void testSymmetric() {
        Network net = new Network(10);
        net.connect(1,2);
        assertTrue(net.isConnected(1, 2));
        assertTrue(net.isConnected(2, 1));
    }

    @Test
    public void testTransitive() {
        Network net = new Network(10);
        net.connect(1,2);
        net.connect(2,3);
        assertTrue(net.isConnected(1, 3));
    }

    @Test
    public void testSeveralGroups() {
        Network net = new Network(10);
        net.connect(0, 5);
        net.connect(2, 7);
        net.connect(1, 6);
        net.connect(5, 6);
        net.connect(1, 2);

        net.connect(3, 8);
        net.connect(4, 9);
        net.connect(3, 4);

        assertTrue(net.isConnected(0, 7));
        assertTrue(net.isConnected(8, 9));
        assertFalse(net.isConnected(0, 9 ));
    }
}
