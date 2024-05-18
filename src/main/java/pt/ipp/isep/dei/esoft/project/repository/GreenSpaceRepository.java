package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpaceRepository {

    List<GreenSpace> greenSpaceList;

    public GreenSpaceRepository() {
        this.greenSpaceList = new ArrayList<>();
    }

    public Optional<GreenSpace> registerGreenSpace(GreenSpaceType type, String name, String street, int streetNumber, String postalCode, String city, String district, double totalArea) {

        GreenSpace greenSpace = new GreenSpace(
                type,
                name,
                street,
                streetNumber,
                postalCode,
                city,
                district,
                totalArea
        );

        Optional<GreenSpace> addedGreenSpace = add(greenSpace);
        return addedGreenSpace;
    }

    public Optional<GreenSpace> add(GreenSpace greenSpace) {

        Optional<GreenSpace> newGreenSpace = Optional.empty();
        boolean operationSuccess = false;

        if (validateGreenSpace(greenSpace)) {
            newGreenSpace = Optional.of(greenSpace);
            operationSuccess = greenSpaceList.add(newGreenSpace.get());
        }

        if (!operationSuccess) {
            newGreenSpace = Optional.empty();
        }

        return newGreenSpace;
    }

    public List<GreenSpace> getGreenSpaceList() {
        return greenSpaceList;
    }

    private boolean validateGreenSpace(GreenSpace greenSpace) {
        boolean isValid = true;
        String greenSpaceName = greenSpace.getName().trim().toLowerCase();

        for (GreenSpace registeredGreenSpace : greenSpaceList) {
            if (registeredGreenSpace.getName().trim().toLowerCase().equals(greenSpaceName)) {
                isValid = false;
                return isValid;
            }
        }
        return isValid;
    }

    public Optional<GreenSpace> getGreenSpaceByName(String greenSpaceName) {
        Optional<GreenSpace> returnGreenSpace = Optional.empty();

        for (GreenSpace greenSpace : greenSpaceList) {
            if (greenSpace.getName().equals(greenSpaceName)) {
                returnGreenSpace = Optional.of(greenSpace);
            }
        }
        return returnGreenSpace;
    }




}
