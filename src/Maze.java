import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Maze {
    private boolean thread1done = false, thread2done=false;
    int height= 5;
    int width = 5;


    static ArrayList<Arm> arms = new ArrayList<>();
    private static Maze instance;
    public static Maze getInstance(){
        if(instance==null){
            instance = new Maze();
        }
        return instance;
    }
    private Maze(){}



    public void notifyWriter() throws IOException {
        if(!thread1done) {
            thread1done = true;
            return;
        }
        if(!thread2done) {
            thread2done = true;
            writeFile(height,width);
        }
    }


    public void createMaze(){
        GridBuilder maze = new GridBuilder(height,width);
        MazeCreatingDFS mazeCreatingDfs1 = new MazeCreatingDFS(maze.get(0,0));
        MazeCreatingDFS mazeCreatingDfs2 = new MazeCreatingDFS(maze.get(height-1, width-1));

        mazeCreatingDfs1.start();
        mazeCreatingDfs2.start();
    }

    public void writeFile(int height, int width) throws IOException{
        int[][] fivePxWidePath = new int[(height -1)*5+1][(width -1)*5+1];
        int[][] onePxWidePath = new int[(height -1)*2+1][(width -1)*2+1];

        for (int[] fivePxRox : fivePxWidePath) {
            Arrays.fill(fivePxRox, 0);
        }
        for (int[] onePxRow : onePxWidePath) {
            Arrays.fill(onePxRow, 0);
        }


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
                fivePxWidePath[i][y1] = 1;
            }
            for (int i=y1; i<=y2; i++){
                fivePxWidePath[x1][i]= 1;
            }

            for (int i=a1;i<=a2;i++){
                onePxWidePath[i][b1] = 1;
            }
            for (int i=b1; i<=b2; i++){
                onePxWidePath[a1][i]= 1;
            }
        }

        BufferedImage fivePxMaze = new BufferedImage(fivePxWidePath[0].length+20, fivePxWidePath.length+20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g1 = fivePxMaze.createGraphics();
        g1.setBackground(Color.WHITE);
        g1.fillRect ( 0, 0, fivePxMaze.getWidth(), fivePxMaze.getHeight() );

        BufferedImage onePxMaze = new BufferedImage(onePxWidePath[0].length+20, onePxWidePath.length+20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = onePxMaze.createGraphics();
        g2.setBackground(Color.WHITE);
        g2.fillRect ( 0, 0, onePxMaze.getWidth(), onePxMaze.getHeight() );

        for (int i=0;i<fivePxWidePath.length;i++){
            for (int j=0; j<fivePxWidePath[i].length;j++){
                if(fivePxWidePath[i][j]==1 || j==0 || j==fivePxWidePath[i].length-1){
                    Color colour = Color.black;
                    fivePxMaze.setRGB(j+10, i+10, colour.getRGB());
                }
            }
        }

        for (int i=0;i<onePxWidePath.length;i++){
            for (int j=0; j<onePxWidePath[i].length;j++){
                if(onePxWidePath[i][j]==1 || j==0 || j==onePxWidePath[i].length-1){
                    Color colour = Color.black;
                    onePxMaze.setRGB(j+10, i+10, colour.getRGB());
                }
            }
        }

        File outputFile = new File("fivePxPath.png");
        ImageIO.write(fivePxMaze, "png", outputFile);

        File functionalFile = new File("onePxPath.png");
        ImageIO.write(onePxMaze, "png", functionalFile);

        System.out.println("everything done");

//
//
//        StringBuilder s= new StringBuilder();
//        for (int[] a: fivePxWidePath){
//            for (int b: a) {
//                if(b!=0)
//                    s.append(b).append(" ");
//                else s.append("  ");
//            }
//            s.append("\n");
//        }
//        System.out.println(s);
//        BufferedOutputStream bfw = new BufferedOutputStream(new FileOutputStream("hah.txt"));
//        bfw.write(s.toString().getBytes());
//        bfw.close();
    }
}
