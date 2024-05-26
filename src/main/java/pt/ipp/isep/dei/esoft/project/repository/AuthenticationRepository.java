package pt.ipp.isep.dei.esoft.project.repository;

import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

/**
 * The AuthenticationRepository class
 */
public class AuthenticationRepository {
    private final AuthFacade authenticationFacade;

    public AuthFacade getAuthenticationFacade() {
        return authenticationFacade;
    }

    /**
     * Constructs an AuthenticationRepository object.
     */
    public AuthenticationRepository() {
        authenticationFacade = new AuthFacade();
    }

    /**
     * Performs login with the provided email and password.
     *
     * @param email The email of the user.
     * @param pwd   The password of the user.
     * @return True if login is successful, false otherwise.
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Performs Log out of the current user.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Retrieves the session of the currently logged-in user.
     *
     * @return The user session.
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Adds a new user role with the specified ID and description.
     *
     * @param id          The ID of the role.
     * @param description The description of the role.
     * @return True if the role is successfully added, false otherwise.
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Adds a new user with the specified name, email, password, and role ID.
     *
     * @param name   The name of the user.
     * @param email  The email of the user.
     * @param pwd    The password of the user.
     * @param roleId The ID of the role to assign to the user.
     * @return True if the user is successfully added, false otherwise.
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }
}