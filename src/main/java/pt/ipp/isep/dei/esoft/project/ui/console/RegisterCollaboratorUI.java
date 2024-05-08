package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;
import pt.ipp.isep.dei.esoft.project.tools.MobileOperator;


import java.util.*;
import java.util.regex.Pattern;


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

    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
    }

    public RegisterCollaboratorController getRegisterCollaboratorController() {
        return controller;
    }

    /*
    public static void main(String[] args) {
        RegisterCollaboratorUI ui = new RegisterCollaboratorUI();
        ui.run();
    }
    */


    @Override
    public void run() {
        System.out.println("\n\n--- Register new Collaborator ------------------------");

        requestData();
        job = displayAndSelectJob();  //done
        submitData();
    }

    private void submitData() {
        Optional<Collaborator> collaborator = getRegisterCollaboratorController().createCollaborator(name, birthdate, admissionDate, street, streetNumber, postalCode, city, district, email, mobileNumber, idDocType, idDocNumber, job);

        if (collaborator.isPresent()) {
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nCollaborator not registered!");
        }
    }

    private Job displayAndSelectJob() {
        System.out.println("List of available jobs:");
        List<Job> jobList = controller.getJobList();

        /*List<Job> jobList = new ArrayList<>();
        Job randomJob = new Job("Programador");
        jobList.add(randomJob);*/

        int listSize = jobList.size();
        int answer = 0;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayJobOptions(jobList);
            System.out.print("Select a job: ");
            try {
                answer = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid Option.");
                input.next();
            }

        }

        Job job = jobList.get(answer - 1);
        return job;
    }

    private void displayJobOptions(List<Job> jobList) {
        int i = 1;
        for (Job job : jobList) {
            System.out.println("  " + i + " - " + job.getJobName());
            i++;
        }
    }

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

    private String requestIdDocNumber() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("ID document number: ");
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

    private IdDocType requestIdDocType() {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        System.out.println("Select ID Document Type:");
        System.out.println("1. CC");
        System.out.println("2. BI");
        System.out.println("3. Passport");


        boolean inputValid = false;
        while (!inputValid) {
            try {
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

    private String requestEmail() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("Email: ");
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

    private String requestMobileNumber() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("Mobile Number: ");
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

    private String requestCity() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("City: ");
                response = input.nextLine();

                if (validateCity(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("City must not be empty");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    private boolean validateCity(String city) {

        return city != null && !city.trim().isEmpty();
    }


    private String requestDistrict() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("District: ");
                response = input.nextLine();

                if (validateDistrict(response)) {
                    validInput = true;
                } else {
                    throw new IllegalArgumentException("District must not be empty");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    private boolean validateDistrict(String district) {
        return district != null && !district.trim().isEmpty();
    }

    private String requestPostalCode() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("Postal Code: ");
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

    private int requestStreetNumber() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        int response = 0;

        while (!validInput) {
            try {
                System.out.println("Street Number: ");
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

    private boolean validateStreetNumber(Integer number) {
        return number > 0;
    }

    private String requestStreet() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("Street: ");
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
        System.out.println("Admission date (format: dd/mm/yyyy): ");
        return input.nextLine();
    }

    private String requestBirthdate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Birthdate (format: dd/mm/yyyy): ");
        return input.nextLine();
    }

    private String requestName() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("Name: ");
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

    private boolean validateMobileNumber(String mobileNumber) {

        if (mobileNumber == null || mobileNumber.trim().isEmpty()) {
            return false;
        }

        String onlyNumericDigits = "[0-9]+";
        if (!mobileNumber.matches(onlyNumericDigits)) {
            return false;
        }

        char[] mobileNumberChars = mobileNumber.toCharArray();

        if (mobileNumberChars.length != 9 || mobileNumberChars[0] != '9' || (mobileNumberChars[1] != MobileOperator.OPERATOR1.getOperatorCode() && mobileNumberChars[1] != MobileOperator.OPERATOR2.getOperatorCode() && mobileNumberChars[1] != MobileOperator.OPERATOR3.getOperatorCode())) {
            return false;
        } else {
            return true;
        }
    }
}

