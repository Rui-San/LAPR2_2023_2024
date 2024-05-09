package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;


/**
 * Represents an Email object.
 * This class provides functionality to validate an email address.
 */
public class Email implements Cloneable {

    /**
     * The email address.
     */
    private String email;


    /**
     * Constructs an Email object with the given email address.
     *
     * @param email the email address
     */
    public Email(String email) {
        setEmail(email);
    }

    /**
     * Returns the email string
     *
     * @return the email string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for the Email object after validating it.
     * If the email is not validated, throws specific exception based on error that occured
     *
     * @param email the email to be set.
     */
    public void setEmail(String email) {
        ValidationAttributeResults validateEmailResults = validateEmail(email);
        switch (validateEmailResults) {
            case EMPTYNULL:
                throw new IllegalArgumentException("Email must not be empty");
            case WRONG_FORMAT:
                throw new IllegalArgumentException("Email format must follow the pattern prefix@domain");
            case INVALID_PREFIX:
                throw new IllegalArgumentException("Email prefix not valid. Only letters, numbers and _ . - are accepted");
            case INVALID_DOMAIN:
                throw new IllegalArgumentException("Email domain not valid.");
            case VALID:
                this.email = email;
                break;
        }
    }


    /**
     * Validates if email is valid or not by returning a ValidateEmailResults enumerate type.
     * Return enumerated type VALID when the email is valid. Possible results when the email is not valid include: EMPTY, WRONG_FORMAT, INVALID_PREFIX, INVALID_DOMAIN.
     *
     * @param email the email to be validated
     * @return an enumerate type depending on the result
     */
    private ValidationAttributeResults validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return ValidationAttributeResults.EMPTYNULL;
        }

        String[] parts = email.split("@");

        if (parts.length != 2) {
            return ValidationAttributeResults.WRONG_FORMAT;
        }

        String prefix = parts[0];
        String domain = parts[1];

        if (prefix.isEmpty() || domain.isEmpty()) {
            return ValidationAttributeResults.WRONG_FORMAT;
        }

        String prefixPattern = "^[a-zA-Z0-9_.-]+$";
        String domainPattern = "^(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (!prefix.matches(prefixPattern)) {
            return ValidationAttributeResults.INVALID_PREFIX;
        }

        if (!domain.matches(domainPattern)) {
            return ValidationAttributeResults.INVALID_DOMAIN;
        }
        return ValidationAttributeResults.VALID;
    }

    /**
     * Returns a string representation of the Email
     *
     * @return Returns a string representation of the Email
     */
    @Override
    public String toString() {
        return "Email: " + email;
    }

    /**
     * Creates a deep copy of the Email.
     *
     * @return the cloned Email
     */
    @Override
    public Email clone() {
        return new Email(this.email);
    }
}
