package graph;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileInputStream;

public class LazyPrimMST {
    private double weight;
    private Queue<Edge> mst;
    private boolean[] marked;
    private Queue<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph g) {
        mst = new LinkedList<Edge>();
        pq = new PriorityQueue<Edge>();
        marked = new boolean[g.getVertices()];
        prim(g, 0);
    }

    private void prim(EdgeWeightedGraph g, int s) {
        visit(g, s);
        while(pq.size() > 0) {
            Edge e = pq.remove();
            int v = e.either(), w = e.other(v);
            if(marked[v] && marked[w]) continue;
            mst.add(e);
            weight += e.getWeight();
            if(!marked[v]) visit(g, v);
            if(!marked[w]) visit(g, w);
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for(Edge e : g.adj(v))
            if(!marked[e.other(v)])
                pq.add(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new FileInputStream(args[0]));
        EdgeWeightedGraph g = new EdgeWeightedGraph(s);
        LazyPrimMST mst = new LazyPrimMST(g);
        for(Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f ", mst.weight());
    }
}
