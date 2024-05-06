package pt.ipp.isep.dei.esoft.project.matdisc.MATDISC_point_to_vertex;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * Main class for processing CSV showing the results for US12 and US13.
 */
public class MainUS13 {

    public static final String CSV_DIVISOR = ";";
    public static final int TOTAL_NUMBER_OF_COLUMNS = 3;
    static final String CSS = "graph {padding: 75px;} node {text-alignment: under;} edge {text-size: 15px; text-color: #000000;}";

    /**
     * Main method
     *
     */
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
            GraphPV graph = readCsvFile(fileName);

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

                    exportDataToCsv(minimalSpanningTree, fileName, totalCost);

                    System.out.println();
                    System.out.println(fileInfo);
                    String csvName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);

                    org.graphstream.graph.Graph g = highlightGraph(drawGraph( (ArrayList<Edge>)graph.getEdges(), "Minimum Spanning Tree"), (ArrayList<Edge>)minimalSpanningTree);
                    addTextToGraph(g, fileName, totalCost);
                    generateGraphViz( graph.getEdges(), minimalSpanningTree, csvName, totalCost);
                }
            } catch (NumberFormatException e) {
                System.out.println("The file must not be empty");
            }

        } else {
            System.out.println("Nenhum arquivo foi selecionado.");
        }
    }

    public static org.graphstream.graph.Graph drawGraph(ArrayList<Edge> edges, String graphTitle){
        System.setProperty("org.graphstream.ui", "swing");
        org.graphstream.graph.Graph graph = new SingleGraph(graphTitle);
        graph.setAttribute("ui.stylesheet", CSS);
        graph.setStrict(false);
        graph.setAutoCreate( true );
        for(Edge edge : edges){
            graph.addEdge((edge.getSource()+edge.getDestination()),edge.getSource(),edge.getDestination()).setAttribute("ui.label", edge.getDistance());
        }
        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
            node.setAttribute("ui.style", "size: 14px;");
            node.setAttribute("ui.style", "text-size: 20px;");
        }
        graph.setAttribute("ui.title", "Minimum Spanning Tree");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        return graph;
    }

    public static org.graphstream.graph.Graph highlightGraph(Graph g, ArrayList<Edge> result){
        for(Edge edge : result){
            g.getEdge((edge.getSource()+edge.getDestination())).setAttribute("ui.style", "fill-color: red; " +
                    "size: 5px;");
        }
        return g;
    }

    public static void addTextToGraph(Graph graph, String csvName, double cost) {

        // Adiciona o texto à visualização do grafo
        Viewer viewer = graph.display();
        ViewerPipe pipe = viewer.newViewerPipe();

        // Adiciona o texto na parte superior da visualização
        JPanel panel = (JPanel) viewer.getDefaultView();
        JLabel title = new JLabel(csvName);
        JLabel label = new JLabel("Total cost: " + cost);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(title, BorderLayout.NORTH);
        panel.add(label, BorderLayout.WEST);

        // Atualiza a visualização para exibir o texto adicionado
        panel.revalidate();
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
    public static GraphPV readCsvFile(String fileName) {
        GraphPV graph = new GraphPV();
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

            String sourceDest = edge.getSource() + " ---- " + edge.getDestination();
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
     * Cria uma representação visual do grafo com o GraphViz. O grafo é exportado para um ficheiro .CSV
     * @param initialGraphEdges O grafo inicial com todos os vertices e arestas
     * @param minimalSpanningTreeEdges O grafo com a árvore geradora mínima
     * @param title o título do grafo
     * @param cost o custo total da árvore geradora mínima
     */
    public static void generateGraphViz(List<Edge> initialGraphEdges, List<Edge> minimalSpanningTreeEdges, String title, double cost) {
        try {
            String directoryPath = "MATDISC_graph_images";
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(directoryPath + "/graph.dot");

            writer.write("graph {\n");
            writer.write("labelloc=\"t\";\n"); // Position the label at the top
            writer.write("label=\"" + title + " (total cost:" + cost + ")\";\n"); // Set the label
            writer.write("fontsize=25;\n"); // Set the font size of the title
            writer.write("fontweight=bold;\n"); // Set the font weight of the title

            for (Edge edge : initialGraphEdges) {
                String color = minimalSpanningTreeEdges.contains(edge) ? "red" : "black";
                String penwidth = minimalSpanningTreeEdges.contains(edge) ? "4.0" : "1.0";
                writer.write("    " + edge.getSource() + " -- " + edge.getDestination() + " [label=\"" + edge.getDistance() + "\", color=\"" + color + "\", len=2, penwidth=" + penwidth + "];\n");
            }

            writer.write("}\n");
            writer.close();
            title = title.substring(0, title.length() - 4);
            try {
                // Use neato layout engine
                Runtime.getRuntime().exec("neato -Tsvg " + directoryPath + "/graph.dot -o " + directoryPath + "/" + title + "_MST.svg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void exportDataToCsv(List<Edge> minimalSpanningTree, String fileName, double totalCost) {
        String csvName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
        String csvNameOriginal = csvName;

        if (csvName.toLowerCase().endsWith(".csv")) {
            csvName = csvName.substring(0, csvName.length() - 4);
        }

        String currentDirectory = System.getProperty("user.dir");
        String directory = currentDirectory + File.separator + "MATDISC_graph_images";
        String fileN = directory + File.separator + csvName + "_MST.csv";

        try (PrintWriter writer = new PrintWriter(fileN)) {

            writer.println("ORIGINAL FILE: " + csvNameOriginal);
            writer.println();
            writer.println("MINIMAL SPANNING TREE:");
            writer.println();
            writer.println("VERTEX1" + CSV_DIVISOR + "VERTEX2" + CSV_DIVISOR + "COST");

            for (Edge edge : minimalSpanningTree) {
                writer.println(edge.getSource() + CSV_DIVISOR + edge.getDestination() + CSV_DIVISOR + edge.getDistance());
            }
            writer.println();
            writer.println("Total cost of the Minimal Spanning Tree: " + totalCost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
