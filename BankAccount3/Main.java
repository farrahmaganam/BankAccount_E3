package BankAccount3;

import java.util.List;
import java.util.Scanner;

/** Runs an interactive console session for bank accounts. */ 
public class Main {
  /** Starts the interactive console session. */
  public static void main(String[] args) {
    BankAccountManager bank = new BankAccountManager();
    BankAccount currentAccount = bank.addAccount(new SavingsAccount("Test1"));

    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("Account owner: Test1");
      while (true) {
        System.out.println("1. Check accounts");
        System.out.println("2. Transact");
        System.out.println("0. Exit");
        System.out.println("Choose an option:");

        String choice = scanner.nextLine().trim();
        switch (choice) {
          case "1":
            currentAccount = manageAccounts(scanner, bank, currentAccount);
            break;
          case "2":
            if (runTransactions(scanner, bank, currentAccount)) {
              return;
            }
            break;
          case "0":
            System.out.println("Exiting...");
            return;
          default:
            System.out.println("Invalid option. Please enter 1, 2, or 0.");
        }
      }
    }
  }

  //ACCOUNTS
  private static BankAccount manageAccounts(Scanner scanner, BankAccountManager bank,
      BankAccount currentAccount) {
    while (true) {
      System.out.println("----------------");
      System.out.println("1. List accounts");
      System.out.println("2. Add account");
      System.out.println("0. Back");
      System.out.println("Choose an option:");

      String choice = scanner.nextLine().trim();
      switch (choice) {
        case "1":
          bank.listAccounts();
          break;
        case "2":
          System.out.println("Enter account owner's name:");
          String ownerName = scanner.nextLine().trim();
          try {
            currentAccount = bank.addAccount(new SavingsAccount(ownerName));
            System.out.println("Account added successfully.");
          } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
          }
          break;
        case "0":
          return currentAccount;
        default:
          System.out.println("Invalid command. Please try again.");
      }
    }
  }

  //TRANSACTIONS
  private static boolean runTransactions(Scanner scanner, BankAccountManager bank,
      BankAccount currentAccount) {
    while (true) {
      System.out.println("----------------");
      System.out.println("1. Deposit");
      System.out.println("2. Withdraw");
      System.out.println("3. Balance");
      System.out.println("4. History");
      System.out.println("5. Freeze");
      System.out.println("6. Unfreeze");
      System.out.println("7. Filter transactions at or above an amount");
      System.out.println("8. Sort transactions by amount");
      System.out.println("9. Back");
      System.out.println("0. Exit");
      System.out.println("Choose an option:");
      String command = scanner.nextLine();

      try {
        switch (command) {
          case "1":
            System.out.println("Enter amount to deposit:");
            double depositAmount = scanner.nextDouble();
            scanner.nextLine();
            currentAccount.deposit(depositAmount);
            break;
          case "2":
            System.out.println("Enter amount to withdraw:");
            double withdrawAmount = scanner.nextDouble();
            scanner.nextLine();
            currentAccount.withdraw(withdrawAmount);
            break;
          case "3":
            System.out.printf("Current balance: %.2f%n", currentAccount.getBalance());
            break;
          case "4":
            currentAccount.printTransactionHistory();
            break;
          case "5":
            currentAccount.freezeAccount();
            break;
          case "6":
            currentAccount.unfreezeAccount();
            break;
          case "7":
            System.out.println("Enter minimum transaction amount:");
            double minimumAmount = scanner.nextDouble();
            scanner.nextLine();
            List<Transaction> filteredTransactions = bank.filterTransactionsAtOrAbove(
                minimumAmount, currentAccount.getTransactionHistory());
            filteredTransactions.forEach(System.out::println);
            break;
          case "8":
            List<Transaction> sortedTransactions = bank.sortTransactionsByAmount(
                currentAccount.getTransactionHistory());
            sortedTransactions.forEach(System.out::println);
            break;
          case "9":
            return false;
          case "0":
            System.out.println("Exiting...");
            return true;
          default:
            System.out.println("Invalid command. Please try again.");
        }
      } catch (AccountFrozenException
            | InsufficientFundsException
            | InvalidAmountException exception) {
        System.out.println(exception.getMessage());
      }
    }
  }
}
