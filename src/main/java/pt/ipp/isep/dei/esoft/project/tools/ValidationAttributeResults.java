package pt.ipp.isep.dei.esoft.project.tools;


/**
 * Type Enumerated, enumerating all the different results that may occur during the validation of attributes.
 */
public enum ValidationAttributeResults {

    /**
     * Indicates that the field is valid.
     */
    VALID,

    /**
     * Indicates that the field is empty or null.
     */
    EMPTYNULL,

    /**
     * Indicates that the name of the person does not contain enough names (at least one first name and one last name).
     */
    NOT_ENOUGH_NAMES,

    /**
     * Indicates that the name of the person contains too many words (more than 6 words, according to Portuguese Law).
     */
    TOO_MANY_WORDS,

    /**
     * Indicates that the field contains special characters, which are not allowed.
     */
    CONTAINS_SPECIAL_CHARACTERS,

    /**
     * Indicates that there is an error with the passport format.
     */
    PASSPORT_ERROR,

    /**
     * Indicates that there is an error with the CC or BI format.
     */
    CC_BI_ERROR,

    /**
     * Field contain contains letters, which are not allowed.
     */
    CONTAIN_LETTERS,

    /**
     * Field has invalid format.
     */
    INVALID_FORMAT,

    /**
     * The month is invalid.
     */
    INVALID_MONTH,

    /**
     * The year is invalid.
     */
    INVALID_YEAR,

    /**
     * The day is invalid.
     */
    INVALID_DAY,

    /**
     * The email format is not valid.
     */
    WRONG_FORMAT,

    /**
     * The email prefix is not valid.
     */
    INVALID_PREFIX,

    /**
     * The email domain is not valid.
     */
    INVALID_DOMAIN


}
