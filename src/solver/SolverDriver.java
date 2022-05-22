package solver;

import java.io.IOException;

public class SolverDriver {
    public static void main(String[] args) throws IOException {
        GraphProducer gp = new GraphProducer("oTest2.png");
        Graph g = gp.makeGraph();
    }
}
