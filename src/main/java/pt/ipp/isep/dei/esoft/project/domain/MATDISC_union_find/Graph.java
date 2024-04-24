package pt.ipp.isep.dei.esoft.project.domain.MATDISC_union_find;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    private List<Edge> edges;

    private List<String> vertexes = new ArrayList<>();

    public Graph() {
        edges = new ArrayList<>();
        vertexes = new ArrayList<>();
    }

    public void addEdge(Edge edge, String v1, String v2) {
        edges.add(edge);
        addUniqueVertex(v1);
        addUniqueVertex(v2);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getTotalNumberOfVertices() {
        return vertexes.size();
    }

    private void addUniqueVertex(String vertex) {
        if (!vertexes.contains(vertex)) {
            vertexes.add(vertex);
        }
    }

    public List<String> getVertices() {
        return vertexes;
    }

    /**
     * Explica passo a passo o como este algoritmo funciona (incluindo a logica de union find que usa (ver class union find), o que faz e adiciona arestas se nao criar um ciclo)
     *
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

    public void sortEdgesByDistance() {
        bubbleSortByDistance(edges);
    }

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
