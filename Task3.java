import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String title;
    private boolean isCompleted;

    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        String status = (isCompleted) ? "✅ Completed" : "⏳ Pending";
        return title + " - " + status;
    }
}

class ToDoList {
    private ArrayList<Task> tasks;
    private Scanner sc;

    public ToDoList() {
        tasks = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    private void addTask() {
        System.out.print("Enter task title: ");
        sc.nextLine(); // consume leftover newline
        String title = sc.nextLine();
        tasks.add(new Task(title));
        System.out.println("Task added successfully!");
    }

    private void markTaskCompleted() {
        displayTasks();
        System.out.print("Enter task number to mark as completed: ");
        int index = sc.nextInt();
        if (index > 0 && index <= tasks.size()) {
            tasks.get(index - 1).markCompleted();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available!");
            return;
        }

        System.out.println("\n--- To-Do List ---");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("------------------");
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n========= TO-DO LIST MENU =========");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Display Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    markTaskCompleted();
                    break;
                case 3:
                    displayTasks();
                    break;
                case 4:
                    System.out.println("Exiting... Have a productive day!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

public class Task3 {
    public static void main(String[] args) {
        ToDoList app = new ToDoList();
        app.showMenu();
    }
}
