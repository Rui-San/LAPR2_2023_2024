package MDISC;

import java.util.List;
import java.util.stream.Collectors;

// Classe que representa um grafo
public class Graph {

    private final List<Vertex> vertices; // Lista de vértices do grafo
    private final List<Edge> edges; // Lista de arestas do grafo

    // Construtor de um novo grafo
    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }
    // Métodos que retorna a lista dos vértices do grafo
    public List<Vertex> getVertexes() {
        return vertices;
    }
    // Método que retorna a lista das arestas do grafo
    public List<Edge> getEdges() {
        return edges;
    }
    // Método que retorna a lista das arestas que saem de um outro vértice
    public List<Edge> getEdgesFromVertex(Vertex vertex) {
        return edges.stream()
                .filter(edge -> edge.getVertexFrom().equals(vertex) || edge.getVertexTo().equals(vertex))
                .collect(Collectors.toList());
    }

    // Método que mostra o grafo na consola
    public static void display(Graph graph){
        for(Edge edge : graph.getEdges()){
            System.out.println(edge);
        }
    }
}
