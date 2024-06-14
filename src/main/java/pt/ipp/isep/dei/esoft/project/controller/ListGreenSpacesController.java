package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceSorting;
import pt.isep.lei.esoft.auth.domain.model.UserRole;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

public class ListGreenSpacesController {

    /**
     * The GreenSpace Repository.
     */
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * Instantiates a new List Green Spaces Controller.
     */
    public ListGreenSpacesController(){
        getGreenSpaceRepository();
    }

    /**
     * Gets the GreenSpace Repository.
     * @return GreenSpace Repository
     */
    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    /**
     * Gets the GreenSpaceDTO list.
     * @return GreenSpaceDTO list
     */
    public List<GreenSpaceDTO> getGreenSpaceDTOlist() {
        List<GreenSpace> greenSpaceList = greenSpaceRepository.getGreenSpaceList();

        return GreenSpaceMapper.toDTOlist(greenSpaceList);
    }

    /**
     * Gets the GreenSpaceDTO list of a specific GSM.
     * @return GreenSpaceDTO list
     */
    public List<GreenSpaceDTO> getManagerGreenSpaceDTOList(){
        String managerEmail = ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail();
        UserRoleDTO managerRole = ApplicationSession.getInstance().getCurrentSession().getUserRoles().get(0);
        List<GreenSpace> managerGreenSpacesList;
        if(!managerRole.getId().equals("ADMINISTRATOR")){
            managerGreenSpacesList = greenSpaceRepository.getGreenSpaceListByManager(managerEmail);
        }else {
            managerGreenSpacesList = greenSpaceRepository.getGreenSpaceList();
        }
        managerGreenSpacesList = GreenSpaceSorting.sort(managerGreenSpacesList);

        return GreenSpaceMapper.toDTOlist(managerGreenSpacesList);
    }

}