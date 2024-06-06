package MDISC;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class US18 {

    private final static File us18_matrix = new File(System.getProperty("user.dir") + "/math_resources/us18_resources/" + "US18_matrix.csv");
    private final static File us18_vertexes = new File(System.getProperty("user.dir") + "/math_resources/us18_resources/" + "US18_points_names.csv");

    private static Map<Vertex, Integer> distance = new HashMap<>();
    private static Map<Vertex, Vertex> previous = new HashMap<>();

    private static Map<Vertex, Integer> distanceDefinitive = new HashMap<>();
    private static Map<Vertex, Vertex> previousDefinitive = new HashMap<>();

    public static void run(){
        Graph us18Graph = Main.readGraph(us18_matrix, us18_vertexes);
        List<Vertex> us18_APs = new ArrayList<>();
        for(Vertex vertex : us18Graph.getVertexes()){
            Pattern pattern = Pattern.compile("AP[0-9]+");
            if(pattern.matcher(vertex.getName()).matches()){
                us18_APs.add(vertex);
            }
        }

        for(Vertex vertexI : us18_APs){
            Main.DijkstraAlgorithm(us18Graph, distance, previous, vertexI);
            for(Vertex vertexJ : us18Graph.getVertexes()){
                if(distanceDefinitive.get(vertexJ) == null || distanceDefinitive.get(vertexJ) > distance.get(vertexJ)){
                    distanceDefinitive.put(vertexJ, distance.get(vertexJ));
                    previousDefinitive.put(vertexJ, previous.get(vertexJ));
                }
            }
        }

        for(Vertex vertex : us18Graph.getVertexes()){
            List<Edge> edgesToPrint = new ArrayList<>();
            if(!us18_APs.contains(vertex)){
                Vertex selectedVertex = vertex;
                while (!us18_APs.contains(selectedVertex)){
                    for(Edge edge : us18Graph.getEdges()){
                        if(edge.getVertexFrom().equals(selectedVertex) && edge.getVertexTo().equals(previousDefinitive.get(selectedVertex))){
                            edgesToPrint.add(edge);
                            selectedVertex = previousDefinitive.get(selectedVertex);
                        }else if(edge.getVertexTo().equals(selectedVertex) && edge.getVertexFrom().equals(previousDefinitive.get(selectedVertex))){
                            edgesToPrint.add(edge);
                            selectedVertex = previousDefinitive.get(selectedVertex);
                        }
                    }
                }
                Main.generateSvgOutput(edgesToPrint, vertex, "us18");
            }
            edgesToPrint.clear();
        }

        Main.generateCsvOutput(us18Graph, distanceDefinitive, previousDefinitive, us18_APs, "us18_output");

        Scanner read = new Scanner(System.in);
        String answer;
        boolean flag = true;
        do {
            System.out.print("\nUS18-\nDo you want to get the shortest route from a specific vertex?(y/n) ");
            answer = read.nextLine();
            if(answer.equals("y") || answer.equals("n")){
                flag = false;
            }
        }while (flag);

        if(answer.equals("y")){
            System.out.println("Graph Vertexes: ");
            final int[] row = {0};
            us18Graph.getVertexes().forEach(vertex -> {
                if(row[0] != 6){
                    System.out.print(vertex.toString() + " , ");
                    row[0] += 1;
                }else {
                    System.out.println();
                    row[0] = 0;
                }
            });
            System.out.print("\n\nType the name of the vertex: ");
            Vertex chosenVertex;
            do {
                chosenVertex = new Vertex(read.nextLine());
                for(Vertex vertex : us18Graph.getVertexes()){
                    if(vertex.equals(chosenVertex))
                        chosenVertex = vertex;
                }
            }while(!us18Graph.getVertexes().contains(chosenVertex));
            System.out.println("Shortest Route from " + chosenVertex + " to the AP: ");
            System.out.println("(" + Main.shortestPathToString(chosenVertex, us18_APs, distance, previous) + " path cost");
        }

    }

}
