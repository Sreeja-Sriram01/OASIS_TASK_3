import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private double balance;
    private ArrayList<String> transactionHistory;
    private String accountId;
    
    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }
     public String getAccountId() {
        return accountId;
    }

    public void deposit(double amount) {

        if (amount > 0) {
            balance = balance+amount;
            transactionHistory.add("Deposited amount: " + amount);
            System.out.println("Successfully deposited amount: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdraw: " + amount);
            System.out.println("withdrawl  Successfull :" + amount +"Please Collect the Cash" );
        } else {
            System.out.println(" Insufficient balance.");
        }
    }

    public void transfer(double amount, Account recipient) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transferred amount of " + amount + " to the " + recipient.getAccountId());
            System.out.println("Successfully transferred amount of " + amount + " to " + recipient.getAccountId());
        } else {
            System.out.println("Insufficient balance....");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }
    public void exit() {
        System.out.println("THANK YOU!!!!!");
    }

    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions done yet.");
        } else {
            System.out.println("Transactions History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

 class ATM {
        private String userId;
        private String userPin;
        private Account account;
    
        public ATM(String userId, String userPin, String accountId) {
            this.userId = userId;
            this.userPin = userPin;
            this.account = new Account(accountId);
        }
    
        public boolean authenticate(String enteredId, String enteredPin) {
            return this.userId.equals(enteredId) && this.userPin.equals(enteredPin);
        }
    
        public Account getAccount() {
            return account;
        }
    }

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ATM atm1 = new ATM("Sreeja", "Sree123","acc1");
        Account recipientAccount = new Account("Pranay Account");

        System.out.println("********************Welcome to the ATM Interface***********************************");
        System.out.print("Enter USER ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        System.out.println("Login Successfull !!!!");

        if (!atm1.authenticate(userId, pin)) {
            System.out.println("Incorrect PIN or User ID. Please check the PIN...");
            scanner.close();
            return;
        }
Account userAccount=atm1.getAccount();
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4.Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                        userAccount.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    userAccount.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdrawl: ");
                    double withdrawAmount = scanner.nextDouble();
                    userAccount.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    userAccount.transfer(transferAmount, recipientAccount);
                    break ;  
                case 5:
                    userAccount.viewTransactionHistory();
                    break;
                case 6:
                    userAccount.exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}


