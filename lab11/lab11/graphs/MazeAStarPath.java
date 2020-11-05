package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private int N;
    private MinPQ<SearchNode> pq;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        N = m.N();
        distTo[s] = 0;
        edgeTo[s] = s;
        pq = new MinPQ<>(new SearchNodeComparator());
    }

    public class SearchNode {
        public int priority;
        public int element;

        public SearchNode(int ele) {
            element = ele;
            priority = distTo[ele] + estimatedDistance(ele);
        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode a, SearchNode b) {
            return a.priority - b.priority;
        }
    }

    /** Estimate of the distance from v to the target. */
    private int estimatedDistance(int v) {
        int vX = Math.floorDiv(v, N);
        int vY = v % N;
        int tX = Math.floorDiv(t, N);
        int tY = t % N;
        return Math.abs(vX - tX) + Math.abs(vY - tY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        pq.insert(new SearchNode(s));
        while (!targetFound){
            SearchNode current = pq.delMin();
            marked[current.element] = true;
            if (estimatedDistance(current.element) == 0) {
                targetFound = true;
            }
            for(int i : maze.adj(current.element)) {
                if (edgeTo[current.element] != i && distTo[i] == Integer.MAX_VALUE) {
                    edgeTo[i] = current.element;
                    announce();
                    distTo[i] = distTo[current.element] + 1;
                    pq.insert(new SearchNode(i));
                }
            }
        }
        marked[t] = true;
        announce();
    }

    @Override
    public void solve() {
        astar(s);
    }

}

