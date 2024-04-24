package pt.ipp.isep.dei.esoft.project.domain.MATDISC_union_find_recursvie;

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


    public List<Edge> getEdges() {
        return edges;
    }

    private int find(String vertex, int[] parent) {
        int index = vertices.indexOf(vertex);
        if (parent[index] != index) {
            parent[index] = find(vertices.get(parent[index]), parent);
        }
        return parent[index];
    }

    private void union(String vertex1, String vertex2, int[] parent) {
        int root1 = find(vertex1, parent);
        int root2 = find(vertex2, parent);

        if (root1 != root2) {
            parent[root1] = root2;
        }
    }

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
