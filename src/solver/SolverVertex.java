package solver;

import java.util.ArrayList;

public class SolverVertex implements Comparable<SolverVertex>{
    final int[] pixelPosition = new int[2];
    boolean isVisited;
    SolverVertex source;
    SolverVertex to;
    int totalRoadVisited;
    ArrayList<SolverVertex> children = new ArrayList<>();
    ArrayList<Integer> weights= new ArrayList<>();

    public SolverVertex(int x, int y){
        pixelPosition[0] =x;
        pixelPosition[1] =y;
        totalRoadVisited = Integer.MAX_VALUE;
    }

    public void connect(SolverVertex toVertex, int d){
        if(!children.contains(toVertex)) {
            children.add(toVertex);
            weights.add(d);
            toVertex.connect(this,d);
        }
    }

    public void reinit(){
        source=null;
        totalRoadVisited = Integer.MAX_VALUE;
        isVisited = false;
    }

    @Override
    public int compareTo(SolverVertex o) {
        return totalRoadVisited-o.totalRoadVisited;
    }

    public String toString(){
        return "[ "+pixelPosition[0] + "," + pixelPosition[1]+" ]";
    }
}
