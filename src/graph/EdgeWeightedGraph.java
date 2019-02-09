package graph;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileInputStream;

public class EdgeWeightedGraph {
    private final int vertices;
    private int edges;
    private List<LinkedList<Edge>> adj;

    public EdgeWeightedGraph(int vertices) {
        if(vertices < 0)
            throw new IllegalArgumentException(
                "Number of vertices must be nonenegative");
        this.vertices = vertices;
        this.edges = 0;
        adj = new ArrayList<LinkedList<Edge>>();
        for(int i = 0; i < vertices; i++) {
            adj.add(new LinkedList<Edge>());
        }
    }

    public EdgeWeightedGraph(Scanner scanner) {
        this(scanner.nextInt());
        int e = scanner.nextInt();
        if(e < 0)
            throw new IllegalArgumentException(
                "Number of edges must be nonnegative");
        for(int i = 0; i < e; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            double weight = scanner.nextDouble();
            Edge edge = new Edge(v, w, weight);
            addEdge(edge);
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        if(v < 0 || v >= vertices)
            throw new IndexOutOfBoundsException(
                "vertex " + v + " is not between 0 and " + (vertices - 1));
        if(w < 0 || w >= vertices)
            throw new IndexOutOfBoundsException(
                "vertex " + w + " is not between 0 and " + (vertices - 1));
        adj.get(v).add(e);
        adj.get(w).add(e);
        edges++;
    }

    public int getVertices() {
        return vertices;
    }

    public int getEdges() {
        return edges;
    }

    public Iterable<Edge> adj(int v) {
        if(v < 0 || v >= vertices)
            throw new IndexOutOfBoundsException();
        return adj.get(v);
    }

    public LinkedList<Edge> edges() {
        LinkedList<Edge> list = new LinkedList<Edge>();
        for(int v = 0; v < vertices; v++) {
            int selfLoops = 0;
            for(Edge e : adj(v)) {
                if(e.other(v) > v) {
                    list.add(e);
                } else if(e.other(v) == v) {
                    if(selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    @Override
    public String toString() {
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        s.append(vertices + " " + edges + NEWLINE);
        for(int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for(Edge e : adj.get(v)) {
                s.append(e + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new FileInputStream(args[0]));
        EdgeWeightedGraph g = new EdgeWeightedGraph(s);
        System.out.println(g);
    }
}
