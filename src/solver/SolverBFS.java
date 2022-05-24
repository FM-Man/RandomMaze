package solver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class SolverBFS {
    PriorityQueue<SolverVertex> queue = new PriorityQueue<>();
    ArrayList<ArrayList<SolverVertex>> ways = new ArrayList<>();
    ArrayList<Integer> totalRoad = new ArrayList<>();
    Graph g;
    boolean done = false;


    public void solve(Graph graph) throws IOException {
        g=graph;

        for (SolverVertex sv:g.starters){
            sv.isVisited = true;
            queue = new PriorityQueue<>();
            //System.out.println("for "+sv);
            for(int i=0; i<sv.children.size();i++){
                if(!g.starters.contains(sv.children.get(i)) && !sv.children.get(i).isVisited){
                    int d =  sv.totalRoadVisited + sv.weights.get(i);
                    if(d< sv.children.get(i).totalRoadVisited){
                        sv.children.get(i).totalRoadVisited = d;
                        //System.out.println(sv+"-"+sv.totalRoadVisited+"<-"+sv.weights.get(i)+"->"+sv.children.get(i)+"-"+sv.children.get(i).totalRoadVisited);
                        sv.children.get(i).source = sv;
                        queue.add(sv.children.get(i));
                    }
                }
            }
            if(!done && queue.size()>=1) {
                SolverVertex v = queue.poll();
                //System.out.println(v);
                solveRecursion(v);
            }
            //System.out.println();
            done = false;
            for (int i=0; i<g.height; i++){
                for (int j=0; j<g.width; j++){
                    if(g.vertices[i][j] != null){
                        g.vertices[i][j].reinit();
                    }
                }
            }
            for (SolverVertex v:g.starters){
                v.totalRoadVisited=0;
            }
        }

        int bestIndex=0;
        int lowestDistance = Integer.MAX_VALUE;
        for (int i=0; i< totalRoad.size();i++){
            if(totalRoad.get(i)<lowestDistance){
                bestIndex=i;
                lowestDistance = totalRoad.get(i);
            }
        }


        int img=0;
        //BufferedImage image = ImageIO.read(new File("_20PX.png"));
        System.out.println(ways.size());
        for (ArrayList<SolverVertex> way:ways){

            BufferedImage image = ImageIO.read(new File("onePxPath.png"));
            for (int i = 0; i < way.size() - 1; i++) {
                int a1 = Math.min(way.get(i).pixelPosition[0], way.get(i + 1).pixelPosition[0]);
                int a2 = Math.max(way.get(i).pixelPosition[0], way.get(i + 1).pixelPosition[0]);
                int b1 = Math.min(way.get(i).pixelPosition[1], way.get(i + 1).pixelPosition[1]);
                int b2 = Math.max(way.get(i).pixelPosition[1], way.get(i + 1).pixelPosition[1]);

                for (int j = a1; j <= a2; j++) {
                    Color colour = Color.RED;
                    image.setRGB(j + 10, b1 + 10, colour.getRGB());
                }
                for (int j = b1; j <= b2; j++) {
                    Color colour = Color.RED;
                    image.setRGB(a1 + 10, j + 10, colour.getRGB());
                }
            }


            if(ways.indexOf(way)!=bestIndex){
                img++;
                File outputFile = new File("solves/solve" + img + ".png");
                ImageIO.write(image, "png", outputFile);
            }
            else{
                File outputFile = new File("bestSolve.png");
                ImageIO.write(image, "png", outputFile);
            }
        }

    }

    private void solveRecursion(SolverVertex s){
        s.isVisited = true;
        //System.out.print("->"+s);
        if(g.enders.contains(s)){
            //System.out.println("yes");
            writeWays(s);
            done = true;
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
        if(!done && queue.size()>=1) {
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
            //System.out.println(way);
        }
        int distance = way.get(0).totalRoadVisited;
        totalRoad.add(distance);
        //System.out.println(distance);
        //way.add(node);
        if(g.starters.contains(way.get(way.size()-1))) {
            Collections.reverse(way);
            ways.add(way);
        }


    }

}
