package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

/**
 * Controller class responsible for handling operations related to team proposals.
 */
public class TeamProposalController {
    /**
     * Skill Repository
     */
    private SkillRepository skillRepository;

    /**
     * Collaborator Repository
     */
    private CollaboratorRepository collaboratorRepository;

    /**
     * MemberOfTeam Repository
     */
    private MemberOfTeamRepository memberOfTeamRepository;

    /**
     * Team repository
     */
    private TeamRepository teamRepository;

    /**
     * Constructs a TeamProposalController object with specified repositories.
     *
     * @param skillRepository       the skill repository
     * @param collaboratorRepository the collaborator repository
     * @param teamRepository         the team repository
     */
    public TeamProposalController(SkillRepository skillRepository, CollaboratorRepository collaboratorRepository, TeamRepository teamRepository, MemberOfTeamRepository memberOfTeamRepository){
        this.skillRepository = skillRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.teamRepository = teamRepository;
        this.memberOfTeamRepository = memberOfTeamRepository;
    }
    /**
     * Constructor of a TeamProposalController object.
     */
    public TeamProposalController() {
        getSkillRepository();
        getCollaboratorRepository();
        getTeamRepository();
    }

    /**
     * Returns the skill repository
     * @return the skill repository
     */
    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    /**
     * Returns the collaborator repository
     * @return the collaborator repository
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Returns the team repository
     * @return the team repository
     */
    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();

            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    public MemberOfTeamRepository getMemberOfTeamRepository() {
        if (memberOfTeamRepository == null) {
            Repositories repositories = Repositories.getInstance();

            memberOfTeamRepository = repositories.getMemberOfTeamRepository();
        }
        return memberOfTeamRepository;
    }

    /**
     * Returns the list of skill from repository
     * @return the list of skill from repository
     */
    public List<Skill> getSkillList() {
        SkillRepository skillRepository = getSkillRepository();
        return skillRepository.getSkillList();
    }

    /**
     * Returns the list of collaborators from repository
     * @return the list of collaborators from repository
     */
    public List<Collaborator> getCollaboratorList() {
        CollaboratorRepository collaboratorRepository = getCollaboratorRepository();
        return collaboratorRepository.getCollaboratorList();
    }

    /**
     * Method that calls the generation of all possible team proposals based on given parameters.
     *
     * @param minTeamSize    the minimum team size
     * @param maxTeamSize    the maximum team size
     * @param skillsNeeded   the list of skills needed
     * @param quantityNeeded the quantity of each skill needed
     * @return a list with all generated team proposals
     */
    public List<Team> generateAllTeamProposal(int minTeamSize, int maxTeamSize, List<Skill> skillsNeeded, List<Integer> quantityNeeded) {

        List<Team> teamsGenerated;
        List<Collaborator> collaboratorList = getCollaboratorList();
        teamsGenerated = teamRepository.generateAllTeamProposals(minTeamSize, maxTeamSize, skillsNeeded, quantityNeeded, collaboratorList);

        return teamsGenerated;
    }

    /**
     * Saves the team selected team proposal.
     *
     * @param teamAccepted the team proposal to save
     */
    public void saveTeamProposal(Team teamAccepted) {
        for (Collaborator collaborator : teamAccepted.getMembers()) {
            MemberOfTeam memberOfTeam = new MemberOfTeam(teamAccepted, collaborator);
            getMemberOfTeamRepository().getListMemberOfTeam().add(memberOfTeam);
        }
        getTeamRepository().saveTeamProposal(teamAccepted);
    }
}
