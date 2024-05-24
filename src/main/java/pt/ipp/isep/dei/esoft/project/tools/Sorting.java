package pt.ipp.isep.dei.esoft.project.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Sorting {

    private Properties properties;

    private List<Object> subject;

    public Sorting() {
        properties = new Properties();
        loadProperties();
    }

    public Sorting(List<Object> subject) {
        properties = new Properties();
        loadProperties();
    }

    public Sorting sort(){
        Sorting configLoader = new Sorting();

        if (configLoader.getProperty("Sorting.algorithm") == "quick") {
            quickSort(subject, 6);
        } else if (configLoader.getProperty("Sorting.algorithm") == "bubble") {
            bubbleSort(subject, 6);
        } else {
            quickSort(subject, 6);
        }


    }

    public List<Object> quickSort(List<Object> subject, int collumnToSort){

        return subject;
    }

    public List<Object> bubbleSort(List<Object> subject, int collumnToSort){

        return subject;
    }



    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            // Load the properties file from class path
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
