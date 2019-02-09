package graph;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileInputStream;

public class KruskalMST {
    private Queue<Edge> mst;
    private double weight;

    public KruskalMST(EdgeWeightedGraph g) {
        mst = new LinkedList<Edge>();
        Queue<Edge> pq = new PriorityQueue<Edge>(g.edges());
        UF uf = new UF(g.getVertices());
        
        while(pq.size() > 0 && mst.size() < g.getVertices() -1) {
            Edge e = pq.remove();
            int v = e.either(), w = e.other(v);
            if(uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.add(e);
            weight += e.getWeight();
        }
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
        KruskalMST mst = new KruskalMST(g);
        for(Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
    }
}
