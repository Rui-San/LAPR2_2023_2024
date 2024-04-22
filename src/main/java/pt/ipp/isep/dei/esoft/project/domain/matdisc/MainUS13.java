package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainUS13 {
    public static final String CSV_DIVISOR = ";";
    public static final int TOTAL_NUMBER_OF_COLUMNS = 3;
    public static List<FileInfo> FILE_INFO_LIST = new ArrayList<>();

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);


        long startTime, endTime;


        System.out.println("What is the path of the file?");
        String fileName = read.nextLine();

        startTime = System.currentTimeMillis();
        Graph graph = readCsvFile(fileName);

        try {
            if (graph != null && graph.getEdges() != null) {
                int totalLines = graph.getEdges().size();
                List<Edge> minimalSpanningTree = graph.calculateMST();
                printMST(minimalSpanningTree);
                double totalCost= obtainTotalCost(minimalSpanningTree);

                endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;
                FileInfo fileInfo = new FileInfo(fileName, totalLines, executionTime, totalCost);
                System.out.println();
                System.out.println(fileInfo);
                generateGraphViz(minimalSpanningTree);
            }
        } catch (NumberFormatException e) {
            System.out.println("The file must not be empty");
        }
    }


    public static Graph readCsvFile(String fileName) {
        Graph graph = new Graph();
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

                        graph.addEdge(waterPointX, waterPointY, distance);

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
        return graph;
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

    public static void printMST(List<Edge> minimalSpanningTree) {
        System.out.println();
        for (Edge edge : minimalSpanningTree) {
            System.out.println(edge.getWaterPointX() + " ---- " + edge.getWaterPointY() + " : " + edge.getDistance());
        }
    }

    public static double obtainTotalCost(List<Edge> minimalSpanningTree) {
        double totalCost = 0;
        for (Edge edge : minimalSpanningTree) {
            System.out.println(edge.getWaterPointX() + " ---- " + edge.getWaterPointY() + " : " + edge.getDistance());
            totalCost += edge.getDistance();
        }
        return totalCost;
    }

    public static void generateGraphViz(List<Edge> edges) {
        try {
            FileWriter writer = new FileWriter("graph.dot");

            writer.write("graph {\n");

            for (Edge edge : edges) {
                writer.write("    " + edge.getWaterPointX() + " -- " + edge.getWaterPointY() + " [label=\"" + edge.getDistance() + "\"];\n");
            }

            writer.write("}\n");
            writer.close();
            System.out.println();
            System.out.println("Graphviz DOT file generated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
