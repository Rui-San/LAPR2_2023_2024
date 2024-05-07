package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

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

    public Optional<Collaborator> createCollaborator(String name, String birthdate, String admissionDate, String street, int streetNumber,String postalCode, String city, String district, String email, String mobileNumber, Collaborator.IdDocType idDocType, String idDocNumber, Job job) {

        Collaborator collaborator = new Collaborator(
                name,
                birthdate,
                admissionDate,
                street,
                streetNumber,
                postalCode,
                city,
                district,
                email,
                mobileNumber,
                idDocType,
                idDocNumber,
                job
        );

        this.add(collaborator);
        return Optional.of(collaborator);

    }

    public boolean updateCollaboratorSkills(Collaborator collaborator, List<Skill> skills) {
        Optional<Collaborator> existingCollaborator = getCollaboratorByCollaboratorEmail(collaborator.getEmail());

        if (existingCollaborator.isPresent()) {
            existingCollaborator.get().assignSkills(skills);
            return true;
        } else {
            return false;
        }
    }

    //public boolean updateCollaboratorSkills(Collaborator collaborator, List<Skill> skills) {
      //  return false;
    //}
}
