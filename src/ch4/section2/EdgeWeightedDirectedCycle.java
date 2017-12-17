package ch4.section2;

import ch1.section3.Stack;
import ch4.section4.DirectedEdge;
import ch4.section4.EdgeWeightedDigraph;

public class EdgeWeightedDirectedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onStack = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (cycle != null) return;

            if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }
            else if (onStack[w]) {
                cycle = new Stack<>();
                DirectedEdge f = e;
                for (; f.from() != w; f = edgeTo[f.from()]) {
                    cycle.push(e);
                }
                cycle.push(f);
                return;
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}
