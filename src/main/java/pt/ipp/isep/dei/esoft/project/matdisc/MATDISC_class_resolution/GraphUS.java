package pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_class_resolution;

import java.util.ArrayList;
import java.util.List;

/**
 * The Graph class represents a graph data structure.
 * <p>
 * It contains methods for adding edges, retrieving vertices, getting the total number of vertices,
 * finding the minimal spanning tree, and sorting edges by distance.
 */
public class GraphUS {
    /**
     * The list of edges in the graph.
     */
    private List<Edge> edges;

    /**
     * The list of vertices in the graph.
     */
    private List<String> vertexes = new ArrayList<>();

    /**
     * Constructs a new {@code Graph} object with an empty list of edges and vertices.
     */
    public GraphUS() {
        edges = new ArrayList<>();
        vertexes = new ArrayList<>();
    }

    /**
     * Adds an edge to the graph and updates the list of vertices.
     *
     * @param edge the edge to be added
     * @param v1   the source vertex of the edge
     * @param v2   the destination vertex of the edge
     */
    public void addEdge(Edge edge, String v1, String v2) {
        edges.add(edge);
        addUniqueVertex(v1);
        addUniqueVertex(v2);
    }

    /**
     * Returns the List of edges of the graph.
     *
     * @return the List of edges of the graph.
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Returns the total number of vertices in the graph.
     *
     * @return the total number of vertices
     */
    public int getTotalNumberOfVertices() {
        return vertexes.size();
    }

    /**
     * Adds a vertex to the list of vertices if it is unique (not already present).
     *
     * @param vertex the vertex to be added
     */
    private void addUniqueVertex(String vertex) {
        if (!vertexes.contains(vertex)) {
            vertexes.add(vertex);
        }
    }

    /**
     * Returns the list of vertices in the graph.
     *
     * @return the list of vertices
     */
    public List<String> getVertices() {
        return vertexes;
    }

    /**
     *
     *This method effectively implements Kruskal's algorithm to find the minimal spanning tree of a graph
     *
     *Here's how Kruskal's algorithm works:
     *
     *1 - Initialization:
     *Initially, each vertex of the graph is considered as a separate component.
     *
     *2 - Sort Edges:
     *Sort all the edges of the graph in non-decreasing order of their weights.
     *
     *3 - Iterate Through Edges:
     *Iterate through the sorted edges.
     *For each edge, check if including it in the MST forms a cycle or not. This can be done by checking if the two vertices of the edge belong to the same connected component.
     *If adding the edge does not create a cycle (i.e., the vertices belong to different connected components), include it in the MST.
     *
     *4 - Repeat Until Spanning Tree Complete:
     *Repeat step 3 until the MST contains n−1 edges, where n is the number of vertices in the graph. This ensures that the MST spans all vertices without creating cycles.
     *
     *Output:
     *The resulting set of edges forms the minimum spanning tree of the graph.
     *
     * @return list of edges representing the minimal spanning tree.
     */
    public List<Edge> getMinimalSpanningTree() {
        List<Edge> minimalSpanningTree = new ArrayList<>();


        sortEdgesByDistance();

        int numVertices = vertexes.size();

        int criterioParagem = numVertices - 1;
        int addedEdges = 0;

        List<List<String>> arrayDeSacos = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            List<String> saco = new ArrayList<>();
            saco.add(vertexes.get(i));
            arrayDeSacos.add(saco);
        }

        for (Edge edge : edges) {
            String v1 = edge.getSource();
            String v2 = edge.getDestination();

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

    /**
     * Sorts the list of edges by distance in ascending order.
     * <p>
     * This method delegates the sorting operation to the private helper method {@code bubbleSortByDistance}.
     * </p>
     */
    public void sortEdgesByDistance() {
        bubbleSortByDistance(edges);
    }

    /**
     * Sorts the list of edges by distance in ascending order using bubble sort.
     *
     * @param edges the list of edges to be sorted
     */
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
