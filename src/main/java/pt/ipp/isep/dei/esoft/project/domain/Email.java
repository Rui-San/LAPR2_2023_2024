package pt.ipp.isep.dei.esoft.project.domain;

public class Email {
    private String email;

    private enum ValidateEmailResults {
        EMPTY, VALID, INVALID_PREFIX, INVALID_DOMAIN, WRONG_FORMAT
    }

    public Email(String email) {
        setEmail(email);
    }

    public String getEmail() {
        return email;
    }

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
     * Validates if email format is validated
     *
     * @param email
     * @return the logical state of the validation. True if email format is valid
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
