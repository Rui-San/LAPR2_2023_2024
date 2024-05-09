package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Collaborator> members;

    public Team(List<Collaborator> members) {
        this.members = members;
    }

    public List<Collaborator> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Collaborator member : members) {
            stringBuilder.append("Name: ").append(member.getName()).append(" | ")
                    .append("Email: ").append(member.getEmail()).append(" | ")
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

    public void addMember(Collaborator collaborator) {
        members.add(collaborator);
    }



}
