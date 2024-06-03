package MDISC;

import java.util.List;
import java.util.stream.Collectors;

public class Graph {

    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getEdgesFromVertex(Vertex vertex) {
        return edges.stream()
                .filter(edge -> edge.getVertexFrom().equals(vertex) || edge.getVertexTo().equals(vertex))
                .collect(Collectors.toList());
    }

    public static void display(Graph graph){
        for(Edge edge : graph.getEdges()){
            System.out.println(edge);
        }
    }

}
