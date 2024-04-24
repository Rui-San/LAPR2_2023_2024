package pt.ipp.isep.dei.esoft.project.domain.MATDISC_union_find;

public class Edge {
    private String source;
    private String destination;
    private double distance;

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }

    public Edge(String source, String destination, double distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }


}
