package MDISC;

// Classe que representa uma aresta de um grafo
public class Edge {

    private Vertex startVertex; // vértice de origem
    private Vertex endVertex; // vértice de destino
    private int weight; // peso da aresta

    // Construtor de uma nova aresta
    public Edge(Vertex startVertex, Vertex endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    // metodo que retorna o vértice de origem
    public Vertex getVertexFrom() {
        return startVertex;
    }

    // metodo que retorna o vértice de destino
    public Vertex getVertexTo() {
        return endVertex;
    }

    // metodo que retorna o peso da aresta
    public int getWeight() {
        return weight;
    }

    //metodo que retorna o vertice na forma "Origem -Peso- Destino"
    @Override
    public String toString(){
        return startVertex + " -" + weight + "- " + endVertex;
    }

    //metodo que verifica se duas arestas são iguais
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
