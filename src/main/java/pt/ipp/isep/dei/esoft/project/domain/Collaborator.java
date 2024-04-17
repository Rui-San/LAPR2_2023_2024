package pt.ipp.isep.dei.esoft.project.domain;

import java.util.regex.Pattern;

public class Collaborator {
    private String name;
    private Date birthdate;
    private Date admissionDate;
    private Address address;
    private Email email;
    private int mobileNumber;

    public static enum IdDocType {
        CC, Passport
    }

    private IdDocType idDocType;

    private int idDocNumber;



    public Collaborator(String name, Date birthdate, Date admissionDate, Address address, Email email, int mobileNumber, IdDocType idDocType, int idDocNumber) {
        if (validateName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name is empty, please fill in the field!");
        }
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.idDocType = idDocType;
        this.idDocNumber = idDocNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setEmail(Email email) {
        this.email = email;
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
     * @return the logical state of the validation. True if name doesn't have special characters.
     */
    private boolean validateName(String name) {

        if (!validateStringNotNullOrEmpty(name)) {
            return false;
        }

        Pattern namePattern = Pattern.compile("[a-zA-Z\\\\s-]+");

        if (namePattern.matcher(name).matches()) {
            return true;
        } else {
            return false;
        }
    }


}

