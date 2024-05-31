package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpaceRepository implements Serializable {

    List<GreenSpace> greenSpaceList;

    public GreenSpaceRepository() {
        this.greenSpaceList = new ArrayList<>();
    }

    public Optional<GreenSpace> registerGreenSpace(GreenSpace greenSpace) {

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

    public List<GreenSpace> getGreenSpaceListByManager(String managerEmail) {
        List<GreenSpace> managerGreenSpacesList = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList){
            if(greenSpace.getManager().equalsIgnoreCase(managerEmail)){
                managerGreenSpacesList.add(greenSpace);
            }
        }
        return managerGreenSpacesList;
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
