package pt.ipp.isep.dei.esoft.project.domain.MATDISC_point_to_vertex;

import java.util.ArrayList;
import java.util.List;

public class PointToVertex {
    private String vertex;
    private int pointToVertex;

    public PointToVertex(String vertex, int pointToVertex) {
        this.vertex = vertex;
        this.pointToVertex = pointToVertex;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    public void setPointToVertex(int pointToVertex) {
        this.pointToVertex = pointToVertex;
    }

    public String getVertex() {
        return vertex;
    }

    public int getPointToVertex() {
        return pointToVertex;
    }


}
