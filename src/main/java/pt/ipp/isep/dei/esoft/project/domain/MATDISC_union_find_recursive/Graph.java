package pt.ipp.isep.dei.esoft.project.domain.MATDISC_union_find_recursive;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Edge> edges = new ArrayList<>();
    private List<String> vertices = new ArrayList<>();

    public void addEdge(String v1, String v2, double dist) {
        edges.add(new Edge(v1, v2, dist));
        addUniqueVertex(v1);
        addUniqueVertex(v2);
    }

    private void addUniqueVertex(String vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
    }

    /**
     * Returns the total number of vertices in the graph.
     *
     * @return the total number of vertices
     */
    public int getTotalNumberOfVertices() {
        return vertices.size();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    /**
     *
     * Tal como o MATDISC_union_find, explica o que faz o método union e find em conjunto.
     * explicar melhor mas basicamente o intuito é adicionar aresta à arvore se nao formar um ciclo.
     *
     * ATENÇÃO este método union + find não é exatamente igual ao do MATDISC_union_find pois é um método recursivo, ele
     * chama o método find dentro do próprio método find (recursivo ainda não demos o que é)
     *
     * @param vertex
     * @param parent
     * @return
     */
    private int find(String vertex, int[] parent) {
        int index = vertices.indexOf(vertex);
        if (parent[index] != index) {
            parent[index] = find(vertices.get(parent[index]), parent);
        }
        return parent[index];
    }

    /**
     * explicar aqui o que faz union + find em conjunto
     *
     * @param vertex1
     * @param vertex2
     * @param parent
     */
    private void union(String vertex1, String vertex2, int[] parent) {
        int root1 = find(vertex1, parent);
        int root2 = find(vertex2, parent);

        if (root1 != root2) {
            parent[root1] = root2;
        }
    }

    /**
     * Explicar a logica e todos os passos que se fazem neste método
     *
     * @return
     */
    public List<Edge> calculateMST() {
        List<Edge> minimalSpanningTree = new ArrayList<>();
        sortEdgesByDistance();
        int size = vertices.size();
        int[] parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }

        for (Edge edge : edges) {
            int root1 = find(edge.getWaterPointX(), parent);
            int root2 = find(edge.getWaterPointY(), parent);

            if (root1 != root2) {
                union(edge.getWaterPointX(), edge.getWaterPointY(), parent);
                minimalSpanningTree.add(edge);
            }
        }
        return minimalSpanningTree;
    }


    /**
     * Sorts a list of edges by distance in ascending order using bubble sort.
     *
     */
    private void sortEdgesByDistance() {
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
