import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TextAnalyzer {

    private String text;
    private static Scanner sc;
    TextAnalyzer()
    {
        sc = new Scanner(System.in);
        text="";
    }
    protected int countLines() {
        if (text.isEmpty())
            return 0;
        String[] lines = text.split("\\r?\\n");
        return lines.length;
    }

    protected void setFileContent(String file) {
        text = file;
    }
    protected boolean isFileEmpty()
    {
        return text.isEmpty();
    }
    protected int countWords() {
        if (text.isEmpty())
            return 0;
        int cnt = 1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 32)
                cnt++;
        }
        return cnt;
    }

    protected int countCharacter() {
        int cnt = 0;
        for (int i = 0; i < text.length(); i++)
            cnt++;
        return cnt;
    }

    protected int searchWord(String word) {
        if (text.isEmpty())
            return 0;
        String[] words = text.toLowerCase().split("\\W+");
        word = word.toLowerCase();
        int count = 0;
        for (String w : words)
            if (w.equals(word))
                count++;
        return count;
    }
    protected void operationOnFile() {
    if (text == null) {
        System.out.println("⚠ Please load a file first before performing operations!");
        ConsoleUtils.pause(3);
        return;
    }

    while (true) {
        ConsoleUtils.clearScreen();
        System.out.println("=====================================");
        System.out.println("📄 FILE OPERATIONS MENU");
        System.out.println("=====================================");
        System.out.println("1  Count Words");
        System.out.println("2  Count Characters");
        System.out.println("3  Search for a Word");
        System.out.println("4  Count Lines");
        System.out.println("5  Back to Main Menu");
        System.out.println("=====================================");
        System.out.print("👉 Enter your choice: ");
        
        int out = sc.nextInt();
        sc.nextLine(); // clear input buffer

        switch (out) {
            case 1:
                System.out.println("Total Words: " + countWords());
                ConsoleUtils.pause(3);
                break;

            case 2:
                System.out.println("Total Characters: " + countCharacter());
                ConsoleUtils.pause(3);
                break;

            case 3:
                System.out.print("Enter word to search: ");
                String word = sc.nextLine();
                int count = searchWord(word);
                if (count > 0)
                    System.out.println("✅ Found \"" + word + "\" " + count + " time(s).");
                else
                    System.out.println("❌ Word not found.");
                ConsoleUtils.pause(3);
                break;

            case 4:
                System.out.println("Total Lines: " + countLines());
                ConsoleUtils.pause(3);
                break;

            case 5:
                System.out.println("↩ Returning to Main Menu...");
                ConsoleUtils.pause(2);
                return;

            default:
                System.out.println("❌ Invalid choice! Try again.");
                ConsoleUtils.pause(2);
        }
    }
}

}

class MenuInterface extends TextAnalyzer {
    private static Map<String, String> loadedFileList;
    static Scanner sc;
    public MenuInterface()
    {
        loadedFileList = new HashMap<>();
    }
    private String normalizeFileName(String fileName) {
        fileName = fileName.trim();
        if (!fileName.toLowerCase().endsWith(".txt")) {
            fileName += ".txt";
        }
        return fileName;
    }
    private void loadFile(String file) {
        try {
            String text = Files.readString(Paths.get(file));
            text = text.trim();
            loadedFileList.put(file, text);
            if(isFileEmpty())
                setFileContent(text);
        } catch (Exception e) {
            System.out.println("file not exist");
            // e.printStackTrace();
        }
    }

    private void switchFile() {
        if (loadedFileList.isEmpty()) {
            System.out.println("⚠️ No files loaded yet!");
            return;
        }

        System.out.println("📁 Loaded files:");
        for (String name : loadedFileList.keySet()) {
            System.out.println("- " + name);
        }

        System.out.print("Enter file name to switch: ");
        String name = sc.nextLine();
        name = normalizeFileName(name);
        if (loadedFileList.containsKey(name)) {
            String currentFile = loadedFileList.get(name);
            setFileContent(currentFile);
            System.out.println("✅ Switched to file: " + name);
        } else {
            System.out.println("❌ File not found in loaded list!");
        }
    }
    private void showLoadedFiles() {
        ConsoleUtils.clearScreen();

        if (loadedFileList.isEmpty()) {
            System.out.println("⚠ No files loaded yet!");
        } else {
            System.out.println("=====================================");
            System.out.println("📁 Loaded Files:");
            System.out.println("=====================================");
            int index = 1;
            for (String fileName : loadedFileList.keySet()) {
                System.out.println(index + ") " + fileName);
                index++;
            }
        }
        ConsoleUtils.holdScreen();
    }

    public  void mainMenu() {
        sc = new Scanner(System.in);
        while (true) {
            ConsoleUtils.clearScreen();
            System.out.println("\n==============================");
            System.out.println("📘 TEXT FILE ANALYZER");
            System.out.println("==============================");
            System.out.println("\n=== 📂 File Analyzer Menu ===");
            System.out.println("1 Load a new file");
            System.out.println("2 Switch to a loaded file");
            System.out.println("3 Show stats (words, lines, chars)");
            System.out.println("4 View loaded files");
            System.out.println("5 Exit");
            System.out.print("\n► Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    String fileName = sc.nextLine();
                    loadFile(normalizeFileName(fileName));
                    break;
                case 2:
                    switchFile();
                case 3:
                    operationOnFile();
                    break;
                case 4:
                    showLoadedFiles();
                    break;
                case 5:
                    System.out.println("👋 Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Try again.");
                    ConsoleUtils.pause(3);
            }
        }
    }
}

public class Task5 {
    public static void main(String[] args) {
        new MenuInterface().mainMenu();
    }
}
