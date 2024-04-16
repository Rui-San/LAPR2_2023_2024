package pt.ipp.isep.dei.esoft.project.domain.matdisc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class RouteImporter {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("What is the path of the File?");
        String fileName = ler.nextLine();

        try {
            RouteImporter routeImporter = new RouteImporter(fileName);
            List<Route> routesList = routeImporter.importRoutes();

        } catch (FileNotFoundException e) {
            System.err.println("File not found, please type a valid path");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File fileCsv;

    private static final String DIVISOR_CSV = ";";

    public RouteImporter(String pathName) throws IOException {
        this.fileCsv = new File(pathName);
        validateCsvExtension();

    }

    public File getFileCsv() {
        return fileCsv;
    }

    private void validateCsvExtension() throws IOException {
        if (!getFileCsv().isFile()) {
            throw new IOException("Invalid File: " + getFileCsv().getAbsolutePath());
        }

        String fileName = getFileCsv().getName();
        if (!fileName.toLowerCase().endsWith(".csv")) {
            throw new IOException("Invalid file type. Only .csv files are allowed!");
        }

        showImportedData(importRoutes());
    }

    public List<Route> importRoutes() throws FileNotFoundException {

        List<Route> routesList;
        routesList = new ArrayList<>();

        String linha;
        String[] dados;

        Scanner in = new Scanner(getFileCsv());

        while (in.hasNextLine()) {
            linha = in.nextLine().trim();
            dados = linha.split(DIVISOR_CSV);

            int waterPointX = Integer.parseInt(dados[0]);
            int waterPointY = Integer.parseInt(dados[1]);
            double distance = Double.parseDouble(dados[2]);

            Route route = new Route(waterPointX, waterPointY, distance);

            routesList.add(route);

        }
        in.close();

        return routesList;
    }

    public void showImportedData(List<Route> routesList) {
        for (Route route : routesList) {
            System.out.println(route);
        }
    }


}



