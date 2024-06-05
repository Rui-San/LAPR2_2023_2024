package MDISC;

import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.*;

public class US17 {

    private final static File us17_matrix = new File(System.getProperty("user.dir") + "/math_resources/us17_resources/" + "US17_matrix.csv");
    private final static File us17_vertexes = new File(System.getProperty("user.dir") + "/math_resources/us17_resources/" + "US17_points_names.csv");

    private static Map<Vertex, Integer> distance = new HashMap<>();
    private static Map<Vertex, Vertex> previous = new HashMap<>();

    public static void run(){
        // US 17
        Graph us17Graph = Main.readGraph(us17_matrix, us17_vertexes);

        int us17_AP = 0;
        for(int i = 0; i < us17Graph.getVertexes().size(); i++){
            if(us17Graph.getVertexes().get(i).getName().equals("AP")){
                us17_AP = i;
            }
        }

        Main.DijkstraAlgorithm(us17Graph, distance, previous, us17Graph.getVertexes().get(us17_AP));

        for(Vertex vertex : us17Graph.getVertexes()){
            List<Edge> edgesToPrint = new ArrayList<>();
            if(!vertex.equals(us17Graph.getVertexes().get(us17_AP))){
                Vertex selectedVertex = vertex;
                while (!selectedVertex.equals(us17Graph.getVertexes().get(us17_AP))){
                    for(Edge edge : us17Graph.getEdges()){
                        if(edge.getVertexFrom().equals(selectedVertex) && edge.getVertexTo().equals(previous.get(selectedVertex))){
                            edgesToPrint.add(edge);
                            selectedVertex = previous.get(selectedVertex);
                        }else if(edge.getVertexTo().equals(selectedVertex) && edge.getVertexFrom().equals(previous.get(selectedVertex))){
                            edgesToPrint.add(edge);
                            selectedVertex = previous.get(selectedVertex);
                        }
                    }
                }
                Main.generateSvgOutput(edgesToPrint, vertex, "us17");
            }
            edgesToPrint.clear();
        }

        Main.generateCsvOutput(us17Graph, distance, previous, List.of(us17Graph.getVertexes().get(us17_AP)) , "us17_output");

        Scanner read = new Scanner(System.in);
        String answer;
        boolean flag = true;
        do {
            System.out.print("\nDo you want to get the shortest route from a specific vertex ? ");
            answer = read.nextLine();
            if(answer.equals("y") || answer.equals("n")){
                flag = false;
            }
        }while (flag);

        if(answer.equals("y")){
            System.out.println("Graph Vertexes: ");
            final int[] row = {0};
            us17Graph.getVertexes().forEach(vertex -> {
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
                for(Vertex vertex : us17Graph.getVertexes()){
                    if(vertex.equals(chosenVertex))
                        chosenVertex = vertex;
                }
            }while(!us17Graph.getVertexes().contains(chosenVertex));
            System.out.println("Shortest Route from " + chosenVertex + " to the AP: ");
            System.out.println("(" + Main.shortestPathToString(chosenVertex, List.of(us17Graph.getVertexes().get(us17_AP)), distance, previous) + " path cost");
        }

    }

}
