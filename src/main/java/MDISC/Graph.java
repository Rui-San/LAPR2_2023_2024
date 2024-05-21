package MDISC;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Edge> edges;
    private List<String> vertexes;

    public List<Edge> getEdges() {
        return edges;
    }

    public List<String> getVertexes() {
        return vertexes;
    }

    public Graph() {
        edges = new ArrayList<>();
        vertexes = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        addVertex(edge.getVertexFrom());
        addVertex(edge.getVertexTo());
    }

    public void addVertex(String vertex) {
        if (!vertexes.contains(vertex)) {
            vertexes.add(vertex);
        }
    }

    public List<Edge> getEdgesFromVertex(String vertex) {
        List<Edge> edgesFromVertex = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getVertexFrom().equals(vertex) || edge.getVertexTo().equals(vertex)) {
                edgesFromVertex.add(edge);
            }
        }
        return edgesFromVertex;
    }

}
