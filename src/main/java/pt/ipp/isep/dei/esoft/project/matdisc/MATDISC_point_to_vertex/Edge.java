package pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_point_to_vertex;

/**
 * The Edge class represents an edge in a graph.
 *
 * An edge consists of a source vertex, a destination vertex, and a distance between them.
 */
public class Edge {

    /**
     * The source vertex of the edge.
     */
    private String source;

    /**
     * The destination vertex of the edge.
     */
    private String destination;

    /**
     * The distance between the source and destination vertices.
     */
    private double distance;

    /**
     * Constructs a new edge with the specified source, destination, and distance.
     *
     * @param source      the source vertex of the edge
     * @param destination the destination vertex of the edge
     * @param distance    the distance between the source and destination vertices
     */
    public Edge(String source, String destination, double distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    /**
     * Returns the source vertex of the edge.
     *
     * @return the source vertex of the edge
     */
    public String getSource() {
        return source;
    }

    /**
     * Returns the destination vertex of the edge.
     *
     * @return the destination vertex of the edge
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Returns the distance between the source and destination vertices of the edge.
     *
     * @return the distance between the source and destination vertices
     */
    public double getDistance() {
        return distance;
    }


}
