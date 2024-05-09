package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.ListVehiclesNeedingCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;

public class ListVehiclesNeedingCheckupUI implements Runnable{

    private final ListVehiclesNeedingCheckupController controller;

    /**
     * Creates an instance of ListVehiclesNeedingCheckupUI.
     */
    public ListVehiclesNeedingCheckupUI() {
        controller = new ListVehiclesNeedingCheckupController();
    }

    /**
     * Gets the controller.
     * @return controller
     */
    private ListVehiclesNeedingCheckupController getController() {
        return controller;
    }

    /**
     * Method that runs the ListVehiclesNeedingCheckupUI.
     */
    public void run() {
        System.out.println("\n ----------LIST VEHICLES NEEDING CHECKUP ----------------\n");

        showData();

    }

    /**
     * Method that shows the data.
     */
    private void showData() {

        List<Vehicle> vehiclesNeedingCheckupList = getController().getVehiclesNeedingCheckup();

        for (Vehicle vehicle : vehiclesNeedingCheckupList){
            System.out.printf("> " + vehicle.getPlateId() + " (" + vehicle.getBrand() + " " + vehicle.getModel() + ")\n");
        }
    }

}
