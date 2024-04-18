package pt.ipp.isep.dei.esoft.project.domain;

public class Address {
    private String street;
    private int streetNumber;
    private String postalCode;
    private String city;
    private String district;

    private enum PostalCodeValidationResults {
        INVALID_FORMAT, VALID, CONTAIN_LETTERS, EMPTY

    }

    private static final int POSTAL_CODE_TOTAL_DIGITS = 8;
    private static final char POSTAL_CODE_SEPARATOR = '-';

    public Address(String street, int streetNumber, String postalCode, String city, String district) {
        setStreet(street);
        setStreetNumber(streetNumber);
        setPostalCode(postalCode);
        setCity(city);
        setDistrict(district);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (validateStreet(street)) {
            this.street = street;
        } else {
            throw new IllegalArgumentException("Street must not be empty!");
        }
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        if (validateStreetNumber(streetNumber)) {
            this.streetNumber = streetNumber;
        } else {
            throw new IllegalArgumentException("Street number must be a positive number!");
        }
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCodeValidationResults postalCodeValidationResults = validatePostalCode(postalCode);
        switch (postalCodeValidationResults) {
            case EMPTY:
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (validateCity(city)) {
            this.city = city;
        } else {
            throw new IllegalArgumentException("City must not be empty");
        }
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        if(validateDistrict(district)){
            this.district=district;
        }else {
            throw new IllegalArgumentException("District must not be empty");
        }
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
     * Validates if the street is not null or empty
     *
     * @param street
     * @return the logical state of the validation. True if street is validated (not null and not empty)
     */
    private static boolean validateStreet(String street) {
        return street != null && !street.trim().isEmpty();
    }

    private static boolean validateStreetNumber(int number) {
        return number > 0;
    }

    /**
     * Validates if the Postal Code is valid with the format XXXX-XXX (where X is an integer)
     *
     * @param postalCode
     * @return the logical state of the validation. True if Postal Code is validated
     */
    private static PostalCodeValidationResults validatePostalCode(String postalCode) {

        if (postalCode == null || postalCode.trim().isEmpty()) {
            return PostalCodeValidationResults.EMPTY;
        }

        if (postalCode.length() != POSTAL_CODE_TOTAL_DIGITS || postalCode.charAt(5) != POSTAL_CODE_SEPARATOR) {
            return PostalCodeValidationResults.INVALID_FORMAT;
        }

        char[] postalCodeByLetters = postalCode.toCharArray();

        for (int i = 0; i < 4; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                return PostalCodeValidationResults.CONTAIN_LETTERS;
            }
        }

        for (int i = 5; i < postalCodeByLetters.length; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                return PostalCodeValidationResults.CONTAIN_LETTERS;
            }
        }
        return PostalCodeValidationResults.VALID;
    }

    /**
     * Validates if City is not null or empty
     *
     * @param city
     * @return
     */
    private static boolean validateCity(String city) {
        return city != null && !city.trim().isEmpty();
    }

    /**
     * Validates if District is not null or empty
     *
     * @param district
     * @return
     */
    private static boolean validateDistrict(String district) {
        return district != null && !district.trim().isEmpty();
    }


}
