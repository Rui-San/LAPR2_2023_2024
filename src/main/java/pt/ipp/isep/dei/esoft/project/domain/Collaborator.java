package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.MobileOperator;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Represents a collaborator containing a name, birthdate, admission date, job, an address, an email, mobile number, type of ID document, ID number and a list of skills.
 */
public class Collaborator implements Cloneable {

    /**
     * The name of the collaborator.
     */
    private String name;

    /**
     * The birthdate of the collaborator.
     */
    private Date birthdate;
    /**
     * The admission date of the collaborator.
     */
    private Date admissionDate;

    /**
     * The job of the collaborator.
     */
    private Job job;

    /**
     * The address of the collaborator.
     */
    private Address address;

    /**
     * The email of the collaborator.
     */
    private Email email;

    /**
     * The mobile number of the collaborator.
     */
    private String mobileNumber;

    /**
     * Type enumerated, representing all the different ID document types that a collaborator can have.
     */
    public enum IdDocType {
        CC, BI, PASSPORT
    }

    /**
     * The type of the ID document of the collaborator.
     */
    private IdDocType idDocType;



    /**
     * The ID number of the collaborator's document.
     */
    private String idDocNumber;

    /**
     * The list of skills of the collaborator.
     */
    private List<Skill> skillList;

    /**
     * The total number of digits in a mobile number.
     */
    private static final int MOBILE_NUMBER_TOTAL_DIGITS = 9;

    /**
     * Constructs a new Collaborator with the specified attributes.
     *
     * @param name          the name of the collaborator
     * @param birthdate     the birthdate of the collaborator
     * @param admissionDate the admission date of the collaborator
     * @param street        the street of the collaborator's address
     * @param streetNumber  the street number of the collaborator's address
     * @param postalCode    the postal code of the collaborator's address
     * @param city          the city of the collaborator's address
     * @param district      the district of the collaborator's address
     * @param email         the email of the collaborator
     * @param mobileNumber  the mobile number of the collaborator
     * @param idDocType     the type of ID document of the collaborator
     * @param idDocNumber   the ID number of the collaborator's document
     * @param job           the job of the collaborator
     */
    public Collaborator(String name, String birthdate, String admissionDate, String street, int streetNumber, String postalCode,
                        String city, String district, String email, String mobileNumber, IdDocType idDocType, String idDocNumber, Job job) {
        setName(name);
        setBirthdate(birthdate);
        setAdmissionDate(admissionDate);
        setAddress(street, streetNumber, postalCode, city, district);
        setEmail(email);
        this.job = job;
        setMobileNumber(mobileNumber);
        setIdDocType(idDocType);
        setIdDocNumber(idDocNumber, idDocType);
        this.skillList = new ArrayList<>();
    }

    /**
     * Constructs a new Collaborator with the specified attributes.
     * Used for the clone method, in order to be a successful clone.
     *
     * @param name          the name of the collaborator
     * @param birthdate     the birthdate of the collaborator
     * @param admissionDate the admission date of the collaborator
     * @param address       the address of the collaborator
     * @param email         the email of the collaborator
     * @param mobileNumber  the mobile number of the collaborator
     * @param idDocType     the type of ID document of the collaborator
     * @param idDocNumber   the ID number of the collaborator's document
     * @param job           the job of the collaborator
     */
    public Collaborator(String name, Date birthdate, Date admissionDate, Address address, Email email, String mobileNumber, IdDocType idDocType, String idDocNumber, Job job) {
        setName(name);
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.email = email;
        this.job = job;
        setMobileNumber(mobileNumber);
        setIdDocType(idDocType);
        setIdDocNumber(idDocNumber, idDocType);
        this.skillList = new ArrayList<>();
    }

    /**
     * Returns the name of the collaborator.
     *
     * @return the name of the collaborator
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the collaborator after validating name.
     * If the name to be set is not valid, throws exception based on the specific error that occured.
     *
     * @param name the name to set
     * @throws IllegalArgumentException if the name is empty, contains special characters, does not include at least one first name and one last name, or contains more than 6 words
     */
    public void setName(String name) {
        ValidationAttributeResults nameValidationResult = validateName(name);
        switch (nameValidationResult) {
            case EMPTYNULL:
                throw new IllegalArgumentException("Name must not be empty");
            case CONTAINS_SPECIAL_CHARACTERS:
                throw new IllegalArgumentException("Name must not contain special characters");
            case NOT_ENOUGH_NAMES:
                throw new IllegalArgumentException("Name must include at least one first name and one last name.");
            case TOO_MANY_WORDS:
                throw new IllegalArgumentException("Name must not contain more than 6 words, according to Portuguese Law");
            case VALID:
                this.name = name;
                break;
        }
    }

    /**
     * Returns the birthdate of the collaborator.
     *
     * @return the birthdate of the collaborator
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the birthdate of the collaborator.
     *
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = new Date(birthdate);
    }

    /**
     * Returns the admission date of the collaborator.
     *
     * @return the admission date of the collaborator
     */
    public Date getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Sets the admission date of the collaborator.
     *
     * @param admissionDate the admission date to set
     */
    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = new Date(admissionDate);
    }

    /**
     * Returns the address of the collaborator.
     *
     * @return the address of the collaborator
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address of the collaborator.
     *
     * @param street       the street of the collaborator's address
     * @param streetNumber the street number of the collaborator's address
     * @param postalCode   the postal code of the collaborator's address
     * @param city         the city of the collaborator's address
     * @param district     the district of the collaborator's address
     */
    public void setAddress(String street, int streetNumber, String postalCode, String city, String district) {
        this.address = new Address(street, streetNumber, postalCode, city, district);
    }

    /**
     * Returns the job of the collaborator.
     *
     * @return the job of the collaborator
     */
    public Job getJob() {
        return job;
    }

    /**
     * Sets the job of the collaborator.
     *
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * Returns the email of the collaborator.
     *
     * @return the email of the collaborator
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Sets the email of the collaborator.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = new Email(email);
    }

    /**
     * Returns the list of skills of the collaborator.
     *
     * @return the list of skills of the collaborator
     */
    public List<Skill> getSkillList() {
        return skillList;
    }

    /**
     * Returns the mobile number of the collaborator.
     *
     * @return the mobile number of the collaborator
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Sets the mobile number of the collaborator after validating it.
     *
     * @param mobileNumber the mobile number to set
     * @throws IllegalArgumentException if the mobile number is not in the correct format
     */
    public void setMobileNumber(String mobileNumber) {
        if (!validateMobileNumber(mobileNumber)) {
            throw new IllegalArgumentException("Mobile Number is not in a correct format");
        }
        this.mobileNumber = mobileNumber;
    }

    /**
     * Returns the ID document type of the collaborator.
     *
     * @return the ID document type of the collaborator.
     */
    public IdDocType getIdDocType() {
        return idDocType;
    }

    /**
     * Sets the type of ID document of the collaborator after validation.
     *
     * @param idDocType the type of ID document to set
     */
    public void setIdDocType(IdDocType idDocType) {
        if (validateIdDocType(idDocType)) {
            this.idDocType = idDocType;
        } else {
            throw new IllegalArgumentException("Document Type is invalid");
        }
    }

    /**
     * Returns the ID number of the collaborator.
     *
     * @return the ID number of the collaborator
     */
    public String getIdDocNumber() {
        return idDocNumber;
    }

    /**
     * Sets the ID number of the collaborator after validation.
     * Depending on the type of document, returns a different enumerated result.
     *
     * @param idDocNumber the ID number to set
     * @param idDocType   the type of ID document
     * @throws IllegalArgumentException if the ID number is not valid (empty, or in the wrong format for the specified ID document type)
     */
    public void setIdDocNumber(String idDocNumber, IdDocType idDocType) {
        ValidationAttributeResults validateIdDocNumberResults = validateIdDocNumber(idDocNumber, idDocType);

        switch (validateIdDocNumberResults) {
            case EMPTYNULL:
                throw new IllegalArgumentException("ID Number must not be empty");
            case PASSPORT_ERROR:
                throw new IllegalArgumentException("Passport in wrong format. Must be two letters + 6 numeric digits (Example: AB222222)");
            case CC_BI_ERROR:
                throw new IllegalArgumentException("NIF in wrong format. Must be 9 numeric digits");
            case VALID:
                this.idDocNumber = idDocNumber;
        }
    }

    /**
     * Generates a string representation of the collaborator.
     *
     * @return the string representation of the collaborator
     */
    @Override
    public String toString() {
        return "Collaborator{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", admissionDate=" + admissionDate +
                ", address=" + address +
                ", email=" + email.toString() +
                ", mobileNumber=" + mobileNumber +
                ", idDocType='" + idDocType + '\'' +
                ", idDocNumber=" + idDocNumber +
                '}';
    }

    /**
     * Checks if the collaborator has at least one of the required skills list.
     *
     * @param requiredSkills the list of required skills
     * @return true if the collaborator has at least one of the required skills, otherwise false
     */
    public boolean hasAtLeastOneSkill(List<Skill> requiredSkills) {
        for (Skill requiredSkill : requiredSkills) {
            if (skillList.contains(requiredSkill)) {
                return true; //
            }
        }
        return false;
    }

    /**
     * Assigns selected skills to a collaborator.
     *
     * @param chosenSkills the list of chosen skills
     */
    public void assignSkills(List<Skill> chosenSkills) {
        skillList.addAll(chosenSkills);
    }

    /**
     * Validates if the name of the collaborator is valid.
     * Return enumerated type VALID when the name is valid. Possible results when the name is not valid include: EMPTYNULL, NOT_ENOUGH_NAMES, TOO_MANY_WORDS and CONTAINS_SPECIAL_CHARACTERS.
     *
     * @param name the name to validate
     * @return the validation result
     */
    private ValidationAttributeResults validateName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return ValidationAttributeResults.EMPTYNULL;
        }

        //("\\s+") means one or more character space
        if (name.split("\\s+").length < 2) {
            return ValidationAttributeResults.NOT_ENOUGH_NAMES;
        }

        //Name, according to Portuguese law must not contain more than 6 words
        if (name.split("\\s+").length > 6) {
            return ValidationAttributeResults.TOO_MANY_WORDS;
        }

        Pattern namePattern = Pattern.compile("[a-zA-Z\\s-]+");

        if (namePattern.matcher(name).matches()) {
            return ValidationAttributeResults.VALID;
        } else {
            return ValidationAttributeResults.CONTAINS_SPECIAL_CHARACTERS;
        }
    }

    /**
     * Validates the mobile number format.
     *
     * @param mobileNumber the mobile number to validate
     * @return true if the mobile number is in the correct format, otherwise false
     */
    private boolean validateMobileNumber(String mobileNumber) {

        if (mobileNumber == null || mobileNumber.trim().isEmpty()) {
            return false;
        }

        String onlyNumericDigits = "[0-9]+";
        if (!mobileNumber.matches(onlyNumericDigits)) {
            return false;
        }

        char[] mobileNumberChars = mobileNumber.toCharArray();

        if (mobileNumberChars.length != MOBILE_NUMBER_TOTAL_DIGITS || mobileNumberChars[0] != '9' || (mobileNumberChars[1] != MobileOperator.OPERATOR1.getOperatorCode() && mobileNumberChars[1] != MobileOperator.OPERATOR2.getOperatorCode() && mobileNumberChars[1] != MobileOperator.OPERATOR3.getOperatorCode())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Validates the ID document type.
     * The valid types are the ones in enumerated IdDocType (CC, PASSPORT, BI)
     *
     * @param idDocType the ID document type to validate
     * @return true if the ID document type is valid, otherwise false
     */
    private boolean validateIdDocType(IdDocType idDocType) {
        return idDocType == IdDocType.CC || idDocType == IdDocType.PASSPORT || idDocType == IdDocType.BI;
    }

    /**
     * Validates the ID document number.
     * Return enumerated type VALID when the ID document number is valid based on ID document type of collaborator.
     * Possible results when the Id document number is not valid include: EMPTY, PASSPORT_ERROR and CC_BI_ERROR.
     *
     * @param idDocNumber the ID document number to validate
     * @param idDocType   the type of ID document
     * @return the validation result
     */
    private ValidationAttributeResults validateIdDocNumber(String idDocNumber, IdDocType idDocType) {
        String nineNumericDigits = "[0-9]{9}";
        String passportPattern = "\\p{Alpha}{2}\\d{6}";

        if (idDocNumber.isEmpty()) {
            return ValidationAttributeResults.EMPTYNULL;
        }

        if (idDocType == IdDocType.PASSPORT) {
            if (idDocNumber.matches(passportPattern)) {
                return ValidationAttributeResults.VALID;
            } else {
                return ValidationAttributeResults.PASSPORT_ERROR;
            }

        } else {
            if (idDocNumber.matches(nineNumericDigits)) {
                return ValidationAttributeResults.VALID;
            } else {
                return ValidationAttributeResults.CC_BI_ERROR;
            }
        }
    }


    /**
     * Creates a deep copy of the collaborator with object atributes in parameter.
     *
     * @return the cloned collaborator
     */
    public Collaborator clone2() {

        Date birthdateClone = this.birthdate.clone();
        Date admissionDateClone = this.admissionDate.clone();
        Address adresssClone = this.address.clone();
        Email emailClone = this.email.clone();

        Collaborator clone = new Collaborator(
                this.name,
                birthdateClone,
                admissionDateClone,
                adresssClone,
                emailClone,
                this.mobileNumber,
                this.idDocType,
                this.idDocNumber,
                this.job
        );

        for (Skill skill : this.skillList) {
            clone.skillList.add(skill.clone());
        }

        return clone;
    }


    /**
     * Creates a deep copy of the collaborator.
     *
     * @return the cloned collaborator
     */
    public Collaborator clone() {
        Collaborator clone = new Collaborator(
                this.name,
                this.birthdate.toString(),
                this.admissionDate.toString(),
                this.address.getStreet(),
                this.address.getStreetNumber(),
                this.address.getPostalCode(),
                this.address.getCity(),
                this.address.getDistrict(),
                this.email.getEmail(),
                this.mobileNumber,
                this.idDocType,
                this.idDocNumber,
                this.job
        );

        for (Skill skill : this.skillList) {
            clone.skillList.add(skill.clone());
        }

        return clone;
    }

}

