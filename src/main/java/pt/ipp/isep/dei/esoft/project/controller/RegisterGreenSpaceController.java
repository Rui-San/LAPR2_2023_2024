package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.util.Optional;

public class RegisterGreenSpaceController {

    /**
     * The Green Space Repository.
     */
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * Instantiates a new Register Green Space Controller.
     */
    public RegisterGreenSpaceController() {
        getGreenSpaceRepository();
    }

    /**
     * Gets the Green Space Repository.
     * @return Green Space Repository
     */
    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    /**
     * Register Green Space.
     * @param greenSpaceDTO Green Space DTO
     * @return the new Green Space
     */
    public Optional<GreenSpace> registerGreenSpace(GreenSpaceDTO greenSpaceDTO) {
        Optional<GreenSpace> newGreenSpace = Optional.empty();
        String managerEmail = ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail();
        newGreenSpace = greenSpaceRepository.registerGreenSpace(GreenSpaceMapper.toGreenSpace(greenSpaceDTO,managerEmail));

        return newGreenSpace;
    }
}
