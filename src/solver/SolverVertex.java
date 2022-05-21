package solver;

public class SolverVertex {
    final int[] pixelPosition = new int[2];
    boolean isVisited;
    SolverVertex source;
    SolverVertex to;
    int totalRoadVisited;
    SolverVertex[] childs = new SolverVertex[4];
    int[] weights = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
}
