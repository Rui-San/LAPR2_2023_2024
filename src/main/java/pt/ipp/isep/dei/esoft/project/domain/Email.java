package pt.ipp.isep.dei.esoft.project.domain;

public class Email {

    private String email;

    private static final int NUMBER_PARTS_OF_DOMAIN = 2;
    private static final String COM_EXTENTION = ".com";
    private static final String PT_EXTENTION = ".pt";

    public Email(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else{
            // criar a class para as exceções
            // throw new InvalidEmailExeption("Endereço de email inválido! Introduza novamente.");
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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


}
