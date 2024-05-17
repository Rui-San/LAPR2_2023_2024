package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaceRepository {

    List<GreenSpace> greenSpaceList;

    public GreenSpaceRepository() {
        this.greenSpaceList = new ArrayList<>();
    }

    public List<GreenSpace> getGreenSpaceList() {
        return greenSpaceList;
    }

}
