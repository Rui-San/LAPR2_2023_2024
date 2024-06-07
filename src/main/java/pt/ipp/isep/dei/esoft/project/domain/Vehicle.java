package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.VehicleType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Vehicle implements Serializable {

    /**
     * The plate ID of the vehicle.
     */
    private String plateId;

    /**
     * The brand of the vehicle.
     */
    private String brand;

    /**
     * The model of the vehicle.
     */
    private String model;

    /**
     * The type of the vehicle.
     */
    private VehicleType type;

    /**
     * The tare of the vehicle.
     */
    private double tare;

    /**
     * The gross weight of the vehicle.
     */
    private double grossWeight;

    /**
     * The current kilometers of the vehicle.
     */
    private int currentKm;

    /**
     * The register date of the vehicle.
     */
    private Date registerDate;

    /**
     * The acquisition date of the vehicle.
     */
    private Date acquisitionDate;

    /**
     * The checkup frequency kilometers of the vehicle.
     */
    private int checkupFrequencyKms;

    private List<WorkPeriod> workPeriods;

    /**
     * Returns the plate ID of the vehicle.
     * @return the plate ID of the vehicle
     */
    public String getPlateId() { return plateId; }

    /**
     * Returns the brand of the vehicle.
     * @return the brand of the vehicle
     */
    public String getBrand() { return brand; }

    /**
     * Returns the model of the vehicle.
     * @return the model of the vehicle
     */
    public String getModel() { return model; }

    /**
     * Returns the type of the vehicle.
     * @return the type of the vehicle
     */
    public VehicleType getType() { return type; }

    /**
     * Returns the tare of the vehicle.
     * @return the tare of the vehicle
     */
    public double getTare() { return tare; }

    /**
     * Returns the gross weight of the vehicle.
     * @return the gross weight of the vehicle
     */
    public double getGrossWeight() { return grossWeight; }

    /**
     * Returns the current kilometers of the vehicle.
     * @return the current kilometers of the vehicle
     */
    public int getCurrentKm() { return currentKm; }

    /**
     * Returns the register date of the vehicle.
     * @return the register date of the vehicle
     */
    public Date getRegisterDate() { return registerDate; }

    /**
     * Returns the acquisition date of the vehicle.
     * @return the acquisition date of the vehicle
     */
    public Date getAcquisitionDate() { return acquisitionDate; }

    /**
     * Returns the checkup frequency kilometers of the vehicle.
     * @return the checkup frequency kilometers of the vehicle
     */
    public int getCheckupFrequencyKms() { return checkupFrequencyKms; }

    /**
     * Sets the plate ID of the vehicle.
     * @param plateId the plate ID of the vehicle
     */
    public void setPlateId(String plateId) {

        validatePlateID(plateId);
        this.plateId = plateId;
    }

    /**
     * Validates the plate ID of the vehicle.
     * @param plateId the plate ID of the vehicle
     */
    public void validatePlateID(String plateId){
        Pattern pattern1 = Pattern.compile("^[0-9]{2}-[0-9]{2}-[A-Z]{2}$");
        Pattern pattern2 = Pattern.compile("^[0-9]{2}-[A-Z]{2}-[0-9]{2}$");
        Pattern pattern3 = Pattern.compile("^[A-Z]{2}-[0-9]{2}-[A-Z]{2}$");

        if (plateId == null || plateId.trim().isEmpty()) {
            throw new IllegalArgumentException("Plate ID cannot be null or empty.");
        }else if (!pattern1.matcher(plateId).matches() && !pattern2.matcher(plateId).matches() && !pattern3.matcher(plateId).matches()){
            throw new IllegalArgumentException("Plate ID must be in the format 00-00-AA or AA-00-AA or 00-AA-00.");
        }
    }

    /**
     * Sets the brand of the vehicle.
     * @param brand the brand of the vehicle
     */
    public void setBrand(String brand) {

        validateBrand(brand);
        this.brand = brand;
    }

    /**
     * Validates the brand of the vehicle.
     * @param brand the brand of the vehicle
     */
    public void validateBrand(String brand){
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty.");
        }else if (!brand.matches("^[\\p{L}0-9 -]+$")) {
            throw new IllegalArgumentException("Brand cannot contain special characters.");
        }
    }

    /**
     * Sets the model of the vehicle.
     * @param model the model of the vehicle
     */
    public void setModel(String model) {

        validateModel(model);
        this.model = model;
    }

    /**
     * Validates the model of the vehicle.
     * @param model the model of the vehicle
     */
    public void validateModel(String model){
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty.");
        }else if (!model.matches("^[\\p{L}0-9 -]+$")) {
            throw new IllegalArgumentException("Model cannot contain special characters.");
        }
    }

    /**
     * Sets the type of the vehicle.
     * @param type the type of the vehicle
     */
    public void setType(VehicleType type) {

        validateType(type);
        this.type = type;
    }

    /**
     * Validates the type of the vehicle.
     * @param type the type of the vehicle
     */
    public void validateType(VehicleType type){
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null or empty.");
        }
    }

    /**
     * Sets the tare of the vehicle.
     * @param tare the tare of the vehicle
     */
    public void setTare(double tare) {

        validateTare(tare);
        this.tare = tare;
    }

    /**
     * Validates the tare of the vehicle.
     * @param tare the tare of the vehicle
     */
    public void validateTare(double tare){
        if (tare <= 0) {
            throw new IllegalArgumentException("Tare must be a positive number.");
        }
    }

    /**
     * Sets the gross weight of the vehicle.
     * @param grossWeight the gross weight of the vehicle
     */
    public void setGrossWeight(double grossWeight) {

        validateGrossWeight(grossWeight);
        this.grossWeight = grossWeight;
    }

    /**
     * Validates the gross weight of the vehicle.
     * @param grossWeight the gross weight of the vehicle
     */
    public void validateGrossWeight(double grossWeight){
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("Gross weight must be a positive number.");
        }else if (grossWeight < this.tare) {
            throw new IllegalArgumentException("Gross weight must be greater than tare.");
        }
    }

    /**
     * Sets the current kilometers of the vehicle.
     * @param currentKm the current kilometers of the vehicle
     */
    public void setCurrentKm(int currentKm) {

        validateCurrentKm(currentKm);
        this.currentKm = currentKm;
    }

    public void validateCurrentKm(int currentKm){
        if (currentKm < 0) {
            throw new IllegalArgumentException("Current kilometers cannot be negative.");
        }
    }

    /**
     * Sets the register date of the vehicle.
     * @param registerDate the register date of the vehicle
     */
    public void setRegisterDate(String registerDate) {

        validateRegisterDate(registerDate);
        this.registerDate = new Date(registerDate);
    }

    /**
     * Validates the register date of the vehicle.
     * @param registerDate the register date of the vehicle
     */
    public void validateRegisterDate(String registerDate){
        Pattern pattern1 = Pattern.compile("^[0-9]{2}-[0-9]{2}-[A-Z]{2}$");
        Pattern pattern2 = Pattern.compile("^[0-9]{2}-[A-Z]{2}-[0-9]{2}$");
        Pattern pattern3 = Pattern.compile("^[A-Z]{2}-[0-9]{2}-[A-Z]{2}$");
        String[] registerDateParts = registerDate.split("/");
        int registerDateYear = Integer.parseInt(registerDateParts[2]);

        if (registerDate == null) {
            throw new IllegalArgumentException("Register date cannot be null.");
        }else if(registerDateYear > 2005 && pattern1.matcher(getPlateId()).matches()){
            throw new IllegalArgumentException("Invalid date, with the plateID format 00-00-AA, the register date must be before 2005");
        }else if ( (registerDateYear <= 2005 || registerDateYear > 2020) && pattern2.matcher(getPlateId()).matches()) {
            throw new IllegalArgumentException("Invalid date, with the plateID format 00-AA-00, the register date must be between 2005 and 2020");
        }else if(registerDateYear <= 2020 && pattern3.matcher(getPlateId()).matches()){
            throw new IllegalArgumentException("Invalid date, with the plateID format AA-00-AA, the register date must be after 2020");
        }
        if(new Date(registerDate).isAfter(new Date())){
            throw new IllegalArgumentException("Register date cannot be after the current date.");
        }
    }

    /**
     * Sets the acquisition date of the vehicle.
     * @param acquisitionDate the acquisition date of the vehicle
     */
    public void setAcquisitionDate(String acquisitionDate) {

        validateAcquisitionDate(acquisitionDate);
        this.acquisitionDate = new Date(acquisitionDate);
    }

    /**
     * Validates the acquisition date of the vehicle.
     * @param acquisitionDate the acquisition date of the vehicle
     */
    public void validateAcquisitionDate(String acquisitionDate) {
        if (acquisitionDate == null) {
            throw new IllegalArgumentException("Acquisition date cannot be null.");
        }
        if(new Date(acquisitionDate).isAfter(new Date())){
            throw new IllegalArgumentException("Acquisition date cannot be after the current date.");
        }
        if(new Date(acquisitionDate).isBefore(this.registerDate)){
            throw new IllegalArgumentException("Acquisition date cannot be before the register date.");
        }
    }

    /**
     * Sets the checkup frequency kilometers of the vehicle.
     * @param checkupFrequencyKms the checkup frequency kilometers of the vehicle
     */
    public void setCheckupFrequencyKms(int checkupFrequencyKms) {

        validateCheckupFrequencyKms(checkupFrequencyKms);
        this.checkupFrequencyKms = checkupFrequencyKms;
    }

    /**
     * Validates the checkup frequency kilometers of the vehicle.
     * @param checkupFrequencyKms the checkup frequency kilometers of the vehicle
     */
    public void validateCheckupFrequencyKms(int checkupFrequencyKms) {
        if (checkupFrequencyKms <= 0) {
            throw new IllegalArgumentException("Checkup frequency kilometers must be a positive number.");
        }
    }

    /**
     * Creates an instance of Vehicle.
     */
    public Vehicle(String plateId, String brand, String model, VehicleType type, double tare, double grossWeight, int currentKm, String registerDate, String acquisitionDate, int checkupFrequencyKms) {
        this.setPlateId(plateId);
        this.setBrand(brand);
        this.setModel(model);
        this.setType(type);
        this.setTare(tare);
        this.setGrossWeight(grossWeight);
        this.setCurrentKm(currentKm);
        this.setRegisterDate(registerDate);
        this.setAcquisitionDate(acquisitionDate);
        this.setCheckupFrequencyKms(checkupFrequencyKms);
        this.workPeriods = new ArrayList<>();
    }

    /**
     * Returns the textual representation of the Vehicle.
     *
     * @return the textual representation of the Vehicle
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Vehicle)) {
            return false;
        }
        Vehicle vehicle = (Vehicle) obj;
        return getPlateId().equals(vehicle.getPlateId());
    }

    /**
     * Returns a hash code value for the Plate ID.
     * @return a hash code value for the Plate ID
     */
    @Override
    public int hashCode() {
        return Objects.hash( getPlateId() );
    }

    /**
     * Creates a deep copy of the Vehicle.
     *
     * @return the cloned Vehicle
     */
    @Override
    public Vehicle clone() {
        return new Vehicle(
                this.plateId,
                this.brand,
                this.model,
                this.type,
                this.tare,
                this.grossWeight,
                this.currentKm,
                this.registerDate.clone().toString(),
                this.acquisitionDate.clone().toString(),
                this.checkupFrequencyKms
        );
    }

    public boolean isAvailable(WorkPeriod taskWorkPeriod) {
        for (WorkPeriod workPeriod : workPeriods) {
            if (workPeriod.isOverlap(taskWorkPeriod)) {
                return false;
            }
        }
        return true;
    }

    public boolean isAvailable(WorkPeriod newTaskWorkPeriod, WorkPeriod oldTaskWorkPeriod) {

        for (WorkPeriod workPeriod : workPeriods) {
            if (workPeriod.isOverlap(newTaskWorkPeriod) && !workPeriod.matches(oldTaskWorkPeriod)){
                return false;
            }
        }
        return true;
    }

    public List<WorkPeriod> getWorkPeriods() {
        return workPeriods;
    }

    // Add work period for assigned task
    public void addWorkPeriod(WorkPeriod workPeriod) {
        workPeriods.add(workPeriod);
    }


    public void removeWorkPeriodIfExists(WorkPeriod taskWorkPeriod) {
        int indexToRemove = -1;
        for (int i = 0; i < workPeriods.size(); i++) {
            if (workPeriods.get(i).matches(taskWorkPeriod)) {
                indexToRemove = i;
            }
        }
        if (indexToRemove != -1) {
            workPeriods.remove(indexToRemove);
        }
    }
}