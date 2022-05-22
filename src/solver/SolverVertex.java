package solver;

import java.util.ArrayList;

public class SolverVertex {
    final int[] pixelPosition = new int[2];
    boolean isVisited;
//    SolverVertex source;
//    SolverVertex to;
    int totalRoadVisited;
    ArrayList<SolverVertex> children = new ArrayList<>();
    ArrayList<Integer> weights= new ArrayList<>();

    public SolverVertex(int x, int y){
        pixelPosition[0] =x;
        pixelPosition[1] =y;
    }

    public void connect(SolverVertex toVertex, int d){
        if(!children.contains(toVertex)) {
            children.add(toVertex);
            weights.add(d);
            toVertex.connect(this,d);
        }
    }
}
