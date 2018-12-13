public class Network {

    private int[] connections;

    public Network(int size) {
        connections = new int[size];
        for(int i = 0; i < connections.length; i++) {
            connections[i] = 0;
        }
    }

    private int root(int index) {
        while(connections[index] != 0) {
            index = connections[index];
        }

        return index;
    }

    public void connect(int a, int b) {
        connections[root(a)] = root(b);
    }

    public boolean isConnected(int a, int b) {
        return root(a) == root(b);
    }
}
