package me.nathangreen.digitalartefact;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Utils {
    public static int menu(String title, String[] choices, Scanner sc) {
        int max = choices.length > 0 ? Stream.of(choices).map(String::length).max(Integer::compareTo).get() : 0;
        max = Math.max(title.length(), max);
        System.out.printf("""
                %n╔%s╗
                ║  %s%s║
                ╠%s╣%n""", "═".repeat(max + 6), title, " ".repeat(max - title.length() + 4), "═".repeat(max + 6));
        for (int i = 0; i < choices.length; i++) {
            System.out.printf("║ %2s. %-" + max + "s ║%n", i + 1, choices[i]);
        }
        System.out.printf("╚%s╝%n", "═".repeat(max + 6));
        boolean valid = false;
        int choice = 0;
        while (!valid) {
            System.out.printf("Choose an option (1–%s): ", choices.length);
            try {
                choice = sc.nextInt();
                sc.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                sc.nextLine();
            }
        }
        return choice;
    }
}
