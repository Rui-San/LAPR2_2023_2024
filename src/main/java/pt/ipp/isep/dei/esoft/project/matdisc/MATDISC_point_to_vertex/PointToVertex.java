package pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_point_to_vertex;

/**
 * Represents a mapping between a vertex and its index.
 * Creates an object "PointToVertex" which basically "points" each vertex to an index.
 * If two objetcts have the same index it means those two vertexes are in the same 'set'
 */
public class PointToVertex {
    private String vertex;
    private int vertexIndex;

    /**
     * Constructor to create a PointToVertex object.
     *
     * @param vertex      the vertex
     * @param vertexIndex the index of the vertex
     */
    public PointToVertex(String vertex, int vertexIndex) {
        this.vertex = vertex;
        this.vertexIndex = vertexIndex;
    }

    /**
     * Sets the vertex.
     *
     * @param vertex the vertex to set
     */
    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    /**
     * Sets the index of the vertex.
     *
     * @param vertexIndex the index of the vertex to set
     */
    public void setVertexIndex(int vertexIndex) {
        this.vertexIndex = vertexIndex;
    }

    /**
     * Gets the vertex.
     *
     * @return the vertex
     */
    public String getVertex() {
        return vertex;
    }

    /**
     * Gets the index of the vertex.
     *
     * @return the index of the vertex
     */
    public int getVertexIndex() {
        return vertexIndex;
    }
}

