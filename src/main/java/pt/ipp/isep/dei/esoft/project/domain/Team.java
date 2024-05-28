package pt.ipp.isep.dei.esoft.project.domain;

import java.time.Duration;
import java.util.ArrayList;
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
        this.workPeriods = new ArrayList<>();
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

    public List<WorkPeriod> getWorkPeriods() {
        return workPeriods;
    }

    public void addMember(Collaborator collaborator) {
        members.add(collaborator);
    }

    public boolean isAvailable(WorkPeriod taskWorkPeriod) {
        System.out.println();
        for(WorkPeriod wr : workPeriods){
            System.out.println(workPeriods);
        }

        if (this.workPeriods.isEmpty()) {
            return true;
        }

        for (WorkPeriod workPeriod : workPeriods) {
            if (workPeriod.overlapsWith(taskWorkPeriod)) {
                return false;
            }
        }
        return true;
    }

    // Add work period for assigned task
    public void addWorkPeriod(WorkPeriod workPeriod) {
        workPeriods.add(workPeriod);
    }


}
