package solver;

import java.io.IOException;

public class SolverDriver {
    public static void main() throws IOException {
        GraphProducer gp = new GraphProducer("onePxPath.png");
        Graph g = gp.makeGraph();
        SolverBFS bfs = new SolverBFS();
        bfs.solve(g);
    }
}
