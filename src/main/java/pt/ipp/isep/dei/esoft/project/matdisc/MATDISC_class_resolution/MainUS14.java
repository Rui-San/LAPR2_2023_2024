package pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_class_resolution;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainUS14 {
    public static final String CSV_DIVISOR = ";";
    public static final int TOTAL_NUMBER_OF_COLUMNS = 3;
    public static List<FileInfo> FILE_INFO_LIST = new ArrayList<>();

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        String currentDirectory = System.getProperty("user.dir");

        File directory = new File(currentDirectory + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "pt" + File.separator + "ipp" + File.separator + "isep" + File.separator + "dei" + File.separator + "esoft" + File.separator + "project" + File.separator + "matdisc" + File.separator + "_US14_DataSet");
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
            GraphUS graph = readCsvFile(csvFile.getAbsolutePath());

            if (graph != null && graph.getEdges() != null) {
                int totalLines = graph.getEdges().size();
                List<Edge> minimalSpanningTree = graph.getMinimalSpanningTree();
                long endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;
                int totalNumberOfVertices = graph.getTotalNumberOfVertices();
                String fileName = csvFile.getName();
                double totalCost = obtainTotalCost(minimalSpanningTree);

                exportDataToCsv(minimalSpanningTree, fileName, totalCost);

                FileInfo fileInfo = new FileInfo(csvFile.getName(), totalLines, executionTime, totalCost, totalNumberOfVertices);
                FILE_INFO_LIST.add(fileInfo);
                System.out.println(fileInfo);
                System.out.println();
            }

        }
        SwingUtilities.invokeLater(() -> {
            showExecutionTimeGraph(FILE_INFO_LIST);
        });


    }

    /**
     * Reads a CSV file and constructs a graph based on its contents.
     * This method have a lot of error preventions such as: only allows .csv files; files that really exist and the
     * content of the file must have only 3 columns (one for WaterPointX, one for WaterPointY and one for Distance)
     * otherwise an error message will appear indicating which error has been found.
     *
     * @param fileName the name of the CSV file to read
     * @return the constructed graph
     */
    public static GraphUS readCsvFile(String fileName) {
        GraphUS graph = new GraphUS();
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

    /**
     * Checks if a file is valid for processing.
     *
     * @param fileName the file to check
     * @return true if the file is valid, false otherwise
     */
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
     *
     *
     * @param minimalSpanningTree
     * @return
     */
    public static double obtainTotalCost(List<Edge> minimalSpanningTree) {
        double totalCost = 0;
        for (Edge edge : minimalSpanningTree) {
            totalCost += edge.getDistance();
        }
        return totalCost;
    }


    public static void showExecutionTimeGraph(List<FileInfo> fileInfoList) {

        XYDataset dataset = createDataset(fileInfoList);

        JFreeChart chart = ChartFactory.createScatterPlot(
                "Execution Time vs Graph Dimension", // Chart title
                "Graph Dimension", // X-Axis label
                "Execution Time (ms)", // Y-Axis label
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        JFrame frame = new JFrame("Execution Time Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Creates a dataset for the execution time graph.
     *
     * @param fileInfoList list of FileInfo objects containing execution time data
     * @return the dataset for the execution time graph
     */
    private static XYDataset createDataset(List<FileInfo> fileInfoList) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series = new XYSeries("Execution Time vs Graph Dimension");

        for (FileInfo fileInfo : fileInfoList) {
            series.add(fileInfo.getTotalLines(), fileInfo.getExecutionTime());
        }

        dataset.addSeries(series);

        return dataset;
    }

    private static void exportDataToCsv(List<Edge> minimalSpanningTree, String fileName, double totalCost) {
        String csvName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
        String csvNameOriginal = csvName;

        if (csvName.toLowerCase().endsWith(".csv")) {
            csvName = csvName.substring(0, csvName.length() - 4); // Remove a extensão .csv
        }


        String currentDirectory = System.getProperty("user.dir");
        String directory = currentDirectory + File.separator + "MATDISC_graph_images";
        String fileN = directory + File.separator + csvName + "_MST.csv";

        try (PrintWriter writer = new PrintWriter(fileN)) {

            writer.println("ORIGINAL FILE: " + csvName);
            writer.println();
            writer.println("MINIMAL SPANNING TREE:");
            writer.println("VERTEX1" + CSV_DIVISOR + "VERTEX2" + CSV_DIVISOR + "COST");
            writer.println();

            for (Edge edge : minimalSpanningTree) {
                writer.println(edge.getSource() + CSV_DIVISOR + edge.getDestination() + CSV_DIVISOR + edge.getDistance());
            }
            writer.println();
            writer.println("Total cost: " + totalCost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
