import java.util.Scanner;

class Calculator {
    private Scanner sc;

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            System.out.println("Unable to clear console.");
        }
    }

    public Calculator() {
        sc = new Scanner(System.in);
    }

    private String addition() {
        System.out.println("Enter the first value:");
        int firstValue = sc.nextInt();
        System.out.println("Enter the second value:");
        int secondValue = sc.nextInt();
        int result = firstValue + secondValue;
        return String.valueOf(result);
    }

    private String subtraction() {
        System.out.println("Enter the first value:");
        int firstValue = sc.nextInt();
        System.out.println("Enter the second value:");
        int secondValue = sc.nextInt();
        int result = firstValue - secondValue;
        return String.valueOf(result);
    }

    private String multiplication() {
        System.out.println("Enter the first value:");
        int firstValue = sc.nextInt();
        System.out.println("Enter the second value:");
        int secondValue = sc.nextInt();
        int result = firstValue * secondValue;
        return String.valueOf(result);
    }

    private String division() {
        System.out.println("Enter the first value:");
        double firstValue = sc.nextDouble();
        System.out.println("Enter the second value:");
        double secondValue = sc.nextDouble();

        if (secondValue == 0) {
            return "Cannot divide by zero";
        }
        double result = firstValue / secondValue;
        return String.valueOf(result);
    }

    private String modulusDivision() {
        System.out.println("Enter the first value:");
        int firstValue = sc.nextInt();
        System.out.println("Enter the second value:");
        int secondValue = sc.nextInt();

        if (secondValue == 0) {
            return "Cannot perform modulus by zero";
        }
        int result = firstValue % secondValue;
        return String.valueOf(result);
    }

    private String calculatePower() {
        System.out.println("Enter the base value:");
        double base = sc.nextDouble();
        System.out.println("Enter the exponent value:");
        double exponent = sc.nextDouble();

        double result = Math.pow(base, exponent);
        return String.valueOf(result);
    }

    private void helperMethod(String choice) {
        switch (choice) {
            case "1":
                System.out.println("O/P: " + addition());
                break;
            case "2":
                System.out.println("O/P: " + subtraction());
                break;
            case "3":
                System.out.println("O/P: " + multiplication());
                break;
            case "4":
                System.out.println("O/P: " + division());
                break;
            case "5":
                System.out.println("O/P: " + modulusDivision());
                break;
            case "6":
                System.out.println("O/P: " + calculatePower());
                break;
            case "7":
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid Input! Please try again.");
        }

        System.out.println("\nPress Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        clearScreen();
    }

    public void homeScreen() {
        while (true) {
            clearScreen();
            System.out.println("-------------------- Welcome To My Calculator --------------------");
            System.out.println("\n                     * Select an Option *  ");
            System.out.println("                  --------------------------------");
            System.out.println("                        1. Addition");
            System.out.println("                        2. Subtraction");
            System.out.println("                        3. Multiplication");
            System.out.println("                        4. Division");
            System.out.println("                        5. Modulus Division");
            System.out.println("                        6. Calculate Power");
            System.out.println("                        7. Exit");
            System.out.println("                  --------------------------------");
            System.out.print("Enter Your Choice: ");

            String userInput = sc.next();
            helperMethod(userInput);
        }
    }
}

public class Task1 {
    public static void main(String[] args) {
        Calculator obj = new Calculator();
        obj.homeScreen();
    }
}
