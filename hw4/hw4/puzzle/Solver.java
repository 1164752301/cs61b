package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    class SearchNode {
        public WorldState tile;
        public int move;
        public SearchNode prev;
        public Integer priority;

        public SearchNode(WorldState current, SearchNode parent) {
            tile = current;
            prev = parent;
            move = parent == null ? 0 : parent.move + 1;
            if (edCache.containsKey(tile)) {
                priority = edCache.get(tile) + move;
            } else {
                priority = move + tile.estimatedDistanceToGoal();
            }
        }

        public ArrayList<WorldState> path() {
            Stack<WorldState> results = new Stack<>();
            SearchNode p = this;
            while (p != null) {
                results.push(p.tile);
                p = p.prev;
            }
            ArrayList<WorldState> resultA = new ArrayList<>();
            while (!results.empty()) {
                resultA.add(results.pop());
            }
            return resultA;
        }
    }

    class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode left, SearchNode right) {
            return left.priority.compareTo(right.priority);
        }
    }

    public HashMap<WorldState, Integer> edCache = new HashMap<>();
    public MinPQ<SearchNode> pq = new MinPQ<>(new SearchNodeComparator());
    public SearchNode BMS;

    public Solver(WorldState initial) {
        SearchNode init = new SearchNode (initial, null);
        pq.insert(init);
        SearchNode current = pq.delMin();
        BMS = SolverHelper(current);
    }

    public SearchNode SolverHelper(SearchNode current) {
        if (current.tile.isGoal()) {
            return current;
        }
        for (WorldState neighbour : current.tile.neighbors()) {
            if (current.prev == null || !neighbour.equals(current.prev.tile)) {
                SearchNode tmp = new SearchNode(neighbour, current);
                pq.insert(tmp);
            }
        }
        return SolverHelper(pq.delMin());
    }

    public int moves() {
        return BMS.move;
    }

    public Iterable<WorldState> solution() {
        return BMS.path();
    }
}



