package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListVehiclesNeedingCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;

public class ListVehiclesNeedingCheckupUI implements Runnable{

    public static void main(String[] args) {
        ListVehiclesNeedingCheckupUI ui = new ListVehiclesNeedingCheckupUI();
        ui.run();
    }

    private final ListVehiclesNeedingCheckupController controller;
    private List<Vehicle> vehiclesNeedingCheckupList;

    public ListVehiclesNeedingCheckupUI() {
        controller = new ListVehiclesNeedingCheckupController();
    }

    private ListVehiclesNeedingCheckupController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n ----------LIST VEHICLES NEEDING CHECKUP ----------------\n");

        showData();

    }

    private void showData() {

        vehiclesNeedingCheckupList = getController().getVehiclesNeedingCheckup();

        for (Vehicle vehicle : vehiclesNeedingCheckupList){
            System.out.printf("> Plate ID: " + vehicle.getPlateId() + " Brand: " + vehicle.getBrand() + " Model: " + vehicle.getModel() + "\n");
        }
    }

}
