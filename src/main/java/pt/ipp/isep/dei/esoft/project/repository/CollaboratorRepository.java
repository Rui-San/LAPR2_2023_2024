package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Collaborator Repository class
 */
public class CollaboratorRepository {

    /**
     * List to store the collaborators.
     */
    private final List<Collaborator> collaboratorList;

    /**
     * Constructs a CollaboratorRepository object.
     */
    public CollaboratorRepository() {
        collaboratorList = new ArrayList<>();
    }

    /**
     * Adds a new collaborator to the repository after validation.
     *
     * @param collaborator the collaborator to add
     * @return an Optional containing the newly added collaborator if successful, empty otherwise
     */
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

    /**
     * Validates if a collaborator is already store in the list based on email and ID document number uniqueness.
     *
     * @param collaborator the collaborator to validate
     * @return true if the collaborator can be added, false otherwise
     */
    private boolean validateCollaborator(Collaborator collaborator) {

        String collaboratorEmail = collaborator.getEmail().getEmail().trim().toLowerCase();
        String collaboratorIdDocNumber = collaborator.getIdDocNumber().trim().toLowerCase();

        for (Collaborator registeredCollaborator : collaboratorList) {

            String registeredCollaboratorEmail = registeredCollaborator.getEmail().getEmail().trim().toLowerCase();
            String registeredCollaboratorIdDocNumber = registeredCollaborator.getIdDocNumber().trim().toLowerCase();
            if (registeredCollaboratorEmail.equals(collaboratorEmail) || registeredCollaboratorIdDocNumber.equals(collaboratorIdDocNumber)) {

                return false;
            }
        }
        return true;
    }

    /**
     * Returns the list of all collaborators.
     *
     * @return the list of all collaborators
     */
    public List<Collaborator> getCollaboratorList() {

        return List.copyOf(collaboratorList);
    }

    /**
     * Method used to get the collaborator object from the collaborator list with a specific email.
     * @param email the email of the collaborator to retrieve
     * @return an Optional containing the collaborator if found, empty otherwise
     */
    public Optional<Collaborator> getCollaboratorByCollaboratorEmail(Email email) {
        Optional<Collaborator> returnCollaborator = Optional.empty();

        for (Collaborator collaborator : collaboratorList) {
            if (collaborator.getEmail().equals(email)) {
                returnCollaborator = Optional.of(collaborator);
            }
        }
        return returnCollaborator;
    }

    /**
     * Creates a new collaborator with the provided information and adds it to the repository.
     *
     * @param name           the name of the collaborator
     * @param birthdate      the birthdate of the collaborator
     * @param admissionDate  the admission date of the collaborator
     * @param street         the street of the collaborator's address
     * @param streetNumber   the street number of the collaborator's address
     * @param postalCode     the postal code of the collaborator's address
     * @param city           the city of the collaborator's address
     * @param district       the district of the collaborator's address
     * @param email          the email of the collaborator
     * @param mobileNumber   the mobile number of the collaborator
     * @param idDocType      the ID document type of the collaborator
     * @param idDocNumber    the ID document number of the collaborator
     * @param job            the job of the collaborator
     * @return an Optional containing the newly created collaborator if successful, empty otherwise
     */
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

        Optional<Collaborator> addedCollaborator = add(collaborator);
        return addedCollaborator;
    }

    /**
     * Assigns skills to a specficic collaborator.
     *
     * @param collaborator the collaborator to assign skills to
     * @param skills       the list of skills to assign
     * @return true if the skills were successfully assigned, false otherwise
     */
    public boolean assignSkillsToCollaborator(Collaborator collaborator, List<Skill> skills) {
        Optional<Collaborator> existingCollaborator = getCollaboratorByCollaboratorEmail(collaborator.getEmail());

        if (existingCollaborator.isPresent()) {
            existingCollaborator.get().assignSkills(skills);
            return true;
        } else {
            return false;
        }
    }

}
