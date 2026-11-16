package org.example;
import java.util.Scanner;


public class Main {
    static double Balance = 2500.75;
    static Scanner input = new Scanner(System.in);
    static int transactions=0;
    // Note: The input validation try/catch with NumberFormatException was implemented with help from Claude ai
    public static int readInt() {
        while (true) {
            String line = input.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number:");
            }
        }
    }
    public static double readDouble() {
        while (true) {
            String line = input.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Enter a number:");
            }
        }
    }
    public static void displayMenu() {
        System.out.println("========= ATM =========\n" +
                "1. Check Balance\n" +
                "2. Deposit\n" +
                "3. Withdraw\n" +
                "4. Show Account Status\n" +
                "5. Exit\n" +
                "=======================");

    }
    //
    public static boolean pinCheck(int currentPIN) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.println("Enter your 4-Digit PIN");
            String line = input.nextLine().trim();
            try {
                int pin = Integer.parseInt(line);
                if (pin == currentPIN) {
                    return true;
                }
            } catch (NumberFormatException e) {
                System.out.println("PIN must be numbers only.");
            }
            attempts++;
        }
        return false;
    }
    public static void checkBalance() {
        if (Balance ==0 ) {
            System.out.println("Warning: Your account is empty.");
        }
        System.out.println("Your current balance= " + Balance);
    }
    public static void showAccountStatus() {
        if(Balance>=5000){
            System.out.println("Account Status: VIP Customer");
        }
        else if(Balance<=4999.99&&Balance>=1000){
            System.out.println("Account Status: Regular Customer");
        } else if (Balance <1000) {
            System.out.println("Account Status: Low Balance");
        }
    }
    public static void deposit() {
        System.out.println("Enter amount to deposit");
        double amount = readDouble();
        if(amount<=0){
            System.out.println("Invalid amount.");
        }else{
            Balance += amount;
            transactions++;
            checkBalance();
        }
    }
    public static void withdraw() {
        System.out.println("Enter amount to withdraw");
        double amount = readDouble();
        if (amount <= 0) {
            System.out.println("Transaction cancelled.");
        } else if (amount > Balance) {
            System.out.println("Insufficient balance.");
        } else {
            Balance -= amount;
            transactions++;
            checkBalance();
        }
    }
    public static void main(String[] args) {

        int currentPIN = 1234;
        if (!pinCheck(currentPIN)) {
            System.out.println("Your account has been locked.");
            return;
        }
        int choice;
        do {
            displayMenu();
            choice = readInt();

            switch (choice) {
                case 1: checkBalance(); break;
                case 2: deposit(); break;
                case 3: withdraw(); break;
                case 4: showAccountStatus(); break;
                case 5:
                    System.out.println("Thank you for using our ATM.");
                    System.out.println("Total transactions: " + transactions);
                    break;
                default: System.out.println("Invalid option.");
            }
        } while (choice != 5);
    }
}

