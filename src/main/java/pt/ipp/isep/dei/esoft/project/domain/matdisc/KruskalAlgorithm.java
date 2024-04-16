package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import java.util.*;

public class KruskalAlgorithm {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);
        System.out.println("What is the path of the file?");
        String fileName = read.nextLine();
        FileHandler file = new FileHandler(fileName);
        List<Edge> edgesList = file.importFromFile();
        System.out.println(file);

        for (Edge edge : edgesList) {
            System.out.println(edge.getWaterPointX() + ", " + edge.getWaterPointY() + ", " + edge.getDistance());
        }


        List<Edge> minimumSpanningTree = kruskalMST(edgesList);
        System.out.println();
        for (Edge edge : minimumSpanningTree) {
            System.out.println(edge.getWaterPointX() + ", " + edge.getWaterPointY() + ", " + edge.getDistance());
        }
        printCost(minimumSpanningTree);
    }

    public static List<Edge> kruskalMST(List<Edge> edgeList) {

        Collections.sort(edgeList);

        int totalVertex = countUniqueVertices(edgeList);
        int stopCriteria = totalVertex - 1;

        int acceptedEdges = 0;
        int edgeIndex = 0;


        List<Edge> minimumSpanningTree = new ArrayList<>();
        DisjointSet disjointSet = new DisjointSet(totalVertex);


        while (acceptedEdges < stopCriteria && edgeIndex < edgeList.size()) {
            Edge nextEdge = edgeList.get(edgeIndex++);

            int rootVertex1 = disjointSet.find(nextEdge.getWaterPointX());
            int rootVertex2 = disjointSet.find(nextEdge.getWaterPointY());
            if (rootVertex1 != rootVertex2) {
                minimumSpanningTree.add(nextEdge);
                disjointSet.union(nextEdge.getWaterPointX(), nextEdge.getWaterPointY());
                acceptedEdges++;
            }
        }
        return minimumSpanningTree;
    }


    public static int countUniqueVertices(List<Edge> edgeList) {
        List<Integer> uniqueVertices = new ArrayList<>();

        for (Edge route : edgeList) {
            if (!uniqueVertices.contains(route.getWaterPointX())) {
                uniqueVertices.add(route.getWaterPointX());
            }
            if (!uniqueVertices.contains(route.getWaterPointY())) {
                uniqueVertices.add(route.getWaterPointY());
            }
        }
        return uniqueVertices.size();
    }

    public static void printCost(List<Edge> minimumSpanningTree) {

        double totalCost = 0;

        for (Edge edge : minimumSpanningTree) {
            totalCost += edge.getDistance();
        }
        System.out.println("The total cost of the Minimum Spanning Tree is: " + totalCost);
    }
}


