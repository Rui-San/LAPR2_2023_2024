package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    public void ensureGetEmailReturnsCorrectValue() {

        Email email = new Email("pedro_teste@gmail.com");
        assertEquals("pedro_teste@gmail.com", email.getEmail());
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
        String[] invalidPrefixEmails = {"''@gmail.com", "!@gmail.com", "+@gmail.com", "´@gmail.com", "~@gmail.com"
                , "*@gmail.com", "`@gmail.com", "^@gmail.com", "ª@gmail.com", "º@gmail.com", ":@gmail.com", ";@gmail.com", ",@gmail.com",
                "#@gmail.com", "$@gmail.com", "%@gmail.com", "&@gmail.com", "/@gmail.com", "(@gmail.com", ")@gmail.com", "=@gmail.com",
                "?@gmail.com", "»@gmail.com", "«@gmail.com", "€@gmail.com", "£@gmail.com", "§@gmail.com", "{@gmail.com", "[@gmail.com",
                "]@gmail.com", "}@gmail.com"};


        for (String email : invalidPrefixEmails) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Email(email);
            });
            assertTrue(exception.getMessage().contains("Email prefix not valid. Only letters, numbers and _ . - are accepted"));
        }
    }


}