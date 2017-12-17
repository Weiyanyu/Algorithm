package ch4.section2;

import ch4.section4.EdgeWeightedDigraph;



public class TopoLogical {
    private Iterable<Integer> order;

    public TopoLogical(Digraph G) {
        DirectedCycle directedCycle = new DirectedCycle(G);
        if (!directedCycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }

    public TopoLogical(EdgeWeightedDigraph G) {
        EdgeWeightedDirectedCycle cycle = new EdgeWeightedDirectedCycle(G);
        if (!cycle.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        String fileName = args[0];
        String delim = args[1];
        SymbolDigraph sg = new SymbolDigraph(fileName, delim);

        TopoLogical tp = new TopoLogical(sg.G());
        for (int v : tp.order()) {
            System.out.println(sg.name(v));
        }
    }
}
