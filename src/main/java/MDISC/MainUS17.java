package MDISC;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class MainUS17 {

    private static final String CSV_SEPARATOR = ";";
    private static File selectedFile;
    private static Graph graph;
    private static Map<String, Integer> distance = new HashMap<>();
    private static Map<String, String> previous = new HashMap<>();
    private static String assemblyPoint;

    public static void main(String[] args) {

        try{
            selectedFile = selectCSV();
        }catch (Exception e){
            System.out.println("No file selected");
            return;
        }

        try {
            graph = readGraphFromCSV(selectedFile);
        }catch (Exception e){
            System.out.println("Invalid file");
            return;
        }

        if (graph == null){
            System.out.println("Invalid file");
            return;
        }

        displayVertices(graph);

        getAssemblyPoint(graph);

        DijkstraAlgorithm(graph, assemblyPoint);

        pathsToCSV();

    }

    private static void pathsToCSV() {

    }

    private static void getAssemblyPoint(Graph graph) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.printf("%nWhat is the assembly point ? ");
            assemblyPoint = scanner.nextLine();
        }while (!graph.getVertexes().contains(assemblyPoint));
    }

    private static void displayVertices(Graph graph) {
        System.out.println("Vertices:");
        for (String vertex : graph.getVertexes()) {
            System.out.println(vertex);
        }
    }

    public static File selectCSV(){
        JFileChooser fileChooser = new JFileChooser();
        String userHome = System.getProperty("user.home");
        fileChooser.setCurrentDirectory(new File(userHome + File.separator + "Desktop"));
        int result = fileChooser.showOpenDialog(null);

        if(result== JFileChooser.APPROVE_OPTION) {
            System.out.println("Selected File: " + fileChooser.getSelectedFile().getName());
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public static Graph readGraphFromCSV(File selectedFile){
        try {
            BufferedReader br = new BufferedReader(new FileReader(selectedFile));
            String line;
            Graph graphFromCSV = new Graph();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(CSV_SEPARATOR);
                Edge edge = new Edge(values[0], values[1], Integer.parseInt(values[2]));
                graphFromCSV.addEdge(edge);
            }
            return graphFromCSV;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void DijkstraAlgorithm(Graph graph, String source) {

        Set<String> visited = new HashSet<>();

        // Initialize distances and predecessors
        for (String vertex : graph.getVertexes()) {
            distance.put(vertex, Integer.MAX_VALUE);
            previous.put(vertex, null);
        }
        distance.put(source, 0);

        String nextVertex = source;
        while (!visited.equals(new HashSet<>(graph.getVertexes())) && distance.get(nextVertex) != Integer.MAX_VALUE) {
            // Find the unvisited vertex with the smallest distance
            nextVertex = null;
            for (String vertex : distance.keySet()) {
                if (!visited.contains(vertex) && (nextVertex == null || distance.get(vertex) < distance.get(nextVertex))) {
                    nextVertex = vertex;
                }
            }

            // Update distances to neighboring vertices
            for (Edge edge : graph.getEdgesFromVertex(nextVertex)) {
                String neighbor = edge.getVertexFrom().equals(nextVertex) ? edge.getVertexTo() : edge.getVertexFrom();
                if (!visited.contains(neighbor)) {
                    int newDistance = distance.get(nextVertex) + edge.getWeight();
                    if (newDistance < distance.get(neighbor)) {
                        distance.put(neighbor, newDistance);
                        previous.put(neighbor, nextVertex);
                    }
                }
            }

            visited.add(nextVertex);
        }

    }


}
