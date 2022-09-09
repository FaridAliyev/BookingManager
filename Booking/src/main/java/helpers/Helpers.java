package helpers;

import entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helpers {
    public static Optional<List<String>> readFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            Stream<String> lines = r.lines();
            return Optional.of(lines.collect(Collectors.toList()));
        } catch (IOException x) {
            return Optional.empty();
        }
    }

    public static <T> Optional<List<T>> getData(Class<T> cl,String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<T> data = (List<T>) ois.readObject();
            return Optional.of(data);
        } catch (IOException | ClassNotFoundException exc) {
            return Optional.empty();
        }
    }

    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("src/main/java/database/users.bin"))) {
            oos.writeObject(new ArrayList<User>());
        } catch (FileNotFoundException exc) {
            System.out.println("File not found!");
        } catch (IOException exc) {
            System.out.println("Something went wrong!");
        }
    }
}
