package pt.ipp.isep.dei.esoft.project.domain;

import java.util.regex.Pattern;

public class Email {

    private String email;

    private static final int NUMBER_PARTS_OF_DOMAIN = 2;
    private static final String COM_EXTENTION = ".com";
    private static final String PT_EXTENTION = ".pt";

    public Email(String email) {
        if (validateEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Endereço de email inválido! Introduza novamente.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Validates if the String is not null or empty
     *
     * @param string
     * @return the logical state of the validation. True if String is not empty/null
     */
    private static boolean validateStringNotNullOrEmpty(String string) {
        return !(string == null) && !(string.isEmpty());
    }

    /**
     * Validates if email format is validated (my algorithm)
     *
     * @param email
     * @return the logical state of the validation. True if email format is valid
     */
    private boolean isValidEmail(String email) {

        if (!email.contains("@")) {
            return false;
        }

        String[] emailParts = email.split("@");
        String emailName = emailParts[0];
        String emailDomain = emailParts[1];

        String[] domainParts = emailDomain.split("\\.");

        if (domainParts.length != NUMBER_PARTS_OF_DOMAIN) {
            return false;
        }

        String domain = domainParts[0];
        String extension = domainParts[1];

        return ((emailName.length() > 1) && (domain.length() > 1) && (extension.equals(COM_EXTENTION) || extension.equals(PT_EXTENTION)));

    }

    /**
     * Validates if email format is validated (regex patern algorithm)
     *
     * @param email
     * @return the logical state of the validation. True if email format is valid
     */
    private boolean validateEmail(String email) {
        if (!validateStringNotNullOrEmpty(email)) {
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);

        if (pat.matcher(email).matches()) {
            return true;
        } else {
            return false;
        }
    }


}
