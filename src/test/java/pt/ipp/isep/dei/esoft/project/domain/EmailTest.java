package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    public void ensureGetEmailReturnsCorrectValue() {

        Email email = new Email("pedroteste@gmail.com");
        assertEquals("pedroteste@gmail.com", email.getEmail());
    }

    @Test
    public void ensureEmailCantBeNullOrEmptyTest() {
        String[] invalidEmails = {"", null, " "};

        for (String email : invalidEmails) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Email(email);
            });
            assertTrue(exception.getMessage().contains("Email must not be empty"));
        }
    }

    @Test
    public void ensureEmailCantHaveInvalidFormatTest() {
        String[] invalidFormatEmails = {"teste@teste@teste.com", "@gmail.com", "teste@", "teste@@gmail.com"};

        for (String email : invalidFormatEmails) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Email(email);
            });
            assertTrue(exception.getMessage().contains("Email format must follow the pattern prefix@domain"));
        }
    }

    /**
     * Ensure that email prefix may only contain letters, numeric digits and symbols allowed are: "_", "." and "-".
     */
    @Test
    public void ensureDetectsInvalidPrefixEmailTest() {

        String invalidPrefixSymbols = "!#$%^&*()+=<>?/,;:'\"[]{}\\|`~";

        for (char symbol : invalidPrefixSymbols.toCharArray()) {
            String emailTest = symbol + "prefix@gmail.com";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Email(emailTest);
            });
            assertTrue(exception.getMessage().contains("Email prefix not valid. Only letters, numbers and _ . - are accepted"));
            ;
        }
    }

    @Test
    public void testToString() {
        Email email = new Email("1221790@isep.ipp.pt");

        String expectedString = "  Email: 1221790@isep.ipp.pt";

        assertEquals(expectedString, email.toString());
    }

    /**
     * Ensure that email domain may only contain letters, numeric digits and symbols allowed are: "_", "." and "-".
     */
    @Test
    public void ensureDetectsInvalidDomainEmailTest() {

        String invalidDomainSymbols = "!#$%^&*()+_.=<>?/,;:'\"[]{}\\|`~";

        for (char symbol : invalidDomainSymbols.toCharArray()) {
            String emailTest = "test@" + symbol + ".com";

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Email(emailTest);
            });
            assertTrue(exception.getMessage().contains("Email domain not valid."));
        }
    }

    @Test
    public void ensureDetectsInvalidExtensionEmailTest() {

        String invalidExtensionSymbols = "0123456789!#$%^&*()+_.=<>?/,;:'\"[]{}\\|`~-";

        for (char symbol : invalidExtensionSymbols.toCharArray()) {
            String emailTest = "test@gmail." + symbol + "com";

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Email(emailTest);
            });
            assertTrue(exception.getMessage().contains("Email domain not valid."));
        }
    }

    @Test
    public void ensureDetectsInvalidDomainLengthEmailTest() {

        String[] invalidDomainLengthEmails = {"pedro@gmail.c", "pedro@gmail.comcomco"};

        for (String email : invalidDomainLengthEmails) {

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Email(email);
            });
            assertTrue(exception.getMessage().contains("Email domain not valid."));
        }
    }
}