package pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_union_find_recursive;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainUS13 {
    public static final String CSV_DIVISOR = ";";
    public static final int TOTAL_NUMBER_OF_COLUMNS = 3;

    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser();

        String userHome = System.getProperty("user.home");
        fileChooser.setCurrentDirectory(new File(userHome + File.separator + "Desktop"));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();
            System.out.println("Selected File: " + selectedFile.getName());

            long startTime, endTime;

            startTime = System.currentTimeMillis();
            Graph graph = readCsvFile(fileName);

            try {
                if (graph != null && graph.getEdges() != null) {
                    int totalLines = graph.getEdges().size();
                    List<Edge> minimalSpanningTree = graph.calculateMST();
                    printMinimalSpanningTree(minimalSpanningTree);
                    double totalCost = obtainTotalCost(minimalSpanningTree);
                    endTime = System.currentTimeMillis();
                    long executionTime = endTime - startTime;
                    int numberOfVertices = graph.getTotalNumberOfVertices();
                    FileInfo fileInfo = new FileInfo(fileName, totalLines, executionTime, totalCost, numberOfVertices);
                    System.out.println();
                    System.out.println(fileInfo);
                    generateGraphViz(minimalSpanningTree);

                }
            } catch (NumberFormatException e) {
                System.out.println("The file must not be empty");
            }
        } else {
            System.out.println("Nenhum arquivo foi selecionado.");
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

    /**
     * Prints the minimal spanning tree to the console with a specific and organized look.
     *
     * @param minimalSpanningTree the minimal spanning tree to print
     */
    public static void printMinimalSpanningTree(List<Edge> minimalSpanningTree) {
        System.out.println();
        String title = "Minimal Spanning Tree";
        int leftPaddingTitle = (65 - title.length()) / 2;
        int rightPaddingTitle = 65 - title.length() - leftPaddingTitle;
        System.out.printf("%" + leftPaddingTitle + "s%s%" + rightPaddingTitle + "s%n", "", title, "");
        System.out.println("-----------------------------------------------------------------");


        String edgesTitle = "Edges";
        String distanceTitle = "Distance";
        int leftPaddingEdgesTitle = (49 - edgesTitle.length()) / 2;
        int rightPaddingEdgesTitle = 49 - edgesTitle.length() - leftPaddingEdgesTitle;
        int leftPaddingDistanceTitle = (19 - distanceTitle.length()) / 2;
        int rightPaddingDistanceTitle = 19 - distanceTitle.length() - leftPaddingDistanceTitle;
        System.out.printf("%" + leftPaddingEdgesTitle + "s%s%" + rightPaddingEdgesTitle + "s%" + leftPaddingDistanceTitle + "s%s%" + rightPaddingDistanceTitle + "s%n", "", edgesTitle, "", "", distanceTitle, "");
        System.out.println("-----------------------------------------------------------------");


        for (Edge edge : minimalSpanningTree) {

            String sourceDest = edge.getWaterPointX() + " ---- " + edge.getWaterPointY();
            int leftPaddingSourceDest = (49 - sourceDest.length()) / 2;
            int rightPaddingSourceDest = 49 - sourceDest.length() - leftPaddingSourceDest;
            System.out.printf("%" + leftPaddingSourceDest + "s%s%" + rightPaddingSourceDest + "s", "", sourceDest, "");

            String distance = String.format("%.2f", edge.getDistance());
            int leftPaddingDistance = (19 - distance.length()) / 2;
            int rightPaddingDistance = 19 - distance.length() - leftPaddingDistance;
            System.out.printf("%" + leftPaddingDistance + "s%s%" + rightPaddingDistance + "s%n", "", distance, "");
        }
    }


    /**
     * Calculates the total cost of the minimal spanning tree.
     *
     * @param minimalSpanningTree the minimal spanning tree
     * @return the total cost of the minimal spanning tree
     */
    public static double obtainTotalCost(List<Edge> minimalSpanningTree) {
        double totalCost = 0;
        for (Edge edge : minimalSpanningTree) {
            totalCost += edge.getDistance();
        }
        return totalCost;
    }

    /**
     * Generates a GraphViz representation of the minimal spanning tree.
     * Creates a graph.dot file which will be converted into a GraphViz representation using the graphPngGenerator.bat.
     * All the images produced using this program will be stored in "MATDISC_graph_images".
     *
     * @param edges the edges of the minimal spanning tree
     */
    public static void generateGraphViz(List<Edge> edges) {
        try {
            String directoryPath = "MATDISC_graph_images";
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(directoryPath + "/graph.dot");

            writer.write("graph {\n");

            for (Edge edge : edges) {
                writer.write("    " + edge.getWaterPointX() + " -- " + edge.getWaterPointY() + " [label=\"" + edge.getDistance() + "\"];\n");
            }

            writer.write("}\n");
            writer.close();


            try {
                Runtime.getRuntime().exec("graphPngGenerator.bat");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
