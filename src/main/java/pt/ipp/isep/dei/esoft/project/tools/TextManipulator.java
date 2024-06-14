package pt.ipp.isep.dei.esoft.project.tools;

public class TextManipulator {

    public static String capitalizeWords(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] words = input.split("\\s+");
        StringBuilder capitalizedString = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                capitalizedString.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase());
            }
            capitalizedString.append(" ");
        }

        return capitalizedString.toString().trim();
    }

}
