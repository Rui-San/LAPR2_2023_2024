package pt.ipp.isep.dei.esoft.project.domain.MATDISC_point_to_vertex;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Edge> edges;

    public List<Edge> getEdges() {
        return edges;
    }

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

    public List<Edge> getMinimalSpanningTree() {
        List<Edge> minimalSpanningTree = new ArrayList<>();

        sortEdgesByDistance(); // Suponho que você tenha implementado isso corretamente

        int numVertices = vertexes.size();
        int criterioParagem = numVertices - 1;
        int addedEdges = 0;

        List<PointToVertex> pointToVertexList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            PointToVertex pointToVertex = new PointToVertex(vertexes.get(i), i);
            pointToVertexList.add(pointToVertex);
        }

        for (Edge edge : edges) {
            String v1 = edge.getSource();  // Nome do vértice 1
            String v2 = edge.getDestination(); // Nome do vértice 2

            int v1PointerIndex = getApontadorByName(v1, pointToVertexList);
            int v2PointerIndex = getApontadorByName(v2, pointToVertexList);

            // Verifica se os vértices apontam para conjuntos diferentes
            if (pointToVertexList.get(v1PointerIndex).getVertexIndex() !=
                    pointToVertexList.get(v2PointerIndex).getVertexIndex()) {
                int v2OldPointerIndex = pointToVertexList.get(v2PointerIndex).getVertexIndex();
                int v1PointerValue = pointToVertexList.get(v1PointerIndex).getVertexIndex();

                // Atualiza os ponteiros de todos os vértices que apontam para v2OldPointerIndex
                for (PointToVertex p : pointToVertexList) {
                    if (p.getVertexIndex() == v2OldPointerIndex) {
                        p.setVertexIndex(v1PointerValue);
                    }
                }

                addedEdges++;
                minimalSpanningTree.add(edge);
            }

            if (addedEdges == criterioParagem) {
                break;
            }
        }

        return minimalSpanningTree;
    }

    // Método auxiliar para obter o índice de um vértice em pointToVertexList
    private int getApontadorByName(String name, List<PointToVertex> pointToVertexList) {
        for (int i = 0; i < pointToVertexList.size(); i++) {
            if (pointToVertexList.get(i).getVertex().equals(name)) {
                return i;
            }
        }
        return -1; // Retorna -1 se não encontrar
    }

    // Método auxiliar para obter o índice de um vértice em pointToVertexList



    public void sortEdgesByDistance() {
        bubbleSort(edges);
    }

    private void bubbleSort(List<Edge> edges) {
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