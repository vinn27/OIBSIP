import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private static double balance ;
    private static ArrayList<String> transactionHistory = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("ATM Interface");
            System.out.println("1: Transaction History");
            System.out.println("2: Withdraw");
            System.out.println("3: Deposit");
            System.out.println("4: Transfer");
            System.out.println("5: Quit");
            System.out.print("Enter an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    viewTransactionHistory();
                    break;
                case 2:
                    withdraw(scanner);
                    break;
                case 3:
                    deposit(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

            System.out.println();
        } while (option != 5);

        scanner.close();
    }

    private static void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            transactionHistory.add("Withdrawal: $" + amount);
            System.out.println("Withdrawal successful. New balance: $" + balance);
        }
    }

    private static void deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        balance += amount;
        transactionHistory.add("Deposit: $" + amount);
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    private static void transfer(Scanner scanner) {
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.print("Enter recipient's account number: ");
            int accountNumber = scanner.nextInt();
            balance -= amount;
            transactionHistory.add("Transfer: $" + amount + " to account " + accountNumber);
            System.out.println("Transfer successful. New balance: $" + balance);
        }
    }
}