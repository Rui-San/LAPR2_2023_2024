package pt.ipp.isep.dei.esoft.project.domain;

public class Route {
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
}
