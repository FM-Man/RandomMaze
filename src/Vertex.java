import java.util.ArrayList;
import java.util.Collections;

public class Vertex {
    int x,y;
    Vertex left,right,up,down;
    boolean visited;
    ArrayList<Integer> sequence;
    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
        sequence = new ArrayList<>();
        sequence.add(0);
        sequence.add(1);
        sequence.add(2);
        sequence.add(3);
        Collections.shuffle(sequence);
    }

    public Vertex get(){
        Collections.shuffle(sequence);
        int i;
        if(sequence.size()!=0)
            i = sequence.remove(0);
        else return null;

        Vertex v=null;
        if(i==0 && left!=null) {
            if (!left.visited) {
                v= left;
            }
        }
        else if(i == 1 && right!=null) {
            if (!right.visited) {
                v= right;
            }
        }
        else if(i == 2 && up != null) {
            if (!up.visited) {
                v= up;
            }
        }
        else if(i == 3 && down != null) {
            if (!down.visited) {
                v= down;
            }
        }
        return v;
    }
}
