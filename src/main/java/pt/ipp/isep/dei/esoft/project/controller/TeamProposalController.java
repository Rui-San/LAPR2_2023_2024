package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

public class TeamProposalController {

    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;
    private TeamRepository teamRepository_;


    public TeamProposalController(SkillRepository skillRepository, CollaboratorRepository collaboratorRepository, TeamRepository teamRepository_) {
        this.skillRepository = skillRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.teamRepository_ = teamRepository_;
    }

    public TeamProposalController() {
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

    private TeamRepository getTeamRepository_testing() {
        if (teamRepository_ == null) {
            Repositories repositories = Repositories.getInstance();

            teamRepository_ = repositories.getTeamRepository_testing();
        }
        return teamRepository_;
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

        teamsGenerated = teamRepository_.generateAllTeamProposals(minTeamSize, maxTeamSize, skillsNeeded, quantityNeeded, collaboratorList);

        return teamsGenerated;

    }

    public void saveTeamProposal(Team teamAccepted) {
        getTeamRepository_testing().saveTeamProposal(teamAccepted);
    }
}
