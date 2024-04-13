package pt.ipp.isep.dei.esoft.project.domain;

public class Collaborator {
    private String name;
    private Date birthdate;
    private Date admissionDate;
    private Address address;
    private Email email;
    private PhoneNumber mobileNumber;
    private String idDocType;
    private int idDocNumber;

    public Collaborator(String name, Date birthdate, Date admissionDate, Address address, Email email, PhoneNumber mobileNumber, String idDocType, int idDocNumber) {
        this.name = name;
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

    public PhoneNumber getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(PhoneNumber mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getIdDocType() {
        return idDocType;
    }

    public void setIdDocType(String idDocType) {
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
}
