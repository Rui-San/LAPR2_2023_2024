package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;

public class GreenSpace {
    GreenSpaceType type;
    String name;
    Address address;
    Double totalArea;

    public GreenSpace(GreenSpaceType type, String name, String street, int streetNumber, String postalCode, String city, String district, Double totalArea) {
        this.type = type;
        this.name = name;
        setAddress(street,streetNumber,postalCode,city,district);
        this.totalArea = totalArea;
    }

    public GreenSpaceType getType() {
        return type;
    }

    public void setType(GreenSpaceType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(String street, int streetNumber, String postalCode, String city, String district) {
        this.address = new Address(street, streetNumber, postalCode, city, district);
    }
}
