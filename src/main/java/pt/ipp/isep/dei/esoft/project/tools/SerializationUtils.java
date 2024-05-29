package pt.ipp.isep.dei.esoft.project.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationUtils {

    public static <T> boolean saveToFile(List<T> listToSave, String fileName) {
        try {

            String directoryPath = System.getProperty("user.dir") + File.separator + "data";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = directoryPath + File.separator + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            try {
                if (!listToSave.isEmpty()) {
                    out.writeObject(listToSave);
                    return true;
                } else {
                    return false;
                }
            } finally {
                out.close();
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
    }

    public static <T> List<T> readFromFile(String fileName) {
        List<T> list;
        try {

            String directoryPath = System.getProperty("user.dir") + File.separator + "data";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = directoryPath + File.separator + fileName;
            File file = new File(filePath);

            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            try {
                list = (List<T>) in.readObject();
            } finally {
                in.close();
            }
            return list;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
