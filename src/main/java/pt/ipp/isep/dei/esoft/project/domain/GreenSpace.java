package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;
import java.io.Serializable;

public class GreenSpace implements Serializable {

    /**
     * Green Space Type
     */
    GreenSpaceType type;
    /**
     * Green Space Name
     */
    String name;
    /**
     * Green Space Address
     */
    Address address;
    /**
     * Green Space Total Area
     */
    Double totalArea;
    /**
     * Green Space Manager
     */
    String manager;

    /**
     * Green Space Constructor
     * @param type Green Space Type
     * @param name Green Space Name
     * @param street Green Space Street
     * @param streetNumber Green Space Street Number
     * @param postalCode Green Space Postal Code
     * @param city Green Space City
     * @param district Green Space District
     * @param totalArea Green Space Total Area
     * @param manager Green Space Manager
     */
    public GreenSpace(GreenSpaceType type, String name, String street, int streetNumber, String postalCode, String city, String district, Double totalArea, String manager) {
        setType(type);
        setName(name);
        setAddress(street, streetNumber, postalCode, city, district);
        setTotalArea(totalArea);
        this.manager = manager;

    }

    /**
     * Get Green Space Type
     * @return
     */
    public GreenSpaceType getType() {
        return type;
    }

    /**
     * Set Green Space Type
     * @param type
     */
    public void setType(GreenSpaceType type) {
        if (validateType(type)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("This option is not a valid type.");
        }
    }

    /**
     * Validate Green Space Type
     * @param type
     * @return
     */
    private boolean validateType(GreenSpaceType type) {
        return type == GreenSpaceType.GARDEN || type == GreenSpaceType.LARGE || type == GreenSpaceType.MEDIUM;
    }

    /**
     * Get Green Space Name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set Green Space Name
     * @param name
     */
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    /**
     * Validate Green Space Name
     * @param name
     */
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Green Space name can't be empty or null.");
        }
        if (!name.matches("[a-zA-Z\\s-\\p{L}]+")) {
            throw new IllegalArgumentException("Green Space name cannot contain Special characters.");
        }
    }

    /**
     * Get Green Space Total Area
     * @return
     */
    public Double getTotalArea() {

        return totalArea;
    }

    /**
     * Set Green Space Total Area
     * @param totalArea
     */
    public void setTotalArea(Double totalArea) {
        validateTotalArea(totalArea);
        this.totalArea = totalArea;
    }

    /**
     * Validate Green Space Total Area
     * @param totalArea
     */
    private void validateTotalArea(Double totalArea) {
        if(totalArea == null){
            throw new IllegalArgumentException("Total Area must not be empty.");
        }
        if (totalArea <= 0) {
            throw new IllegalArgumentException("Total Area must be a postive number.");
        }
    }

    /**
     * Get Green Space Address
     * @return
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set Green Space Address
     * @param street Green Space Street
     * @param streetNumber Green Space Street Number
     * @param postalCode Green Space Postal Code
     * @param city Green Space City
     * @param district Green Space District
     */
    public void setAddress(String street, int streetNumber, String postalCode, String city, String district) {
        this.address = new Address(street, streetNumber, postalCode, city, district);
    }

    /**
     * Get Green Space Manager
     * @return
     */
    public String getManager() {
        return this.manager;
    }

}
