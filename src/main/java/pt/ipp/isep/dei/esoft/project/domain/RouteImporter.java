package pt.ipp.isep.dei.esoft.project.domain;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class RouteImporter {

    private File fileCsv;

    private static final String DIVISOR_CSV = ",";

    public RouteImporter(File fileCsv) {
        this.fileCsv = fileCsv;
    }

    public ArrayList<Route> importRoutes() throws FileNotFoundException {

        ArrayList<Route> routesList = new ArrayList<>();
        String linha;
        String[] dados;
        Scanner in = new Scanner(fileCsv);

        while (in.hasNextLine()) {
            linha = in.nextLine();
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


}



