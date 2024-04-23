package pt.ipp.isep.dei.esoft.project.domain.MATDISC_class_resolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        String fileName = ler.nextLine();

        Graph graphOriginal = readCsvFile(fileName);
        List<Edge> minimalSpanningTree = graphOriginal.getMinimalSpanningTree();
        printMSTandCost(minimalSpanningTree);

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
                String[] parts = line.split(";");

                if (parts.length == 3) {
                    try {
                        String vertex1 = parts[0].trim();
                        String vertex2 = parts[1].trim();
                        double distance = Double.parseDouble(parts[2].trim());

                        Edge edge = new Edge(vertex1, vertex2, distance);

                        graph.addEdge(edge, vertex1, vertex2);

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

    public static void printMSTandCost(List<Edge> minimalSpanningTree) {
        System.out.println();
        double totalCost = 0;
        for (Edge edge : minimalSpanningTree) {
            System.out.println(edge.getSource() + " ---- " + edge.getDestination() + " : " + edge.getDistance());
            totalCost += edge.getDistance();
        }
        System.out.println("Total cost is: " + totalCost);
    }
}
