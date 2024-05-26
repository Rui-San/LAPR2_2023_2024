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

    private List<WorkPeriod> workPeriods;

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
                    .append(member.getEmail()).append("\n")
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
/*
    public boolean isAvailable(Date start, Date end) {

        if (this.workPeriods.isEmpty()) {
            return true;
        }

        for (WorkPeriod period : workPeriods) {
            if (period.overlapsWith(start, end)) {
                return false;
            }
        }
        return true;
    }

    public void addWorkPeriod(Date start, Date end) {
        workPeriods.add(new WorkPeriod(start, end));
    }

    public List<WorkPeriod> getWorkPeriods() {
        return this.workPeriods;
    }

 */

    /**
     * Adds a new member to the team.
     *
     * @param collaborator the collaborator to add to the team
     */
    public void addMember(Collaborator collaborator) {
        members.add(collaborator);
    }
}
