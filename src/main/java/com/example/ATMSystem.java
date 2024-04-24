package com.example;

import com.example.data.Account;
import com.example.functions.Admin;
import com.example.functions.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This class represents the main ATM system that provides functionality for both customers and administrators.
 */
public class ATMSystem {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ATM";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "zx19980611";

    /**
     * The main method that serves as the entry point of the ATM system.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create instances of Customer and Admin classes
            Customer customer = new Customer(connection);
            Admin admin = new Admin(connection);

            // Create Scanner object to read user input
            Scanner scanner = new Scanner(System.in);

            // Main menu loop
            while (true) {
                // Display main menu options
                System.out.println("1 - Customer Login");
                System.out.println("2 - Administrator Login");
                System.out.println("3 - Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                // Handle user choice
                switch (choice) {
                    case 1:
                        customer.customerLogin(scanner); // Customer login
                        break;
                    case 2:
                        admin.administratorLogin(scanner); // Administrator login
                        break;
                    case 3:
                        System.out.println("Exiting..."); // Exit the program
                        connection.close(); // Close database connection
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again."); // Invalid choice
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print SQL exceptions
        }
    }
}
