package pt.ipp.isep.dei.esoft.project.domain.MATDISC_class_resolution;

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

        if(result== JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();
            System.out.println("File Selected: " + selectedFile.getName());

            long startTime, endTime;


            startTime = System.currentTimeMillis();
            Graph graph = readCsvFile(fileName);

            try {
                if (graph != null && graph.getEdges() != null) {
                    int totalLines = graph.getEdges().size();
                    List<Edge> minimalSpanningTree = graph.getMinimalSpanningTree();
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

        } else{
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

    public static void printMinimalSpanningTree(List<Edge> minimalSpanningTree) {
        System.out.println();
        for (Edge edge : minimalSpanningTree) {
            System.out.println(edge.getSource() + " ---- " + edge.getDestination() + " : " + edge.getDistance());
        }
    }

    public static double obtainTotalCost(List<Edge> minimalSpanningTree) {
        double totalCost = 0;
        for (Edge edge : minimalSpanningTree) {
            totalCost += edge.getDistance();
        }
        return totalCost;
    }

    public static void generateGraphViz(List<Edge> edges) {
        try {
            FileWriter writer = new FileWriter("graph.dot");

            writer.write("graph {\n");

            for (Edge edge : edges) {
                writer.write("    " + edge.getSource() + " -- " + edge.getDestination() + " [label=\"" + edge.getDistance() + "\"];\n");
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
