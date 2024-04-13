package pt.ipp.isep.dei.esoft.project.domain;

public class Address {
    private String street;
    private int streetNumber;
    private String postalCode;
    private String city;
    private String district;

    private static final int POSTAL_CODE_TOTAL_DIGITS = 8;
    private static final char POSTAL_CODE_SEPARATOR = '-';

    public Address(String street, int streetNumber, String postalCode, String city, String district) {
        if (validateStreet(street)) {
            this.street = street;
        } else {
            throw new IllegalArgumentException("Street is empty, please fill in the field!");
        }
        if (validateStreetNumber(streetNumber)) {
            this.streetNumber = streetNumber;
        } else {
            throw new IllegalArgumentException("Street Number is empty, please full in the field!");
        }
        if (validatePostalCode(postalCode)) {
            this.postalCode = postalCode;
        } else {
            throw new IllegalArgumentException("Postal Code is invalid, please follow the format XXXX-XXX");
        }
        if (validateCity(city)) {
            this.city = city;
        } else {
            throw new IllegalArgumentException("City is empty, please fill in the field!");
        }
        if (validateDistrict(district)) {
            this.district = district;
        } else {
            throw new IllegalArgumentException("District is empty, please fill in the field!");
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
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
     * Validates if the number is not null or empty
     *
     * @param number
     * @return the logical state of the validation. True if number is not empty/null
     */
    private static boolean isValidPositiveInteger(int number) {
        return number > 0;
    }

    /**
     * @param street
     * @return the logical state of the validation. True if street is validated
     */
    private static boolean validateStreet(String street) {
        return validateStringNotNullOrEmpty(street);
    }

    private static boolean validateStreetNumber(int number) {
        return isValidPositiveInteger(number);
    }

    /**
     * Validates if the Postal Code is valid with the format XXXX-XXX (where X is an integer)
     *
     * @param postalCode
     * @return the logical state of the validation. True if Postal Code is validated
     */
    private static boolean validatePostalCode(String postalCode) {

        if (!validateStringNotNullOrEmpty(postalCode) || postalCode.length() != POSTAL_CODE_TOTAL_DIGITS || postalCode.charAt(5) != POSTAL_CODE_SEPARATOR) {
            return false;
        }

        char[] postalCodeByLetters = postalCode.toCharArray();

        for (int i = 0; i < 4; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                return false;
            }
        }

        for (int i = 5; i < postalCodeByLetters.length; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Validates if City is not null or empty
     *
     * @param city
     * @return
     */
    private static boolean validateCity(String city) {
        return validateStringNotNullOrEmpty(city);
    }

    /**
     * Validates if District is not null or empty
     *
     * @param district
     * @return
     */
    private static boolean validateDistrict(String district) {
        return validateStringNotNullOrEmpty(district);
    }


}
