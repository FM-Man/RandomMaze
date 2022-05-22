package solver;

import java.util.ArrayList;

public class Graph {
    ArrayList<SolverVertex> starters;
    ArrayList<SolverVertex> enders;
    int height,width;
    SolverVertex[][] vertices;
    public Graph(int h, int w){
        height = h;
        width=w;
        vertices = new SolverVertex[height][width];
        starters = new ArrayList<>();
        enders = new ArrayList<>();
    }
}
