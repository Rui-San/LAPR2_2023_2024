package pt.ipp.isep.dei.esoft.project.domain.MATDISC_union_find_recursive;


public class Edge {
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




}