package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents an Email object.
 * This class provides functionality to validate an email address.
 */
public class Email {

    /**
     * The email address.
     */
    private String email;

    /**
     * Type Enumerated, enumerating all the different results that may occur during an email validation.
     */
    private enum ValidateEmailResults {
        /**
         * Email is empty.
         */
        EMPTY,
        /**
         * Email is valid.
         */
        VALID,
        /**
         * Prefix of email is invalid.
         */
        INVALID_PREFIX,
        /**
         * Domain of email is invalid.
         */
        INVALID_DOMAIN,
        /**
         * Email has wrong format.
         */
        WRONG_FORMAT
    }

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
     * @param email
     */
    public void setEmail(String email) {
        ValidateEmailResults validateEmailResults = validateEmail(email);
        switch (validateEmailResults) {
            case EMPTY:
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
     *
     * @param email
     * @return the logical state of the validation. Valid if the return is enumerate type VALID.
     */
    private ValidateEmailResults validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return ValidateEmailResults.EMPTY;
        }

        String[] parts = email.split("@");

        if (parts.length != 2) {
            return ValidateEmailResults.WRONG_FORMAT;
        }

        String prefix = parts[0];
        String domain = parts[1];

        if (prefix.isEmpty() || domain.isEmpty()) {
            return ValidateEmailResults.WRONG_FORMAT;
        }

        String prefixPattern = "^[a-zA-Z0-9_.-]+$";
        String domainPattern = "^(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (!prefix.matches(prefixPattern)) {
            return ValidateEmailResults.INVALID_PREFIX;
        }

        if (!domain.matches(domainPattern)) {
            return ValidateEmailResults.INVALID_DOMAIN;
        }
        return ValidateEmailResults.VALID;
    }
}
