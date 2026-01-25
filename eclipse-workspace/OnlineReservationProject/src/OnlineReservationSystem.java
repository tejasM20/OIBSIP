import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Random;

public class OnlineReservationSystem {

    // -------- ACCOUNT DATA --------
    static boolean accountCreated = false;
    static String username;
    static String password;
    static String mobileNo;
    static String aadharNo;

    // -------- RESERVATION DATA --------
    static boolean isReserved = false;
    static String pnr;
    static String name;
    static int trainNo;
    static String trainName;
    static String classType;
    static LocalDate journeyDate;
    static String source;
    static String destination;
    static int platformNo;
    static String arrivalTime;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== ONLINE RESERVATION SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Delete Account");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> login();
                case 3 -> deleteAccount();
                case 4 -> {
                    System.out.println("Thank you for using the system.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // -------- CREATE ACCOUNT --------
    static void createAccount() {

        if (accountCreated) {
            System.out.println("Account already exists. Please login.");
            return;
        }

        System.out.print("Enter username (max 30 characters): ");
        username = sc.nextLine();
        if (username.length() > 30) {
            System.out.println("Username exceeds character limit.");
            return;
        }

        System.out.print("Enter password (allowed: letters, numbers, @ # _ ): ");
        password = sc.nextLine();
        if (!password.matches("[a-zA-Z0-9@#_]+")) {
            System.out.println("Invalid password format.");
            return;
        }

        System.out.print("Enter mobile number (10 digits): ");
        mobileNo = sc.nextLine();
        if (!mobileNo.matches("\\d{10}")) {
            System.out.println("Invalid mobile number.");
            return;
        }

        System.out.print("Enter Aadhar number (12 digits): ");
        aadharNo = sc.nextLine();
        if (!aadharNo.matches("\\d{12}")) {
            System.out.println("Invalid Aadhar number.");
            return;
        }

        accountCreated = true;
        System.out.println("Account created successfully.");
    }

    // -------- LOGIN --------
    static void login() {

        if (!accountCreated) {
            System.out.println("No account found. Please create one.");
            return;
        }

        System.out.print("Enter username: ");
        String u = sc.nextLine();

        System.out.print("Enter password: ");
        String p = sc.nextLine();

        if (u.equals(username) && p.equals(password)) {
            System.out.println("Login successful.");
            reservationMenu();
        } else {
            System.out.println("Invalid login credentials.");
        }
    }

    // -------- DELETE ACCOUNT --------
    static void deleteAccount() {

        if (!accountCreated) {
            System.out.println("No account exists.");
            return;
        }

        System.out.print("Are you sure you want to delete your account? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            accountCreated = false;
            isReserved = false;
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account deletion cancelled.");
        }
    }

    // -------- RESERVATION MENU --------
    static void reservationMenu() {

        while (true) {
            System.out.println("\n----- RESERVATION MENU -----");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> bookTicket();
                case 2 -> cancelTicket();
                case 3 -> {
                    System.out.println("Logged out successfully.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // -------- BOOK TICKET --------
    static void bookTicket() {

        if (isReserved) {
            System.out.println("Ticket already booked.");
            return;
        }

        System.out.print("Enter passenger name: ");
        name = sc.nextLine();

        System.out.print("Enter train number (6 digits): ");
        String trainInput = sc.nextLine();
        if (!trainInput.matches("\\d{6}")) {
            System.out.println("Invalid train number.");
            return;
        }
        trainNo = Integer.parseInt(trainInput);

        System.out.print("Enter train name: ");
        trainName = sc.nextLine();

        System.out.print("Enter class type: ");
        classType = sc.nextLine();

        System.out.print("Enter journey date (YYYY-MM-DD): ");
        try {
            journeyDate = LocalDate.parse(sc.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }

        System.out.print("Enter source station: ");
        source = sc.nextLine();

        System.out.print("Enter destination station: ");
        destination = sc.nextLine();

        Random r = new Random();
        pnr = "PNR" + (100000 + r.nextInt(900000));
        platformNo = r.nextInt(10) + 1;
        arrivalTime = (10 + r.nextInt(12)) + ":" + (r.nextInt(60));

        isReserved = true;

        System.out.println("\nTicket booked successfully.");
        System.out.println("PNR Number: " + pnr);
        System.out.println("Platform Number: " + platformNo);
        System.out.println("Train Arrival Time: " + arrivalTime);
    }

    // -------- CANCEL TICKET --------
    static void cancelTicket() {

        if (!isReserved) {
            System.out.println("No active reservation found.");
            return;
        }

        System.out.print("Enter PNR number: ");
        String inputPNR = sc.nextLine();

        if (!inputPNR.equals(pnr)) {
            System.out.println("Incorrect PNR number.");
            return;
        }

        System.out.print("Confirm ticket cancellation? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            isReserved = false;
            System.out.println("Ticket cancelled successfully.");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }
}
