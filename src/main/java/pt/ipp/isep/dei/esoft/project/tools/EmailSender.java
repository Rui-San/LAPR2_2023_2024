package pt.ipp.isep.dei.esoft.project.tools;

public interface EmailSender {

    public void sendEmailToCollaborator(String to, String subject, String body);

}
