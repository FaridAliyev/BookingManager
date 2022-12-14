package helpers;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helpers {
    public static Optional<List<String>> readFile(String filePath) {
        File file = new File(filePath);
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            Stream<String> lines = r.lines();
            return Optional.of(lines.collect(Collectors.toList()));
        } catch (IOException x) {
            return Optional.empty();
        }
    }

    public static <T> Optional<List<T>> getData(Class<T> cl, String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<T> data = (List<T>) ois.readObject();
            return Optional.of(data);
        } catch (IOException | ClassNotFoundException exc) {
            return Optional.empty();
        }
    }

    public static <T> void saveData(List<T> data, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        } catch (FileNotFoundException exc) {
            System.out.println("File not found!");
        } catch (IOException exc) {
            System.out.println("Something went wrong!");
        }
    }
}
