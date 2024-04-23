package pt.ipp.isep.dei.esoft.project.domain.MATDISC_class_resolution;

import java.util.ArrayList;
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

        List<List<String>> arrayDeSacos = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            List<String> saco = new ArrayList<>();
            saco.add(vertexes.get(i)); // Adiciona a string correspondente ao vértice
            arrayDeSacos.add(saco);
        }


        for (Edge edge : edges) {
            String v1 = edge.getSource();  //nome do vertice1
            String v2 = edge.getDestination(); //nome do vertice2

            int indexSacoV1 = -1;
            int indexSacoV2 = -1;

            for (int i = 0; i < arrayDeSacos.size(); i++) {
                if (arrayDeSacos.get(i).contains(v1)) {
                    indexSacoV1 = i;
                }
                if (arrayDeSacos.get(i).contains(v2)) {
                    indexSacoV2 = i;
                }
            }

            if (indexSacoV1 != indexSacoV2) {
                arrayDeSacos.get(indexSacoV1).addAll(arrayDeSacos.get(indexSacoV2));
                arrayDeSacos.get(indexSacoV2).clear();
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
