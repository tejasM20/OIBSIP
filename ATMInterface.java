package ATMtask;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

/*
 * =========================================================
 * TRANSACTION CLASS
 * ---------------------------------------------------------
 * Stores each transaction details like:
 * account number, type, amount, date and description
 * =========================================================
 */
class Transaction {
    String accountNumber;
    String transactionType;
    String description;
    double amount;
    LocalDate date;

    Transaction(String accountNumber, String transactionType,
                double amount, String description) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.now(); // current date
    }
}

/*
 * =========================================================
 * ATM ACCOUNT CLASS
 * ---------------------------------------------------------
 * Stores all details of a bank account
 * =========================================================
 */
class ATMAccount {
    String accountNumber;
    String pin;
    String mobileNumber;
    double balance;

    ATMAccount(String accountNumber, String pin,
               String mobileNumber, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
    }
}

/*
 * =========================================================
 * MAIN ATM INTERFACE CLASS
 * =========================================================
 */
public class ATMInterface {

    // Scanner for user input
    static Scanner sc = new Scanner(System.in);

    // Stores all accounts in memory
    static Map<String, ATMAccount> accounts = new HashMap<>();

    // Files for permanent storage
    static final String ACCOUNT_FILE = "accounts.txt";
    static final String TRANSACTION_FILE = "transactions.txt";

    // Default opening balance
    static final double BASE_BALANCE = 5000;

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        // Load previously saved accounts
        loadAccounts();

        while (true) {
            System.out.println("\n========= ATM SYSTEM =========");
            System.out.println("1. Create New Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    saveAccounts();
                    System.out.println("Thank you for using the ATM!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= CREATE ACCOUNT =================
    static void createAccount() {

        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        if (accounts.containsKey(accNo)) {
            System.out.println("Account already exists!");
            return;
        }

        System.out.print("Create PIN: ");
        String pin = sc.nextLine();

        System.out.print("Enter Mobile Number: ");
        String mobile = sc.nextLine();

        ATMAccount newAccount =
                new ATMAccount(accNo, pin, mobile, BASE_BALANCE);

        accounts.put(accNo, newAccount);
        saveAccounts();

        System.out.println("Account created successfully!");
        System.out.println("Initial Balance: Rs. " + BASE_BALANCE);
    }

    // ================= LOGIN =================
    static void login() {

        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        ATMAccount user = accounts.get(accNo);

        if (user == null || !user.pin.equals(pin)) {
            System.out.println("Invalid account number or PIN!");
            return;
        }

        atmMenu(user);
    }

    // ================= SECURITY VERIFICATION =================
    static boolean verifyUser(ATMAccount user) {

        System.out.print("Re-enter PIN: ");
        String pin = sc.nextLine();

        System.out.print("Enter Mobile Number: ");
        String mobile = sc.nextLine();

        if (user.pin.equals(pin) && user.mobileNumber.equals(mobile)) {
            return true;
        }

        System.out.println("Verification failed!");
        return false;
    }

    // ================= ATM MENU =================
    static void atmMenu(ATMAccount user) {

        boolean logout = false;

        while (!logout) {
            System.out.println("\n-------- ATM MENU --------");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Change PIN");
            System.out.println("6. View Statement");
            System.out.println("7. Logout");
            System.out.print("Choose option: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    if (verifyUser(user)) deposit(user);
                    break;

                case 2:
                    if (verifyUser(user)) withdraw(user);
                    break;

                case 3:
                    if (verifyUser(user)) transfer(user);
                    break;

                case 4:
                    if (verifyUser(user)) checkBalance(user);
                    break;

                case 5:
                    if (verifyUser(user)) changePin(user);
                    break;

                case 6:
                    if (verifyUser(user)) showStatement(user.accountNumber);
                    break;

                case 7:
                    logout = true;
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // ================= DEPOSIT =================
    static void deposit(ATMAccount user) {

        System.out.print("Enter deposit amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        user.balance += amount;
        saveTransaction(new Transaction(
                user.accountNumber, "Deposit", amount, "Cash deposit"));

        saveAccounts();
        System.out.println("Amount deposited successfully!");
    }

    // ================= WITHDRAW =================
    static void withdraw(ATMAccount user) {

        System.out.print("Enter withdrawal amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= 0 || amount > user.balance) {
            System.out.println("Insufficient balance!");
            return;
        }

        user.balance -= amount;
        saveTransaction(new Transaction(
                user.accountNumber, "Withdraw", amount, "Cash withdrawal"));

        saveAccounts();
        System.out.println("Please collect your cash!");
    }

    // ================= TRANSFER =================
    static void transfer(ATMAccount user) {

        System.out.print("Enter receiver account number: ");
        String receiverAcc = sc.nextLine();

        ATMAccount receiver = accounts.get(receiverAcc);

        if (receiver == null) {
            System.out.println("Receiver account not found!");
            return;
        }

        System.out.print("Enter transfer amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= 0 || amount > user.balance) {
            System.out.println("Invalid or insufficient balance!");
            return;
        }

        user.balance -= amount;
        receiver.balance += amount;

        saveTransaction(new Transaction(
                user.accountNumber, "Transfer",
                amount, "Transferred to " + receiverAcc));

        saveAccounts();
        System.out.println("Amount transferred successfully!");
    }

    // ================= CHECK BALANCE =================
    static void checkBalance(ATMAccount user) {
        System.out.println("Available Balance: Rs. " + user.balance);
    }

    // ================= CHANGE PIN =================
    static void changePin(ATMAccount user) {

        System.out.print("Enter new PIN: ");
        user.pin = sc.nextLine();

        saveAccounts();
        System.out.println("PIN updated successfully!");
    }

    // ================= TRANSACTION STATEMENT =================
    static void showStatement(String accNo) {

        System.out.println("\n------ TRANSACTION STATEMENT ------");

        try (BufferedReader br = new BufferedReader(
                new FileReader(TRANSACTION_FILE))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(accNo + "|")) {
                    System.out.println(line.replace("|", "   "));
                }
            }

        } catch (IOException e) {
            System.out.println("No transaction history found.");
        }
    }

    // ================= FILE HANDLING =================
    static void loadAccounts() {

        try (BufferedReader br = new BufferedReader(
                new FileReader(ACCOUNT_FILE))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                accounts.put(data[0],
                        new ATMAccount(data[0], data[1],
                                data[2], Double.parseDouble(data[3])));
            }

        } catch (IOException ignored) {
        }
    }

    static void saveAccounts() {

        try (PrintWriter pw = new PrintWriter(
                new FileWriter(ACCOUNT_FILE))) {

            for (ATMAccount acc : accounts.values()) {
                pw.println(acc.accountNumber + "," +
                           acc.pin + "," +
                           acc.mobileNumber + "," +
                           acc.balance);
            }

        } catch (IOException ignored) {
        }
    }

    static void saveTransaction(Transaction t) {

        try (PrintWriter pw = new PrintWriter(
                new FileWriter(TRANSACTION_FILE, true))) {

            pw.println(t.accountNumber + "|" +
                       t.date + "|" +
                       t.transactionType + "|" +
                       t.amount + "|" +
                       t.description);

        } catch (IOException ignored) {
        }
    }
}
