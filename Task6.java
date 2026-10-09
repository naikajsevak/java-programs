import java.util.*;

class Account {
    protected String name;
    protected long accNumber;
    protected double balance;
    protected List<String> transactionHistory = new ArrayList<>();

    public Account(String name, long accNumber, double balance) {
        this.name = name;
        this.accNumber = accNumber;
        this.balance = balance;
        transactionHistory.add("Account created with balance: ₹" + balance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid deposit amount.");
            return;
        }
        balance += amount;
        transactionHistory.add("Deposited: ₹" + amount);
        System.out.println("✅ Deposited successfully!");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("⚠ Insufficient balance!");
        } else {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount);
            System.out.println("✅ Withdrawal successful!");
        }
    }

    public void showBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }

    public void showTransactionHistory() {
        System.out.println("📜 Transaction History:");
        for (String record : transactionHistory)
            System.out.println(" - " + record);
    }
}
class Bank {
    private Map<Long, Account> accounts = new HashMap<>();
    private Random random = new Random();

    private long generateUniqueAccountNumber() {
        long accNum;
        do {
            accNum = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        } while (accounts.containsKey(accNum));
        return accNum;
    }

    public Account createAccount(String name, double initialDeposit) {
        long accNum = generateUniqueAccountNumber();
        Account account = new Account(name, accNum, initialDeposit);
        accounts.put(accNum, account);
        System.out.println("✅ Account created successfully!");
        System.out.println("Account Number: " + accNum);
        return account;
    }

    public Account getAccount(long accNum) {
        return accounts.get(accNum);
    }

    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("🏦 All Accounts:");
        for (Account acc : accounts.values()) {
            System.out.println(acc.accNumber + " - " + acc.name + " (₹" + acc.balance + ")");
        }
    }
}


public class Task6 {
    
     private static Scanner sc = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        while (true) {
            ConsoleUtils.clearScreen();
            System.out.println("\n===== 🏦 Banking System Menu =====");
            System.out.println("1️⃣ Create Account");
            System.out.println("2️⃣ Deposit");
            System.out.println("3️⃣ Withdraw");
            System.out.println("4️⃣ View Balance");
            System.out.println("5️⃣ Transaction History");
            System.out.println("6️⃣ View All Accounts");
            System.out.println("7️⃣ Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter initial deposit: ");
                    double deposit = sc.nextDouble();
                    bank.createAccount(name, deposit);
                }
                case 2 -> {
                    Account acc = getAccountInput();
                    if (acc != null) {
                        System.out.print("Enter amount to deposit: ");
                        acc.deposit(sc.nextDouble());
                    }
                }
                case 3 -> {
                    Account acc = getAccountInput();
                    if (acc != null) {
                        System.out.print("Enter amount to withdraw: ");
                        acc.withdraw(sc.nextDouble());
                    }
                }
                case 4 -> {
                    Account acc = getAccountInput();
                    if (acc != null) acc.showBalance();
                }
                case 5 -> {
                    Account acc = getAccountInput();
                    if (acc != null) acc.showTransactionHistory();
                }
                case 6 -> bank.listAccounts();
                case 7 -> {
                    System.out.println("👋 Thank you for using our banking system!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice! Try again.");
            }
            ConsoleUtils.holdScreen();
        }
    }

    private static Account getAccountInput() {
        System.out.print("Enter account number: ");
        long accNum = sc.nextLong();
        Account acc = bank.getAccount(accNum);
        if (acc == null)
            System.out.println("⚠ Account not found!");
        return acc;
    }
}
