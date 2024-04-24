package pt.ipp.isep.dei.esoft.project.domain.MATDISC_point_to_vertex;

public class PointToVertex {
    private String vertex;
    private int vertexIndex;

    public PointToVertex(String vertex, int vertexIndex) {
        this.vertex = vertex;
        this.vertexIndex = vertexIndex;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    public void setVertexIndex(int vertexIndex) {
        this.vertexIndex = vertexIndex;
    }

    public String getVertex() {
        return vertex;
    }

    public int getVertexIndex() {
        return vertexIndex;
    }
}

