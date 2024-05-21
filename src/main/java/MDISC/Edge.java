package MDISC;

public class Edge {

    private String vertexFrom;
    private String vertexTo;
    private int weight;

    public String getVertexFrom() {
        return vertexFrom;
    }

    public String getVertexTo() {
        return vertexTo;
    }

    public void setVertexFrom(String vertexFrom) {
        this.vertexFrom = vertexFrom;
    }

    public int getWeight() {
        return weight;
    }

    public void setVertexTo(String vertexTo) {
        this.vertexTo = vertexTo;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge(String vertexFrom, String vertexTo, int weight) {
        this.vertexFrom = vertexFrom;
        this.vertexTo = vertexTo;
        this.weight = weight;
    }

}
