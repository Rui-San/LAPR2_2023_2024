package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class VfmUI implements Runnable {

    public VfmUI() {

    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register new Vehicle", new RegisterVehicleUI()));
        options.add(new MenuItem("Register new Vehicle Check-up", new RegisterCheckupUI()));
        options.add(new MenuItem("List Vehicles needing check-up", new ListVehiclesNeedingCheckupUI()));

        int option = 0;

        do {
            option = Utils.showAndSelectIndex(options, "\n\n=== VEHICLE FLEET MANAGER MENU ===");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }

        } while (option != -1);
    }
}
