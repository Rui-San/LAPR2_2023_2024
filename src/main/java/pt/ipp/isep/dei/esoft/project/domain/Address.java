package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.SerializationFiles;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.io.Serializable;

/**
 * Represents an Address object containing street, street number, postal code, city, and district.
 */
public class Address implements Cloneable, Serializable {

    /**
     * The street of the address.
     */
    private String street;
    /**
     * The street number of the address.
     */
    private int streetNumber;
    /**
     * The postal code of the address.
     */
    private String postalCode;
    /**
     * The city of the address.
     */
    private String city;
    /**
     * The district of the address.
     */
    private String district;

    /**
     * Total digits that a postal code contain.
     */
    private static final int POSTAL_CODE_TOTAL_DIGITS = 8;

    /**
     * Separator used in a postal code.
     */
    private static final char POSTAL_CODE_SEPARATOR = '-';


    /**
     * Constructs an Address object with the given parameters.
     *
     * @param street       the street of the address
     * @param streetNumber the street number of the address
     * @param postalCode   the postal code of the address
     * @param city         the city of the address
     * @param district     the district of the address
     */
    public Address(String street, int streetNumber, String postalCode, String city, String district) {
        setStreet(street);
        setStreetNumber(streetNumber);
        setPostalCode(postalCode);
        setCity(city);
        setDistrict(district);
    }

    /**
     * Returns the street of the address.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street of the address after validation.
     * If the street to be set is not valid, throws exception.
     *
     * @param street the street to be set
     * @throws IllegalArgumentException if the street is empty
     */
    public void setStreet(String street) {
        if (validateStreet(street)) {
            this.street = street;
        } else {
            throw new IllegalArgumentException("Street must not be empty!");
        }
    }

    /**
     * Returns the street number of the address.
     *
     * @return the street number
     */
    public int getStreetNumber() {
        return streetNumber;
    }

    /**
     * Sets the street number of the address after validation.
     * If the street number to be set is not valid, throws exception.
     *
     * @param streetNumber the street number to be set
     * @throws IllegalArgumentException if the street number is not positive
     */
    public void setStreetNumber(int streetNumber) {
        if (validateStreetNumber(streetNumber)) {
            this.streetNumber = streetNumber;
        } else {
            throw new IllegalArgumentException("Street number must be a positive integer!");
        }
    }

    /**
     * Returns the postal code of the address.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the address after validation.
     * If the postal code is not valid, throws specific exception based on error that occured.
     *
     * @param postalCode the postal code to be set
     * @throws IllegalArgumentException if the postal code is invalid
     */
    public void setPostalCode(String postalCode) {
        ValidationAttributeResults postalCodeValidationResults = validatePostalCode(postalCode);
        switch (postalCodeValidationResults) {
            case EMPTYNULL:
                throw new IllegalArgumentException("Postal code must not be empty");
            case INVALID_FORMAT:
                throw new IllegalArgumentException("Postal code must follow the format XXXX-XXX");
            case CONTAIN_LETTERS:
                throw new IllegalArgumentException("Postal code must not contain letters");
            case VALID:
                this.postalCode = postalCode;
                break;
        }
    }

    /**
     * Returns the city of the address.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the address after validation.
     * If the city to be set is not valid, throws exception.
     *
     * @param city the city to be set
     * @throws IllegalArgumentException if the city is empty
     */
    public void setCity(String city) {
        if (validateCity(city)) {
            this.city = city;
        } else {
            throw new IllegalArgumentException("City is empty");
        }
    }

    /**
     * Returns the district of the address.
     *
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the district of the address after validation.
     * If the district to be set is not valid, throws exception.
     *
     * @param district the district to be set
     * @throws IllegalArgumentException if the district is empty
     */
    public void setDistrict(String district) {
        if (validateDistrict(district)) {
            this.district = district;
        } else {
            throw new IllegalArgumentException("District is empty");
        }
    }

    /**
     * Returns a string representation of the address.
     *
     * @return a string representation of the address
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(street).append(" ");
        stringBuilder.append(streetNumber).append(" ");
        stringBuilder.append(", ").append(postalCode);
        stringBuilder.append(city);
        return stringBuilder.toString();
    }

    /**
     * Validates if the street is not null or empty.
     *
     * @param street the street to be validated
     * @return the logical state of the validation. True if street is validated (not null and not empty), false otherwise
     */
    private boolean validateStreet(String street) {
        return street != null && !street.trim().isEmpty();
    }

    /**
     * Validates if street number is valid.
     *
     * @param number the street number to be validated
     * @return true if the street number is a positive intefer, false otherwise
     */
    private boolean validateStreetNumber(Integer number) {
        return number > 0;
    }

    /**
     * Validates if the Postal Code is valid with the format XXXX-XXX (where X is an integer).
     * Return enumerated type VALID when the postal code is valid. Possible results when the postal code is not valid include: EMPTY, INVALID_FORMAT, and CONTAIN_LETTERS.
     *
     * @param postalCode the postal code to be validated
     * @return an enumerate type depending on the result
     */
    private ValidationAttributeResults validatePostalCode(String postalCode) {

        if (postalCode == null || postalCode.trim().isEmpty()) {
            return ValidationAttributeResults.EMPTYNULL;
        }

        if (postalCode.length() != POSTAL_CODE_TOTAL_DIGITS || postalCode.charAt(4) != POSTAL_CODE_SEPARATOR) {
            return ValidationAttributeResults.INVALID_FORMAT;
        }

        char[] postalCodeByLetters = postalCode.toCharArray();

        for (int i = 0; i < 4; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                return ValidationAttributeResults.CONTAIN_LETTERS;
            }
        }

        for (int i = 5; i < postalCodeByLetters.length; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                return ValidationAttributeResults.CONTAIN_LETTERS;
            }
        }
        return ValidationAttributeResults.VALID;
    }

    /**
     * Validates if City is not null or empty.
     *
     * @param city the city to be validated
     * @return true if the city is not null or empty, false otherwise
     */
    private boolean validateCity(String city) {

        return city != null && !city.trim().isEmpty();
    }

    /**
     * Validates if District is not null or empty.
     *
     * @param district the district to be validated
     * @return true if the district is not null or empty, false otherwise
     */
    private boolean validateDistrict(String district) {
        return district != null && !district.trim().isEmpty();
    }

    /**
     * Creates a deep copy of the Address.
     *
     * @return the cloned Address
     */
    @Override
    public Address clone() {
        return new Address(this.street, this.streetNumber, this.postalCode, this.city, this.district);
    }


}
