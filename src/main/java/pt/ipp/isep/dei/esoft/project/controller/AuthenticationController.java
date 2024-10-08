package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;
import java.util.List;

/**
 * Controller class responsible for handling authentication-related operations.
 */
public class AuthenticationController {
    /**
     * Constant representing the administrator role.
     */
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    /**
     * Constant representing the human resources manager role.
     */
    public static final String ROLE_HRM = "HUMAN RESOURCES MANAGER";
    /**
     * Constant representing the vehicle fleet manager role.
     */
    public static final String ROLE_VFM = "VEHICLE FLEET MANAGER";
    /**
     * Constant representing the green space manager role.
     */
    public static final String ROLE_GSM = "GREEN SPACE MANAGER";

    public static final String ROLE_COLLABORATOR = "COLLABORATOR";


    private final ApplicationSession applicationSession;

    /**
     * Constructs an AuthenticationController object.
     */
    public AuthenticationController() {
        this.applicationSession = ApplicationSession.getInstance();
    }

    /**
     * Performs a login operation.
     *
     * @param email the email of the user
     * @param pwd   the password of the user
     * @return true if login is successful, false otherwise
     */
    public boolean doLogin(String email, String pwd) {
        try {
            return this.applicationSession.getAuthenticationRepository().doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Retrieves the roles associated with the current user.
     *
     * @return a list of UserRoleDTO representing the roles of the current user, or null if the user is not logged in
     */
    public List<UserRoleDTO> getUserRoles() {
        if (this.applicationSession.getAuthenticationRepository().getCurrentUserSession().isLoggedIn()) {
            return this.applicationSession.getAuthenticationRepository().getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    /**
     * Performs a logout operation.
     */
    public void doLogout() {
        this.applicationSession.getAuthenticationRepository().doLogout();
    }
}