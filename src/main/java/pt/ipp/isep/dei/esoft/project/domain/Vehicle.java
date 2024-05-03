package pt.ipp.isep.dei.esoft.project.domain;

import java.util.regex.Pattern;

public class Vehicle {

    private enum VehicleValidationResult {
        EMPTY_NULL,
        VALID,
        INVALID_INPUT,
        INVALID_DATE,
        INVALID_PLATE_ID_FORMAT,
        TARE_NOT_LESS_THAN_GROSS_WEIGHT
    }

    private String plateId;
    private String brand;
    private String model;
    private String type;
    private double tare;
    private double grossWeight;
    private int currentKm;
    private Date registerDate;
    private Date acquisitionDate;
    private int checkupFrequencyKms;

    public String getPlateId() { return plateId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public double getTare() { return tare; }
    public double getGrossWeight() { return grossWeight; }
    public int getCurrentKm() { return currentKm; }
    public Date getRegisterDate() { return registerDate; }
    public Date getAcquisitionDate() { return acquisitionDate; }
    public int getCheckupFrequencyKms() { return checkupFrequencyKms; }

    public void setPlateId(String plateId) {
        if (plateId == null || plateId.trim().isEmpty()) {
            throw new IllegalArgumentException("Plate ID cannot be null or empty.");
        }
        this.plateId = plateId;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty.");
        }
        this.brand = brand;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty.");
        }
        this.model = model;
    }

    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty.");
        }
        this.type = type;
    }

    public void setTare(double tare) {
        if (tare <= 0) {
            throw new IllegalArgumentException("Tare must be a positive number.");
        }
        this.tare = tare;
    }

    public void setGrossWeight(double grossWeight) {
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("Gross weight must be a positive number.");
        }
        this.grossWeight = grossWeight;
    }

    public void setCurrentKm(int currentKm) {
        if (currentKm < 0) {
            throw new IllegalArgumentException("Current kilometers cannot be negative.");
        }
        this.currentKm = currentKm;
    }

    public void setRegisterDate(Date registerDate) {
        if (registerDate == null) {
            throw new IllegalArgumentException("Register date cannot be null.");
        }
        this.registerDate = registerDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        if (acquisitionDate == null) {
            throw new IllegalArgumentException("Acquisition date cannot be null.");
        }
        this.acquisitionDate = acquisitionDate;
    }

    public void setCheckupFrequencyKms(int checkupFrequencyKms) {
        if (checkupFrequencyKms <= 0) {
            throw new IllegalArgumentException("Checkup frequency kilometers must be a positive number.");
        }
        this.checkupFrequencyKms = checkupFrequencyKms;
    }

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