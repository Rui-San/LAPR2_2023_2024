package pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_union_find;

import pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_union_find.Edge;
import java.util.ArrayList;
import java.util.List;

public class GraphUF {
    private List<Edge> edges;

    private List<String> vertexes = new ArrayList<>();

    // Constructor initializes the edges and vertexes lists
    public GraphUF() {
        edges = new ArrayList<>();
        vertexes = new ArrayList<>();
    }

    // Adds an edge to the graph and adds the vertices to the vertexes list
    public void addEdge(Edge edge, String v1, String v2) {
        edges.add(edge);
        addUniqueVertex(v1);
        addUniqueVertex(v2);
    }

    // Returns the edges list
    public List<Edge> getEdges() {

        return edges;
    }

    // Returns the total number of vertices
    public int getTotalNumberOfVertices() {

        return vertexes.size();
    }

    // Adds a vertex to the vertexes list if it is not already in the list
    private void addUniqueVertex(String vertex) {
        if (!vertexes.contains(vertex)) {
            vertexes.add(vertex);
        }
    }

    // Returns the vertexes list
    public List<String> getVertices() {

        return vertexes;
    }

    /**
     * Explica passo a passo o como este algoritmo funciona (incluindo a logica de union find que usa (ver class union find), o que faz e adiciona arestas se nao criar um ciclo)
     *
     * @return
     */
    /** Uses a union find algorithm to find the minimal spanning tree of the graph.
     * The algorithm sorts the edges by distance and then iterates through the edges,
     * adding an edge to the minimal spanning tree if it connects vertices in different components.
     * The algorithm stops when the minimal spanning tree has the same number of edges as the number of vertices minus one.
     * @return
     */
    public List<Edge> getMinimalSpanningTree() {
        List<Edge> minimalSpanningTree = new ArrayList<>();

        sortEdgesByDistance();

        UnionFind unionFind = new UnionFind(vertexes.size());

        for (Edge edge : edges) {
            int v1Index = vertexes.indexOf(edge.getSource());
            int v2Index = vertexes.indexOf(edge.getDestination());

            // Verifica se as arestas conectam vértices em componentes distintas
            if (!unionFind.connected(v1Index, v2Index)) {
                unionFind.union(v1Index, v2Index); // Une os componentes
                minimalSpanningTree.add(edge); // Adiciona a aresta à árvore
            }

            // Se a árvore já contém arestas suficientes para formar uma árvore geradora mínima
            if (minimalSpanningTree.size() == vertexes.size() - 1) {
                break;
            }
        }

        return minimalSpanningTree;
    }

    // Sorts the edges by distance
    public void sortEdgesByDistance() {
        bubbleSortByDistance(edges);
    }

    // Sorts the edges by distance using the bubble sort algorithm.
    // It swaps the elements if the distance of the current edge is greater than the distance of the next edge.
    private void bubbleSortByDistance(List<Edge> edges) {
        int n = edges.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (edges.get(j).getDistance() > edges.get(j + 1).getDistance()) {
                    // Swap the elements
                    Edge temp = edges.get(j);
                    edges.set(j, edges.get(j + 1));
                    edges.set(j + 1, temp);
                }
            }
        }
    }
}
