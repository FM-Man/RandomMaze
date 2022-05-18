import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<Arm> arms = new ArrayList<>();

    public void main() throws IOException {
        int height =50;
        int width = 50;

        MazeBuilder maze = new MazeBuilder(height,width);
        DFS dfs1 = new DFS(maze.get(0,0));
        DFS dfs2 = new DFS(maze.get(height-1, width-1));

        dfs1.start();
        dfs2.start();

        do {
            System.out.print(".");
        } while (!dfs1.x || !dfs2.x);
        System.out.println();

        writeFile(height,width);
    }

    public void writeFile(int height, int width) throws IOException{
        int[][] imageOutPixels = new int[(height -1)*5+1][(width -1)*5+1];

        for (int[] imageOutPixel : imageOutPixels) {
            Arrays.fill(imageOutPixel, 0);
        }

        for (Arm a: arms){
            System.out.println(a);
        }

        for (Arm arm:arms){
            Vertex v1 = arm.get(0);
            Vertex v2 = arm.get(1);
            int x1 = 5*Math.min(v1.x, v2.x);
            int x2 = 5*Math.max(v1.x, v2.x);
            int y1 = 5*Math.min(v1.y, v2.y);
            int y2 = 5*Math.max(v1.y, v2.y);
            for (int i=x1;i<=x2;i++){
                imageOutPixels[i][y1] = 1;
            }
            for (int i=y1; i<=y2; i++){
                imageOutPixels[x1][i]= 1;
            }
        }

        for (int[] a: imageOutPixels){
            for (int b: a) {
                if(b!=0)
                    System.out.print(b + " ");
                else System.out.print("  ");
            }
            System.out.println();
        }

        StringBuilder s= new StringBuilder();
        for (int[] a: imageOutPixels){
            for (int b: a) {
                if(b!=0)
                    s.append(b).append(" ");
                else s.append("  ");
            }
            s.append("\n");
        }
        System.out.println(s);
        BufferedOutputStream bfw = new BufferedOutputStream(new FileOutputStream("heh.txt"));
        bfw.write(s.toString().getBytes());
        bfw.close();
    }
}
