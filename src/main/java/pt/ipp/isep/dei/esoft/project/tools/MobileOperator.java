package pt.ipp.isep.dei.esoft.project.tools;

import java.io.Serializable;

/**
 * Type enumerated, enumerating all the different mobile operators that a phone number can have.
 */
public enum MobileOperator implements Serializable {
    OPERATOR1('1'),
    OPERATOR2('2'),
    OPERATOR3('3');

    /**
     * The operator code of the mobile phone.
     */
    private final char operatorCode;

    /**
     * Constructs a Mobile Operator with the specified operator code.
     *
     * @param operatorCode the operator code
     */
    MobileOperator(char operatorCode) {
        this.operatorCode = operatorCode;
    }

    /**
     * Gets the operator code.
     *
     * @return the operator code
     */
    public char getOperatorCode() {
        return operatorCode;
    }
}
