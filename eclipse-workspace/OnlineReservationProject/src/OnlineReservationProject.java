import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.io.*;


public class OnlineReservationProject implements Serializable {

    private static final long serialVersionUID = 1L;
    static final String FILE_NAME = "reservation_data.ser";

    /* -------- Account Details -------- */
    boolean accountCreated = false;
    String username;
    String password;
    String mobileNo;
    String aadharNo;

    /* -------- Reservation Details -------- */
    boolean isReserved = false;
    String pnr;
    String passengerName;
    int trainNo;
    String trainName;
    String classType;
    LocalDate journeyDate;
    String source;
    String destination;
    int platformNo;
    String arrivalTime;

    static Scanner sc = new Scanner(System.in);
    static OnlineReservationProject data;

    /* -------- MAIN -------- */
    public static void main(String[] args) {

        data = loadData();

        while (true) {
            System.out.println("\n===== ONLINE RESERVATION SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Delete Account");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    data.createAccount();
                    break;
                case 2:
                    data.login();
                    break;
                case 3:
                    data.deleteAccount();
                    break;
                case 4:
                    saveData();
                    System.out.println("Thank you for using the system.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /* -------- CREATE ACCOUNT -------- */
    void createAccount() {

        if (accountCreated) {
            System.out.println("Account already exists.");
            return;
        }

        System.out.print("Enter Username (max 30 chars): ");
        username = sc.nextLine();
        if (username.length() > 30) {
            System.out.println("Username too long.");
            return;
        }

        System.out.print("Enter Password (@ # _ allowed): ");
        password = sc.nextLine();
        if (!password.matches("[a-zA-Z0-9@#_]+")) {
            System.out.println("Invalid password format.");
            return;
        }

        System.out.print("Enter Mobile Number (10 digits): ");
        mobileNo = sc.nextLine();
        if (!mobileNo.matches("\\d{10}")) {
            System.out.println("Invalid mobile number.");
            return;
        }

        System.out.print("Enter Aadhar Number (12 digits): ");
        aadharNo = sc.nextLine();
        if (!aadharNo.matches("\\d{12}")) {
            System.out.println("Invalid Aadhar number.");
            return;
        }

        accountCreated = true;
        saveData();
        System.out.println("Account created successfully.");
    }

    /* -------- LOGIN -------- */
    void login() {

        if (!accountCreated) {
            System.out.println("No account found.");
            return;
        }

        System.out.print("Enter Username: ");
        String u = sc.nextLine();

        System.out.print("Enter Password: ");
        String p = sc.nextLine();

        if (u.equals(username) && p.equals(password)) {
            System.out.println("Login successful.");
            reservationMenu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    /* -------- DELETE ACCOUNT -------- */
    void deleteAccount() {

        if (!accountCreated) {
            System.out.println("No account exists.");
            return;
        }

        System.out.print("Confirm delete account (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            accountCreated = false;
            isReserved = false;
            saveData();
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    /* -------- RESERVATION MENU -------- */
    void reservationMenu() {

        while (true) {
            System.out.println("\n----- RESERVATION MENU -----");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    cancelTicket();
                    break;
                case 3:
                    saveData();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /* -------- BOOK TICKET -------- */
    void bookTicket() {

        if (isReserved) {
            System.out.println("Ticket already booked.");
            return;
        }

        System.out.print("Passenger Name: ");
        passengerName = sc.nextLine();

        System.out.print("Train Number (6 digits): ");
        String t = sc.nextLine();
        if (!t.matches("\\d{6}")) {
            System.out.println("Invalid train number.");
            return;
        }
        trainNo = Integer.parseInt(t);

        System.out.print("Train Name: ");
        trainName = sc.nextLine();

        System.out.print("Class Type: ");
        classType = sc.nextLine();

        System.out.print("Journey Date (YYYY-MM-DD): ");
        try {
            journeyDate = LocalDate.parse(sc.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }

        System.out.print("Source Station: ");
        source = sc.nextLine();

        System.out.print("Destination Station: ");
        destination = sc.nextLine();

        Random r = new Random();
        pnr = "PNR" + (100000 + r.nextInt(900000));
        platformNo = r.nextInt(10) + 1;
        arrivalTime = (10 + r.nextInt(12)) + ":" + String.format("%02d", r.nextInt(60));

        isReserved = true;
        saveData();

        System.out.println("\nTicket booked successfully!");
        System.out.println("PNR: " + pnr);
        System.out.println("Platform No: " + platformNo);
        System.out.println("Arrival Time: " + arrivalTime);
    }

    /* -------- CANCEL TICKET -------- */
    void cancelTicket() {

        if (!isReserved) {
            System.out.println("No reservation found.");
            return;
        }

        System.out.print("Enter PNR: ");
        String inputPNR = sc.nextLine();

        if (!inputPNR.equals(pnr)) {
            System.out.println("Incorrect PNR.");
            return;
        }

        System.out.print("Confirm cancellation (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            isReserved = false;
            saveData();
            System.out.println("Ticket cancelled successfully.");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }

    /* -------- FILE HANDLING -------- */
    static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    static OnlineReservationProject loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (OnlineReservationProject) ois.readObject();
        } catch (Exception e) {
            return new OnlineReservationProject();
        }
    }
}
