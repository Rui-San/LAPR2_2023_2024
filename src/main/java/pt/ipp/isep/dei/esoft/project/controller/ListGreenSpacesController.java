package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceSorting;
import pt.ipp.isep.dei.esoft.project.tools.Sorting;

import java.util.ArrayList;
import java.util.List;

public class ListGreenSpacesController {

    private GreenSpaceRepository greenSpaceRepository;

    public ListGreenSpacesController(){
        getGreenSpaceRepository();
    }

    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    public List<GreenSpaceDTO> getGreenSpaceDTOlist() {
        List<GreenSpace> greenSpaceList = greenSpaceRepository.getGreenSpaceList();

        return GreenSpaceMapper.toDTOlist(greenSpaceList);
    }

    public List<GreenSpaceDTO> getManagerGreenSpaceDTOList(){
        String managerEmail = ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail();
        List<GreenSpace> managerGreenSpacesList = greenSpaceRepository.getGreenSpaceListByManager(managerEmail);
        managerGreenSpacesList = GreenSpaceSorting.sort(managerGreenSpacesList);

        return GreenSpaceMapper.toDTOlist(managerGreenSpacesList);
    }

}