package BankAccount3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Manages bank accounts and provides transaction filtering and sorting operations. */
public class BankAccountManager implements Bank {

  private static final int MAX_ACCOUNTS = 100;

  private final Map<Integer, BankAccount> accounts;
  private int id;

  public BankAccountManager() {
    this.accounts = new HashMap<>();
    this.id = 0;
  }

  /** ADD ACCOUNT. */
  public BankAccount addAccount(BankAccount account) {
    if (accounts.size() >= MAX_ACCOUNTS) {
     throw new IllegalStateException("Cannot add account: maximum of 100 accounts reached.");
    }
    int assignedId = id++;
    accounts.put(assignedId, account);
    System.out.printf("Account added successfully with ID: %d%n", assignedId);
    return account;
  }

  /** GET ACCOUNT. */
  public BankAccount getAccount(int id) {
    return accounts.get(id);
  }

  /** LIST ACCOUNTS. */
  public void listAccounts() {
    if (accounts.isEmpty()) {
      System.out.println("No accounts available.");
      return;
    }

    System.out.println("Bank accounts:");
    for (Map.Entry<Integer, BankAccount> entry : accounts.entrySet()) {
      BankAccount account = entry.getValue();
      String ownerName = account instanceof SavingsAccount
          ? ((SavingsAccount) account).getOwnerName()
          : "Unknown";
      System.out.printf("Account ID: %d, Account Name: %s, Balance: %.2f%n",
          entry.getKey(), ownerName, account.getBalance());
    }
  }

  /** FILTERS. */
  public List<Transaction> filterTransactionsAtOrAbove(double amount, List<Transaction> txList) {
    System.out.println("Filtering transactions at or above " + amount + "...");
    return txList.stream()
        .filter(transaction -> transaction.amount >= amount)
        .collect(Collectors.toList());
  }

  /** SORTS. */
  public List<Transaction> sortTransactionsByAmount(List<Transaction> txList) {
    System.out.println("Sorting transactions by amount...");
    return txList.stream()
        .sorted((first, second) -> Double.compare(first.amount, second.amount))
        .collect(Collectors.toList());
  }

}
