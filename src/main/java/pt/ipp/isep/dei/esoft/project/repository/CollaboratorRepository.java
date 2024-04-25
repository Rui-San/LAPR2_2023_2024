package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollaboratorRepository {

    private final List<Collaborator> collaboratorList;

    public CollaboratorRepository() {
        collaboratorList = new ArrayList<>();
    }

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

    /*A method that could work and way more simple than above:
    public boolean addCollaborator(Collaborator collaborator) {
        boolean operationSuccess = false;
        if (validateCollaborator(collaborator)) {
            collaboratorList.add(collaborator);
            operationSuccess = true;
        }
        return operationSuccess;
    }
     */

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
