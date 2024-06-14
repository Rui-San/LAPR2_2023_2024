package pt.ipp.isep.dei.esoft.project.session;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApplicationSession {
    private final AuthenticationRepository authenticationRepository;
    public static final String CONFIGURATION_FILENAME = "src/main/resources/config.properties";
    public static final String COMPANY_DESIGNATION = "Company.Designation";
    public static final String[] SORTING_ALGORITHMS_OPTIONS = {"BubbleSort", "SelectionSort"};
    public static final String SORTING_ALGORITHM = "Sorting.Algorithm";
    public static final HashMap<String, String> EMAIL_SERVICES_OPTIONS = new HashMap<String, String>() {{
        put("GmailService", "gmail.com");
        put("DEIService", "isep.ipp.pt");
    }};
    public static final String EMAIL_SERVICE = "Email.Service";
    public static final String EMAIL_NOREPLY = "Email.NoReply";
    public static Properties props = null;

    private ApplicationSession() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        Properties props = getProperties();
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public UserSession getCurrentSession() {
        return this.authenticationRepository.getCurrentUserSession();
    }

    public Properties getProperties() {
        if (props == null) {
            props = new Properties();

            // Add default properties and values
            props.setProperty(COMPANY_DESIGNATION, "MusgoSublime");
            props.setProperty(SORTING_ALGORITHM, "BubbleSort");
            props.setProperty(EMAIL_SERVICE, "GmailService");
            props.setProperty(EMAIL_NOREPLY, "musgosubline.noreply");

            // Read configured values
            try {
                InputStream in = new FileInputStream(CONFIGURATION_FILENAME);
                props.load(in);
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return props;
        }else {
            return props;
        }
    }

    private static ApplicationSession singleton = null;

    public static ApplicationSession getInstance() {
        if (singleton == null) {
            synchronized (ApplicationSession.class) {
                singleton = new ApplicationSession();
            }
        }
        return singleton;
    }
}