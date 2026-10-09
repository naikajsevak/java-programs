import java.util.Scanner;
public class Task2 {
    static Scanner sc = new Scanner(System.in);
     public static void main(String[] args) {
       
        while (true) {
            System.out.println("------ Temperature Converter ------");
            System.out.println("1. Celsius to Fahrenheit");
            System.out.println("2. Fahrenheit to Celsius");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    convertCelsiusToFahrenheit();
                    break;

                case 2:
                    convertFahrenheitToCelsius();
                    break;

                case 3:
                    System.out.println("Exiting... Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.\n");
            }

            System.out.println(); // Add blank line for better readability
        }
    }

    // Method to convert Celsius → Fahrenheit
    static void convertCelsiusToFahrenheit() {
        System.out.print("Enter temperature in Celsius: ");
        double celsius = sc.nextDouble();
        double fahrenheit = (celsius * 9 / 5) + 32;
        System.out.printf("%.2f°C = %.2f°F\n", celsius, fahrenheit);
    }

    // Method to convert Fahrenheit → Celsius
    static void convertFahrenheitToCelsius() {
        System.out.print("Enter temperature in Fahrenheit: ");
        double fahrenheit = sc.nextDouble();
        double celsius = (fahrenheit - 32) * 5 / 9;
        System.out.printf("%.2f°F = %.2f°C\n", fahrenheit, celsius);
    }
}
