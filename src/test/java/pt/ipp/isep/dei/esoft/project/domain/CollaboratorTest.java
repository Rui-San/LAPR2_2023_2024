package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator.IdDocType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    @Test
    public void ensureValidNameAcceptedTest() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);
        assertEquals("Pedro Costa", collaborator.getName());
    }

    @Test
    public void ensureNameHasAtLeastTwoNamesTest() {

        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Collaborator("Pedro", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);

        });
        assertTrue(exception.getMessage().contains("Name must include at least one first name and one last name."));
    }

    @Test
    public void ensureNameDontHaveMoreThanSixWordsTest() {

        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Collaborator("Pedro Miguel Santos Costa Costa Costa Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);

        });
        assertTrue(exception.getMessage().contains("Name must not contain more than 6 words, according to Portuguese Law"));
    }

    @Test
    public void ensureNameCantBeEmptyOrNullTest() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        String[] invalidNames = {"", " ", null};

        for (String invalidName : invalidNames) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Collaborator(invalidName, birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);

            });
            assertTrue(exception.getMessage().contains("Name must not be empty"));
        }
    }

    /**
     * This test ensures that name can only contain letters and the only symbol permited in the name is hyphen.
     */
    @Test
    public void ensureNameCantContainSpecialCharactersTest() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        String invalidNameSymbols = "0123456789!#$%^&*()+_.=<>?/,;:'\\\"[]{}\\\\|`~";

        for (char invalidSymbol : invalidNameSymbols.toCharArray()) {
            String name = "Pedro Costa" + invalidSymbol;
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Collaborator(name, birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);

            });
            assertTrue(exception.getMessage().contains("Name must not contain special characters"));
        }
    }

    @Test
    public void ensureMobileNumberIsntEmptyOrNullTest() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        String[] invalidMobileNumber = {"", " ", null};

        for (String invalidMobile : invalidMobileNumber) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", invalidMobile, Collaborator.IdDocType.CC, "234324235", job);

            });
            assertTrue(exception.getMessage().contains("Mobile Number is not in a correct format"));
        }
    }

    @Test
    public void ensureMobileNumberHaveNineDigitsTest() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        String invalidMobile = "91234548a";


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", invalidMobile, Collaborator.IdDocType.CC, "234324235", job);

        });
        assertTrue(exception.getMessage().contains("Mobile Number is not in a correct format"));
    }

    @Test
    public void ensureMobileNumberDontHaveSpecialCharactersTest() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        String invalidNameSymbols = "!#$%^&*()+_.=<>?/,;:'\\\"[]{}\\\\|`~";


        for (char symbol : invalidNameSymbols.toCharArray()) {
            String invalidMobile = "91234548" + symbol;
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", invalidMobile, Collaborator.IdDocType.CC, "234324235", job);

            });
            assertTrue(exception.getMessage().contains("Mobile Number is not in a correct format"));
        }
    }

    /*
    Verificar a questão de como se criar address e email no construtor do collaborador, devido ao clone

    @Test
    void ensureCloneWorks() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);
        Collaborator clone = collaborator.clone();
        assertEquals(collaborator, clone);
    }
    */

    @Test
    void ensureTheSameObjectIsEqual() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);
        assertEquals(collaborator, collaborator);
    }

    @Test
    public void testSetIdDocType_ValidIdDocType() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator1 = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", IdDocType.BI, "234324235", job);
        assertEquals(IdDocType.BI, collaborator1.getIdDocType());

        Collaborator collaborator2 = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", IdDocType.CC, "234324235", job);
        assertEquals(IdDocType.CC, collaborator2.getIdDocType());

        Collaborator collaborator3 = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", IdDocType.PASSPORT, "AB222222", job);
        assertEquals(IdDocType.PASSPORT, collaborator3.getIdDocType());
    }

    @Test
    public void ensureAssignSkillsTest() {

        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.BI, "234324235", job);


        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");
        Skill skill3 = new Skill("SQL");

        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill1);
        skillList.add(skill2);
        skillList.add(skill3);

        collaborator.assignSkills(skillList);

        List<Skill> assignedSkills = collaborator.getSkillList();

        assertTrue(assignedSkills.containsAll(skillList));
    }

    @Test
    public void ensureCCBInumberDontAllowWrongFormatTest() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        String[] invalidCCformats = {"12345678", "1234567890", "12345655f"};


        for (String ccFormat : invalidCCformats) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, ccFormat, job);

            });
            assertTrue(exception.getMessage().contains("NIF in wrong format. Must be 9 numeric digits"));
        }
    }

    @Test
    public void ensurePassportNumberDontAllowWrongFormatTest() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        String[] invalidPassportFormats = {"12345678", "1234567890", "12345655f", "AB1234567", "AB123456789", "AB12345"};

        for (String passportFormat : invalidPassportFormats) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.PASSPORT, passportFormat, job);
            });
            assertTrue(exception.getMessage().contains("Passport in wrong format. Must be two letters + 6 numeric digits"));
        }
    }

    @Test
    public void testHasAtLeastOneSkill() {

        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");
        Skill skill3 = new Skill("SQL");

        List<Skill> fullList = new ArrayList<>();
        fullList.add(skill1);
        fullList.add(skill2);
        fullList.add(skill3);

        Skill skillCollaboratorDontHave1 = new Skill("Medicin");
        Skill skillCollaboratorDontHave2 = new Skill("Professor");
        Skill skillCollaboratorDontHave3 = new Skill("Dentist");

        List<Skill> otherSkillList = new ArrayList<>();
        otherSkillList.add(skillCollaboratorDontHave1);
        otherSkillList.add(skillCollaboratorDontHave2);
        otherSkillList.add(skillCollaboratorDontHave3);

        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", IdDocType.BI, "234324235", job);

        collaborator.assignSkills(fullList);

        assertTrue(collaborator.hasAtLeastOneSkill(fullList));

        assertFalse(collaborator.hasAtLeastOneSkill(otherSkillList));
    }

    @Test
    public void testGetName() {

        String name = "Pedro Costa";

        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", IdDocType.BI, "234324235", job);

        String retrievedName = collaborator.getName();

        assertEquals(name, retrievedName);
    }

    @Test
    public void testGetBirthdate() {

        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", IdDocType.BI, "234324235", job);

        Date retrievedBirthdate = collaborator.getBirthdate();

        assertEquals(birthdate, retrievedBirthdate);
    }

    @Test
    public void testGetAdmissionDate() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", IdDocType.BI, "234324235", job);

        Date retrievedAdmissionDate = collaborator.getAdmissionDate();

        assertEquals(admissionDate, retrievedAdmissionDate);
    }

    @Test
    public void testGetAddress() {
        String street = "Rua das travessas";
        int streetNumber = 123;
        String postalCode = "1234-123";
        String city = "Matosinhos";
        String district = "Porto";
        Address expectedAddress = new Address(street, streetNumber, postalCode, city, district);

        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, street, streetNumber, postalCode, city, district, "1221790@isep.ipp.pt", "931231234", IdDocType.BI, "234324235", job);

        Address retrievedAddress = collaborator.getAddress();

        assertEquals(expectedAddress.getStreet(), retrievedAddress.getStreet());
        assertEquals(expectedAddress.getStreetNumber(), retrievedAddress.getStreetNumber());
        assertEquals(expectedAddress.getPostalCode(), retrievedAddress.getPostalCode());
        assertEquals(expectedAddress.getCity(), retrievedAddress.getCity());
        assertEquals(expectedAddress.getDistrict(), retrievedAddress.getDistrict());
    }

    @Test
    public void testGetEmail() {
        String email = "1221790@isep.ipp.pt";
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", email, "931231234", IdDocType.BI, "234324235", job);

        Email retrievedEmail = collaborator.getEmail();

        assertEquals(email, retrievedEmail.getEmail());
    }

    @Test
    public void testGetMobileNumber() {

        String mobileNumber = "931231234";
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", IdDocType.BI, "234324235", job);

        String retrievedMobileNumber = collaborator.getMobileNumber();

        assertEquals(mobileNumber, retrievedMobileNumber);
    }

    @Test
    public void testGetIdDocType() {
        IdDocType idDocType = Collaborator.IdDocType.CC;
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", idDocType, "234324235", job);

        IdDocType retrievedIdDocType = collaborator.getIdDocType();

        assertEquals(idDocType, retrievedIdDocType);
    }

    @Test
    public void testGetIdDocNumber() {
        String idDocNumber = "234324235";
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", IdDocType.CC, "234324235", job);

        String retrievedIdDocNumber = collaborator.getIdDocNumber();

        assertEquals(idDocNumber, retrievedIdDocNumber);
    }

    @Test
    public void testGetJob() {
        String jobName = "Gestor";
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job(jobName);

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);

        Job retrievedJob = collaborator.getJob();

        assertEquals(jobName, retrievedJob.getJobName());
    }



    @Test
    public void testToString() {
        Date birthdate = new Date("07/10/1995");
        Date admissionDate = new Date("20/03/2024");
        Job job = new Job("Gestor");

        Collaborator collaborator = new Collaborator("Pedro Costa", birthdate, admissionDate, "Rua das travessas", 123, "1234-123", "Matosinhos", "Porto", "1221790@isep.ipp.pt", "931231234", Collaborator.IdDocType.CC, "234324235", job);

        String expectedString = "Collaborator{name='Pedro Costa', birthdate=7/10/1995, admissionDate=20/3/2024, address=Address{street='Rua das travessas', streetNumber=123, postalCode='1234-123', city='Matosinhos', district='Porto'}, email=pt.ipp.isep.dei.esoft.project.domain.Email@3745e5c6, mobileNumber=931231234, idDocType='CC', idDocNumber=234324235}";
        assertEquals(expectedString, collaborator.toString());
    }

}


