public class Network {

    private int[] connections;

    public Network(int size) {
        connections = new int[size];
        for(int i = 0; i < connections.length; i++) {
            connections[i] = i;
        }
    }

    public void connect(int a, int b) {
    }

    public boolean isConnected(int a, int b) {
        return false;
    }
}
