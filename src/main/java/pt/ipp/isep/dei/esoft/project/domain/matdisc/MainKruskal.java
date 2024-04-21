package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
            Graph graph = readCsvFile(fileName);

            try {
                if (graph != null && graph.getEdges() != null) {
                    int totalLines = graph.getEdges().size();
                    List<Edge> minimalSpanningTree = graph.calculateMST();
                    printMSTandCost(minimalSpanningTree);
                    endTime = System.currentTimeMillis();
                    long executionTime = endTime - startTime;
                    FileInfo fileInfo = new FileInfo(fileName, totalLines, executionTime);
                    FILE_INFO_LIST.add(fileInfo);
                }
            } catch (NumberFormatException e) {
                System.out.println("The file must not be empty");
            }

            boolean validAnswer = false;
            while (!validAnswer) {
                System.out.println();
                System.out.println("You wish to insert more csv files?");
                System.out.println("Yes or No");
                String answer = read.nextLine();
                if (answer.equalsIgnoreCase("No")) {
                    continueProgram = false;
                    validAnswer = true;
                } else if (answer.equalsIgnoreCase("Yes")) {
                    validAnswer = true;
                } else {
                    System.out.println("Please, answer Yes or No");
                }

            }
        }
        System.out.println();
        System.out.println("Ficheiros lidos até ao fecho do programa:");

        for (FileInfo fileInfo : FILE_INFO_LIST) {
            System.out.println(fileInfo);
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

    public static void printMSTandCost(List<Edge> minimalSpanningTree) {
        System.out.println();
        double totalCost = 0;
        for (Edge edge : minimalSpanningTree) {
            System.out.println(edge.getWaterPointX() + " ---- " + edge.getWaterPointY() + " : " + edge.getDistance());
            totalCost += edge.getDistance();
        }
        System.out.println("Total cost is: " + totalCost);
    }

}
