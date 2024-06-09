package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class MemberOfTeam implements Serializable {

    /**
     * Team record
     */
    private Team teamRecord;

    /**
     * Collaborator reference
     */
    private Collaborator collaboratorReference;

    /**
     * Date added to team
     */
    private Date dateAddedToTeam;

    /**
     * Date removed from team (if applicable)
     */
    private Date dateRemovedFromTeam;

    /**
     * Gets the team record
     * @return
     */
    public Team getTeamRecord() {
        return teamRecord;
    }

    /**
     * Gets the collaborator reference
     * @return collaborator reference
     */
    public Collaborator getCollaboratorReference() {
        return collaboratorReference;
    }

    /**
     * Gets the date added to team
     * @return date added to team
     */
    public Date getDateAddedToTeam() {
        return dateAddedToTeam;
    }

    /**
     * Gets the date removed from team
     * @return date removed from team
     */
    public Date getDateRemovedFromTeam() {
        return dateRemovedFromTeam;
    }

    /**
     * Sets the team record
     * @param teamRecord team record
     */
    public void setTeamRecord(Team teamRecord) {
        this.teamRecord = teamRecord;
    }

    /**
     * Sets the collaborator reference
     * @param collaboratorReference collaborator reference
     */
    public void setCollaboratorReference(Collaborator collaboratorReference) {
        this.collaboratorReference = collaboratorReference;
    }

    /**
     * Sets the date added to team
     * @param dateAddedToTeam date added to team
     */
    public void setDateAddedToTeam(Date dateAddedToTeam) {
        this.dateAddedToTeam = dateAddedToTeam;
    }

    /**
     * Sets the date removed from team
     * @param dateRemovedFromTeam date removed from team
     */
    public void setDateRemovedFromTeam(Date dateRemovedFromTeam) {
        this.dateRemovedFromTeam = dateRemovedFromTeam;
    }

    /**
     * Constructs a MemberOfTeam object with specified team record and collaborator reference.
     * @param teamRecord team record
     * @param collaboratorReference collaborator reference
     */
    public MemberOfTeam(Team teamRecord, Collaborator collaboratorReference) {
        setTeamRecord(teamRecord);
        setCollaboratorReference(collaboratorReference);
        setDateAddedToTeam(new Date());
        setDateRemovedFromTeam(null);
    }

}
