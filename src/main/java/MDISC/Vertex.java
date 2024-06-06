package MDISC;


public class Vertex {

    private String name;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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
