package pt.ipp.isep.dei.esoft.project.tools;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Comparator;
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
        if (subject == null || subject.isEmpty()) {
            return this;
        }

        String algorithm = getProperty("Sorting.algorithm");


        int columnToSort = 6;
        Sorting configLoader = new Sorting();
        Field field = subject.get(0).getClass().getDeclaredFields()[columnToSort];


        Comparator<Object> comparator = Comparator.comparing(o -> {
            try {
                field.setAccessible(true);
                return (Comparable) field.get(o);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        if (algorithm == "quick") {
            quickSort(subject, comparator);
        } else if (algorithm == "bubble") {
            bubbleSort(subject, comparator);
        } else {
            quickSort(subject, comparator);
        }

        return this;
    }

    private void quickSort(List<Object> list, Comparator<Object> comparator) {
        quickSort(list, 0, list.size() - 1, comparator);
    }

    private void quickSort(List<Object> list, int low, int high, Comparator<Object> comparator) {
        if (low < high) {
            int pi = partition(list, low, high, comparator);
            quickSort(list, low, pi - 1, comparator);
            quickSort(list, pi + 1, high, comparator);
        }
    }

    private int partition(List<Object> list, int low, int high, Comparator<Object> comparator) {
        Object pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<Object> list, int i, int j) {
        Object temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private void bubbleSort(List<Object> list, Comparator<Object> comparator) {
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    swap(list, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
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
