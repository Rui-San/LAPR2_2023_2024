package pt.ipp.isep.dei.esoft.project.matdisc.dto;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;

public class GreenSpaceDTO {
    public String name;
    public GreenSpaceType type;
    public Double totalArea;
    public String street;
    public int streetNumber;
    public String postalCode;
    public String city;
    public String district;



    public GreenSpaceDTO(String name, GreenSpaceType type, Double totalArea, String street, int streetNumber, String postalCode, String city, String ditrict) {
        this.name = name;
        this.type = type;
        this.totalArea = totalArea;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.district = ditrict;

    }
}
