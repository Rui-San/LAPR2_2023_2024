package pt.ipp.isep.dei.esoft.project.domain;


import java.io.Serializable;

/**
 * Represents a Vehicle Checkup, through its Vehicle, checkup date and checkup kilometers.
 */
public class VehicleCheckup implements Serializable {

    /**
     * The vehicle selected to register the checkup.
     */
    private Vehicle vehicle;

    /**
     * The date of the checkup.
     */
    private Date checkupDate;

    /**
     * The kilometers of the vehicle at the time of the checkup.
     */
    private int checkupKms;

    /**
     * Gets the vehicle of a checkup.
     *
     * @return
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Gets the date of a checkup.
     *
     * @return
     */
    public Date getCheckupDate() {
        return checkupDate;
    }

    /**
     * Gets the kilometers of a checkup.
     *
     * @return
     */
    public int getCheckupKms() {
        return checkupKms;
    }

    /**
     * Sets the vehicle of a checkup.
     *
     * @param vehicle the vehicle that was checked
     */
    public void setVehicle(Vehicle vehicle) {
        validateVehicle(vehicle);
        this.vehicle = vehicle;
    }

    public void validateVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle does not exist.");
        }
    }

    /**
     * Sets the date of a checkup.
     *
     * @param checkupDate the date of the checkup
     */
    public void setCheckupDate(Date checkupDate) {
        validateCheckupDate(checkupDate);
        this.checkupDate = checkupDate;
    }

    /**
     * Sets the kilometers of a checkup.
     *
     * @param checkupKms the kilometers at the time of the checkup
     */
    public void setCheckupKms(int checkupKms) {
        validateCheckupKm(this.vehicle, checkupKms);
        this.checkupKms = checkupKms;
    }

    /**
     * Instantiates a new Vehicle Checkup.
     *
     * @param vehicle     the vehicle
     * @param checkupDate the checkup date
     * @param checkupKms  the checkup kilometers
     */
    public VehicleCheckup(Vehicle vehicle, Date checkupDate, int checkupKms) {
        setVehicle(vehicle);
        setCheckupDate(checkupDate);
        setCheckupKms(checkupKms);
    }

    /**
     * Validates the checkup kilometers.
     *
     * @param vehicle    the vehicle
     * @param checkupKms the checkup kilometers
     */
    private void validateCheckupKm(Vehicle vehicle, int checkupKms) {
        if (checkupKms < 0) {
            throw new IllegalArgumentException("Checkup kilometers must be a positive number.");
        } else if (checkupKms > vehicle.getCurrentKm()) {
            throw new IllegalArgumentException("Checkup kilometers must be less than the current kilometers of the vehicle.");
        }
    }

    /**
     * Validates the checkup date.
     *
     * @param checkupDate the checkup date
     */
    private void validateCheckupDate(Date checkupDate) {
        if (checkupDate.compareTo(getVehicle().getRegisterDate()) < 0) {
            throw new IllegalArgumentException("Checkup cannot have been done before the register date.");
        }

        if (!checkupDate.isPastDate() || checkupDate.compareTo(new Date()) == 0) {
            throw new IllegalArgumentException("Check-up cannot be made in the future.");
        }
    }

    /**
     * Returns the textual representation of the Vehicle Checkup. In this case, showing the vehicle, checkup date and checkup kilometers.
     *
     * @return the textual representation of the Vehicle Checkup
     */
    @Override
    public String toString() {
        return "VehicleCheckup{" +
                "vehicle=" + vehicle.getPlateId() +
                ", checkupDate=" + checkupDate +
                ", checkupKms=" + checkupKms +
                '}';
    }

    /**
     * Creates a deep copy of the checkup
     *
     * @return a deep copy of the checkup
     */
    @Override
    public VehicleCheckup clone() {
        return new VehicleCheckup(this.vehicle, this.checkupDate, this.checkupKms);
    }

}
