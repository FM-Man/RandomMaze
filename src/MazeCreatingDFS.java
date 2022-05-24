import java.io.IOException;

public class MazeCreatingDFS extends Thread{
    boolean x=false;
    Vertex root;
    public MazeCreatingDFS(Vertex root){
        this.root = root;
    }

    private void util(Vertex source, Vertex to){
        to.visited = true;
        Arm nA= new Arm(to,source);
        Maze.arms.add(nA);
        //System.out.println(root.x+"->"+nA);

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(int i=0;i<4;i++)
            line(to);
    }

    private void line(Vertex from) {
        Vertex newTo;
        newTo = from.get();
        if(newTo != null) {
            util(from, newTo);
        }
    }

    public void run() {
        root.visited = true;
        for(int i=0;i<4;i++)
            line(root);
        x=true;
        try {
            Maze.getInstance().notifyWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
