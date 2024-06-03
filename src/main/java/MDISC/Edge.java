package MDISC;

public class Edge {

    private Vertex startVertex;
    private Vertex endVertex;
    private int weight;

    public Edge(Vertex startVertex, Vertex endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    public Vertex getVertexFrom() {
        return startVertex;
    }

    public Vertex getVertexTo() {
        return endVertex;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString(){
        return startVertex + " -" + weight + "- " + endVertex;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge edge = (Edge) obj;
            boolean equal = false;
            if(startVertex.equals(edge.getVertexFrom()) && endVertex.equals(edge.getVertexTo()))
                equal = true;
            if(startVertex.equals(edge.getVertexTo()) && endVertex.equals(edge.getVertexFrom()))
                equal = true;
            return equal;
        }
        return false;
    }

}
