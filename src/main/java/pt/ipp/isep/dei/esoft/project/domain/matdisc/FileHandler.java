package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private File file;

    private static final String CSV_DIVISOR = ";";
    private static final int TOTAL_NUMBER_OF_COLUMNS = 3;

    public FileHandler(String filePath) {
        this.file = new File(filePath);
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "File=" + file +
                '.';
    }

    public List<Edge> importFromFile() {
        List<Edge> edgesList = new ArrayList<>();

        if (!isValidFile()) {
            try (Scanner in = new Scanner(getFile())) {
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    String[] parts = line.split(CSV_DIVISOR);

                    if (parts.length == TOTAL_NUMBER_OF_COLUMNS) {
                        try {
                            int waterPointX = Integer.parseInt(parts[0].trim());
                            int waterPointY = Integer.parseInt(parts[1].trim());
                            double distance = Double.parseDouble(parts[2].trim());

                            Edge edge = new Edge(waterPointX, waterPointY, distance);
                            edgesList.add(edge);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Unable to parse values in line: " + line);
                        }
                    } else {
                        throw new FileNotFoundException("Número de colunas tem que ser exatamente 3");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: File Not Found.");
            }
        }
        return edgesList;
    }

    private boolean isValidFile() {

        if (!getFile().getName().endsWith(".csv")) {
            System.out.println("Error: File is not a CSV file.");
            return false;
        }

        if (!getFile().isFile() || !getFile().canRead()) {
            System.out.println("Error: File does not exist or cannot be read.");
            return false;
        }
        return true;
    }
}





