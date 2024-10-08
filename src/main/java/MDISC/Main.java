package MDISC;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    private static final String CSV_SEPARATOR = ";";
    static final String CSS = "graph {padding: 75px;} node {text-alignment: under;} edge {text-size: 15px; text-color: #000000;}";

    public static void main(String[] args) {

        System.out.println("---- GSM - Emergency Plans ----");
        US17.run();
        US18.run();

    }

    public static void DijkstraAlgorithm(Graph graph,Map<Vertex, Integer> distance, Map<Vertex, Vertex> previous, Vertex source) {
        // Lista de vértices visitados
        List<Vertex> visited = new ArrayList<>();

        // Inicializa as distâncias e os vértices anteriores
        for (Vertex vertex : graph.getVertexes()) {
            distance.put(vertex, Integer.MAX_VALUE); // todas as distâncias são inicializadas com infinito
            previous.put(vertex, null); // todos os vértices anteriores são inicializados com null
        }
        distance.put(source, 0); // a distância do vértice AP começará em 0
        previous.put(source, source); // o vértice anterior do vértice AP será ele mesmo

        Vertex visiting = source; // colocar o vértice AP como o vertice que estamos a visitar

        while(visited.size() != graph.getVertexes().size()){
            // Obter as arestas adjacentes ao vértice que estamos a visitar
            List<Edge> adjacentEdges = graph.getEdgesFromVertex(visiting);
            for(Edge adjacentEdge : adjacentEdges){ // para cada aresta
                Vertex neighbor; // obter o vértice vizinho ao vértice que estamos a visitar
                if(adjacentEdge.getVertexFrom().getName().equals(visiting.getName())){
                    neighbor = adjacentEdge.getVertexTo();
                } else {
                    neighbor = adjacentEdge.getVertexFrom();
                }
                // se a distancia alocada ao vertice vizinho for maior que a distancia da aresta + a distancia do vertice que estamos a visitar
                // alocar a distancia do vertice vizinho como a distancia da aresta + a distancia do vertice que estamos a visitar
                // alocar o vertice anterior do vertice vizinho como o vertice que estamos a visitar
                if(distance.get(neighbor) > distance.get(visiting) + adjacentEdge.getWeight() && !visited.contains(neighbor) ){
                    distance.put(neighbor, distance.get(visiting) + adjacentEdge.getWeight());
                    previous.put(neighbor, visiting);
                }
            }
            // adicionar o vertice que estamos a visitar à lista de visitados
            visited.add(visiting);
            // obter o vertice com a menor distancia que ainda não foi visitado
            visiting = getMinDistanceVertex(graph, distance, visited);
        }
    }

    // Metodo para obter o vertice com a menor distancia que ainda não foi visitado
    public static Vertex getMinDistanceVertex(Graph graph, Map<Vertex, Integer> distance, List<Vertex> visited) {
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

    public static Graph readGraph(File matrixFile, File vertexesFile) {

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

    public static void generateSvgOutput(List<Edge> path, Vertex fromVertex, String usPath){
        try {
            String directoryPath = "src/main/java/MDISC/output";
            File directory = new File(directoryPath);
            File directoryUS = new File(directoryPath + "/" + usPath);

            if (!directory.exists()) {
                directory.mkdir();
            }

            if(!directoryUS.exists()){
                directoryUS.mkdir();
            }

            FileWriter writer = new FileWriter(directoryPath + "/" + usPath + "_graph.dot");

            writer.write("graph {\n");
            writer.write("labelloc=\"t\";\n"); // Position the label at the top
            writer.write("label=\"" + fromVertex + "\";\n"); // Set the label
            writer.write("fontsize=25;\n"); // Set the font size of the title
            writer.write("fontweight=bold;\n"); // Set the font weight of the title

            for (Edge edge : path) {
                String color = "red";
                String penwidth = "2.0";
                writer.write("    " + edge.getVertexFrom().getName() + " -- " + edge.getVertexTo().getName() + " [label=\"" + edge.getWeight() + "\", color=\"" + color + "\", len=2, penwidth=" + penwidth + "];\n");
            }

            writer.write("}\n");
            writer.close();
            try {
                // Use neato layout engine
                Runtime.getRuntime().exec("neato -Tsvg " + directoryPath + "/" + usPath + "_graph.dot -o " + directoryUS + "/" + fromVertex + ".svg").waitFor();
                File dotFile = new File(directoryPath + "/" + usPath + "_graph.dot");
                if(dotFile.exists()){
                    dotFile.deleteOnExit();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateCsvOutput(Graph graph, Map<Vertex, Integer> distance, Map<Vertex, Vertex> previous, List<Vertex> sources, String title) {
        File outputFolder = new File(System.getProperty("user.dir") + "/src/main/java/MDISC/output");
        if(!outputFolder.exists()){
            outputFolder.mkdirs();
        }
        try{
            FileWriter writer = new FileWriter(outputFolder + "/" + title + ".csv");
            for(Vertex vertex : graph.getVertexes()){
                if(!sources.contains(vertex)){
                    StringBuilder path = new StringBuilder();
                    path.append("(");
                    path.append(shortestPathToString(vertex, sources, distance, previous));
                    path.append("\n");
                    writer.write(path.toString());
                }
             }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public static String shortestPathToString(Vertex vertex, List<Vertex> sources, Map<Vertex, Integer> distance , Map<Vertex, Vertex> previous){
        String shortestPath = "";
        Vertex currentVertex = vertex;
        Vertex sourceOfVertex = null;
        while(!sources.contains(currentVertex)){
            shortestPath += currentVertex.getName() + ";";
            currentVertex = previous.get(currentVertex);
            sourceOfVertex = currentVertex;
        }
        shortestPath += sourceOfVertex + "); ";
        shortestPath += distance.get(vertex);
        return shortestPath;
    }

}
