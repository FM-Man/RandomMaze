package solver;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class SolverBFS {
    PriorityQueue<SolverVertex> queue = new PriorityQueue<>();
    ArrayList<ArrayList<SolverVertex>> ways = new ArrayList<>();
    Graph g;
    //boolean done = false;
    public void solve(Graph graph){
        g=graph;
        for (SolverVertex startingPoint:g.starters){
            startingPoint.isVisited = true;
            for(int i=0; i<startingPoint.children.size();i++){
                if(!g.starters.contains(startingPoint.children.get(i)) && !startingPoint.children.get(i).isVisited){
                    //System.out.println(startingPoint.children.get(i));
                    int d =  startingPoint.totalRoadVisited + startingPoint.weights.get(i);
                    if(d< startingPoint.children.get(i).totalRoadVisited){
                        startingPoint.children.get(i).totalRoadVisited = d;
                        startingPoint.children.get(i).source = startingPoint;
                        queue.add(startingPoint.children.get(i));
                        //System.out.println(queue.peek());
                    }
                }
            }
            if(queue.size()>=1) {
                SolverVertex v = queue.poll();
                //System.out.println(v);
                solveRecursion(v);
            }
        }

        System.out.println("\n\n\n\n\n\n\n");
        for (ArrayList<SolverVertex> way:ways){
            for (SolverVertex v: way){
                System.out.print(v+" -> ");
            }
            System.out.println();
            System.out.println();
        }

    }

    private void solveRecursion(SolverVertex s){
        s.isVisited = true;
        if(g.enders.contains(s)){
            System.out.println("yes");
            writeWays(s);
            //done = true;
            return;
        }
        for (int i=0; i<s.children.size();i++){
            if(!g.starters.contains(s.children.get(i)) && !s.children.get(i).isVisited){
                int d = s.totalRoadVisited+s.weights.get(i);
                if(s.children.get(i).totalRoadVisited > d){
                    s.children.get(i).totalRoadVisited = d;
                    s.children.get(i).source = s;
                    queue.add(s.children.get(i));
                }
            }
        }
        if(queue.size()>=1) {
            SolverVertex v = queue.poll();
            solveRecursion(v);
        }
    }

    private void writeWays(SolverVertex v){
        SolverVertex node = v;
        ArrayList<SolverVertex> way = new ArrayList<>();
        while (node!=null){
            way.add(node);
            node = node.source;
            System.out.println(way);
        }
        //way.add(node);
        ways.add(way);
        for (int i=0; i<g.height; i++){
            for (int j=0; j<g.width; j++){
                if(g.vertices[i][j] != null){
                    g.vertices[i][j].reinit();
                }
            }
        }
    }

}
