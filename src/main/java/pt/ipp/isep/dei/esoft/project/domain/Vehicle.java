package pt.ipp.isep.dei.esoft.project.domain;

public class Vehicle {

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

    //TODO: validação nos metodos set

    public void setPlateId(String plateId) { this.plateId = plateId; }

    public void setBrand(String brand) { this.brand = brand; }

    public void setModel(String model) { this.model = model; }

    public void setType(String type) { this.type = type; }

    public void setTare(double tare) { this.tare = tare; }

    public void setGrossWeight(double grossWeight) { this.grossWeight = grossWeight; }

    public void setCurrentKm(int currentKm) { this.currentKm = currentKm; }

    public void setRegisterDate(Date registerDate) { this.registerDate = registerDate; }

    public void setAcquisitionDate(Date acquisitionDate) { this.acquisitionDate = acquisitionDate; }

    public void setCheckupFrequencyKms(int checkupFrequencyKms) { this.checkupFrequencyKms = checkupFrequencyKms; }

    public Vehicle(String plateId, String brand, String model, String type, double tare, double grossWeight, int currentKm, Date registerDate, Date acquisitionDate, int checkupFrequencyKms) {
        this.plateId = plateId;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.tare = tare;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.checkupFrequencyKms = checkupFrequencyKms;
    }

    /**
     * Creates a deep copy of the Vehicle.
     *
     * @return the cloned Vehicle
     */
    @Override
    public Vehicle clone() {
        Vehicle clone = new Vehicle(
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

        return clone;
    }

    //TODO: metodos de validaçao

}
