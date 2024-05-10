package pt.ipp.isep.dei.esoft.project.domain;

public class VehicleNeedingCheckup {

    /**
     * The vehicle that needs checkup.
     */
    private Vehicle vehicle;

    /**
     * The last checkup km.
     */
    private int lastCheckupKm;

    /**
     * The optimal Km for the next checkup.
     */
    private int optimalNextCheckupKm;

    /**
     * Gets the vehicle needing checkup.
     * @return vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Gets the last checkup km.
     * @return lastCheckupKm
     */
    public int getLastCheckupKm() {
        return lastCheckupKm;
    }

    /**
     * Gets the optimal next checkup km.
     * @return optimalNextCheckupKm
     */
    public int getOptimalNextCheckupKm() {
        return optimalNextCheckupKm;
    }

    /**
     * Sets the vehicle needing checkup.
     * @param vehicle vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Sets the last checkup km.
     * @param lastCheckupKm lastCheckupKm
     */
    public void setLastCheckupKm(int lastCheckupKm) {
        this.lastCheckupKm = lastCheckupKm;
    }

    /**
     * Sets the optimal next checkup km.
     * @param optimalNextCheckupKm
     */
    public void setOptimalNextCheckupKm(int optimalNextCheckupKm) {
        this.optimalNextCheckupKm = optimalNextCheckupKm;
    }

    /**
     * Instantiates a new Vehicle needing checkup.
     * @param vehicle vehicle
     * @param lastCheckupKm lastCheckupKm
     * @param optimalNextCheckupKm optimalNextCheckupKm
     */
    public VehicleNeedingCheckup(Vehicle vehicle, int lastCheckupKm, int optimalNextCheckupKm){
        setVehicle(vehicle);
        setLastCheckupKm(lastCheckupKm);
        setOptimalNextCheckupKm(optimalNextCheckupKm);
    }

    /**
     * Returns a string representation of the vehicle needing checkup for the UI.
     * @return string representation of the vehicle needing checkup
     */
    @Override
    public String toString(){
        return "- " + getVehicle().getPlateId() + ": " + getVehicle().getBrand() + " " + getVehicle().getModel() + " | Current km: " + getVehicle().getCurrentKm() + "km\n   (Last Checkup Km: " + getLastCheckupKm()+ "km | Checkup Frequency: every " + getVehicle().getCheckupFrequencyKms() + "km | Optimal next Checkup Km: " + getOptimalNextCheckupKm() + "km )" ;
    }

}
