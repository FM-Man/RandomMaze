package solver;

import java.io.IOException;

public class SolverDriver {
    public static void main(String[] args) throws IOException {
        GraphProducer gp = new GraphProducer("_20PX.png");
        Graph g = gp.makeGraph();
        SolverBFS bfs = new SolverBFS();
        bfs.solve(g);
    }
}
