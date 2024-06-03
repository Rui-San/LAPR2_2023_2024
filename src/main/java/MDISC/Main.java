package MDISC;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    private static final String CSV_SEPARATOR = ";";
    static final String CSS = "graph {padding: 75px;} node {text-alignment: under;} edge {text-size: 15px; text-color: #000000;}";

    private final static File us17_matrix = new File(System.getProperty("user.dir") + "/math_resources/us17_resources/" + "US17_matrix.csv");
    private final static File us17_vertexes = new File(System.getProperty("user.dir") + "/math_resources/us17_resources/" + "US17_points_names.csv");

    private final static File us18_matrix = new File(System.getProperty("user.dir") + "/math_resources/us18_resources/" + "US18_matrix.csv");
    private final static File us18_vertexes = new File(System.getProperty("user.dir") + "/math_resources/us18_resources/" + "US18_points_names.csv");

    private static Map<Vertex, Integer> distance = new HashMap<>();
    private static Map<Vertex, Vertex> previous = new HashMap<>();

    public static void main(String[] args) {

        // US 17
        Graph us17Graph = readGraph(us17_matrix, us17_vertexes);

        int us17_AP = 0;
        for(int i = 0; i < us17Graph.getVertexes().size(); i++){
            if(us17Graph.getVertexes().get(i).getName().equals("AP")){
                us17_AP = i;
            }
        }
        DijkstraAlgorithm(us17Graph, us17Graph.getVertexes().get(us17_AP));

        Graph us17_minimalGraph = createMinimalGraph(distance, previous, us17Graph);

        generateSvgOutput(us17Graph.getEdges(), us17_minimalGraph.getEdges(), "us17_output");
        generateCsvOutput(us17Graph, distance, previous, us17Graph.getVertexes().get(us17_AP), "us17_output");
        // ----------

        // US 18
        previous.clear();
        distance.clear();

        Map<Vertex, Integer> distanceDefinitive = new HashMap<>();
        Map<Vertex, Vertex> previousDefinitive = new HashMap<>();

        Graph us18Graph = readGraph(us18_matrix, us18_vertexes);
        List<Vertex> us18_APs = new ArrayList<>();
        for(Vertex vertex : us18Graph.getVertexes()){
            Pattern pattern = Pattern.compile("AP[0-9]+");
            if(pattern.matcher(vertex.getName()).matches()){
                us18_APs.add(vertex);
            }
        }

        for(Vertex vertexI : us18_APs){
            DijkstraAlgorithm(us18Graph, vertexI);
            for(Vertex vertexJ : us18Graph.getVertexes()){
                if(distanceDefinitive.get(vertexJ) == null || distanceDefinitive.get(vertexJ) > distance.get(vertexJ)){
                    distanceDefinitive.put(vertexJ, distance.get(vertexJ));
                    previousDefinitive.put(vertexJ, previous.get(vertexJ));
                }
            }
        }

        Graph us18_minimalGraph = createMinimalGraph(distanceDefinitive, previousDefinitive, us18Graph);
        generateSvgOutput(us18Graph.getEdges(), us18_minimalGraph.getEdges(), "us18_output");
        //generateCsvOutput(us18Graph, distanceDefinitive, previousDefinitive, us17Graph.getVertexes().get(us17_AP), "us18_output");

        // ----------
    }

    public static void DijkstraAlgorithm(Graph graph, Vertex source) {

        List<Vertex> visited = new ArrayList<>();

        for (Vertex vertex : graph.getVertexes()) {
            distance.put(vertex, Integer.MAX_VALUE);
            previous.put(vertex, null);
        }
        distance.put(source, 0);
        previous.put(source, source);

        Vertex visiting = source;

        while(visited.size() != graph.getVertexes().size()){
            List<Edge> adjacentEdges = graph.getEdgesFromVertex(visiting);
            for(Edge adjacentEdge : adjacentEdges){
                Vertex neighbor;
                if(adjacentEdge.getVertexFrom().getName().equals(visiting.getName())){
                    neighbor = adjacentEdge.getVertexTo();
                } else {
                    neighbor = adjacentEdge.getVertexFrom();
                }
                if(distance.get(neighbor) > distance.get(visiting) + adjacentEdge.getWeight() && !visited.contains(neighbor) ){
                    distance.put(neighbor, distance.get(visiting) + adjacentEdge.getWeight());
                    previous.put(neighbor, visiting);
                }
            }
            visited.add(visiting);
            visiting = getMinDistanceVertex(graph, visited);
        }
    }

    private static Vertex getMinDistanceVertex(Graph graph, List<Vertex> visited) {
        Vertex nextVertex = null;
        for(Vertex vertex : graph.getVertexes()){
            if(!visited.contains(vertex)){
                if(nextVertex == null){
                    nextVertex = vertex;
                }
                if(distance.get(vertex) < distance.get(nextVertex)){
                    nextVertex = vertex;
                }
            }
        }
        return nextVertex;
    }

    private static Graph readGraph(File matrixFile, File vertexesFile) {

        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(vertexesFile)))) {
            String line = br.readLine();
            if(line != null) {
                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }
                String[] values = line.split(CSV_SEPARATOR);
                for(String vertexName : values){
                    vertices.add(new Vertex(vertexName));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(matrixFile)))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }
                String[] values = line.split(CSV_SEPARATOR);
                for(int j = i; j < values.length; j++){
                    if(!values[j].equals("0")){
                        edges.add(new Edge(vertices.get(i), vertices.get(j), Integer.parseInt(values[j])));
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Graph(vertices, edges);
    }

    private static Graph createMinimalGraph(Map<Vertex, Integer> distance, Map<Vertex, Vertex> previous, Graph graph) {
        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        for(Vertex vertex : graph.getVertexes()){
            vertices.add(vertex);
        }

        for(Vertex vertex : graph.getVertexes()){
            if(previous.get(vertex) != null){
                edges.add(new Edge(vertex, previous.get(vertex), distance.get(vertex)));
            }
        }

        return new Graph(vertices, edges);
    }

    public static void generateSvgOutput(List<Edge> initialGraphEdges, List<Edge> minimalSpanningTreeEdges, String title)                  {
        try {
            String directoryPath = "src/main/java/MDISC/output";
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(directoryPath + "/" + title + "_graph.dot");

            writer.write("graph {\n");
            writer.write("labelloc=\"t\";\n"); // Position the label at the top
            writer.write("label=\"" + title + "\";\n"); // Set the label
            writer.write("fontsize=25;\n"); // Set the font size of the title
            writer.write("fontweight=bold;\n"); // Set the font weight of the title

            for (Edge edge : initialGraphEdges) {
                String color = minimalSpanningTreeEdges.contains(edge) ? "red" : "black";
                String penwidth = minimalSpanningTreeEdges.contains(edge) ? "4.0" : "1.0";
                writer.write("    " + edge.getVertexFrom().getName() + " -- " + edge.getVertexTo().getName() + " [label=\"" + edge.getWeight() + "\", color=\"" + color + "\", len=2, penwidth=" + penwidth + "];\n");
            }

            writer.write("}\n");
            writer.close();
            try {
                // Use neato layout engine
                Runtime.getRuntime().exec("neato -Tsvg " + directoryPath + "/" + title + "_graph.dot -o " + directoryPath + "/" + title + ".svg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateCsvOutput(Graph graph, Map<Vertex, Integer> distance, Map<Vertex, Vertex> previous, Vertex source, String title) {
        File outputFolder = new File(System.getProperty("user.dir") + "/src/main/java/MDISC/output");
        if(!outputFolder.exists()){
            outputFolder.mkdirs();
        }
        try{
            FileWriter writer = new FileWriter(outputFolder + "/" + title + ".csv");
            for(Vertex vertex : graph.getVertexes()){
                if(vertex != source){
                    StringBuilder path = new StringBuilder();
                    path.append("(");
                    Vertex currentVertex = vertex;
                    while(!currentVertex.equals(source)){
                        path.append(currentVertex.getName()).append(";");
                        currentVertex = previous.get(currentVertex);
                    }
                    path.append(source.getName()).append("); ");
                    path.append(distance.get(vertex)).append("\n");
                    writer.write(path.toString());
                }
             }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
