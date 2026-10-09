import java.util.*;
class ConsoleUtils {
    protected static void holdScreen()
    {
        try {
            System.in.read();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            System.out.println("Unable to clear console.");
        }
    }
    protected static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PlayGuessNumber extends ConsoleUtils{
    private int randomNumber, guess, range;
    private int userInput;
    private Scanner sc;

    // Constructor (initial setup)
    public PlayGuessNumber() {
        range = 100;
        resetGame();
        sc = new Scanner(System.in);
    }

    // Reset guesses and generate new random number
    public void resetGame() {
        guess = 5;
        Random random = new Random();
        randomNumber = random.nextInt(range) + 1;
    }

    // Set range manually
    public void setRange(int range) {
        this.range = range;
        resetGame();
    }

    // Start the guessing game
    public void startGame() {
        while (true) {
            while (guess > 0) {
                clearScreen();
                System.out.println("===============================");
                System.out.println("♥  NUMBER GUESSING GAME  ♥");
                System.out.println("===============================");
                System.out.println("Attempts left: " + guess);
                System.out.println("-------------------------------");
                System.out.print("► Enter your guess: ");
                userInput = sc.nextInt();
                guess--;

                if (userInput == randomNumber) {
                    System.out.println("\n✓ Congratulations! You guessed it right!");
                    System.out.println("♦ That was fun! Want to try again?");
                    System.out.println("► Press 'y' to restart or 'n' to return to menu.");
                    char result = sc.next().charAt(0);

                    if (result == 'Y' || result == 'y') {
                        resetGame();  // reset all values
                        continue;     // restart game immediately
                    } else {
                        return;       // go back to menu
                    }
                } else if (userInput > randomNumber) {
                    System.out.println("✗ You went a bit too high!");
                } else {
                    System.out.println("✗ You went a bit too low!");
                }
                pause(3);
            }

            // If all guesses used
            clearScreen();
            System.out.println("☹ Oops! You’ve used all your chances.");
            System.out.println("The correct number was: " + randomNumber);
            System.out.println("► Press 'y' to play again or 'n' to return to menu.");
            char retry = sc.next().charAt(0);

            if (retry == 'Y' || retry == 'y') {
                resetGame();
                continue;
            } else {
                return;
            }
        }
    }
}

// Main Menu Class
class MainMenu extends ConsoleUtils{
    private Scanner sc;
    PlayGuessNumber obj;

    MainMenu() {
        sc = new Scanner(System.in);
        obj = new PlayGuessNumber();
    }

    public void mainMenu() {
        while (true) {
            clearScreen();
            System.out.println("========================================");
            System.out.println("♥     WELCOME TO THE NUMBER GUESSING GAME     ♥");
            System.out.println("========================================");
            System.out.println();
            System.out.println("1)  Start Game");
            System.out.println("2)  Set Range Manually");
            System.out.println("3)  Instructions");
            System.out.println("4)  Exit");
            System.out.println();
            System.out.print("► Enter your choice: ");
            int choice = sc.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.println("Starting the game...");
                    pause(1);
                    obj.resetGame();  // clean start each time
                    obj.startGame();
                    break;

                case 2:
                    System.out.print("Enter the maximum range (e.g., 100): ");
                    int userRange = sc.nextInt();
                    obj.setRange(userRange);
                    System.out.println("Range successfully set to 1 - " + userRange);
                    pause(3);
                    break;

                case 3:
                    System.out.println("INSTRUCTIONS:");
                    System.out.println("► Guess the number between 1 and your chosen range.");
                    System.out.println("► You’ll be told if your guess is too high or too low.");
                    System.out.println("► Try to find the correct number within limited attempts!");
                    System.out.println("► You get 5 attempts per game.");
                    pause(3);
                    break;

                case 4:
                    System.out.println("Thanks for playing! Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
                    pause(3);
            }
        }
    }
}

// Main class
public class Task4 {
    public static void main(String[] args) {
        new MainMenu().mainMenu();
    }
}
