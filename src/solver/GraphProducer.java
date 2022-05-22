package solver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GraphProducer {
    private final String imagePath;

    public GraphProducer(String s){
        imagePath=s;
    }


    public Graph makeGraph() throws IOException {

        BufferedImage image = ImageIO.read(new File(imagePath));
        int height = image.getHeight()-20;
        int width = image.getWidth()-20;
        int[][] pxType = new int[height][width];
        Graph g = new Graph(height,width);

        for (int[] pxTypeRow:pxType){
            Arrays.fill(pxTypeRow,0);
        }

        for(int i=0;i<height;i++){
            for(int j=0; j<width; j++){
                Color pixel = new Color(image.getRGB(j+10,i+10));
                if(pixel.equals(Color.WHITE)){
                    if (hasWidePipe(image, j + 10, i + 10) || hasLongPipe(image, j + 10, i + 10)) {
                        continue;
                    }
                    image.setRGB(j+10,i+10,Color.RED.getRGB());
//                    SolverVertex thisVertex = new SolverVertex(j, i);
//                    g.vertices[i][j] = thisVertex;
//                    pxType[i][j] = 2;
//
//                    if(i==0){
//                        g.starters.add(thisVertex);
//                    }
//                    else {
//                        int distance = 0;
//                        for(int a=i;pxType[a][j]!=1;a--){
//                            distance++;
//                            if(g.vertices[a][j] != null){
//                                thisVertex.connect(g.vertices[a][j],distance);
//                                break;
//                            }
//                        }
//                    }
//                }
//                else {
//                    pxType[i][j] = 1;
                }

            }
        }

        File outputFile = new File("oTest.png");
        ImageIO.write(image, "png", outputFile);

        return g;
    }

    private boolean hasWidePipe(BufferedImage img, int x, int y){
        return new Color(img.getRGB(x, y - 1)).equals(Color.BLACK)
                && new Color(img.getRGB(x, y + 1)).equals(Color.BLACK)
                && !new Color(img.getRGB(x - 1, y)).equals(Color.BLACK)
                && !new Color(img.getRGB(x + 1, y)).equals(Color.BLACK);
    }

    private boolean hasLongPipe(BufferedImage img, int x, int y){
        return !new Color(img.getRGB(x, y - 1)).equals(Color.BLACK)
                && !new Color(img.getRGB(x, y + 1)).equals(Color.BLACK)
                && new Color(img.getRGB(x - 1, y)).equals(Color.BLACK)
                && new Color(img.getRGB(x + 1, y)).equals(Color.BLACK);
    }
}
