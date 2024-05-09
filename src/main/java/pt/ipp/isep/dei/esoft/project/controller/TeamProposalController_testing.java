package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

public class TeamProposalController_testing {

    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;
    private TeamRepository_testing teamRepository_testing;


    public TeamProposalController_testing(SkillRepository skillRepository, CollaboratorRepository collaboratorRepository, TeamRepository_testing teamRepository_testing) {
        this.skillRepository = skillRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.teamRepository_testing = teamRepository_testing;
    }

    public TeamProposalController_testing() {
        getSkillRepository();
        getCollaboratorRepository();
        getTeamRepository_testing();
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private TeamRepository_testing getTeamRepository_testing() {
        if (teamRepository_testing == null) {
            Repositories repositories = Repositories.getInstance();

            teamRepository_testing = repositories.getTeamRepository_testing();
        }
        return teamRepository_testing;
    }


    public List<Skill> getSkillList() {
        SkillRepository skillRepository = getSkillRepository();
        return skillRepository.getSkillList();
    }

    public List<Collaborator> getCollaboratorList() {
        CollaboratorRepository collaboratorRepository = getCollaboratorRepository();
        return collaboratorRepository.getCollaboratorList();
    }


    public List<Team> generateAllTeamProposal(int minTeamSize, int maxTeamSize, List<Skill> skillsNeeded, List<Integer> quantityNeeded) {

        List<Team> teamsGenerated;

        List<Collaborator> collaboratorList = getCollaboratorList();

        teamsGenerated = teamRepository_testing.generateAllTeamProposals(minTeamSize, maxTeamSize, skillsNeeded, quantityNeeded, collaboratorList);

        return teamsGenerated;

    }

    public void saveTeamProposal(Team teamAccepted) {
        getTeamRepository_testing().saveTeamProposal(teamAccepted);
    }
}
