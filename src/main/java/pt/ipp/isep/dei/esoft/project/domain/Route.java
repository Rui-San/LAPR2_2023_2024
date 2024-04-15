package pt.ipp.isep.dei.esoft.project.domain;


import java.util.Comparator;

public class Route implements Comparable<Route> {
    private int waterPointX;
    private int waterPointY;
    private double distance;

    public Route(int waterPointX, int waterPointY, double distance) {
        this.waterPointX = waterPointX;
        this.waterPointY = waterPointY;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "FileCsv{" +
                "waterPointX=" + waterPointX +
                ", waterPointY=" + waterPointY +
                ", distance=" + distance +
                '}';
    }

    public int compareTo(Route otherRoute) {
        if (this.distance < otherRoute.distance) {
            return -1;
        } else if (this.distance > otherRoute.distance) {
            return 1;
        } else {
            return 0;
        }
    }
}
