public class Arm {
    Vertex[] vertices = new Vertex[2];
    public Arm(Vertex v1, Vertex v2){
        vertices[0] = v1;
        vertices[1] = v2;
    }

    public Vertex get(int i){
        return vertices[i];
    }

    public String toString(){
        return vertices[0].x+","+vertices[0].y +" -> " +vertices[1].x+","+vertices[1].y;
    }
}
