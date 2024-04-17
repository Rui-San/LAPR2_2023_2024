package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MainKruskal {
    public static final String CSV_DIVISOR = ";";
    public static final int TOTAL_NUMBER_OF_COLUMNS = 3;

    public static List<FileInfo> FILE_INFO_LIST = new ArrayList<>();

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);
        boolean continueProgram = true;

        long startTime, endTime;

        while (continueProgram) {
            System.out.println("What is the path of the file?");
            String fileName = read.nextLine();

            startTime = System.currentTimeMillis();
            List<Edge> edgeList = readCsvFile(fileName);
            if (edgeList != null) {
                int totalLines = edgeList.size();
                List<Edge> minimalSpanningTree = calculateKruskalMST(edgeList);
                printMinimalSpanningTree(minimalSpanningTree, fileName);
                endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;
                FileInfo fileInfo = new FileInfo(fileName, totalLines, executionTime);
                FILE_INFO_LIST.add(fileInfo);

                System.out.println();
                System.out.println("You wish to insert more csv files?");
                System.out.println("Yes or No");
                String answer = read.nextLine();
                if (answer.equalsIgnoreCase("No")) {
                    continueProgram = false;
                }
            } else {
                continueProgram = false;
            }
        }
        System.out.println();
        System.out.println("PARA EFEITOS DE TESTE (esta lista de objetos FileInfo será usada para gerar gráfico)");
        System.out.println("Ficheiros lidos até ao fecho do programa:");

        for (FileInfo fileInfo : FILE_INFO_LIST) {
            System.out.println(fileInfo);
        }

    }

    public static List<Edge> readCsvFile(String fileName) {
        List<Edge> edgeList = new ArrayList<>();
        File csv = new File(fileName);

        if (!isValidFile(csv)) {
            return null;
        }

        try (Scanner in = new Scanner(csv)) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] parts = line.split(CSV_DIVISOR);

                if (parts.length == TOTAL_NUMBER_OF_COLUMNS) {
                    try {
                        String waterPointX = parts[0].trim();
                        String waterPointY = parts[1].trim();
                        double distance = Double.parseDouble(parts[2].trim());

                        Edge edge = new Edge(waterPointX, waterPointY, distance);
                        edgeList.add(edge);

                    } catch (NumberFormatException e) {
                        System.out.println("Error: Unable to parse values in line.");
                        return null;
                    }
                } else {
                    System.out.println("Error: The number of Columns of the .csv file must be exactly 3");
                    return null;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File Not Found.");
            return null;
        }
        return edgeList;
    }

    public static boolean isValidFile(File fileName) {

        if (!fileName.getName().endsWith(".csv")) {
            System.out.println("Error: File is not a CSV file.");
            return false;
        }

        if (!fileName.isFile() || !fileName.canRead()) {
            System.out.println("Error: File does not exist or cannot be read.");
            return false;
        }
        return true;
    }

    public static List<Edge> calculateKruskalMST(List<Edge> edgeList) {
        List<Edge> minimalSpanningTree = new ArrayList<>();

        Collections.sort(edgeList);

        int totalVertex = countUniqueVertices(edgeList);
        int stopCriteria = totalVertex - 1;

        int acceptedEdges = 0;
        int edgeIndex = 0;


        UnionFind uf = new UnionFind();


        while (acceptedEdges < stopCriteria && edgeIndex < edgeList.size()) {
            Edge nextEdge = edgeList.get(edgeIndex++);

            if (uf.union(nextEdge.getWaterPointX(), nextEdge.getWaterPointY())) { // Se a união não criar um ciclo
                minimalSpanningTree.add(nextEdge); // Adiciona a aresta à MST
                acceptedEdges++;
            }
        }
        return minimalSpanningTree;
    }

    public static int countUniqueVertices(List<Edge> edgeList) {
        List<String> uniqueVertices = new ArrayList<>();

        for (Edge route : edgeList) {
            String waterPointX = route.getWaterPointX();
            String waterPointY = route.getWaterPointY();

            // Adiciona waterPointX ao Array se ainda não estiver na lista de vértices únicos
            if (!uniqueVertices.contains(waterPointX)) {
                uniqueVertices.add(waterPointX);
            }

            // Adiciona waterPointX ao Array se ainda não estiver na lista de vértices únicos
            if (!uniqueVertices.contains(waterPointY)) {
                uniqueVertices.add(waterPointY);
            }
        }
        return uniqueVertices.size();
    }

    public static void printMinimalSpanningTree(List<Edge> minimalSpanningTree, String fileName) {
        System.out.println();
        System.out.printf("Minimal Spanning Tree of file : %s%n", fileName);
        for (Edge edge : minimalSpanningTree) {
            System.out.println(edge.getWaterPointX() + " ---- " + edge.getWaterPointY() + " : " + edge.getDistance());
        }
        System.out.println("Total cost is: " + obtainTotalCost(minimalSpanningTree));
    }

    public static double obtainTotalCost(List<Edge> minimumSpanningTree) {

        double totalCost = 0;

        for (Edge edge : minimumSpanningTree) {
            totalCost += edge.getDistance();
        }
        return totalCost;
    }
}
