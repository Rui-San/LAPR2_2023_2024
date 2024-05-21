package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;

public class GreenSpaceDTO {
    public String name;
    public GreenSpaceType type;
    public Double totalArea;

    public GreenSpaceDTO(String name, GreenSpaceType type, Double totalArea) {
        this.name = name;
        this.type = type;
        this.totalArea = totalArea;

    }
}
