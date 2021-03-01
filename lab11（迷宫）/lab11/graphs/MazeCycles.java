package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    public int[] parent;

    public MazeCycles(Maze m) {
        super(m);
        parent = new int[m.N() * m.N()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        marked[0] = true;
        dfs(0);
    }

    // Helper methods go here
    private void dfs(int v){
        for (int w: maze.adj(v)) {
            if (marked[w]) {
                if (parent[v] != w) {
                    edgeTo[v] = w;
                    announce();
                    return;
                }
            } else {
                parent[w] = v;
                marked[w] = true;
                announce();
                dfs(w);
            }

        }
        return;
    }
}

