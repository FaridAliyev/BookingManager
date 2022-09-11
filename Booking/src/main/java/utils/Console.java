package utils;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static int nextInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid input!");
            scanner.next();
        }
        int number = scanner.nextInt();
        return number;
    }


    public static String next() {
        return next(-1);
    }

    public static String next(int length) {
        String str;
        do {
            while (!scanner.hasNext()) {
                System.out.println("Please enter a valid input!");
                scanner.next();
            }
            str = scanner.next();
        } while (str.length() < length);
        return str;
    }
}
