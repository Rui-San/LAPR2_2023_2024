package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Team_testing {
    private List<Collaborator> members;

    public Team_testing(List<Collaborator> members) {
        this.members = members;
    }

    public Team_testing() {

    }
    public List<Collaborator> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Collaborator member : members) {
            stringBuilder.append("Name: ").append(member.getName()).append(" | ").append(member.getEmail()).append("\n");
        }
        return stringBuilder.toString();
    }

    public void addMember(Collaborator collaborator) {
        members.add(collaborator);
    }



}
