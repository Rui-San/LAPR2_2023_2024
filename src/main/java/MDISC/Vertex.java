package MDISC;public class Vertex {

    private String name;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object possibleVertex){
        // learnt by doing this method:
        // in java you can omit the { } in the if statement if the line inside the if is a return statement
        // this is useful to make the code more readable and "smaller" in terms of lines
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
