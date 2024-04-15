package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    public static List<Route> minimalSpanningTree(List<Route> routesList) {

        ArrayList<Route> minimalSpanningTree = new ArrayList<>();

        Collections.sort(routesList);

        int v = countUniqueVertices(routesList);
        int stopCriteria = v - 1;

        int j = 0;
        int numberOfEdges = 0;

        // Allocate memory for creating V subsets
        Subset[] subsets = new Subset[v];

        // Allocate memory for results
        Route[] results = new Route[v];

        for (int i = 0; i < v; i++) {
            subsets[i] = new Subset(i, 0);
        }

        while (numberOfEdges < stopCriteria) {
            Route nextRoute = routesList.get(j);
            int x = findRoot(subsets, nextRoute.getWaterPointX());
            int y = findRoot(subsets, nextRoute.getWaterPointY());

            if (x != y) {
                results[numberOfEdges] = nextRoute;
                union(subsets, x, y);
                numberOfEdges++;
            }
            j++;


        }


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

    public static Subset[] totalSubsets(int v) {

        Subset[] subsets = new Subset[v];

        for (int i = 0; i < v; i++) {
            subsets[i] = new Subset(i, 0);
        }
        return subsets;
    }

    // Defines subset element structure
    static class Subset {
        int parent, rank;

        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    // Function to unite two disjoint sets
    private static void union(Subset[] subsets, int x,
                              int y) {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);

        if (subsets[rootY].rank < subsets[rootX].rank) {
            subsets[rootY].parent = rootX;
        } else if (subsets[rootX].rank
                < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        } else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    // Function to find parent of a set
    private static int findRoot(Subset[] subsets, int i) {
        if (subsets[i].parent == i)
            return subsets[i].parent;

        subsets[i].parent
                = findRoot(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    /*
        private static void union(Subset[] subsets, int x, int y) {
            int rootX = findRoot(subsets, x);
            int rootY = findRoot(subsets, y);

            if (subsets[rootY].getRank() < subsets[rootX].getRank()) {
                subsets[rootY].setParent(rootX);
            } else if (subsets[rootX].getRank() < subsets[rootY].getRank()) {
                subsets[rootX].setParent(rootY);
            } else {
                subsets[rootY].setParent(rootX);
                subsets[rootX].rank++;
            }
        }

        private static int findRoot(Subset[] subsets, int i) {
            if (subsets[i].getParent() == i) {
                return subsets[i].getParent();
            }
            subsets[i].getParent()
                    = findRoot(subsets, subsets[i].getParent());
            return subsets[i].getParent();
        }

     */
    public static void printMinimumSpanningTree(Route[] results, int numberOfEdges) {
        double minCost = 0;
        for (int i = 0; i < numberOfEdges; i++) {
            System.out.println(results[i].getWaterPointX() + " -- "
                    + results[i].getWaterPointY() + " == "
                    + results[i].getDistance());
            minCost += results[i].getDistance();
        }
        System.out.println("Total cost of MST: " + minCost);
    }
}



