package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project._templateFiles.domain.Task;

import java.util.Objects;
import java.util.regex.Pattern;

public class Vehicle {

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
    private String type;

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
    public String getType() { return type; }

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

        Pattern pattern = Pattern.compile("^[A-Z]{2}-\\d{2}-[A-Z]{2}$|^[0-9]{2}-[A-Z]{2}-[0-9]{2}$");

        if (plateId == null || plateId.trim().isEmpty()) {
            throw new IllegalArgumentException("Plate ID cannot be null or empty.");
        }else if (!pattern.matcher(plateId).matches()) {
            throw new IllegalArgumentException("Plate ID must be in the format AA-00-AA or 00-AA-00.");
        }
        this.plateId = plateId;
    }

    /**
     * Sets the brand of the vehicle.
     * @param brand the brand of the vehicle
     */
    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty.");
        }else if (!brand.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Brand cannot contain special characters.");
        }

        this.brand = brand;
    }

    /**
     * Sets the model of the vehicle.
     * @param model the model of the vehicle
     */
    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty.");
        }else if (!model.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Model cannot contain special characters.");
        }

        this.model = model;
    }

    /**
     * Sets the type of the vehicle.
     * @param type the type of the vehicle
     */
    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty.");
        }else if (!type.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Type cannot contain special characters.");
        }

        this.type = type;
    }

    /**
     * Sets the tare of the vehicle.
     * @param tare the tare of the vehicle
     */
    public void setTare(double tare) {
        if (tare <= 0) {
            throw new IllegalArgumentException("Tare must be a positive number.");
        }
        this.tare = tare;
    }

    /**
     * Sets the gross weight of the vehicle.
     * @param grossWeight the gross weight of the vehicle
     */
    public void setGrossWeight(double grossWeight) {
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("Gross weight must be a positive number.");
        }else if (grossWeight < this.tare) {
            throw new IllegalArgumentException("Gross weight must be greater than tare.");
        }
        this.grossWeight = grossWeight;
    }

    /**
     * Sets the current kilometers of the vehicle.
     * @param currentKm the current kilometers of the vehicle
     */
    public void setCurrentKm(int currentKm) {
        if (currentKm < 0) {
            throw new IllegalArgumentException("Current kilometers cannot be negative.");
        }
        this.currentKm = currentKm;
    }

    /**
     * Sets the register date of the vehicle.
     * @param registerDate the register date of the vehicle
     */
    public void setRegisterDate(Date registerDate) {
        if (registerDate == null) {
            throw new IllegalArgumentException("Register date cannot be null.");
        }
        this.registerDate = registerDate;
    }

    /**
     * Sets the acquisition date of the vehicle.
     * @param acquisitionDate the acquisition date of the vehicle
     */
    public void setAcquisitionDate(Date acquisitionDate) {
        if (acquisitionDate == null) {
            throw new IllegalArgumentException("Acquisition date cannot be null.");
        }
        this.acquisitionDate = acquisitionDate;
    }

    /**
     * Sets the checkup frequency kilometers of the vehicle.
     * @param checkupFrequencyKms the checkup frequency kilometers of the vehicle
     */
    public void setCheckupFrequencyKms(int checkupFrequencyKms) {
        if (checkupFrequencyKms <= 0) {
            throw new IllegalArgumentException("Checkup frequency kilometers must be a positive number bigger than zero.");
        }
        this.checkupFrequencyKms = checkupFrequencyKms;
    }

    /**
     * Creates an instance of Vehicle.
     */
    public Vehicle(String plateId, String brand, String model, String type, double tare, double grossWeight, int currentKm, Date registerDate, Date acquisitionDate, int checkupFrequencyKms) {
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
     * @return
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
                this.registerDate.clone(),
                this.acquisitionDate.clone(),
                this.checkupFrequencyKms
        );
    }

}