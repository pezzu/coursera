public class Network {

    private int[] connections;

    public Network(int size) {
        connections = new int[size];
        for(int i = 0; i < connections.length; i++) {
            connections[i] = i;
        }
    }

    public void connect(int a, int b) {
        int aid = connections[a];
        int bid = connections[b];
        
        for(int i = 0; i < connections.length; i++) {
            if(connections[i] == bid) {
                connections[i] = aid;
            }
        }
    }

    public boolean isConnected(int a, int b) {
        return connections[a] == connections[b];
    }
}
