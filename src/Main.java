import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<Arm> arms = new ArrayList<>();

    public void main() throws IOException {
        int height =70;
        int width = 50;

        MazeBuilder maze = new MazeBuilder(height,width);
        DFS dfs1 = new DFS(maze.get(0,0));
        DFS dfs2 = new DFS(maze.get(height-1, width-1));

        dfs1.start();
        dfs2.start();

        do {
            System.out.print(".");
        } while (!dfs1.x /*|| !dfs2.x*/);
        System.out.println();

        writeFile(height,width);
    }

    public void writeFile(int height, int width) throws IOException{
        int[][] imageOutPixels = new int[(height -1)*5+1][(width -1)*5+1];
        int[][] functionalMaze = new int[(height -1)*2+1][(width -1)*2+1];

        for (int[] imageOutPixel : imageOutPixels) {
            Arrays.fill(imageOutPixel, 0);
        }
        for (int[] functional : functionalMaze) {
            Arrays.fill(functional, 0);
        }

//        for (Arm a: arms){
//            System.out.println(a);
//        }

        for (Arm arm:arms){
            Vertex v1 = arm.get(0);
            Vertex v2 = arm.get(1);
            int x1 = 5*Math.min(v1.x, v2.x);
            int x2 = 5*Math.max(v1.x, v2.x);
            int y1 = 5*Math.min(v1.y, v2.y);
            int y2 = 5*Math.max(v1.y, v2.y);

            int a1 = 2*Math.min(v1.x, v2.x);
            int a2 = 2*Math.max(v1.x, v2.x);
            int b1 = 2*Math.min(v1.y, v2.y);
            int b2 = 2*Math.max(v1.y, v2.y);

            for (int i=x1;i<=x2;i++){
                imageOutPixels[i][y1] = 1;
            }
            for (int i=y1; i<=y2; i++){
                imageOutPixels[x1][i]= 1;
            }

            for (int i=a1;i<=a2;i++){
                functionalMaze[i][b1] = 1;
            }
            for (int i=b1; i<=b2; i++){
                functionalMaze[a1][i]= 1;
            }
        }

        BufferedImage bi = new BufferedImage(imageOutPixels[0].length+20, imageOutPixels.length+20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();
        ig2.setBackground(Color.WHITE);
        ig2.fillRect ( 0, 0, bi.getWidth(), bi.getHeight() );

        BufferedImage fi = new BufferedImage(functionalMaze[0].length+20, functionalMaze.length+20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D fig2 = fi.createGraphics();
        fig2.setBackground(Color.WHITE);
        fig2.fillRect ( 0, 0, fi.getWidth(), fi.getHeight() );

        for (int i=0;i<imageOutPixels.length;i++){
            for (int j=0; j<imageOutPixels[i].length;j++){
                if(imageOutPixels[i][j]==1 || j==0 || j==imageOutPixels[i].length-1){
                    Color colour = Color.black;
                    bi.setRGB(j+10, i+10, colour.getRGB());
                }
            }
        }

        for (int i=0;i<functionalMaze.length;i++){
            for (int j=0; j<functionalMaze[i].length;j++){
                if(functionalMaze[i][j]==1 || j==0 || j==functionalMaze[i].length-1){
                    Color colour = Color.black;
                    fi.setRGB(j+10, i+10, colour.getRGB());
                }
            }
        }

        File outputFile = new File("mazeOutput.png");
        ImageIO.write(bi, "png", outputFile);

        File functionalFile = new File("functionalMaze.png");
        ImageIO.write(fi, "png", functionalFile);


        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println();

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
        BufferedOutputStream bfw = new BufferedOutputStream(new FileOutputStream("hah.txt"));
        bfw.write(s.toString().getBytes());
        bfw.close();
    }
}
