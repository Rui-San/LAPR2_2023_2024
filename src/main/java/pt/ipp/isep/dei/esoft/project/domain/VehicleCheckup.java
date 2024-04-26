package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project._templateFiles.domain.TaskCategory;

public class VehicleCheckup {

    private Vehicle vehicle;
    private Date checkupDate;
    private int checkupKms;

    public Vehicle getVehicle() { return vehicle; }

    public Date getCheckupDate() { return checkupDate; }

    public int getCheckupKms() { return checkupKms; }

    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public void setCheckupDate(Date checkupDate) { this.checkupDate = checkupDate; }

    public void setCheckupKms(int checkupKms) { this.checkupKms = checkupKms; }

    public VehicleCheckup(Vehicle vehicle, Date checkupDate, int checkupKms) {
        this.vehicle = vehicle;
        this.checkupDate = checkupDate;
        this.checkupKms = checkupKms;
    }

    @Override
    public String toString() {
        return "VehicleCheckup{" +
                "vehicle=" + vehicle.getPlateId() +
                ", checkupDate=" + checkupDate +
                ", checkupKms=" + checkupKms +
                '}';
    }

    @Override
    public VehicleCheckup clone() {
        return new VehicleCheckup(this.vehicle, this.checkupDate, this.checkupKms);
    }

}
