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

        sortEdgesByDistance();

        int numVertices = vertexes.size();

        int criterioParagem = numVertices - 1;
        int addedEdges = 0;

        int[] vertexPoints = new int[numVertices];

        List<PointToVertex> pointToVertexList = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            PointToVertex pointToVertex = new PointToVertex(vertexes.get(i), i);
            pointToVertexList.add(pointToVertex);
        }


        for (Edge edge : edges) {
            String v1 = edge.getSource();  //nome do vertice1
            String v2 = edge.getDestination(); //nome do vertice2

            int indexV1 = -1;
            int indexV2 = -1;

            for (int i = 0; i < pointToVertexList.size(); i++) {
                if (edge.getSource().equalsIgnoreCase(pointToVertexList.get(i).getVertex())) {
                    indexV1 = i;
                }
                if (edge.getDestination().equalsIgnoreCase(pointToVertexList.get(i).getVertex())) {
                    indexV2 = i;
                }
            }

            if (indexV1 != indexV2) {
                pointToVertexList.get(indexV2).setPointToVertex(indexV1);
                addedEdges++;
                minimalSpanningTree.add(edge);
            }
            if (addedEdges == criterioParagem) {
                break;
            }

        }


        return minimalSpanningTree;
    }


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
