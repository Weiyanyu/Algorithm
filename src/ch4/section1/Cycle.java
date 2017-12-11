package ch4.section1;

import ch4.Graph;
import edu.princeton.cs.algs4.In;

public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;
    private int cycleCount;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s, s);
            }
        }
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, v);
            }
            else if (w != u) {
                hasCycle = true;
                cycleCount++;
            }

        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public int cycleCount() {
        return cycleCount / 2;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(args[0]));
        Cycle cycle = new Cycle(graph);

        System.out.println("has cycle ? " + cycle.hasCycle());
        System.out.println("has " + cycle.cycleCount() + " cycles");
    }
}
