package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;

public class RegisterGreenSpaceController {

    private GreenSpaceRepository greenSpaceRepository;


    public RegisterGreenSpaceController() {
        getGreenSpaceRepository();
    }

    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    public Optional<GreenSpace> registerGreenSpace(GreenSpaceDTO greenSpaceDTO) {
        Optional<GreenSpace> newGreenSpace = Optional.empty();
        newGreenSpace = greenSpaceRepository.registerGreenSpace(GreenSpaceMapper.toGreenSpace(greenSpaceDTO));

        return newGreenSpace;
    }
}
