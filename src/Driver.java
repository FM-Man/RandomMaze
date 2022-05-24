import solver.SolverDriver;

import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        Maze.getInstance().createMaze(125,125);
        //SolverDriver.main();
    }
//    public void solveGraph() throws IOException {
//        SolverDriver.main();
//    }
}
