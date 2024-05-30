package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;

import java.io.Serializable;

public class GreenSpace implements Serializable {
    GreenSpaceType type;
    String name;
    Address address;
    Double totalArea;
    String manager;

    public GreenSpace(GreenSpaceType type, String name, String street, int streetNumber, String postalCode, String city, String district, Double totalArea, String manager) {
        setType(type);
        setName(name);
        setAddress(street, streetNumber, postalCode, city, district);
        setTotalArea(totalArea);
        this.manager = manager;

    }

    public GreenSpaceType getType() {
        return type;
    }

    public void setType(GreenSpaceType type) {
        if (validateType(type)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("This option is not a valid type.");
        }
    }

    private boolean validateType(GreenSpaceType type) {
        return type == GreenSpaceType.GARDEN || type == GreenSpaceType.LARGE || type == GreenSpaceType.MEDIUM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Green Space name can't be empty or null.");
        }
        if (!name.matches("[a-zA-Z\\s-\\p{L}]+")) {
            throw new IllegalArgumentException("Green Space name cannot contain Special characters.");
        }
    }

    public Double getTotalArea() {

        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        validateTotalArea(totalArea);
        this.totalArea = totalArea;
    }

    private void validateTotalArea(Double totalArea) {
        if(totalArea == null){
            throw new IllegalArgumentException("Total Area must not be empty.");
        }
        if (totalArea <= 0) {
            throw new IllegalArgumentException("Total Area must be a postive number.");
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(String street, int streetNumber, String postalCode, String city, String district) {
        this.address = new Address(street, streetNumber, postalCode, city, district);
    }

    public String getManager() {
        return this.manager;
    }
}
