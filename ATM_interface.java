import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class User {
    private int userId;
    private int userPin;
    public User(int userId, int userPin) {
        this.userId = userId;
        this.userPin = userPin;
    }
    public int getUserId() {
        return userId;
    }
    public int getUserPin() {
        return userPin;
    }
}
class Transaction {
    private double amount;
    private TransactionType type;
    private LocalDateTime date;
    public Transaction(double amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
        this.date = LocalDateTime.now();
    }
    public double getAmount() {
        return amount;
    }
    public TransactionType getType() {
        return type;
    }
    public LocalDateTime getDate() {
        return date;
    }
}
enum TransactionType {
    WITHDRAWAL,
    DEPOSIT,
    TRANSFER
}
class TransactionHistory {
    private List<Transaction> transactions;
    public TransactionHistory() {
        transactions = new ArrayList<>();
    }
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
class ATM {
    private User user;
    private double accountBalance;
    private TransactionHistory transactionHistory;
    public ATM(User user) {
        this.user = user;
        this.accountBalance = 10000.0; // Set an initial balance for demonstration purposes
        this.transactionHistory = new TransactionHistory();
    }
    public double getAccountBalance() {
        return accountBalance;
    }
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter your PIN: ");
        int userPin = scanner.nextInt();
        if (user.getUserId() == userId && user.getUserPin() == userPin) {
            System.out.println("Authentication successful. Welcome!");
            boolean quit = false;
            int choice=0;
            while (!quit) {
                System.out.println("ATM Menu:");
                System.out.println("1. Deposit");
                System.out.println("2. Transfer");
                System.out.println("3. Withdraw");
                System.out.println("4. Transactions History");
                System.out.println("5. Quit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        performDeposit();
                        break;
                    case 2:
                        performTransfer();
                        break;
                    case 3:
                        performWithdrawal();
                        break;
                    case 4:
                        displayTransactionHistory();
                        break;
                    case 5:
                        quit = true;
                        System.out.println("Thank you for using our ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Invalid user ID or PIN. Exiting.");
        }
    }
    private void displayTransactionHistory() {
        List<Transaction> transactions = transactionHistory.getTransactions();
        System.out.println("Transaction History:");
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : transactions) {
                String typeString = "";
                switch (transaction.getType()) {
                    case WITHDRAWAL:
                        typeString = "Withdrawal";
                        break;
                    case DEPOSIT:
                        typeString = "Deposit";
                        break;
                    case TRANSFER:
                        typeString = "Transfer";
                        break;
                }
                System.out.println("Date: " + transaction.getDate() + ", Type: " + typeString + ", Amount: " + transaction.getAmount());
            }
        }
    }
    private void performWithdrawal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else if (amount > accountBalance) {
            System.out.println("Insufficient balance. Your current balance is: " + accountBalance);
        } else {
            accountBalance -= amount;
            Transaction withdrawalTransaction = new Transaction(amount, TransactionType.WITHDRAWAL);
            transactionHistory.addTransaction(withdrawalTransaction);
            System.out.println("Withdrawal successful. Your current balance is: " + accountBalance);
        }
    }
    private void performDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else {
            accountBalance += amount;
            Transaction depositTransaction = new Transaction(amount, TransactionType.DEPOSIT);
            transactionHistory.addTransaction(depositTransaction);
            System.out.println("Deposit successful. Your current balance is: " + accountBalance);
        }
    }
    private void performTransfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the recipient's account number: ");
        int recipientAccountNumber = scanner.nextInt();
        System.out.print("Enter the transfer amount: ");
        double transferAmount = scanner.nextDouble();
        if (transferAmount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else if (transferAmount > accountBalance) {
            System.out.println("Insufficient balance. Your current balance is: " + accountBalance);
        } else {
            accountBalance -= transferAmount;

            double recipientAccountBalance = 1000.0; 
            recipientAccountBalance += transferAmount;
            Transaction transferTransaction = new Transaction(transferAmount, TransactionType.TRANSFER);
            transactionHistory.addTransaction(transferTransaction);
            Transaction recipientTransaction = new Transaction(transferAmount, TransactionType.DEPOSIT);
            transactionHistory.addTransaction(recipientTransaction);
            System.out.println("Transfer successful. Your current balance is: " + accountBalance);
        }

    }

}
class ATM_interface {
    public static void main(String[] args) {
        User user = new User(12345, 6789);
        ATM atm = new ATM(user);
        atm.start();
    }
}