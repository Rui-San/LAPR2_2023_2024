package pt.ipp.isep.dei.esoft.project.domain.matdisc;


public class Edge implements Comparable<Edge> {
    private String waterPointX;
    private String waterPointY;
    private double distance;

    public Edge(String waterPointX, String waterPointY, double distance) {
        this.waterPointX = waterPointX;
        this.waterPointY = waterPointY;
        this.distance = distance;
    }

    public String getWaterPointX() {
        return waterPointX;
    }

    public String getWaterPointY() {
        return waterPointY;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Water Point X=" + waterPointX +
                ", Water Point Y=" + waterPointY +
                ", Distance=" + distance +
                '}';
    }

    public int compareTo(Edge otherEdge) {
        if (this.distance < otherEdge.distance) {
            return -1;
        } else if (this.distance > otherEdge.distance) {
            return 1;
        } else {
            return 0;
        }
    }


}