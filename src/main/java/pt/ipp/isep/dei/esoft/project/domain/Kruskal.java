package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    public static List<Route> minimalSpanningTree(List<Route> routesList) {

        ArrayList<Route> minimalSpanningTree = new ArrayList<>();

        Collections.sort(routesList);

        int StopCriteria = countUniqueVertices(routesList) - 1;



        // ainda em implementacao do algoritmo


        return minimalSpanningTree;
    }


    public static int countUniqueVertices(List<Route> routesList) {
        List<Integer> uniqueVertices = new ArrayList<>();

        for (Route route : routesList) {
            if (!uniqueVertices.contains(route.getWaterPointX())) {
                uniqueVertices.add(route.getWaterPointX());
            }
            if (!uniqueVertices.contains(route.getWaterPointY())) {
                uniqueVertices.add(route.getWaterPointY());
            }
        }

        return uniqueVertices.size();
    }

}
