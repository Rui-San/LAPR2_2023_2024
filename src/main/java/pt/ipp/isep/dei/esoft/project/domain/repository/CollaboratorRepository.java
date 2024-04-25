package pt.ipp.isep.dei.esoft.project.domain.repository;

import pt.ipp.isep.dei.esoft.project._templateFiles.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Email;

import java.util.List;
import java.util.Optional;

public class CollaboratorRepository {

    private List<Collaborator> collaboratorList;

    public Optional<Collaborator> add(Collaborator collaborator) {
        Optional<Collaborator> newCollaborator = Optional.empty();
        boolean operationSuccess = false;

        if (validateCollaborator(collaborator)) {
            newCollaborator = Optional.of(collaborator.clone());
            operationSuccess = collaboratorList.add(newCollaborator.get());
        }

        if (!operationSuccess) {
            newCollaborator = Optional.empty();
        }

        return newCollaborator;
    }

    private boolean validateCollaborator(Collaborator collaborator) {
        boolean isValid = !collaboratorList.contains(collaborator);
        return isValid;
    }

    public List<Collaborator> getCollaboratorList() {
        return List.copyOf(collaboratorList);
    }

    public Optional<Collaborator> getCollaboratorByCollaboratorEmail(Email email) {
        Optional<Collaborator> returnCollaborator = Optional.empty();

        for (Collaborator collaborator : collaboratorList) {
            if (collaborator.getEmail().equals(email)) {
                returnCollaborator = Optional.of(collaborator);
            }
        }
        return returnCollaborator;
    }
}
