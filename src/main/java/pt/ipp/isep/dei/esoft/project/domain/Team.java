package pt.ipp.isep.dei.esoft.project.domain;


import java.util.List;

/**
 * Represents a Team as a list of collaborators
 */
public class Team {
    /**
     * The list of members in the team.
     */
    private List<Collaborator> members;

    /**
     * Constructs a Team object with the specified list of members.
     *
     * @param members the list of members in the team
     */
    public Team(List<Collaborator> members) {
        this.members = members;
    }

    /**
     * Returns the list of members in the team.
     *
     * @return the list of members in the team
     */
    public List<Collaborator> getMembers() {
        return members;
    }

    /*
     * Returns a copy of the list of members in the team.
     *
     * @return a copy of the list of members in the team
     *
    public List<Collaborator> getMembers() {
        return members;
    }
    */


    /**
     * Returns a string representation of the team, including member names, emails, and their skills.
     *
     * @return a string representation of the team
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Collaborator member : members) {
            stringBuilder.append("Name: ").append(member.getName()).append(" |")
                    .append(member.getEmail()).append(" | ")
                    .append("Skills: ");

            List<Skill> memberSkills = member.getSkillList();
            for (int i = 0; i < memberSkills.size(); i++) {
                stringBuilder.append(memberSkills.get(i).getSkillName());
                if (i < memberSkills.size() - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Adds a new member to the team.
     *
     * @param collaborator the collaborator to add to the team
     */
    public void addMember(Collaborator collaborator) {
        members.add(collaborator);
    }
}
