🧾 Online Reservation System (Java)


A console-based Online Reservation System developed using Core Java, designed to simulate real-world railway ticket reservation operations such as account creation, secure login, ticket booking, ticket cancellation, and data persistence.
The application uses Java Serialization for permanent storage of user and reservation data without relying on external databases.

________________________________________

📌 Project Overview


The Online Reservation System is a menu-driven Java application that replicates the core functionality of an online railway reservation platform.
It allows users to create an account, authenticate securely, book train tickets, cancel reservations, and retain booking data even after program termination.
The system focuses on security, validation, modular design, and persistent storage, making it suitable for academic projects, Java practical exams, and learning object serialization.

This project helps in understanding:

•	Object-Oriented Programming (OOP) concepts

•	Java Serialization (Serializable)

•	File handling using object streams

•	Input validation and exception handling

•	Menu-driven console application design

________________________________________

🎯 Project Objectives


•	To design a real-world online reservation workflow using Java

•	To implement secure user authentication and validation

•	To demonstrate persistent data storage using Java Serialization

•	To apply OOP principles such as encapsulation and abstraction

•	To build a scalable console-based reservation system

________________________________________

⚙️ Technologies Used


•	Programming Language: Java

•	Java Version: JDK 8 or higher

•	Development Concepts:

        o	Core Java

        o	Object-Oriented Programming (OOP)

        o	Java Serialization (Serializable)

        o	File Handling (ObjectInputStream, ObjectOutputStream)

        o	Exception Handling

        o	Date & Time API (java.time.LocalDate)

        o	Random Data Generation (java.util.Random)

________________________________________

🧱 System Architecture & Design

🔹 Design Approach

•	Single-object persistence model using serialization

•	Modular method-based structure for clarity

•	Separation of concerns between account management and reservation management

•	File-based permanent storage using .ser file

_______________________________________________________________________________________________________________

🧩 Class Description

1️⃣   OnlineReservationProject Class (Main Class)

Implements Serializable and controls the complete application workflow.

________________________________________

🔐 Account-Related Attributes

•	boolean accountCreated

•	String username

•	String password

•	String mobileNo

•	String aadharNo

________________________________________


🎫 Reservation-Related Attributes

•	boolean isReserved

•	String pnr

•	String passengerName

•	int trainNo

•	String trainName

•	String classType

•	LocalDate journeyDate

•	String source

•	String destination

•	int platformNo

•	String arrivalTime


________________________________________

📌 Responsibilities


•	Account creation and deletion

•	Secure login authentication

•	Ticket booking and cancellation

•	Input validation

•	Data persistence using serialization

•	Menu-driven user interaction

________________________________________

📂 Project Structure


				Reservation

				│

				├── OnlineReservationProject.java              # Main application logic

				├── reservation_data.ser                       # Serialized persistent data file

				└── README.md                                  # Project documentation
________________________________________


✨ Features

🔐 Account Management

•	Create user account with:

	o	Username

	o	Password

	o	Mobile number

	o	Aadhar number

•	Input validation for:

	o	Username length

	o	Password format

	o	Mobile number (10 digits)

	o	Aadhar number (12 digits)

•	Delete account with confirmation

________________________________________

🎫 Ticket Reservation

•	Book railway ticket with:

	o	Passenger name

	o	Train number & name

	o	Class type

	o	Journey date

	o	Source & destination

•	Auto-generated:

	o	PNR number

	o	Platform number

	o	Arrival time

________________________________________

❌ Ticket Cancellation

•	Cancel ticket using PNR verification

•	Confirmation required before cancellation

•	Prevents invalid cancellation attempts

________________________________________

💾 Data Persistence

•	Uses Java Object Serialization

•	Data stored in reservation_data.ser

•	Retains account and reservation data after program exit

•	No database dependency

________________________________________

▶️ How to Run the Project

1️⃣   Prerequisites

•	Java JDK installed (8 or above)

•	Any IDE (Eclipse / IntelliJ / VS Code) or Command Prompt

________________________________________

2️⃣   Compile the Program

javac OnlineReservationProject.java

________________________________________

3️⃣   Run the Program

java OnlineReservationProject

________________________________________

🖥️ Sample Output

	Main Menu

	===== ONLINE RESERVATION SYSTEM =====

	1. Create Account

	2. Login

	3. Delete Account

	4. Exit

________________________________________

Reservation Menu

	----- RESERVATION MENU -----

	1. Book Ticket

	2. Cancel Ticket

	3. Logout

________________________________________

📄 File Details

🔹 reservation_data.ser

•	Serialized binary file

•	Stores complete object state

•	Includes:

	o	Account details

	o	Reservation details

	o	Booking status

________________________________________

⚠️ Error Handling & Validation

•	Prevents duplicate account creation

•	Validates login credentials

•	Ensures correct date format

•	Validates train number length

•	Prevents multiple bookings

•	Handles file I/O exceptions gracefully

________________________________________

🚀 Future Enhancements

•	Multiple user account support

•	Database integration (MySQL)

•	OTP-based login authentication

•	Seat availability management

•	Payment gateway simulation

•	Admin dashboard

•	GUI using JavaFX or Swing

________________________________________

👨‍💻 Author

Tejas Babasaheb Margaj

Java Developer | Student | Software Enthusiast

________________________________________

📜 License

This project is developed strictly for educational purposes and is free to use, modify, and distribute for academic learning.


