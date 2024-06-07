package pt.ipp.isep.dei.esoft.project.tools;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class GmailService implements EmailSender{

    @Override
    public void sendEmailToCollaborator(String collaboratorEmail, String subject, String body) {
        File collaboratorInbox = new File("emails/" + collaboratorEmail + "/inbox/");
        if (!collaboratorInbox.exists()) {
            collaboratorInbox.mkdirs();
        }
        FileWriter writer = null;
        try {
            //turn subject into a valid file name
            String filename = subject.toLowerCase().replace(" ", "_");
            writer = new FileWriter(collaboratorInbox + "/" + filename + ".txt");
            LocalDateTime now = LocalDateTime.now();
            writer.write("Time: " + now.toLocalDate() + " - " + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond() + "\n");
            writer.write("From: " + ApplicationSession.getInstance().getProperties().getProperty(ApplicationSession.EMAIL_NOREPLY) + "@gmail.com \n");
            writer.write("To: " + collaboratorEmail + "\n");
            writer.write("Subject: " + subject + "\n");
            writer.write("Body: " + body + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("[Error] Could not write to file.");
        }
    }

}
