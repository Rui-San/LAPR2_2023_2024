package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainUS14 {
    public static final String CSV_DIVISOR = ";";
    public static final int TOTAL_NUMBER_OF_COLUMNS = 3;
    public static List<FileInfo> FILE_INFO_LIST = new ArrayList<>();

    public static void main(String[] args) {

        String currentDirectory = System.getProperty("user.dir");
        File directory = new File(currentDirectory + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "pt" + File.separator + "ipp" + File.separator + "isep" + File.separator + "dei" + File.separator + "esoft" + File.separator + "project" + File.separator + "domain" + File.separator + "matdisc" + File.separator + "US14_DataSet");
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Directory 'US14_DataSet' not found.");
            return;
        }
        File[] csvFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
        if (csvFiles == null || csvFiles.length == 0) {
            System.out.println("No CSV files found in the specified directory.");
            return;
        }

        for (File csvFile : csvFiles) {
            long startTime = System.currentTimeMillis();
            Graph graph = readCsvFile(csvFile.getAbsolutePath());

            if (graph != null && graph.getEdges() != null) {
                int totalLines = graph.getEdges().size();
                graph.calculateMST();
                long endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;

                FileInfo fileInfo = new FileInfo(csvFile.getName(), totalLines, executionTime);
                System.out.println();
                System.out.println(fileInfo);
            }
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




}
