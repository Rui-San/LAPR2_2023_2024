package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Organization {
    private final List<Collaborator> collaborators;

    public Organization() {
        collaborators = new ArrayList<>();
    }

    public Organization(List<Collaborator> collaborators) {

        collaborators = null;
    }

    public Optional<Collaborator> add(Collaborator skill) {

        Optional<Collaborator> newSkill = Optional.empty();
        boolean operationSuccess = false;

        if (validateSkill(skill)) {
            newSkill = Optional.of(skill.clone());
            operationSuccess = collaborators.add(newSkill.get());
        }

        if (!operationSuccess) {
            newSkill = Optional.empty();
        }

        return newSkill;
    }

    /**
     * @param skill
     * @return the logical state of the validation. True if the list of skills doesn't contain that skill.
     */
    private boolean validateSkill(Skill skill) {
        boolean isValid = !collaborators.contains(skill);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<Collaborator> getSkills() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(collaborators);
    }


}

