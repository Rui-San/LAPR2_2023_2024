package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;
import pt.ipp.isep.dei.esoft.project.tools.MobileOperator;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;


import java.util.*;
import java.util.regex.Pattern;

/**
 * Register Collaborator UI class
 */
public class RegisterCollaboratorUI implements Runnable {

    private final RegisterCollaboratorController controller;
    private String name;
    private String birthdate;
    private String admissionDate;
    private String street;
    private int streetNumber;
    private String postalCode;
    private String city;
    private String district;
    private String email;
    private String mobileNumber;
    private IdDocType idDocType;
    private String idDocNumber;
    private Job job;

    /**
     * Constructor for the RegisterCollaboratorUI class
     */
    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
    }

    /**
     * Returns the controller
     * @return the controller
     */
    public RegisterCollaboratorController getRegisterCollaboratorController() {
        return controller;
    }

    /**
     * Runs the Register Collaborator UI
     */
    @Override
    public void run() {
        System.out.println("\n\n=== REGISTER NEW COLLABORATOR ===");

        requestData();
        job = displayAndSelectJob();  //done
        submitData();
    }

    /**
     * Submits the data
     */
    private void submitData() {

        showAllDataForConfirmation(name, birthdate, admissionDate, street, streetNumber, postalCode, city, district, email, mobileNumber, idDocType, idDocNumber, job, "You're about to register the following collaborator:");
        if (Utils.confirm("Do you want to proceed? (y/n)")) {

            Optional<Collaborator> collaborator = getRegisterCollaboratorController().createCollaborator(name, birthdate, admissionDate, street, streetNumber, postalCode, city, district, email, mobileNumber, idDocType, idDocNumber, job);

            if (collaborator.isPresent()) {
                System.out.println("\nCollaborator successfully registered!");
            } else {
                System.out.println("\nThis collaborator is already in the system!");
            }
        }
    }

    /**
     * Displays and selects a job
     * @return the selected job
     */
    private Job displayAndSelectJob() {
        System.out.println("\nList of available jobs:");
        List<Job> jobList = controller.getJobList();

        int listSize = jobList.size();
        int answer = 0;

        Scanner input = new Scanner(System.in);
        displayJobOptions(jobList);
        while (answer < 1 || answer > listSize) {
            System.out.print("\nSelect a job: ");
            try {
                answer = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid Option.");
                input.next();
            }
            if (answer < 1 || answer > listSize) {
                System.out.println("Select an option from the list.");
            }

        }

        Job job = jobList.get(answer - 1);
        return job;
    }

    /**
     * Displays the job options
     * @param jobList the list of jobs
     */
    private void displayJobOptions(List<Job> jobList) {
        int i = 1;
        for (Job job : jobList) {
            System.out.println("  " + i + " - " + job.getJobName());
            i++;
        }
    }

    /**
     * Requests data from the user
     */
    private void requestData() {
        name = requestName();  //done
        birthdate = requestBirthdate();
        admissionDate = requestAdmissionDate();
        street = requestStreet();  //done
        streetNumber = requestStreetNumber(); //done
        postalCode = requestPostalCode();  //done
        city = requestCity(); //done
        district = requestDistrict(); //done
        email = requestEmail(); //done
        mobileNumber = requestMobileNumber();  //done
        idDocType = requestIdDocType(); //done
        idDocNumber = requestIdDocNumber();  //done
    }

    /**
     * Requests the ID document number
     * @return the ID document number
     */
    private String requestIdDocNumber() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nID document number: ");
                response = input.nextLine();

                ValidationAttributeResults validateIdDocNumberResults = validateIdDocNumber(response, idDocType);

                switch (validateIdDocNumberResults) {
                    case EMPTYNULL:
                        throw new IllegalArgumentException("ID Number must not be empty");
                    case PASSPORT_ERROR:
                        throw new IllegalArgumentException("Passport in wrong format. Must be two letters + 6 numeric digits (Example: AB222222)");
                    case CC_BI_ERROR:
                        throw new IllegalArgumentException("NIF in wrong format. Must be 9 numeric digits");
                    case VALID:
                        validInput = true;
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
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
     * Requests the ID document type
     * @return the ID document type
     */
    private IdDocType requestIdDocType() {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        System.out.println("\nAvailable document types:");
        System.out.println("1. CC");
        System.out.println("2. BI");
        System.out.println("3. Passport");


        boolean inputValid = false;
        while (!inputValid) {
            try {
                System.out.print("\nSelect ID Document Type: ");
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        return IdDocType.CC;
                    case 2:
                        return IdDocType.BI;
                    case 3:
                        return IdDocType.PASSPORT;
                    default:
                        inputValid = false;
                        System.out.println("Please select a valid option.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please select a valid option.");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Document Type is invalid");
            }
        }
        return null;
    }

    /**
     * Requests the email
     * @return the email
     */
    private String requestEmail() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nEmail: ");
                response = input.nextLine();

                ValidationAttributeResults validateEmailResults = validateEmail(response);
                switch (validateEmailResults) {
                    case EMPTYNULL:
                        throw new IllegalArgumentException("Email must not be empty");
                    case WRONG_FORMAT:
                        throw new IllegalArgumentException("Email format must follow the pattern prefix@domain");
                    case INVALID_PREFIX:
                        throw new IllegalArgumentException("Email prefix not valid. Only letters, numbers and _ . - are accepted");
                    case INVALID_DOMAIN:
                        throw new IllegalArgumentException("Email domain not valid.");
                    case VALID:
                        validInput = true;
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Validates if email is valid or not by returning a ValidateEmailResults enumerate type.
     * Return enumerated type VALID when the email is valid. Possible results when the email is not valid include: EMPTY, WRONG_FORMAT, INVALID_PREFIX, INVALID_DOMAIN.
     *
     * @param email the email to be validated
     * @return an enumerate type depending on the result
     */
    private ValidationAttributeResults validateEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            return ValidationAttributeResults.EMPTYNULL;
        }

        String[] parts = email.split("@");

        if (parts.length != 2) {
            return ValidationAttributeResults.WRONG_FORMAT;
        }

        String prefix = parts[0];
        String domain = parts[1];

        if (prefix.isEmpty() || domain.isEmpty()) {
            return ValidationAttributeResults.WRONG_FORMAT;
        }

        String prefixPattern = "^[a-zA-Z0-9_.-]+$";
        String domainPattern = "^(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (!prefix.matches(prefixPattern)) {
            return ValidationAttributeResults.INVALID_PREFIX;
        }

        if (!domain.matches(domainPattern)) {
            return ValidationAttributeResults.INVALID_DOMAIN;
        }
        return ValidationAttributeResults.VALID;
    }

    /**
     * Requests the mobile number
     * @return the mobile number
     */
    private String requestMobileNumber() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nMobile Number: ");
                response = input.nextLine();

                if (validateMobileNumber(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Mobile Number is not in a correct format");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Requests the city
     * @return the city
     */
    private String requestCity() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nCity: ");
                response = input.nextLine();

                if (validateCity(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("City is empty");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Validates if the city is not null
     * @param city the city to be validated
     * @return the logical state of the validation. True if city is validated (not null), false otherwise
     */
    private boolean validateCity(String city) {

        return city != null && !city.trim().isEmpty();
    }

    /**
     * Requests the district
     * @return the district
     */
    private String requestDistrict() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nDistrict: ");
                response = input.nextLine();

                if (validateDistrict(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("District is empty");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Validates if the district is not null
     * @param district the district to be validated
     * @return the logical state of the validation. True if district is validated (not null), false otherwise
     */
    private boolean validateDistrict(String district) {
        return district != null && !district.trim().isEmpty();
    }

    /**
     * Requests the postal code
     * @return the postal code
     */
    private String requestPostalCode() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nPostal Code: ");
                response = input.nextLine();

                ValidationAttributeResults postalCodeValidationResults = validatePostalCode(response);

                switch (postalCodeValidationResults) {
                    case EMPTYNULL:
                        throw new IllegalArgumentException("Postal code must not be empty");
                    case INVALID_FORMAT:
                        throw new IllegalArgumentException("Postal code must follow the format XXXX-XXX");
                    case CONTAIN_LETTERS:
                        throw new IllegalArgumentException("Postal code must not contain letters");
                    case VALID:
                        validInput = true;
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Street number must be a positive integer!");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Validates if the Postal Code is valid with the format XXXX-XXX (where X is an integer).
     * Return enumerated type VALID when the postal code is valid. Possible results when the postal code is not valid include: EMPTY, INVALID_FORMAT, and CONTAIN_LETTERS.
     *
     * @param postalCode the postal code to be validated
     * @return an enumerate type depending on the result
     */
    private static ValidationAttributeResults validatePostalCode(String postalCode) {

        if (postalCode == null || postalCode.trim().isEmpty()) {
            return ValidationAttributeResults.EMPTYNULL;
        }

        if (postalCode.length() != 8 || postalCode.charAt(4) != '-') {
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
     * Requests the street number
     * @return the street number
     */
    private int requestStreetNumber() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        int response = 0;

        while (!validInput) {
            try {
                System.out.print("\nStreet Number: ");
                response = input.nextInt();

                if (validateStreetNumber(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Street number must be a positive integer!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Street number must be a positive integer!");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        return response;
    }

    /**
     * Validates if the street number is a positive integer
     * @param number the street number to be validated
     * @return the logical state of the validation. True if street number is validated (positive integer), false otherwise
     */
    private boolean validateStreetNumber(Integer number) {
        return number > 0;
    }

    /**
     * Requests the street
     * @return the street
     */
    private String requestStreet() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nStreet: ");
                response = input.nextLine();

                if (validateStreet(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Street must not be empty!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error :" + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Validates if the street is not null or empty.
     *
     * @param street the street to be validated
     * @return the logical state of the validation. True if street is validated (not null and not empty), false otherwise
     */
    private static boolean validateStreet(String street) {
        return street != null && !street.trim().isEmpty();
    }

    private String requestAdmissionDate() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nAdmission date (format: dd/mm/yyyy): ");
                response = input.nextLine();

                if (validateAdmissionDate(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Collaborator must be 18 years old.");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
        return response;
    }

    /**
     * Validates if the admission date is valid
     * @param response the admission date to be validated
     * @return the logical state of the validation. True if admission date is validated, false otherwise
     */
    private boolean validateAdmissionDate(String response) {
        Date date = new Date(response);
        if (date.getDateDifferenceInDays(new Date(birthdate)) < 6574.5) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Requests the birthdate
     * @return the birthdate
     */
    private String requestBirthdate() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nBirthdate (format: dd/mm/yyyy): ");
                response = input.nextLine();

                if (validateBirthdate(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("Birthdate cannot be in the future.");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
        return response;
    }

    /**
     * Validates if the birthdate is valid
     * @param birthdate the birthdate to be validated
     * @return the logical state of the validation. True if birthdate is validated, false otherwise
     */
    public boolean validateBirthdate(String birthdate) {
        Date date = new Date(birthdate);

        if (date.isPastDate()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Requests the name
     * @return the name
     */
    private String requestName() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nName: ");
                response = input.nextLine();

                ValidationAttributeResults nameValidationResult = validateName(response);

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
                        validInput = true;
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
        return response;
    }

    /**
     * Validates if the name is valid
     * @param name the name to be validated
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

        Pattern namePattern = Pattern.compile("[a-zA-Z\\s-\\p{L}]+");

        if (namePattern.matcher(name).matches()) {
            return ValidationAttributeResults.VALID;
        } else {
            return ValidationAttributeResults.CONTAINS_SPECIAL_CHARACTERS;
        }
    }

    /**
     * Validates if the mobile number is valid
     * @param mobileNumber the mobile number to be validated
     * @return the logical state of the validation. True if mobile number is validated, false otherwise
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

        if (mobileNumberChars.length != 9 || mobileNumberChars[0] != '9' || (mobileNumberChars[1] != MobileOperator.OPERATOR1.getOperatorCode() && mobileNumberChars[1] != MobileOperator.OPERATOR2.getOperatorCode() && mobileNumberChars[1] != MobileOperator.OPERATOR3.getOperatorCode() && mobileNumberChars[1] != MobileOperator.OPERATOR6.getOperatorCode())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Shows all data for confirmation
     * @param name the name
     * @param birthdate the birthdate
     * @param admissionDate the admission date
     * @param street the street
     * @param streetNumber the street number
     * @param postalCode the postal code
     * @param city the city
     * @param district the district
     * @param email the email
     * @param mobileNumber the mobile number
     * @param idDocType the ID document type
     * @param idDocNumber the ID document number
     * @param job the job
     * @param header the header displays a custom message
     */
    private void showAllDataForConfirmation(String name, String birthdate, String admissionDate, String street, int streetNumber, String postalCode, String city, String district, String email, String mobileNumber, IdDocType idDocType, String idDocNumber, Job job, String header) {
        System.out.println();
        System.out.println(header);
        System.out.println();
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Admission Date: " + admissionDate);
        System.out.println("Street: " + street);
        System.out.println("Street Number: " + streetNumber);
        System.out.println("Postal Code: " + postalCode);
        System.out.println("City: " + city);
        System.out.println("District: " + district);
        System.out.println("Email: " + email);
        System.out.println("Mobile Number: " + mobileNumber);
        System.out.println("ID Document Type: " + idDocType);
        System.out.println("ID Document Number: " + idDocNumber);
        System.out.println("Job: " + job.getJobName());
    }
}

