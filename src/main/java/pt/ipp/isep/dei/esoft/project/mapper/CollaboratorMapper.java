package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.dto.CollaboratorDTO;

import java.util.ArrayList;
import java.util.List;

public class CollaboratorMapper {
    public CollaboratorMapper() {
    }

    public static List<CollaboratorDTO> toDTOlist(List<Collaborator> collaboratorList) {
        List<CollaboratorDTO> collaboratorDTOList = new ArrayList<>();
        for (Collaborator collaborator : collaboratorList) {
            collaboratorDTOList.add(toDTO(collaborator));
        }
        return collaboratorDTOList;
    }

    public static CollaboratorDTO toDTO(Collaborator collaborator) {

        return new CollaboratorDTO(collaborator);
    }


}
