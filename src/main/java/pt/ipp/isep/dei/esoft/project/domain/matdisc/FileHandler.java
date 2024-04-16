package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import pt.ipp.isep.dei.esoft.project.domain.matdisc.Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private File file;

    public FileHandler(String filePath) {
        this.file = new File(filePath);
    }

    public File getFile() {
        return file;
    }

    public List<Edge> importFromFile() {
        List<Edge> edgesList = new ArrayList<>();

        if (getFile().isFile() && getFile().canRead()) {
            try (Scanner in = new Scanner(getFile())) {
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    String[] parts = line.split(";");

                    int waterPointX = Integer.parseInt(parts[0].trim());
                    int waterPointY = Integer.parseInt(parts[1].trim());
                    double distance = Double.parseDouble(parts[2].trim());

                    Edge edge = new Edge(waterPointX, waterPointY, distance);
                    edgesList.add(edge);
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error: File Not Found.");
            }
        } else {
            System.err.println("Error: File does not exist or cannot be read.");
        }
        return edgesList;
    }

}



