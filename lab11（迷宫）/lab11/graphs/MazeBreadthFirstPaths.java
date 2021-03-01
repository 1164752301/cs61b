package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private Maze maze;
    private boolean targetFound = false;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        s = m.xyTo1D(sourceX, sourceY);
        t = m.xyTo1D(targetX, targetY);
        maze = m;
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> workflow = new Queue<>();
        workflow.enqueue(s);
        marked[s] = true;
        while (!workflow.isEmpty()) {
                while (!targetFound){
                    int e = workflow.dequeue();
                    for(int tile: maze.adj(e)) {
                    if (!marked[tile]) {
                        marked[tile] = true;
                        announce();
                        workflow.enqueue(tile);
                        distTo[tile] = distTo[e] + 1;
                        edgeTo[tile] = e;
                        announce();
                    }
                    if (tile == t) {
                        targetFound = true;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

