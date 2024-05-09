package pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_point_to_vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * The Graph class represents a graph data structure.
 * <p>
 * It contains methods for adding edges, retrieving vertices, getting the total number of vertices,
 * finding the minimal spanning tree, and sorting edges by distance.
 */
public class GraphPV {

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
    public GraphPV() {
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
        boolean isUnique = true;
        int index = 0;
        while (isUnique && index < vertexes.size()) {
            if (vertexes.get(index).equals(vertex)) {
                isUnique = false;
            }
            index++;
        }
        if (isUnique) {
            vertexes.add(vertex);
        }
    }
    /*
    private void addUniqueVertex(String vertex) {
        if (!vertexes.contains(vertex)) {
            vertexes.add(vertex);
        }
    }
    */

    /**
     * Returns the list of vertices in the graph.
     *
     * @return the list of vertices
     */
    public List<String> getVertices() {
        return vertexes;
    }

    /**
     * Finds the minimal spanning tree of the graph using Kruskal's algorithm.
     * 1) Sorts the graph by ascending distance
     * 2) Calculates the stop criteria (number of vertex -1)
     * 3) The logic of this algorithm is to point edges to vertices, that is, in the first iteration it points each
     * unique vertex to a different index (object PointToVertex).
     * For each edge of the graph being analyzed, it checks which index that edge is pointing to. If they point to the
     * same index, it means they are in the same 'set' and it does not add that edge; if they point to different indices,
     * it means they are in different 'sets' and will change the second vertex to point to the same index as the first vertex,
     * increments the stop criteria, and adds the edge to the minimal spanning tree.
     * The algorithm stops and returns the list of edges when the number of selected edges equals the stopping criterion.
     * This algorithm is very efficient because it does not store information about the vertex, it only points to an
     * integer (array index).
     *
     * @return the list of edges in the minimal spanning tree
     */
    public List<Edge> getMinimalSpanningTree() {
        List<Edge> minimalSpanningTree = new ArrayList<>();

        sortEdgesByDistance();

        int numVertices = vertexes.size();
        int criterioParagem = numVertices - 1;
        int addedEdges = 0;

        List<PointToVertex> pointToVertexList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            PointToVertex pointToVertex = new PointToVertex(vertexes.get(i), i);
            pointToVertexList.add(pointToVertex);
        }

        for (int j = 0; j < edges.size() && addedEdges <= criterioParagem; j++) {
            Edge edge = edges.get(j);
            String v1 = edge.getSource();
            String v2 = edge.getDestination();

            int v1PointerIndex = getApontadorByName(v1, pointToVertexList);
            int v2PointerIndex = getApontadorByName(v2, pointToVertexList);

            if (pointToVertexList.get(v1PointerIndex).getVertexIndex() !=
                    pointToVertexList.get(v2PointerIndex).getVertexIndex()) {
                int v2OldPointerIndex = pointToVertexList.get(v2PointerIndex).getVertexIndex();
                int v1PointerValue = pointToVertexList.get(v1PointerIndex).getVertexIndex();


                for (PointToVertex p : pointToVertexList) {
                    if (p.getVertexIndex() == v2OldPointerIndex) {
                        p.setVertexIndex(v1PointerValue);
                    }
                }
                addedEdges++;
                minimalSpanningTree.add(edge);
            }

        }

        return minimalSpanningTree;
    }

    /**
     * Returns the index of the vertex with the specified name in the list of PointToVertex objects.
     *
     * @param name              the name of the vertex to find
     * @param pointToVertexList the list of PointToVertex objects to search in
     * @return the index of the vertex, or -1 if not found
     */
    private int getApontadorByName(String name, List<PointToVertex> pointToVertexList) {
        for (int i = 0; i < pointToVertexList.size(); i++) {
            if (pointToVertexList.get(i).getVertex().equals(name)) {
                return i;
            }
        }
        return -1;
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

                    Edge temp = edges.get(j);
                    edges.set(j, edges.get(j + 1));
                    edges.set(j + 1, temp);
                }
            }
        }
    }
}