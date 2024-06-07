package MDISC;

//Classe que representa um vertice do grafo
public class Vertex {

    private String name; //nome do vertice

    // Construtor de um novo vertice
    public Vertex(String name) {
        this.name = name;
    }

    // metodo que retorna o nome do vertice
    public String getName() {
        return name;
    }

    // metodo que verifica se dois vertices sao iguais
    @Override
    public boolean equals(Object possibleVertex){
        if(this == possibleVertex)
            return true;
        if(possibleVertex == null)
            return false;
        if(getClass() != possibleVertex.getClass())
            return false;
        Vertex otherVertex = (Vertex) possibleVertex;
        if (name == null) {
            if (otherVertex.name != null)
                return false;
        } else if (!name.equals(otherVertex.name))
            return false;
        return true;
    }

    @Override
    public String toString(){
        return name;
    }

}
