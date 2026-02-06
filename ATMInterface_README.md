🏧 ATM Interface System (Java)

A console-based ATM simulation system developed using Core Java, designed to replicate real-world banking operations such as account creation, authentication, deposits, withdrawals, fund transfers, PIN management, and transaction history tracking.
The application uses file-based persistence to ensure data durability without relying on external databases.
________________________________________

📌 Project Overview

The ATM Interface System is a menu-driven Java application that simulates the functional behavior of an Automated Teller Machine (ATM).
It enables users to securely manage bank accounts and perform transactions through a structured console interface.
The system emphasizes security, modularity, and data persistence, making it suitable for academic projects, practical demonstrations, and Java fundamentals learning.

This project strengthens understanding of:

•	Object-Oriented Programming (OOP) concepts

•	File handling and persistent storage

•	Java Collections (HashMap)

•	Secure user authentication mechanisms

•	Menu-driven console application design

________________________________________


🎯 Project Objectives

•	To design a real-world ATM workflow using Java

•	To implement secure user authentication and verification

•	To demonstrate persistent data storage using text files

•	To apply OOP principles such as encapsulation and abstraction

•	To provide a scalable and extensible application structure

________________________________________

⚙️ Technologies Used

•	Programming Language: Java

•	Java Version: JDK 8 or higher

•	Development Concepts:

o	Core Java

o	Object-Oriented Programming (OOP)

o	File Handling (IO)

o	Java Collections Framework

o	Exception Handling

o	Date and Time API (java.time.LocalDate)

________________________________________

🧱 System Architecture & Design

🔹 Design Approach

•	Modular architecture using multiple classes

•	Separation of concerns between account data, transaction data, and user interface

•	In-memory processing using collections with file-based persistence

________________________________________

🧩 Class Description

1️⃣ Transaction Class
Represents a single banking transaction.

Attributes:

•	String accountNumber

•	String transactionType

•	double amount

•	String description

•	LocalDate date


Responsibilities:

•	Store transaction metadata

•	Automatically capture transaction date

•	Facilitate transaction logging into file storage

________________________________________

2️⃣ ATMAccount Class

Represents a bank account.

Attributes:

•	String accountNumber

•	String pin

•	String mobileNumber

•	double balance

Responsibilities:

•	Store user credentials

•	Maintain account balance

•	Support account-level operations

________________________________________

3️⃣ ATMInterface Class (Main Class)

Controls application execution and user interaction.

Responsibilities:

•	Display menus

•	Handle authentication

•	Execute banking operations

•	Manage file I/O

•	Maintain account data using HashMap

________________________________________

📂 Project Structure

ATMtask/
│

├── ATMInterface.java      # Main program logic

├── accounts.txt           # Persistent account storage

├── transactions.txt       # Persistent transaction logs

└── README.md              # Project documentation

________________________________________

✨ Features

🔐 Account Management

•	Create new bank accounts

•	Ensures unique account numbers

•	Default opening balance of ₹5000

•	Secure login using account number and PIN

________________________________________

💰 Banking Operations

•	Deposit: Adds money to the account

•	Withdraw: Deducts money after balance validation

•	Transfer: Moves funds between two valid accounts

•	Balance Enquiry: Displays current available balance

________________________________________

🔄 Security Verification

To perform sensitive operations, the system requires:

•	Re-entry of PIN

•	Verification of registered mobile number

This adds an additional layer of security beyond login.

________________________________________

🧾 Transaction Statement

•	Displays user-specific transaction history

•	Includes:

o	Transaction Date

o	Transaction Type

o	Amount

o	Description

•	Reads data dynamically from transactions.txt

________________________________________

💾 Data Persistence

•	Account data stored in accounts.txt

•	Transaction data stored in transactions.txt

•	Ensures data retention after program termination

•	Eliminates dependency on databases for simplicity

________________________________________

▶️ How to Run the Project

1️⃣ Prerequisites

•	Java JDK installed (8 or above)

•	Any IDE (Eclipse / IntelliJ / VS Code) or Command Prompt

________________________________________

2️⃣ Compile the Program

javac ATMInterface.java

________________________________________

3️⃣ Run the Program

java ATMInterface

________________________________________

🖥️ Sample Output

Main Menu

========= ATM SYSTEM =========

1. Create New Account

2. Login

3. Exit

ATM Menu

-------- ATM MENU --------

1. Deposit

2. Withdraw

3. Transfer

4. Check Balance

5. Change PIN

6. View Statement

7. Logout

________________________________________

📄 File Details

🔹 accounts.txt

CSV format used to store account information:

AccountNumber,PIN,MobileNumber,Balance

________________________________________

🔹 transactions.txt

Pipe-separated format for transaction logs:

AccountNumber|Date|TransactionType|Amount|Description

________________________________________

⚠️ Error Handling & Validation

•	Prevents invalid login attempts

•	Validates transaction amounts

•	Ensures sufficient balance before withdrawals

•	Handles missing files gracefully

•	Prevents duplicate account creation

________________________________________

🚀 Future Enhancements

•	PIN encryption & masking

•	Daily withdrawal limits

•	OTP-based authentication

•	Database integration (MySQL)

•	Admin dashboard

•	GUI using JavaFX or Swing

•	Interest calculation module

________________________________________

👨‍💻 Author

Tejas Babasaheb Margaj

Java Developer | Student | Software Enthusiast

________________________________________

📜 License

This project is developed strictly for educational purposes and is free to use, modify, and distribute for learning and academic submissions.




