public class GridBuilder {
    private final int height;
    private final int width;
    private final Vertex[][] vertices;

    public GridBuilder(int height, int width) {
        this.height = height;
        this.width = width;
        vertices = new Vertex[height][width];
        for (int i=0;i<height;i++){
            for (int j=0; j<width;j++){
                vertices[i][j] = new Vertex(i,j);
            }
        }

        for (int i=0;i<height;i++){
            for (int j=0; j<width;j++){
                if(j<width-1)
                    vertices[i][j].right = vertices[i][j+1];
                if(j>0)
                    vertices[i][j].left = vertices[i][j-1];
                if(i<height-1)
                    vertices[i][j].down = vertices[i+1][j];
                if(i>0)
                    vertices[i][j].up = vertices[i-1][j];
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Vertex get(int i, int j){
        return vertices[i][j];
    }
}
