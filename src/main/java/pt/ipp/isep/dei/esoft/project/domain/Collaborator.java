package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Collaborator {
    private String name;
    private Date birthdate;
    private Date admissionDate;


    private Job job;
    private Address address;
    private Email email;
    private String mobileNumber;

    private enum IdDocType {
        CC, BI, Passport
    }

    private IdDocType idDocType;


    private enum MobileOperator {
        OPERATOR1('1'),
        OPERATOR2('2'),
        OPERATOR3('3');

        private final char operatorCode;

        MobileOperator(char operatorCode) {
            this.operatorCode = operatorCode;
        }

        public char getOperatorCode() {
            return operatorCode;
        }
    }

    private int idDocNumber;
    private List<Skill> skillList;

    private enum NameValidationResults {
        VALID, EMPTYNULL, NOT_ENOUGH_NAMES, TOO_MANY_WORDS, CONTAINS_SPECIAL_CHARACTERS
    }

    private enum ValidateIdDocNumberResults {
        VALID, PASSPORT_ERROR, EMPTY, CC_BI_ERROR
    }

    private static final int MOBILE_NUMBER_TOTAL_DIGITS = 9;

    public Collaborator(String name, Date birthdate, Date admissionDate, String street, int streetNumber, String postalCode,
                        String city, String district, String email, String mobileNumber, IdDocType idDocType, int idDocNumber, Job job) {
        setName(name);
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
        this.address = new Address(street, streetNumber, postalCode, city, district);
        this.email = new Email(email);
        this.job = job;
        setMobileNumber(mobileNumber);
        setIdDocType(idDocType);
        setIdDocNumber(idDocNumber, idDocType);
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
            case NOT_ENOUGH_NAMES:
                throw new IllegalArgumentException("name must include at least one first name and one last name.");
            case TOO_MANY_WORDS:
                throw new IllegalArgumentException("Name must not contain more than 6 words, according to Portuguese Law");
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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setEmail(email);
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        if (!validateMobileNumber(mobileNumber)) {
            throw new IllegalArgumentException("Mobile Number is not in a correct format");
        }
        this.mobileNumber = mobileNumber;
    }

    public IdDocType getIdDocType() {
        return idDocType;
    }

    public void setIdDocType(IdDocType idDocType) {
        if (validateIdDocType(idDocType)) {
            this.idDocType = idDocType;
        }
    }

    public int getIdDocNumber() {
        return idDocNumber;
    }

    public void setIdDocNumber(int idDocNumber, IdDocType idDocType) {
        ValidateIdDocNumberResults validateIdDocNumberResults = validateIdDocNumberResults(idDocNumber, idDocType);

        switch (validateIdDocNumberResults) {
            case EMPTY:
                throw new IllegalArgumentException("ID Number must not be empty");
            case PASSPORT_ERROR:
                throw new IllegalArgumentException("Passport in wrong format. Must be two letters + 6 numeric digits (Example: AB222222");
            case CC_BI_ERROR:
                throw new IllegalArgumentException("NIF in wrong format. Must be 9 numeric digits");
            case VALID:
                this.idDocNumber = idDocNumber;
        }
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
     * Validates if name contains special characters.
     *
     * @param name
     * @return the logical state of the validation. True if name is not null, not empty and the only permited characters are spaces and hifens.
     */
    private NameValidationResults validateName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return NameValidationResults.EMPTYNULL;
        }

        //("\\s+") means one or more character space
        if (name.split("\\s+").length < 2) {
            return NameValidationResults.NOT_ENOUGH_NAMES;
        }

        //Name, according to Portuguese law must not contain more than 6 words
        if (name.split("\\s+").length > 6) {
            return NameValidationResults.TOO_MANY_WORDS;
        }

        Pattern namePattern = Pattern.compile("[a-zA-Z\\\\s-]+");

        if (namePattern.matcher(name).matches()) {
            return NameValidationResults.VALID;
        } else {
            return NameValidationResults.CONTAINS_SPECIAL_CHARACTERS;
        }
    }

    private boolean validateMobileNumber(String mobileNumber) {

        if (mobileNumber == null || mobileNumber.trim().isEmpty()) {
            return false;
        }

        String onlyNumericDigits = "[0-9]+";
        if (!mobileNumber.matches(onlyNumericDigits)) {
            return false;
        }

        char[] mobileNumberChars = mobileNumber.toCharArray();

        if (mobileNumberChars.length != MOBILE_NUMBER_TOTAL_DIGITS || mobileNumberChars[0] != 9 || mobileNumberChars[1] != MobileOperator.OPERATOR1.getOperatorCode() || mobileNumberChars[1] != MobileOperator.OPERATOR2.getOperatorCode() || mobileNumberChars[1] != MobileOperator.OPERATOR3.getOperatorCode()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateIdDocType(IdDocType idDocType) {
        return idDocType == IdDocType.CC || idDocType == IdDocType.Passport || idDocType == IdDocType.BI;
    }

    private ValidateIdDocNumberResults validateIdDocNumberResults(int idDocNumber, IdDocType idDocType) {
        String nineNumericDigits = "[0-9]{9}";
        String passportPattern = "[a-z][A-Z]{2}[0-9]{6}";

        String idDocNumberString = String.valueOf(idDocNumber);

        if (!idDocNumberString.isEmpty()) {
            return ValidateIdDocNumberResults.EMPTY;
        }

        if (idDocType == IdDocType.Passport) {
            if (idDocNumberString.matches(passportPattern)) {
                return ValidateIdDocNumberResults.VALID;
            } else {
                return ValidateIdDocNumberResults.PASSPORT_ERROR;
            }

        } else {
            if (idDocNumberString.matches(nineNumericDigits)) {
                return ValidateIdDocNumberResults.VALID;
            } else {
                return ValidateIdDocNumberResults.CC_BI_ERROR;
            }
        }
    }
}

