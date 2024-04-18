package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Collaborator {
    private String name;
    private Date birthdate;
    private Date admissionDate;
    private Address address;
    private Email email;
    private int mobileNumber;

    private enum IdDocType {
        CC, Passport
    }

    private IdDocType idDocType;

    private int idDocNumber;
    private List<Skill> skillList;

    private enum NameValidationResults {
        VALID, EMPTYNULL, CONTAINS_SPECIAL_CHARACTERS
    }


    public Collaborator(String name, Date birthdate, Date admissionDate, String street, int streetNumber, String postalCode,
                        String city, String district, String email, int mobileNumber, IdDocType idDocType, int idDocNumber) {
        setName(name);
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
        this.address = new Address(street, streetNumber, postalCode, city, district);
        this.email = new Email(email);
        this.mobileNumber = mobileNumber;
        this.idDocType = idDocType;
        this.idDocNumber = idDocNumber;
        this.skillList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        NameValidationResults nameValidationResult = validateName(name);
        switch (nameValidationResult) {
            case EMPTYNULL:
                throw new IllegalArgumentException("Name must not be empty");
            case CONTAINS_SPECIAL_CHARACTERS:
                throw new IllegalArgumentException("Name must not contain special characters");
            case VALID:
                this.name = name;
                break;
        }
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setEmail(email);
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public IdDocType getIdDocType() {
        return idDocType;
    }

    public void setIdDocType(IdDocType idDocType) {
        this.idDocType = idDocType;
    }

    public int getIdDocNumber() {
        return idDocNumber;
    }

    public void setIdDocNumber(int idDocNumber) {
        this.idDocNumber = idDocNumber;
    }

    @Override
    public String toString() {
        return "Collaborator{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", admissionDate=" + admissionDate +
                ", address=" + address +
                ", email=" + email +
                ", mobileNumber=" + mobileNumber +
                ", idDocType='" + idDocType + '\'' +
                ", idDocNumber=" + idDocNumber +
                '}';
    }

    /**
     * Assign selected skills to a collaborator (1 or more skills)
     *
     * @param skillsToAdd
     */
    public void assignSkills(List<Skill> skillsToAdd) {
        skillList.addAll(skillsToAdd);
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
     * Validates if name contains special characters.
     *
     * @param name
     * @return the logical state of the validation. True if name is not null, not empty and the only permited characters are spaces and hifens.
     */
    private NameValidationResults validateName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return NameValidationResults.EMPTYNULL;
        }

        Pattern namePattern = Pattern.compile("[a-zA-Z\\\\s-]+");

        if (namePattern.matcher(name).matches()) {
            return NameValidationResults.VALID;
        } else {
            return NameValidationResults.CONTAINS_SPECIAL_CHARACTERS;
        }
    }


}

