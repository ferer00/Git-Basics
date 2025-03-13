package Equation;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Equation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select mode: (1) Interactive (2) File");
        System.out.print("Enter 1 or 2: ");

        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            interMode(scanner);
        }else if (choice.equals("2")) {
            System.out.print("Enter file path: ");
            String filePath = scanner.nextLine().trim();
            fileMode(filePath);
        }
        else {
            System.out.println("Invalid choice. Exiting.");
            System.exit(1);
        }
    }

    // Інтерактивний режим
    private static void interMode(Scanner scanner) {
        double a = checkCoefficient(scanner, "a");
        while (a == 0) {
            System.out.println("Error. a cannot be 0");
            a = checkCoefficient(scanner, "a");
        }
        double b = checkCoefficient(scanner, "b");
        double c = checkCoefficient(scanner, "c");

        printEquation(a, b, c);
        solve(a, b, c);
    }

    // Метод для зчитування коефіцієнтів з перевіркою
    private static double checkCoefficient(Scanner scanner, String coefName) {
        while (true) {
            System.out.print(coefName + " = ");
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Error. Expected a valid real number, got " + input + " instead");
            }
        }
    }

    // Файловий режим
    private static void fileMode(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File " + filePath + " does not exist");
            System.exit(1);
        }

        try (Scanner fileScanner = new Scanner(file)) {
            if (!fileScanner.hasNextLine()) {
                System.out.println("Invalid file format");
                System.exit(1);
            }
            String line = fileScanner.nextLine().trim();
            String[] parts = line.split(" ");
            if (parts.length != 3) {
                System.out.println("Invalid file format ");
                System.exit(1);
            }
            double a, b, c;
            try {
                a = Double.parseDouble(parts[0]);
                b = Double.parseDouble(parts[1]);
                c = Double.parseDouble(parts[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid file format ");
                System.exit(1);
                return;
            }

            if (a == 0) {
                System.out.println("Error. a cannot be 0");
                System.exit(1);
            }

            printEquation(a, b, c);
            solve(a, b, c);
        } catch (FileNotFoundException e) {
            System.out.println("File " + filePath + " does not exist");
            System.exit(1);
        }
    }

    // Метод для виведення рівняння
    private static void printEquation(double a, double b, double c) {
        System.out.println("Equation is: (" + a + ") x^2 + (" + b + ") x + (" + c + ") = 0");
    }

    // Метод для знаходження і виведення коренів рівняння
    private static void solve(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            System.out.println("There are 0 roots");
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            System.out.println("There is 1 root");
            System.out.println("x1 = " + x);
        } else {
            double sqrtDiscriminant = Math.sqrt(discriminant);
            double x1 = (-b - sqrtDiscriminant) / (2 * a);
            double x2 = (-b + sqrtDiscriminant) / (2 * a);
            System.out.println("There are 2 roots");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        }
    }
    // text
}
