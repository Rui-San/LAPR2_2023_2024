package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpaceRepository implements Serializable {

    /**
     * List of Green Spaces
     */
    List<GreenSpace> greenSpaceList;

    /**
     * Instantiates a new Green Space Repository.
     */
    public GreenSpaceRepository() {
        this.greenSpaceList = new ArrayList<>();
    }

    /**
     * Register Green Space
     * @param greenSpace Green Space
     * @return Optional<GreenSpace>
     */
    public Optional<GreenSpace> registerGreenSpace(GreenSpace greenSpace) {

        Optional<GreenSpace> addedGreenSpace = add(greenSpace);
        return addedGreenSpace;
    }

    /**
     * Add Green Space
     * @param greenSpace Green Space
     * @return Optional<GreenSpace>
     */
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

    /**
     * Gets the list of Green Spaces
     * @return list of Green Spaces
     */
    public List<GreenSpace> getGreenSpaceList() {
        return greenSpaceList;
    }

    /**
     * Gets the Green Spaces of a specific GSM
     * @param managerEmail GSM email
     * @return list of Green Spaces managed by the GSM
     */
    public List<GreenSpace> getGreenSpaceListByManager(String managerEmail) {
        List<GreenSpace> managerGreenSpacesList = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList){
            if(greenSpace.getManager().equalsIgnoreCase(managerEmail)){
                managerGreenSpacesList.add(greenSpace);
            }
        }
        return managerGreenSpacesList;
    }

    /**
     * Validates the Green Space
     * @param greenSpace Green Space
     * @return true if the Green Space is valid, false otherwise
     */
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

    /**
     * Gets the Green Space by its name
     * @param greenSpaceName Green Space name
     * @return a greenspace if it is found
     */
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
